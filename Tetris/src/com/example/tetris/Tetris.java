package com.example.tetris;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.TextView;

import com.example.tetris.object.GameConfig;
import com.example.tetris.service.GameService;
import com.example.tetris.service.implement.GameServiceImplement;
import com.example.tetris.view.GameView;
import com.example.tetris.view.NextBlockView;

/**
 * Description ����˹����
 * 
 * @author ZJQ
 * @Date: 2014-03-7
 * @version 1.0
 */
public class Tetris extends Activity {
	private static final int HANDLE_MSG_BLOCK_DROP = 0x01;
	private static final int BLOCK_TYPE_NUM = 7;// 7�����͵ķ���
	private int tetrisHeight;
	private int tetrisWidth;
	private int beginImageY;
	private int beginImageX;
	private int imageHeight;
	private int imageWidth;
	/* ��Ϸ���� */
	private GameConfig gameConfig;
	/* ��Ϸҵ���߼��ӿ� */
	private GameService gameService;
	// /* �������䶨ʱ�� */
	private Timer blockDropTimer = new Timer();
	// ��Ϸ����
	private GameView gameView;

	// ��һ��������ʾ����
	// TextView nextBlock;
	private NextBlockView nextBlock;
	// �÷���ʾ����
	private TextView gameScore;
	// �ȼ���ʾ����
	private TextView gameLevel;

	// �ȼ��������ð�ť
	private Button levelIncreasesButton;
	// ��ͣ������ť
	private Button pauseContinueButton;
	// �������Ұ�ť
	private Button upButton;
	private Button leftButton;
	private Button downButton;
	private Button rightButton;
	// �˳���ť
	private Button backButton;

	// ����˹����С����ͼƬ
	private Bitmap[] grid_color;

