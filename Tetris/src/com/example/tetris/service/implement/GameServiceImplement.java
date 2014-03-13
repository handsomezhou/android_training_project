package com.example.tetris.service.implement;

import java.sql.Array;
import java.util.Arrays;

import android.widget.Toast;
import com.example.tetris.object.Block;
import com.example.tetris.object.GameConfig;
import com.example.tetris.object.GameConfig.BlockType;
import com.example.tetris.object.Grid;
import com.example.tetris.service.GameService;

public class GameServiceImplement implements GameService {

	/* 定义一个Grid数组保存游戏区域的方块信息 */
	private Grid[][] board = null;

	/* 游戏配置对象 */
	private GameConfig gameConfig = null;
	/* 当前显示的方块与下一个方块 */
	private Block curBlock = null;
	private Block nextBlock = null;

	public GameServiceImplement(GameConfig gameConfig) {
		// TODO Auto-generated constructor stub
		this.gameConfig = gameConfig;
		this.board = init_board(this.gameConfig);
		this.curBlock = new Block(0, 0);
		this.nextBlock = new Block(0, 0);
		produceCurBlock(1);
		produceNextBlock(getCurBlock());
		produceCurBlock(8);
		
	};

	private Grid[][] init_board(GameConfig gameConfig) {
		Grid[][] board = new Grid[gameConfig.getYSize()][gameConfig.getXSize()];
		for (int y = 0; y < gameConfig.getYSize(); y++) {
			for (int x = 0; x < gameConfig.getXSize(); x++) {
				Grid grid = new Grid(y, x);
				grid.setBlockType(gameConfig.getBlockInitType());
				grid.setValue(gameConfig.getValueZero());
				board[y][x] = grid;
				// System.out.printf("(%d,%d)-[%d]-[%c]\n",board[y][x].getIndexY(),board[y][x].getIndexX(),board[y][x].getBlockType(),board[y][x].getValue());
			}
			// System.out.printf("\n");
		}
		return board;
	}

	public Block produceCurBlock(int i) {
		curBlock.setIndexYX(0, 0);
		curBlock.setBlockType(BlockType.BLOCK_I);
		curBlock.setBlockNumber(1);
		curBlock.setBlockData(gameConfig.getBlocks(i));
		// System.out.printf("produceCurBlock==[%s]",curBlock);
		return this.curBlock;
	}

	public Block produceNextBlock(Block block) {
		this.nextBlock = block;
		
		return this.nextBlock;
	}

	@Override
	public void start() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		Toast.makeText(gameConfig.getContext(), "I'm pauseContinueButton!",
				Toast.LENGTH_SHORT).show();

	}

	@Override
	public void set_level(int level) {
		// TODO Auto-generated method stub
		Toast.makeText(gameConfig.getContext(), "I'm levelIncreasesButton!",
				Toast.LENGTH_SHORT).show();
	}

	@Override
	public Block move_left_block() {
		// TODO Auto-generated method stub
		Toast.makeText(gameConfig.getContext(), "I'm leftButton!",
				Toast.LENGTH_SHORT).show();

		return null;
	}

	@Override
	public Block move_right_block() {
		// TODO Auto-generated method stub
		Toast.makeText(gameConfig.getContext(), "I'm rightButton!",
				Toast.LENGTH_SHORT).show();
		return null;
	}

	@Override
	public Block fast_down_block() {
		// TODO Auto-generated method stub
		Toast.makeText(gameConfig.getContext(), "I'm downButton!",
				Toast.LENGTH_SHORT).show();
		return null;
	}

	@Override
	public Block rotate_block() {
		// TODO Auto-generated method stub

		Toast.makeText(gameConfig.getContext(), "I'm upButton!",
				Toast.LENGTH_SHORT).show();
		return null;
	}

	@Override
	public Grid[][] getGrid() {
		// TODO Auto-generated method stub
		return this.board;
	}

	@Override
	public GameConfig getGameConfig() {
		return this.gameConfig;
	}

	@Override
	public Block getCurBlock() {
		// TODO Auto-generated method stub
		return this.curBlock;
	}

	@Override
	public Block getNextBlock() {
		// TODO Auto-generated method stub
		return this.nextBlock;
	}
}
