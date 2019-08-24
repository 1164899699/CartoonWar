package com.liuyu.action;

import java.awt.Graphics;

/**
* @ClassName: ActionAble
* @Description: 行为接口
* @author YangSong
* @date 2019年8月20日 下午7:55:31
*
*/
public interface ActionAble {

	// 移动方法
	void move();
	
	//画方法
	void draw(Graphics g);
	
}
