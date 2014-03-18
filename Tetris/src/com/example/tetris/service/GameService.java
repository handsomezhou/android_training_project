package com.example.tetris.service;

import com.example.tetris.object.Block;
import com.example.tetris.object.GameConfig;
import com.example.tetris.object.Grid;

public interface GameService {
	// Block [][]board=null;
	/*
	 * ��Ϸ��ʼ
	 */
	public void start();

	/*
	 * ��Ϸ��ͣ
	 */
	public void pause();
	
	/*
	 * ��Ϸ����
	 * */
	public void resume_playing();

	/*
	 * �ȼ�����
	 */
	public void set_level(int level);

	/*
	 * ��������
	 */
	public Block move_left_block();

	/*
	 * ��������
	 */
	public Block move_right_block();
	
	/*��������*/
	public Block move_down_block();

	/*
	 * �����������
	 */
	public Block fast_down_block();

	/*
	 * ���鷭ת
	 */
	public Block rotate_block();

	/*
	 * ��ȡ��������
	 */
	public Grid[][] getGrid();

	/*
	 * ��ȡ���������Ϣ
	 */
	public GameConfig getGameConfig();

	/*
	 * ��ȡ��ǰ����
	 */

	public Block getCurBlock();

	/*
	 * ��ȡ��һ������
	 */
	public Block getNextBlock();

}