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
 * Description 俄罗斯方块
 * 
 * @author ZJQ
 * @Date: 2014-03-7
 * @version 1.0
 */
public class Tetris extends Activity {
	private static final int HANDLE_MSG_BLOCK_DROP = 0x01;
	private static final int HANDLE_MSG_DISPLAY_REFRESH=0x02;
	private static final int BLOCK_TYPE_NUM = 7;// 7种类型的方块
	private static final int TIME_DISPLAY_REFRESH_MSECOND=40;//界面刷新时间毫秒
	private int tetrisHeight;
	private int tetrisWidth;
	private int gameViewHeight;
	private int gameViewWidth;
	private int beginImageY;
	private int beginImageX;
	private int gridImageHeight;
	private int gridImageWidth;
	/* 游戏配置 */
	private GameConfig gameConfig;
	/* 游戏业务逻辑接口 */
	private GameService gameService;
	// /* 方块下落定时器 */
	private Timer blockDropTimer = new Timer();
	//俄罗斯方块显示刷新定时器
	private Timer displayRefreshTimer=new Timer(); 
	// 游戏界面
	private GameView gameView;

	// 下一个方块提示区域
	// TextView nextBlock;
	private NextBlockView nextBlock;
	// 得分显示区域
	private TextView gameScore;
	// 等级显示区域
	private TextView gameLevel;

	// 暂停继续按钮
	private Button pauseContinueButton;
	// 上下左右按钮
	private Button upButton;
	private Button leftButton;
	private Button downButton;
	private Button rightButton;
	// 退出按钮
	private Button backButton;

