package com.liuyu.client;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import com.liuyu.constant.Constant;
import com.liuyu.entity.BackGround;
import com.liuyu.entity.Boom;
import com.liuyu.entity.Bullet;
import com.liuyu.entity.EnemyPlane;
import com.liuyu.entity.Plane;
import com.liuyu.uitl.GetImageUtil;

/**
* 游戏客户端
* @author YangSong
* @version 0.1
* @since 2019.08.20
*
*/
public class GameClient extends Frame {
	
	
	
	// 创建一个plane出来
	Plane plane = new Plane(100,200,"plane/06.png",this);

	// 创建一颗子弹
	// Bullet bullet = new Bullet(500, 200, "bullet/02.png");
	
	//创建一个子弹的集合
	public List<Bullet> bullets = new ArrayList<>();
	
	// 创建一个背景图出来
	BackGround backImg = new BackGround(0, -9200, "bg.png");
	
	// 创建一个爆炸的集合
	public List<Boom> booms = new ArrayList<>();
	
	// 创建敌人的集合
	public List<Plane> enemys = new ArrayList<>();
	
	// 解决图片闪烁
	public void update(Graphics g) {
		  Image backImg = createImage(Constant.GAME_WIDTH,Constant.GAME_HEIGHT);
		  Graphics backg = backImg.getGraphics();
		  Color color = backg.getColor();
		  backg.setColor(Color.WHITE);
		  backg.fillRect(0,0,Constant.GAME_WIDTH,Constant.GAME_HEIGHT);
		  backg.setColor(color);
		  paint(backg);
		  g.drawImage(backImg,0,0,null);
		  
		 }
	//生成客户端窗口的方法
	public void launchFrame() {
		//生成客户端窗口
		this.setSize(Constant.GAME_WIDTH,Constant.GAME_HEIGHT);
		// 设置标题
		this.setTitle("飞机大战");
		// 设置是否显示窗口
		this.setVisible(true);
		Image img = GetImageUtil.getImg("icon.png");
		this.setIconImage(img);
		//禁止最大化
		this.setResizable(false);
		// 窗口居中
		this.setLocationRelativeTo(null);
		//关闭窗口 添加关闭监听器
		this.addWindowListener(new WindowAdapter() {	
		  @Override
		    public void windowClosing(WindowEvent e) {
		    	System.exit(0);
		    }  
		});
		// 添加鼠标监听
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("你点了一下鼠标");
			}
		});
		// 添加键盘监听
		this.addKeyListener(new KeyAdapter() {
			//键盘摁下的情况
			@Override
			public void keyPressed(KeyEvent e) {
				plane.keyPressed(e);
			}
			@Override
			public void keyReleased(KeyEvent e) {
				plane.keyReleased(e);
				
			}
		});
		
		// 启动线程
		new paintThread().start();
		
		// 往敌方容器中添加敌人
		EnemyPlane enemy1 = new EnemyPlane(400,50,1);
		EnemyPlane enemy2 = new EnemyPlane(300,100,1);
		enemys.add(enemy1);
		enemys.add(enemy2);
	}
	
	//重写paint方法
	@Override
	public void paint(Graphics g) {
		backImg.draw(g);
		plane.draw(g);
		// 循环画出每个子弹
		for(int i=0;i<bullets.size();i++) {
			bullets.get(i).draw(g);
			bullets.get(i).hitMonsters(enemys);
		}
		g.drawString("当前子弹数"+bullets.size(), 10, 60);
		g.drawString("当前敌机的数量："+enemys.size(),10,80);
		// 循环画敌方
		for(int i=0;i<enemys.size();i++) {
			enemys.get(i).draw(g);
		}
		
		// 循环爆炸
		for(int i=0;i<booms.size();i++) {
			booms.get(i).draw(g);
		}
	}
	

	
	// 内部类
	class paintThread extends Thread{
		@Override
		public void run() {
			while(true) {
				repaint();
				try {
					Thread.sleep(40);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
