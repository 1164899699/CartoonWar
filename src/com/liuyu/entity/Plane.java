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
* @Description: ����һ���ɻ���
* @author YangSong
* @date 2019��8��21�� ����8:16:01
*
*/
public class Plane extends GameObj implements ActionAble{

	//�ٶ�
	private int speed;
	// ���򲼶�����
	private boolean left,up,right,down;
	
	// �ͻ����ù���
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
	// �ƶ��ķ���
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
	// ��һ���ɻ�����
	@Override
	public void draw(Graphics g) {
		
		g.drawImage(img, x, y, null);
		move();
	}
	
	// ����ɻ��ı߽�����
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
	// ��������
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
	// �����ͷ�
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
	// �ҷ��ɻ��Ŀ���
	public void fire() {
		Bullet b = new Bullet(x+this.img.getWidth(null)/2-20,y,"bullet/02.png",gc);
		gc.bullets.add(b);
	}
	// ��ȡ����ǰ�ľ���
		public Rectangle getRec() {
			return new Rectangle(x, y,this.img.getWidth(null), this.img.getHeight(null));
		}
	
	
}
