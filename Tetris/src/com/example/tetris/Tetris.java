package com.example.tetris;

import android.app.Activity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tetris.board.GameService;
import com.example.tetris.board.implement.GameServiceImplement;
import com.example.tetris.object.GameConfig;
import com.example.tetris.view.GameView;

public class Tetris extends Activity {
	/* 定义俄罗斯方块游戏界面的高和宽 */
	private static final int TETRIS_HEIGHT = 20;
	private static final int TETRIS_WIDTH = 10;
	/* 游戏配置 */
	GameConfig gameConfig;
	/* 游戏业务逻辑接口 */
	GameService gameService;
	// 游戏界面
	GameView gameView;

	// 下一个方块提示区域
	TextView nextBlock;
	// 得分显示区域
	TextView gameScore;
	// 等级显示区域
	TextView gameLevel;

	// 等级升级设置按钮
	Button levelIncreasesButton;
	// 暂停继续按钮
	Button pauseContinueButton;
	// 上下左右按钮
	Button upButton;
	Button leftButton;
	Button downButton;
	Button rightButton;
	// 退出按钮
	Button backButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tetris);
		init_tetris();
		// LayoutInflater.from(getApplicationContext()).inflate(R.layout.activity_tetris,
		// null);
	}

	/* 初始化游戏 */
	public void init_tetris() {
		gameConfig = new GameConfig(TETRIS_HEIGHT, TETRIS_WIDTH, this);
		gameService = new GameServiceImplement(gameConfig);
		gameView = (GameView) findViewById(R.id.game_view);
		gameView.setGameService(gameService);

		nextBlock = (TextView) findViewById(R.id.next_block);
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
				Toast.makeText(Tetris.this, "I'm levelIncreasesButton!",
						Toast.LENGTH_SHORT).show();

			}
		});

		// 为暂停继续按钮的单击事件绑定监听器
		pauseContinueButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(Tetris.this, "I'm pauseContinueButton!",
						Toast.LENGTH_SHORT).show();

			}
		});

		// 为上下左右按钮的单击事件绑定监听器
		upButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(Tetris.this, "I'm upButton!", Toast.LENGTH_SHORT)
						.show();
			}
		});

		leftButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(Tetris.this, "I'm leftButton!",
						Toast.LENGTH_SHORT).show();

			}
		});

		downButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(Tetris.this, "I'm downButton!",
						Toast.LENGTH_SHORT).show();

			}
		});

		rightButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(Tetris.this, "I'm rightButton!",
						Toast.LENGTH_SHORT).show();

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
