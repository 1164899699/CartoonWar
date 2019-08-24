package com.liuyu.entity;

import java.awt.Graphics;
import java.awt.Image;

import com.liuyu.action.ActionAble;
import com.liuyu.client.GameClient;
import com.liuyu.uitl.GetImageUtil;

/**
* @ClassName: Boom
* @Description: 爆炸类
* @author YangSong
* @date 2019年8月23日 上午8:15:19
*
*/
public class Boom extends GameObj implements ActionAble{

	
	private boolean isLive;
	private GameClient gc;
	
	
	public boolean isLive() {
		return isLive;
	}

	public void setLive(boolean isLive) {
		this.isLive = isLive;
	}

	// 动态初始化
	private static Image[] boomImgs = new Image[3];
	static {
		for(int i=1;i<3;i++) {
			boomImgs[i] = GetImageUtil.getImg("Boom/"+i+".png");
		}
	}
	
	
	
	public Boom() {
		
	}
	
	public Boom(int x,int y, GameClient gc,boolean isLive) {
		this.x = x;
		this.y = y;
		this.isLive = isLive;
		this.gc = gc;
	}
	
	
	@Override
	public void move() {
		
	}

	int count = 0;
	@Override
	public void draw(Graphics g) {
		if(isLive) {
			if(count>2) {
				System.out.println("count:"+count);
				count = 0;
				isLive = false;
				gc.booms.remove(this);
				return;
			}
			g.drawImage(boomImgs[count++], x, y, null);
		}
		
	}

}
