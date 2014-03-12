package com.example.tetris.object;

import android.content.Context;

public class GameConfig {

	/* 定义俄罗斯方块7种方块类型 */
	public enum BlockType {
		BLOCK_I, BLOCK_J, BLOCK_L, BLOCK_O, BLOCK_S, BLOCK_Z, BLOCK_T, BLOCK_TYPE
	}

	/* 俄罗斯方块中方块总类型数目 */
	public static final int BLOCK_TYPE_NUM = 7;

	public static final int BLOCK_WIDTH = 4;
	public static final int BLOCK_HEIGHT = 4;
	/* 俄罗斯方块每个方块的具体数目 */
	public static final int BLOCK_I_NUM = 2;
	public static final int BLOCK_J_NUM = 4;
	public static final int BLOCK_L_NUM = 4;
	public static final int BLOCK_O_NUM = 1;
	public static final int BLOCK_S_NUM = 2;
	public static final int BLOCK_Z_NUM = 2;
	public static final int BLOCK_T_NUM = 4;
	public static final int BLOCK_SINGLE_MAX_NUM = 4;

	/* 俄罗斯方块中方块总数目 */
	public static final int BLOCK_TOTAL_NUM = 19; /*
												 * BLOCK_I_NUM+...+BLOCK_T_NUM
												 */
	/* 将俄罗斯方块中方块编号:BLOCK_X_START_NUM~BLOCK_X_NUM-1 代表X类型的方块所属编号 */
	public static final int BLOCK_I_START_NUM = 0;
	public static final int BLOCK_J_START_NUM = (BLOCK_I_START_NUM + BLOCK_I_NUM);
	public static final int BLOCK_L_START_NUM = (BLOCK_J_START_NUM + BLOCK_J_NUM);
	public static final int BLOCK_O_START_NUM = (BLOCK_L_START_NUM + BLOCK_L_NUM);
	public static final int BLOCK_S_START_NUM = (BLOCK_O_START_NUM + BLOCK_O_NUM);
	public static final int BLOCK_Z_START_NUM = (BLOCK_S_START_NUM + BLOCK_S_NUM);
	public static final int BLOCK_T_START_NUM = (BLOCK_Z_START_NUM + BLOCK_Z_NUM);
	/* 俄罗斯方块界面的长和宽by default */
	private static final int TETRIS_HEIGHT = 20;
	private static final int TETRIS_WIDTH = 10;
	private static char[][] block={//[BLOCK_TOTAL_NUM][BLOCK_HEIGHT*BLOCK_WIDTH+1]
		//BLOCK_I
		{'0','1','0','0',
			 '0','1','0','0',
			 '0','1','0','0',
			 '0','1','0','0','\0',},

			{'0','0','0','0',
			 '1','1','1','1',
			 '0','0','0','0',
			 '0','0','0','0','\0',},
	//BLOCK_J
			{'0','0','1','0',
			 '0','0','1','0',
			 '0','1','1','0',
			 '0','0','0','0','\0',},

			{'1','0','0','0',
			 '1','1','1','0',
			 '0','0','0','0',
			 '0','0','0','0','\0',},

			{'0','1','1','0',
			 '0','1','0','0',
			 '0','1','0','0',
			 '0','0','0','0','\0',},
			
			{'0','0','0','0',
			 '1','1','1','0',
			 '0','0','1','0',
			 '0','0','0','0','\0',},
	//BLOCK_L
			{'0','1','0','0',
			 '0','1','0','0',
			 '0','1','1','0',
			 '0','0','0','0','\0',},

			{'0','0','0','0',
			 '1','1','1','0',
			 '1','0','0','0',
			 '0','0','0','0','\0',},

			{'1','1','0','0',
			 '0','1','0','0',
			 '0','1','0','0',
			 '0','0','0','0','\0',},

			{'0','0','1','0',
			 '1','1','1','0',
			 '0','0','0','0',
			 '0','0','0','0','\0',},
	//BLOCK_O
			{'0','0','0','0',
			 '0','1','1','0',
			 '0','1','1','0',
			 '0','0','0','0','\0',},
	//BLOCK_S
			{'0','1','1','0',
			 '1','1','0','0',
			 '0','0','0','0',
			 '0','0','0','0','\0',},

			{'0','1','0','0',
			 '0','1','1','0',
			 '0','0','1','0',
			 '0','0','0','0','\0',},
	//BLOCK_Z
			{'1','1','0','0',
			 '0','1','1','0',
			 '0','0','0','0',
			 '0','0','0','0','\0',},

			{'0','0','1','0',
			 '0','1','1','0',
			 '0','1','0','0',
			 '0','0','0','0','\0',},
	//BLOCK_T
			{'0','1','0','0',
			 '1','1','1','0',
			 '0','0','0','0',
			 '0','0','0','0','\0',},

			{'0','1','0','0',
			 '0','1','1','0',
			 '0','1','0','0',
			 '0','0','0','0','\0',},

			{'0','0','0','0',
			 '1','1','1','0',
			 '0','1','0','0',
			 '0','0','0','0','\0',},

			{'0','1','0','0',
			 '1','1','0','0',
			 '0','1','0','0',
			 '0','0','0','0','\0',},

	};

