package com.example.tetris.object;
import com.example.tetris.object.GameConfig.BlockType;


/**
 * Description: ����˹�����еķ������
 * 
 * @author ZJQ
 * @Date: 2014-03-7
 * @version 1.0
 */
public class Block {
	public static final int BLOCK_HEIGHT = 4;
	public static final int BLOCK_WIDTH = 4;

	private int indexY; /* �ö�����Block[y][x]������ y */
	private int indexX; /* �ö�����Block[y][x]������ x */
	private BlockType blockType;/* ����˹�������� */
	private int blockNumber; /* ����ı���,��ΧΪ0~BLOCK_X_NUM-1,�����ж���blockType�����¾����ĸ����� */
	private char[] blockData = new char[BLOCK_WIDTH * BLOCK_HEIGHT + 1];/* ���淽������ */

	// ���ø�Block���������������е�λ��
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

	public int getIndexX() {
		return this.indexX;
	}

	public void setBlockType(BlockType blockType) {
		this.blockType = blockType;
	}

	public BlockType getBlockType() {
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