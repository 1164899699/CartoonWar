package com.liuyu.entity;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import com.liuyu.action.ActionAble;
import com.liuyu.uitl.GetImageUtil;

public class EnemyPlane extends Plane implements ActionAble{

	private Integer enemyType;
	//速度
	private Integer speed;
	
	private static Image[] imgs1 = {
			
		GetImageUtil.getImg("enemy/04.png")	
	};
	
	
	
	
	public EnemyPlane() {
		
	}
	public EnemyPlane(int x,int y,int enemyType) {
		this.x = x;
		this.y = y;
		this.enemyType = enemyType;
		this.speed = 2;
	}
	
	@Override
	public void move() {
		y += speed;
	}
	int count = 0;
	@Override
	public void draw(Graphics g) {
		if(count >0) {
			count = 0;
		}
	    g.drawImage(imgs1[count], x, y, null);
	    move();
	}
	
	
	// 获取到子弹的矩形
		public Rectangle getRec() {
			return new Rectangle(x, y,this.imgs1[0].getWidth(null), this.imgs1[0].getHeight(null));
		}
}