	private Handler hander = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			case HANDLE_MSG_BLOCK_DROP:
				switch (gameConfig.getGameStatus()) {
				case STATUS_PAUSE:
					break;
				case STATUS_PLAYING:
					gameLevel.setText(String.valueOf(gameConfig.getGameLevel()));
					gameScore.setText(String.valueOf(gameConfig.getGameScore()));
					gameService.move_down_block();
					if(gameConfig.getIsLevelUp()){
						continueGame();
						gameConfig.setIsLevelUp(false);
					}
					gameView.invalidate();
					nextBlock.invalidate();
					break;
				case STATUS_OVER:
					overGame();
					break;
				default:
					break;
				}
				break;
			default:
				break;
			}
		}

	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tetris);
		Resources resources = getResources();
		tetrisHeight = resources.getInteger(R.integer.tetris_hight);
		tetrisWidth = resources.getInteger(R.integer.tetris_widht);
		beginImageY = resources.getInteger(R.integer.begin_image_y);
		beginImageX = resources.getInteger(R.integer.begin_image_x);
		imageHeight = resources.getInteger(R.integer.image_height);
		imageWidth = resources.getInteger(R.integer.image_width);

		initGame();
		startGame();
	}

	/*
	 * ��ʼ������˹����С������ɫ
	 */
	public void init_grid_color() {
		this.grid_color = new Bitmap[BLOCK_TYPE_NUM];
		this.grid_color[0] = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.block_blue);
		this.grid_color[1] = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.block_cyan);
		this.grid_color[2] = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.block_green);
		this.grid_color[3] = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.block_magenta);
		this.grid_color[4] = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.block_purple);
		this.grid_color[5] = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.block_red);
		this.grid_color[6] = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.block_yellow);
	}

	/* ��ʼ����Ϸ */
	public void initGame() {
		init_grid_color();
		gameConfig = new GameConfig(this.tetrisHeight, this.tetrisWidth,
				this.beginImageY, this.beginImageX, this.imageHeight,
				this.imageWidth, this);
		System.out.printf("y=%d, x=%d+++++++++++\n", gameConfig.getYSize(),
				gameConfig.getXSize());
		gameService = new GameServiceImplement(gameConfig);
		gameView = (GameView) findViewById(R.id.game_view);
		gameView.setGameService(gameService);
		gameView.setGridColor(this.grid_color);

		nextBlock = (NextBlockView) findViewById(R.id.next_block);
		nextBlock.setGameService(gameService);
		nextBlock.setGridColor(this.grid_color);

		gameScore = (TextView) findViewById(R.id.game_score);
		gameScore.setText(getString(R.string.score_prompt)
				+ String.valueOf(gameConfig.getGameScore()));
		gameLevel = (TextView) findViewById(R.id.game_level);
		gameLevel.setText(getString(R.string.level_prompt)
				+ String.valueOf(gameConfig.getGameLevel()));
		levelIncreasesButton = (Button) findViewById(R.id.level_increases_button);
		pauseContinueButton = (Button) findViewById(R.id.pause_continue_button);
		upButton = (Button) findViewById(R.id.up_button);
		leftButton = (Button) findViewById(R.id.left_button);
		downButton = (Button) findViewById(R.id.down_button);
		rightButton = (Button) findViewById(R.id.right_button);
		backButton = (Button) findViewById(R.id.back_button);
		// gameConfig.setGameLevel(gameConfig.getGameLevel()+1);
		

		// Ϊ��Ϸ����Ĵ����¼��󶨼�����

		gameView.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				switch(gameConfig.getGameStatus()){
				case STATUS_PLAYING:
					gameView.onTouchEvent(event);
					break;
				default:
					break;
				}
			
				return true;
			}
		});

		// Ϊ�ȼ��������ð�ť�ĵ����¼��󶨼�����
		levelIncreasesButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// ����ȡ���������ù���
				gameService.set_level(1);
			}
		});

		pauseContinueButton.setOnTouchListener(new View.OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				switch (gameConfig.getGameStatus()) {
				case STATUS_PAUSE:
					if (MotionEvent.ACTION_DOWN == event.getAction()) {
						pauseContinueButton
								.setBackgroundResource(R.drawable.continue_sel);
						pauseContinueButton.invalidate();
					} else if (MotionEvent.ACTION_UP == event.getAction()) {
						continueGame();
						pauseContinueButton
								.setBackgroundResource(R.drawable.pause_normal);
						pauseContinueButton.invalidate();
					}
					break;
				case STATUS_PLAYING:
					if (MotionEvent.ACTION_DOWN == event.getAction()) {
						pauseContinueButton
								.setBackgroundResource(R.drawable.pause_sel);
						pauseContinueButton.invalidate();
					} else if (MotionEvent.ACTION_UP == event.getAction()) {
						pauseGame();
						pauseContinueButton
								.setBackgroundResource(R.drawable.continue_normal);
						pauseContinueButton.invalidate();
					}
					break;
				case STATUS_OVER:
					if (MotionEvent.ACTION_DOWN == event.getAction()) {
						pauseContinueButton
								.setBackgroundResource(R.drawable.continue_sel);
						pauseContinueButton.invalidate();
					} else if (MotionEvent.ACTION_UP == event.getAction()) {
						startGame();
						pauseContinueButton
								.setBackgroundResource(R.drawable.pause_normal);
						pauseContinueButton.invalidate();
					}
					break;
				default:
					break;
				}
				return true;
			}
		});

		// Ϊ�������Ұ�ť�ĵ����¼��󶨼�����
		upButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				switch(gameConfig.getGameStatus()){
				case STATUS_PLAYING:
					gameService.rotate_block();
					gameView.invalidate();
				default:
					break;
				}
			}
		});

		leftButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				switch(gameConfig.getGameStatus()){
				case STATUS_PLAYING:
					gameService.move_left_block();
					gameView.invalidate();
				default:
					break;
				}
				
			}
		});

		downButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//gameService.fast_down_block();
				switch(gameConfig.getGameStatus()){
				case STATUS_PLAYING:
					gameService.fast_down_block();
					gameView.invalidate();
				default:
					break;
				}
			}
		});

		rightButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				switch(gameConfig.getGameStatus()){
				case STATUS_PLAYING:
					gameService.move_right_block();
					gameView.invalidate();
				default:
					break;
				}
			}
		});

		// Ϊ�˳���ť�ĵ����¼��󶨼�����
		backButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();
			}
		});

	}

	/* ��ʼ��Ϸ */
	public void startGame() {
		stopTimer(blockDropTimer);
		gameService.start();
		this.blockDropTimer = new Timer();
		this.blockDropTimer.schedule(new TimerTask() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				hander.sendEmptyMessage(HANDLE_MSG_BLOCK_DROP);
			}
		}, 0, gameService.getGameConfig().getMsecond());

	}

	/* ��ͣ��Ϸ */
	public void pauseGame() {
		stopTimer(blockDropTimer);
		gameService.pause();
	}

	/* ������Ϸ */
	public void continueGame() {
		stopTimer(blockDropTimer);
		gameService.resume_playing();
		this.blockDropTimer = new Timer();
		this.blockDropTimer.schedule(new TimerTask() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				hander.sendEmptyMessage(HANDLE_MSG_BLOCK_DROP);
			}
		}, 0, gameService.getGameConfig().getMsecond());
	}

	/*������Ϸ*/
	public void overGame(){
		stopTimer(blockDropTimer);
		//gameService.over();
		pauseContinueButton.setBackgroundResource(R.drawable.continue_normal);pauseContinueButton.invalidate();
		gameView.invalidate();
	}
	
	private void stopTimer(Timer timer) {
		if (null == timer) {
			return;
		}
		timer.cancel();
		timer = null;
	}
}
