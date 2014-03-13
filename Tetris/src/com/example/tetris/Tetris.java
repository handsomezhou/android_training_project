package com.example.tetris;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
	private static final int BLOCK_TYPE_NUM = 7;// 7种类型的方块
	private int tetrisHeight;
	private int tetrisWidth;
	private int beginImageY;
	private int beginImageX;
	private int imageHeight;
	private int imageWidth;
	/* 游戏配置 */
	private GameConfig gameConfig;
	/* 游戏业务逻辑接口 */
	private GameService gameService;
	// 游戏界面
	private GameView gameView;

	// 下一个方块提示区域
	// TextView nextBlock;
	private NextBlockView nextBlock;
	// 得分显示区域
	private TextView gameScore;
	// 等级显示区域
	private TextView gameLevel;

	// 等级升级设置按钮
	private Button levelIncreasesButton;
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

		init_tetris();
		// LayoutInflater.from(getApplicationContext()).inflate(R.layout.activity_tetris,
		// null);
	}

	/*
	 * 初始化俄罗斯方块小方块颜色
	 * */
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
	public void init_tetris() {
		init_grid_color();
		gameConfig = new GameConfig(this.tetrisHeight, this.tetrisWidth,
				this.beginImageY, this.beginImageX, this.imageHeight,
				this.imageWidth, this);
		System.out.printf("y=%d, x=%d+++++++++++", gameConfig.getYSize(),
				gameConfig.getXSize());
		gameService = new GameServiceImplement(gameConfig);
		gameView = (GameView) findViewById(R.id.game_view);
		gameView.setGameService(gameService);
		gameView.setGridColor(this.grid_color);

		nextBlock = (NextBlockView) findViewById(R.id.next_block);
		nextBlock.setGameService(gameService);
		nextBlock.setGridColor(this.grid_color);
		
		gameScore = (TextView) findViewById(R.id.game_score);
		gameLevel = (TextView) findViewById(R.id.game_level);
		levelIncreasesButton = (Button) findViewById(R.id.level_increases_button);
		pauseContinueButton = (Button) findViewById(R.id.pause_continue_button);
		upButton = (Button) findViewById(R.id.up_button);
		leftButton = (Button) findViewById(R.id.left_button);
		downButton = (Button) findViewById(R.id.down_button);
		rightButton = (Button) findViewById(R.id.right_button);
		backButton = (Button) findViewById(R.id.back_button);

		// 为游戏区域的触碰事件绑定监听器

		gameView.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				gameView.onTouchEvent(event);
				return true;
			}
		});

		// 为等级升级设置按钮的单击事件绑定监听器
		levelIncreasesButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// 建议取消级别设置功能
				gameService.set_level(1);
			}
		});

		// 为暂停继续按钮的单击事件绑定监听器
		pauseContinueButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				gameService.pause();
			}
		});

		// 为上下左右按钮的单击事件绑定监听器
		upButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				gameService.rotate_block();
			}
		});

		leftButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				gameService.move_left_block();
			}
		});

		downButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				gameService.fast_down_block();
			}
		});

		rightButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				gameService.move_right_block();

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

}
