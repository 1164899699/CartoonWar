package com.liuyu.entity;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.List;

import com.liuyu.action.ActionAble;
import com.liuyu.client.GameClient;
import com.liuyu.constant.Constant;
import com.liuyu.uitl.GetImageUtil;

/**
* @ClassName: Bullet
* @Description: �ӵ���
* @author YangSong
* @date 2019��8��21�� ����11:03:42
*
*/
public class Bullet extends GameObj implements ActionAble{

	//�����ٶ�����
	private Integer speed;
	
	// �õ��ͻ���
	public GameClient gc;
	
	public Bullet() {
		
	}
	public Bullet(int x,int y,String imgName,GameClient gc) {
		this.x = x;
		this.y = y;
		this.img = GetImageUtil.getImg(imgName);
		this.speed = 50;
		this.gc = gc;
	}
	@Override
	public void move() {
		y -= speed;
		
	}
	@Override
	public void draw(Graphics g) {
		g.drawImage(img, x, y, null);
		move();
		outOfBounds();
	}

	// �ӵ�Խ������
	public void outOfBounds() {
		if(y<0) {
			gc.bullets.remove(this);
		}
	}
	// �ӵ���һ������
	public boolean hitMonster(Plane plane) {
		if(this.getRec().intersects(plane.getRec())) {
			Boom boom = new Boom(plane.x, plane.y,gc,true);
			// �Ƴ����еĵ���
			gc.enemys.remove(plane);
			// �Ƴ��ӵ�
			gc.bullets.remove(this);
			// ��ӱ�ը
			gc.booms.add(boom);
			System.out.println("��ը��״̬:"+boom.isLive());
			if(boom.isLive()==false) {
				System.out.println("��ը����");
				gc.booms.remove(boom);
			}
			return true;
			
		}
		return false;
	}
	// �ӵ���������
	public boolean hitMonsters(List<Plane> monsters) {
		for(int i=0;i<monsters.size();i++) {
			if(hitMonster(monsters.get(i))) {
				return true;
			}
		}
		return false;
	}
	
	
	
	// ��ȡ���ӵ��ľ���
	public Rectangle getRec() {
		return new Rectangle(x, y,this.img.getWidth(null), this.img.getHeight(null));
	}
}