	// Block[y][x]数组第一二维的长度
	private int xSize;
	private int ySize;
	// Board中第一张图片出现的x座标与y座标
	private int beginImageX;
	private int beginImageY;
	// Block 小方块图片的宽和高
	private int imageWidth;
	private int imageHeight;

	private Context context;

	public GameConfig(int beginImageY, int beginImageX, int imageHeight,
			int imageWidth, Context context) {
		this.ySize = TETRIS_HEIGHT;
		this.xSize = TETRIS_WIDTH;
		this.beginImageY = beginImageY;
		this.beginImageX = beginImageX;
		this.imageHeight = imageHeight;
		this.imageWidth = imageWidth;
		this.context = context;
	}

	/**
	 * 
	 * @param tetrisHeight
	 *            俄罗斯方块的高(行)
	 * @param tetrisWidth
	 *            俄罗斯方块的宽度(列)
	 * @param beginImageY
	 *            最左上角显示的小方块,相对于方块显示区域的Y偏移量
	 * @param beginImageX
	 *            最左上角显示的小方块,相对于方块显示区域的X偏移量
	 * @param imageHeight
	 *            小方块的高
	 * @param imageWidth
	 *            小方块的宽
	 * @param context
	 */
	public GameConfig(int tetrisHeight, int tetrisWidth, int beginImageY,
			int beginImageX, int imageHeight, int imageWidth, Context context) {
		this.ySize = tetrisHeight;
		this.xSize = tetrisWidth;
		this.beginImageY = beginImageY;
		this.beginImageX = beginImageX;
		this.imageHeight = imageHeight;
		this.imageWidth = imageWidth;
		this.context = context;
	}

	public void setXSize(int xSize) {
		this.xSize = xSize;
	}

	public int getXSize() {
		return this.xSize;
	}

	public void setYSize(int ySize) {
		this.ySize = ySize;
	}

	public int getYSize() {
		return this.ySize;
	}

	public void setBeginImageY(int beginImageY) {
		this.beginImageY = beginImageY;
	}

	public int getBeginImageY() {
		return this.beginImageY;
	}

	public void setBeginImageX(int beginImageX) {
		this.beginImageX = beginImageX;
	}

	public int getBeginImageX() {
		return this.beginImageX;
	}

	public void setImageWidth(int imageWidth) {
		this.imageWidth = imageWidth;
	}

	public int getImageWidth() {
		return this.imageWidth;
	}

	public void setImageHeight(int imageHeight) {
		this.imageHeight = imageHeight;
	}

	public int getImageHeight() {
		return this.imageHeight;
	}

	public Context getContext() {
		return context;
	}
}
