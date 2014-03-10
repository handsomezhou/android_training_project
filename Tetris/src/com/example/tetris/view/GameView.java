package com.example.tetris.view;

import com.example.tetris.R;
import com.example.tetris.board.GameService;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Toast;

public class GameView extends View implements OnGestureListener,
		OnTouchListener {
	GestureDetector mGestureDetector;
	// 游戏逻辑的实现类
	private GameService gameService;

	// 俄罗斯方块图片
	// private Bitmap block_color[];

	public GameView(Context context, AttributeSet attrs) {
		super(context, attrs);
		mGestureDetector = new GestureDetector(context, this);

		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		// TODO Auto-generated method stub
		float distance = e2.getX() - e1.getX();
		if (distance > 0) {
			Toast.makeText(getContext(), "I'm onFling!---right",
					Toast.LENGTH_SHORT).show();
		} else {
			Toast.makeText(getContext(), "I'm onFling!---left",
					Toast.LENGTH_SHORT).show();
		}

		return true;
	}

	@Override
	public boolean onDown(MotionEvent e) {
		// TODO Auto-generated method stub
		// TextView test = new TextView(getContext());
		// st.setText("I'm onDown");
		// System.out.println("I'm onDown");
		// TextView test=(TextView) findViewById(R.id.next_block);
		// .setText("I'm onDown");

		System.out.println("---------------------");
		Toast.makeText(getContext(), "I'm onDown!", Toast.LENGTH_SHORT).show();
		return true;
	}

	@Override
	public void onLongPress(MotionEvent e) {
		// TODO Auto-generated method stub
		Toast.makeText(getContext(), "I'm onLongPress!", Toast.LENGTH_SHORT)
				.show();
	}

	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onShowPress(MotionEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub

		mGestureDetector.onTouchEvent(event);

		return true;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		// super.onDraw(canvas);

		Block[][] block = gameService.getBlocks();

		Bitmap bm = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.block_blue);
		// canvas.drawBitmap(bm, 100, 100, null);
		int y = 0;
		int x = 0;
		if (null != block) {
			// canvas.drawBitmap(bm, 200, 100, null);
			for (int i = 0; i < block.length; i++) {
				for (int j = 0; j < block[i].length; j++) {
					canvas.drawBitmap(bm, 0 + block[i][j].getIndexX() * 40,
							0 + block[i][j].getIndexY() * 40, null);

					// canvas.drawBitmap(bm, block[i][j].getIndexX(),
					// block[i][j].getIndexY(), null);
					// canvas.drawBitmap(bm, x+=40, y+=40, null);
					//
					// canvas.drawBitmap(BitmapFactory.decodeResource(
					// this.getResources(), j),
					// R.drawable.block_blue),0+block[i][j].getIndexX()*40,0+block[i][j].getIndexY()+40
					// , null);
				}

			}
		}
		// System.out.printf("on Draw++++++++++++++++++++++++++++ block=%p",block);
		// canvas.drawBitmap(bm, 300, 100, null);
		System.out.printf("on Draw++++++++++++++++++++++++++++");
	}

	public void setGameService(GameService gameService) {
		this.gameService = gameService;
	}
}
