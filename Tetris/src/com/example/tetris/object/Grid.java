package com.example.tetris.object;

/**
 * Description:俄罗斯方块中的方块对象中小方格对象
 * 
 * @author Administrator
 */
public class Grid {
	private int indexY; /* 该小方格Grid[y][x]中索引 y */
	private int indexX; /* 该小方格Grid[y][x]中索引 x */
	private int blockType; /* 所属方块类型 */
	private char value; /* '1'or '0','1'表示此位置有小方格,'0'表示此位置无小方格 */

	// 设置Block中的小方格在棋盘数组中的位置
	public Grid(int indexY, int indexX) {
		this.indexY = indexY;
		this.indexX = indexX;
	}

	public void setIndexYX(int indexY, int indexX) {
		this.indexY = indexY;
		this.indexX = indexX;
	}

	public int getIndexY() {
		return this.indexY;
	}

	public int getIndexX() {
		return this.indexX;
	}

	public void setBlockType(int blockType) {
		this.blockType = blockType;
	}

	public int getBlockType() {
		return this.blockType;
	}

	public void setValue(char value) {
		this.value = value;
	}

	public char getValue() {
		return this.value;
	}
}
