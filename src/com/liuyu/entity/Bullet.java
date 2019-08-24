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
* @Description: 子弹类
* @author YangSong
* @date 2019年8月21日 上午11:03:42
*
*/
public class Bullet extends GameObj implements ActionAble{

	//创建速度属性
	private Integer speed;
	
	// 拿到客户端
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

	// 子弹越界销毁
	public void outOfBounds() {
		if(y<0) {
			gc.bullets.remove(this);
		}
	}
	// 子弹打一个敌人
	public boolean hitMonster(Plane plane) {
		if(this.getRec().intersects(plane.getRec())) {
			Boom boom = new Boom(plane.x, plane.y,gc,true);
			// 移除打中的敌人
			gc.enemys.remove(plane);
			// 移除子弹
			gc.bullets.remove(this);
			// 添加爆炸
			gc.booms.add(boom);
			System.out.println("爆炸的状态:"+boom.isLive());
			if(boom.isLive()==false) {
				System.out.println("爆炸死亡");
				gc.booms.remove(boom);
			}
			return true;
			
		}
		return false;
	}
	// 子弹打多个敌人
	public boolean hitMonsters(List<Plane> monsters) {
		for(int i=0;i<monsters.size();i++) {
			if(hitMonster(monsters.get(i))) {
				return true;
			}
		}
		return false;
	}
	
	
	
	// 获取到子弹的矩形
	public Rectangle getRec() {
		return new Rectangle(x, y,this.img.getWidth(null), this.img.getHeight(null));
	}
}
