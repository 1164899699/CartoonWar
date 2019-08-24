package com.liuyu.entity;

import java.awt.Graphics;

import com.liuyu.action.ActionAble;
import com.liuyu.uitl.GetImageUtil;

/**
* @ClassName: BackGround
* @Description: 背景类
* @author YangSong
* @date 2019年8月21日 下午3:07:42
*
*/
public class BackGround extends GameObj implements ActionAble{

	private Integer speed;

	public BackGround() {
		// TODO Auto-generated constructor stub
	}
	
	public BackGround(int x, int y,String imgName) {
		this.x = x;
		this.y = y;
		this.img = GetImageUtil.getImg(imgName);
		this.speed = 2;
	}
	
	
	@Override
	public void move() {
		y += speed;
		
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(img, x, y, null);
		move();
	}

}
