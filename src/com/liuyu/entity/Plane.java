package com.liuyu.entity;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import com.liuyu.action.ActionAble;
import com.liuyu.client.GameClient;
import com.liuyu.constant.Constant;
import com.liuyu.uitl.GetImageUtil;

/**
* @ClassName: Plane
* @Description: 创建一个飞机类
* @author YangSong
* @date 2019年8月21日 上午8:16:01
*
*/
public class Plane extends GameObj implements ActionAble{

	//速度
	private int speed;
	// 方向布尔变量
	private boolean left,up,right,down;
	
	// 客户端拿过来
	public GameClient gc;
	
	
	public Plane() {
		
	}
	public Plane(int x,int y,String imgName,GameClient gc) {
		this.x = x;
		this.y = y;
		this.img = GetImageUtil.getImg(imgName);
		this.speed = 15;
		this.gc = gc;
	}
	// 移动的方法
	@Override
	public void move() {
		if(left) {
			x -= speed;
		}
		if(up) {
			y -= speed;
		}
		if(right) {
			x += speed;
		}
		if(down) {
			y += speed;
		}
		outOfBound();
	}
	// 画一个飞机出来
	@Override
	public void draw(Graphics g) {
		
		g.drawImage(img, x, y, null);
		move();
	}
	
	// 处理飞机的边界问题
	public void outOfBound() {
		if(y<=26) {
			y = 26;
		}
		if(x<=0) {
			x = 0;
		}
		if(x>=Constant.GAME_WIDTH-img.getWidth(null)) {
			x = Constant.GAME_WIDTH-img.getWidth(null);
		}
		if(y>=Constant.GAME_HEIGHT-img.getHeight(null)) {
			y = Constant.GAME_HEIGHT-img.getHeight(null);
		}
	}
	// 键盘摁下
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_A:
			left = true;
			break;
		case KeyEvent.VK_W:
			up = true;
			break;
		case KeyEvent.VK_D:
			right = true;
			break;
		case KeyEvent.VK_S:
			down = true;
			break;
	
		default:
			break;
		}
	}
	// 键盘释放
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_A:
			left = false;
			break;
		case KeyEvent.VK_W:
			up = false;
			break;
		case KeyEvent.VK_D:
			right = false;
			break;
		case KeyEvent.VK_S:
			down = false;
			break;
		case KeyEvent.VK_J:
			fire();
			break;
			
		default:
			break;
		}
	}
	// 我方飞机的开火
	public void fire() {
		Bullet b = new Bullet(x+this.img.getWidth(null)/2-20,y,"bullet/02.png",gc);
		gc.bullets.add(b);
	}
	// 获取到当前的矩形
		public Rectangle getRec() {
			return new Rectangle(x, y,this.img.getWidth(null), this.img.getHeight(null));
		}
	
	
}
