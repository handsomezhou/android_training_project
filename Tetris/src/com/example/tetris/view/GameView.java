package com.example.tetris.view;

import com.example.tetris.R;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.TextView;
public class GameView extends View implements OnGestureListener ,OnTouchListener{
	GestureDetector mGestureDetector;

	public GameView(Context context, AttributeSet attrs) {
		super(context, attrs);
		 mGestureDetector=new GestureDetector(context,this);
		 
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean onDown(MotionEvent e) {
		// TODO Auto-generated method stub
		//TextView test = new TextView(getContext());
		//st.setText("I'm onDown");
		//System.out.println("I'm onDown");
		//TextView test=(TextView) findViewById(R.id.next_block);
		//.setText("I'm onDown");
		
		System.out.println("---------------------");
		return true;
	}

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onLongPress(MotionEvent e) {
		// TODO Auto-generated method stub
		TextView test=(TextView) findViewById(R.id.next_block);
		test.setText("I'm  onLongPress");
		System.out.println("I'm onDown");
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
}
