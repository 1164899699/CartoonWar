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
* ��Ϸ�ͻ���
* @author YangSong
* @version 0.1
* @since 2019.08.20
*
*/
public class GameClient extends Frame {
	
	
	
	// ����һ��plane����
	Plane plane = new Plane(100,200,"plane/06.png",this);

	// ����һ���ӵ�
	// Bullet bullet = new Bullet(500, 200, "bullet/02.png");
	
	//����һ���ӵ��ļ���
	public List<Bullet> bullets = new ArrayList<>();
	
	// ����һ������ͼ����
	BackGround backImg = new BackGround(0, -9200, "bg.png");
	
	// ����һ����ը�ļ���
	public List<Boom> booms = new ArrayList<>();
	
	// �������˵ļ���
	public List<Plane> enemys = new ArrayList<>();
	
	// ���ͼƬ��˸
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
	//���ɿͻ��˴��ڵķ���
	public void launchFrame() {
		//���ɿͻ��˴���
		this.setSize(Constant.GAME_WIDTH,Constant.GAME_HEIGHT);
		// ���ñ���
		this.setTitle("�ɻ���ս");
		// �����Ƿ���ʾ����
		this.setVisible(true);
		Image img = GetImageUtil.getImg("icon.png");
		this.setIconImage(img);
		//��ֹ���
		this.setResizable(false);
		// ���ھ���
		this.setLocationRelativeTo(null);
		//�رմ��� ��ӹرռ�����
		this.addWindowListener(new WindowAdapter() {	
		  @Override
		    public void windowClosing(WindowEvent e) {
		    	System.exit(0);
		    }  
		});
		// ���������
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("�����һ�����");
			}
		});
		// ��Ӽ��̼���
		this.addKeyListener(new KeyAdapter() {
			//�������µ����
			@Override
			public void keyPressed(KeyEvent e) {
				plane.keyPressed(e);
			}
			@Override
			public void keyReleased(KeyEvent e) {
				plane.keyReleased(e);
				
			}
		});
		
		// �����߳�
		new paintThread().start();
		
		// ���з���������ӵ���
		EnemyPlane enemy1 = new EnemyPlane(400,50,1);
		EnemyPlane enemy2 = new EnemyPlane(300,100,1);
		enemys.add(enemy1);
		enemys.add(enemy2);
	}
	
	//��дpaint����
	@Override
	public void paint(Graphics g) {
		backImg.draw(g);
		plane.draw(g);
		// ѭ������ÿ���ӵ�
		for(int i=0;i<bullets.size();i++) {
			bullets.get(i).draw(g);
			bullets.get(i).hitMonsters(enemys);
		}
		g.drawString("��ǰ�ӵ���"+bullets.size(), 10, 60);
		g.drawString("��ǰ�л���������"+enemys.size(),10,80);
		// ѭ�����з�
		for(int i=0;i<enemys.size();i++) {
			enemys.get(i).draw(g);
		}
		
		// ѭ����ը
		for(int i=0;i<booms.size();i++) {
			booms.get(i).draw(g);
		}
	}
	

	
	// �ڲ���
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