	// 俄罗斯方块小格子图片
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
					gameLevel
							.setText(String.valueOf(gameConfig.getGameLevel()));
					gameScore
							.setText(String.valueOf(gameConfig.getGameScore()));
					gameService.move_down_block();
					if (gameConfig.getIsLevelUp()) {
						continueGame();
						gameConfig.setIsLevelUp(false);
					}
					gameView.invalidate();
					nextBlock.invalidate();
					//System.out.printf("________HANDLE_MSG_BLOCK_DROP_____________________%s\n", new Exception().getStackTrace()[0].getMethodName() );
					break;
				case STATUS_OVER:
					overGame();
					break;
				default:
					break;
				}
				break;
			case HANDLE_MSG_DISPLAY_REFRESH:
				gameView.invalidate();
				nextBlock.invalidate();
				//System.out.printf("__________HANDLE_MSG_DISPLAY_REFRESH___________________%s\n", new Exception().getStackTrace()[0].getMethodName() );
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
		gameViewHeight = resources.getInteger(R.integer.game_view_height);
		gameViewWidth = resources.getInteger(R.integer.game_view_width);
		beginImageY = resources.getInteger(R.integer.begin_image_y);
		beginImageX = resources.getInteger(R.integer.begin_image_x);
		gridImageHeight = resources.getInteger(R.integer.grid_image_height);
		gridImageWidth = resources.getInteger(R.integer.grid_image_width);

		initGame();
		startGame();
		
		System.out.printf("_____________________________%s\n", new Exception().getStackTrace()[0].getMethodName() );
	}

	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		System.out.printf("_____________________________%s\n", new Exception().getStackTrace()[0].getMethodName() );

	}


	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		System.out.printf("_____________________________%s\n", new Exception().getStackTrace()[0].getMethodName() );
		stopTimer(blockDropTimer);
		stopTimer(displayRefreshTimer);

	}


	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
		System.out.printf("_____________________________%s\n", new Exception().getStackTrace()[0].getMethodName() );

	}


	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		System.out.printf("_____________________________%s\n", new Exception().getStackTrace()[0].getMethodName() );
		blockDropTimer=startTimer(blockDropTimer, HANDLE_MSG_BLOCK_DROP, gameService.getGameConfig().getMsecond());
		displayRefreshTimer=startTimer(displayRefreshTimer, HANDLE_MSG_DISPLAY_REFRESH, TIME_DISPLAY_REFRESH_MSECOND);

	}


	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		System.out.printf("_____________________________%s\n", new Exception().getStackTrace()[0].getMethodName() );

	}


	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		System.out.printf("_____________________________%s\n", new Exception().getStackTrace()[0].getMethodName() );

	}


	/*
	 * 初始化俄罗斯方块小方块颜色
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

	/* 初始化游戏 */
	public void initGame() {
		init_grid_color();
		gameConfig = new GameConfig(this.tetrisHeight, this.tetrisWidth,
				this.gameViewHeight, this.gameViewWidth, this.beginImageY,
				this.beginImageX, this.gridImageHeight, this.gridImageWidth,
				this);
		System.out.printf("y=%d, x=%d+++++++++++\n", gameConfig.getYSize(),
				gameConfig.getXSize());
		gameService = new GameServiceImplement(gameConfig);
		gameView = (GameView) findViewById(R.id.game_view);
		// gameView.setGridColor(this.grid_color);
		gameView.setGameService(gameService, this.grid_color);

		// 根据GameView区域动态设置小方块大小
		int w = View.MeasureSpec.makeMeasureSpec(0,
				View.MeasureSpec.UNSPECIFIED);
		int h = View.MeasureSpec.makeMeasureSpec(0,
				View.MeasureSpec.UNSPECIFIED);
		gameView.measure(w, h);
		gameService.getGameConfig().setGridImageHeight(gridImageHeight);
		gameService.getGameConfig().setGridImageWidth(gridImageWidth);

		nextBlock = (NextBlockView) findViewById(R.id.next_block);
		nextBlock.setGameService(gameService);

		gameScore = (TextView) findViewById(R.id.game_score);
		gameScore.setText(getString(R.string.score_prompt)
				+ String.valueOf(gameConfig.getGameScore()));
		gameLevel = (TextView) findViewById(R.id.game_level);
		gameLevel.setText(getString(R.string.level_prompt)
				+ String.valueOf(gameConfig.getGameLevel()));
		pauseContinueButton = (Button) findViewById(R.id.pause_continue_button);
		upButton = (Button) findViewById(R.id.up_button);
		leftButton = (Button) findViewById(R.id.left_button);
		downButton = (Button) findViewById(R.id.down_button);
		rightButton = (Button) findViewById(R.id.right_button);
		backButton = (Button) findViewById(R.id.back_button);
		// gameConfig.setGameLevel(gameConfig.getGameLevel()+1);

		// 为游戏区域的触碰事件绑定监听器

		gameView.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				switch (gameConfig.getGameStatus()) {
				case STATUS_PLAYING:
					gameView.onTouchEvent(event);
					break;
				default:
					break;
				}

				return true;
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

		// 为上下左右按钮的单击事件绑定监听器
		upButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				switch (gameConfig.getGameStatus()) {
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
				switch (gameConfig.getGameStatus()) {
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
				// gameService.fast_down_block();
				switch (gameConfig.getGameStatus()) {
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
				switch (gameConfig.getGameStatus()) {
				case STATUS_PLAYING:
					gameService.move_right_block();
					gameView.invalidate();
				default:
					break;
				}
			}
		});

		// 为退出按钮的单击事件绑定监听器
		backButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();
			}
		});

	}

	/* 开始游戏 */
	public void startGame() {
//		stopTimer(blockDropTimer);
//		stopTimer(displayRefreshTimer);
		gameService.start();
		blockDropTimer=startTimer(blockDropTimer, HANDLE_MSG_BLOCK_DROP, gameService.getGameConfig().getMsecond());
		displayRefreshTimer=startTimer(displayRefreshTimer, HANDLE_MSG_DISPLAY_REFRESH, TIME_DISPLAY_REFRESH_MSECOND);

	}

	/* 暂停游戏 */
	public void pauseGame() {
		stopTimer(blockDropTimer);
		stopTimer(displayRefreshTimer);
		gameService.pause();
	}

	/* 继续游戏 */
	public void continueGame() {
//		stopTimer(blockDropTimer);
//		stopTimer(displayRefreshTimer);
		gameService.resume_playing();
		blockDropTimer=startTimer(blockDropTimer, HANDLE_MSG_BLOCK_DROP, gameService.getGameConfig().getMsecond());
		displayRefreshTimer=startTimer(displayRefreshTimer, HANDLE_MSG_DISPLAY_REFRESH, TIME_DISPLAY_REFRESH_MSECOND);
	}

	/* 结束游戏 */
	public void overGame() {
		stopTimer(blockDropTimer);
		stopTimer(displayRefreshTimer);
		// gameService.over();
		pauseContinueButton.setBackgroundResource(R.drawable.continue_normal);
		pauseContinueButton.invalidate();
		gameView.invalidate();
	}

	private void stopTimer(Timer timer) {
		if (null == timer) {
			return;
		}
		timer.cancel();
		timer = null;
	}
	
	private Timer startTimer(Timer timer, final int  handle_msg,long period ){
		stopTimer(timer);
		timer=new Timer();
		timer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				hander.sendEmptyMessage(handle_msg);
			}
		}, 0, period);
	
		return timer;
	}
}
