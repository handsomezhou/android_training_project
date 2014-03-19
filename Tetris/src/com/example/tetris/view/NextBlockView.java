package com.example.tetris.view;

import com.example.tetris.object.Block;
import com.example.tetris.service.GameService;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.ImageView;

public class NextBlockView extends ImageView {
	// 游戏逻辑的实现类
	private GameService gameService = null;
	// 俄罗斯方块图片
	private Bitmap[] block_color=null;

	public NextBlockView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		if((null==this.gameService)||(null==this.block_color)){
			return;
		}
		Block nextBlock=gameService.getNextBlock();
		if(null!=nextBlock){
			for(int i=0; i<gameService.getGameConfig().getBlockHeight(); i++){
					for(int j=0; j<gameService.getGameConfig().getBlockWidth(); j++){
						if(nextBlock.getBlockData()[i * gameService.getGameConfig().getBlockHeight() + j]==gameService.getGameConfig().getValueOne())
						canvas.drawBitmap(this.block_color[nextBlock.getBlockType()], 
								this.getLeft()+j*36, this.getTop()+i*36, null);
					}
			}
			
		}
	}
	
	public void setGameService(GameService gameService) {
		this.gameService = gameService;
	}
	
	public void setGridColor(Bitmap[] bitbmp)
	{
		this.block_color=bitbmp;
	}
}
