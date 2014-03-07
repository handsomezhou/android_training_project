package com.example.tetris.object;

public class GameConfig {
	/* �������˹����7�ַ������� */
	public enum BlockType {
		BLOCK_I, BLOCK_J, BLOCK_L, BLOCK_O, BLOCK_S, BLOCK_Z, BLOCK_T, /* BLOCK_TYPE_NUM */
	}

	/* ����˹�����з�����������Ŀ */
	public static final int BLOCK_TYPE_NUM = 7;
	/* ����˹����ÿ�����鳤�Ϳ� */
	//public static final int BLOCK_WIDTH = 4;
	//public static final int BLOCK_HEIGHT = 4;
	/* ����˹����ÿ������ľ�����Ŀ */
	public static final int BLOCK_I_NUM = 2;
	public static final int BLOCK_J_NUM = 4;
	public static final int BLOCK_L_NUM = 4;
	public static final int BLOCK_O_NUM = 1;
	public static final int BLOCK_S_NUM = 2;
	public static final int BLOCK_Z_NUM = 2;
	public static final int BLOCK_T_NUM = 4;
	public static final int BLOCK_SINGLE_MAX_NUM = 4;

	/* ����˹�����з�������Ŀ */
	public static final int BLOCK_TOTAL_NUM = 19; /*
													 * BLOCK_I_NUM+...+BLOCK_T_NUM
													 */
	/* ������˹�����з�����:BLOCK_X_START_NUM~BLOCK_X_NUM-1 ����X���͵ķ���������� */
	public static final int BLOCK_I_START_NUM = 0;
	public static final int BLOCK_J_START_NUM = (BLOCK_I_START_NUM + BLOCK_I_NUM);
	public static final int BLOCK_L_START_NUM = (BLOCK_J_START_NUM + BLOCK_J_NUM);
	public static final int BLOCK_O_START_NUM = (BLOCK_L_START_NUM + BLOCK_L_NUM);
	public static final int BLOCK_S_START_NUM = (BLOCK_O_START_NUM + BLOCK_O_NUM);
	public static final int BLOCK_Z_START_NUM = (BLOCK_S_START_NUM + BLOCK_S_NUM);
	public static final int BLOCK_T_START_NUM = (BLOCK_Z_START_NUM + BLOCK_Z_NUM);
}
