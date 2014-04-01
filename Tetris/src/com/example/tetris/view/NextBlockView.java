package com.example.tetris.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.example.tetris.object.Block;
import com.example.tetris.service.GameService;

public class NextBlockView extends ImageView {
	// 游戏逻辑的实现类
	private GameService gameService = null;

	public NextBlockView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		if ((null == this.gameService)) {
			return;
		}
		Block nextBlock = gameService.getNextBlock();
		if (null != nextBlock) {
			for (int i = 0; i < gameService.getGameConfig().getBlockHeight(); i++) {
				for (int j = 0; j < gameService.getGameConfig().getBlockWidth(); j++) {
					if (nextBlock.getBlockData()[i
							* gameService.getGameConfig().getBlockHeight() + j] == gameService
							.getGameConfig().getValueOne()) {
						canvas.drawBitmap(gameService.getBlockColor()[nextBlock
								.getBlockType()], this.getLeft()
								+ j
								* gameService.getGameConfig()
										.getGridImageWidth()
								* gameService.getGameConfig()
										.getGameViewScaleWidth(), this.getTop()
								+ i
								* gameService.getGameConfig()
										.getGridImageHeight()
								* gameService.getGameConfig()
										.getGameViewScaleHeight(), null);
					}

				}
			}

		}
	}

	public void setGameService(GameService gameService) {
		this.gameService = gameService;
	}
}
