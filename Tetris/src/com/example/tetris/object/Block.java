package com.example.tetris.object;

import com.example.tetris.object.GameConfig.BlockType;

/**
 * Description:俄罗斯方块中的方块对象
 * 
 * @author Administrator
 */
public class Block {
	public static final int BLOCK_HEIGHT = 4;
	public static final int BLOCK_WIDTH = 4;

	private int indexY; /* 该对象在Block[y][x]中索引 y */
	private int indexX; /* 该对象在Block[y][x]中索引 x */
	private int blockType;/* 俄罗斯方块类型 */
	private int blockNumber; /* 方块的编码,范围为0~BLOCK_X_NUM-1,用来判断是blockType类型下具体哪个方块 */
	private char[] blockData = new char[BLOCK_WIDTH * BLOCK_HEIGHT + 1];/* 保存方块数据 */

	// 设置该Block对象在棋盘数组中的位置
	public Block(int indexY, int indexX) {
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

	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
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

	public void setBlockNumber(int blockNumber) {
		this.blockNumber = blockNumber;
	}

	public int getBlockNumber() {
		return this.blockNumber;
	}

	public void setBlockData(char[] blockData) {
		this.blockData = blockData;
	}

	public char[] getBlockData() {
		return this.blockData;
	}

	static public int getBlockHeight() {
		return BLOCK_HEIGHT;
	}

	static public int getBlockWidth() {
		return BLOCK_WIDTH;
	}

}
