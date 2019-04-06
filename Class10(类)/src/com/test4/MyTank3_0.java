/*
 * 1.画出坦克
 * 2.上下左右移动
 * 3.可以发射子弹，并能连发(最多五颗)
 * 4.
 */
package com.test4;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
public class MyTank3_0 extends JFrame{
	MyPanel mp=null;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyTank3_0 mt=new MyTank3_0();
	}
	//构造函数
	public MyTank3_0()
	{
		mp=new MyPanel();
		//启动mp线程
		Thread t=new Thread(mp);
		t.start();
		this.add(mp);
		//注册监听
		this.addKeyListener(mp);
		this.setSize(400, 300);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
//移动面板
class MyPanel extends JPanel implements KeyListener,Runnable
{
	//定义一个我的坦克
	Hero hero=null;
	
	//定义敌人坦克组
	Vector<EnemyTank> ets=new Vector<EnemyTank>();
	
	//定义炸弹集合
	Vector<Bomb> bombs=new Vector<Bomb>();
	
	int enSize=3;
	//定义三张图片,三张才能组成一颗炸弹
	Image image1=null;
	Image image2=null;
	Image image3=null;
	
	//构造函数
	public MyPanel()
	{
		hero=new Hero(200,200);
		//初始化敌人的坦克
		for(int i=0;i<enSize;i++)
		{
			//创建敌人坦克
			EnemyTank et=new EnemyTank((i+1)*50,0);
			et.setColor(0);
			et.setDirect(2);
			//启动
			Thread t1=new Thread(et);
			t1.start();
			//添加子弹
			//Shot s=new Shot(et.x+10,et.y+30,2);
			//加入给敌人
			//et.ss.add(s);
			//Thread t2=new Thread(s);
			//加入到Vector中
			ets.add(et);
		}
		//初始化图片
//		image1=Toolkit.getDefaultToolkit().getImage(Panel.class.getResource(null));
//		image2=Toolkit.getDefaultToolkit().getImage(Panel.class.getResource(null));
//		image3=Toolkit.getDefaultToolkit().getImage(Panel.class.getResource(null));
	}
	
	//重写paint
	public void paint(Graphics g)
	{
		super.paint(g);
		//填充背景色
		g.fillRect(0, 0, 400, 300);
		g.setColor(Color.CYAN);
		//画出我的坦克(到时再封装成一个函数)
		this.drawTank(hero.getX(), hero.getY(), g, this.hero.direct, 1);
		//从ss中取出每颗子弹并绘制
		for(int i=0;i<this.hero.ss.size();i++)
		//画出子弹
		{
			Shot myShot=hero.ss.get(i);
			if(myShot!=null&&myShot.isLive==true)
			{
				g.fill3DRect(myShot.x, myShot.y, 1, 1, false);
			}
			if(myShot.isLive==false)
			{
				//从ss中 删除该子弹
				hero.ss.remove(myShot);
			}
		}
		//画出炸弹效果
		for(int i=0;i<bombs.size();i++)
		{
			//取出炸弹
			Bomb b=bombs.get(i);
			if(b.life>6)
			{
				g.drawImage(image1, b.x, b.y, 30, 30, this);
			}else if(b.life>3) {
				g.drawImage(image2, b.x, b.y, 30, 30, this);
			}else {
				g.drawImage(image3, b.x, b.y, 30, 30, this);
			}
			b.lifeDown();
			if(b.life==0)
			{
				bombs.remove(b);
			}
		}
		//画出敌人的坦克
		for(int i=0;i<ets.size();i++) {
			EnemyTank et=ets.get(i);
			if(et.isLive) {
				this.drawTank(et.getX(), et.getY(), g, et.getDirect(), 0);
				//画出敌人的子弹
				for(int j=0;j<et.ss.size();j++)
				{
					//取出子弹
					Shot enemyShot=et.ss.get(j);
					if(enemyShot.isLive)
					{
						g.fill3DRect(enemyShot.x, enemyShot.y, 1, 1, false);
					}else {
						//如果敌人坦克死亡，就从向量中删除掉
						et.ss.remove(enemyShot);
					}
				}
			}
			this.repaint();
		}
	}
	//写一个函数专门判断子弹是否击中敌人坦克
	public void hitTank(Shot s,EnemyTank et)
	{
		//判断该坦克的方向
		switch(et.direct)
		{
		//上或者下
		case 0:
		case 2:
			if(s.x>et.x&&s.x<et.x+20&&s.y>et.y&&s.y<et.y+30)
			{
				//击中
				//子弹死亡
				s.isLive=false;
				//敌人坦克死亡
				et.isLive=false;
				//创建一颗炸弹
				Bomb b=new Bomb(et.x,et.y);
				//放入集合中
				bombs.add(b);
			}
		case 1:
		case 3:
			if(s.x>et.x&&s.x<et.x+30&&s.y>et.y&&s.y<et.y+20)
			{
				//击中
				//子弹死亡
				s.isLive=false;
				//敌人坦克死亡
				et.isLive=false;
				//创建一颗炸弹
				Bomb b=new Bomb(et.x,et.y);
				//放入集合中
				bombs.add(b);
			}
		}
	}
	//画出坦克的函数
	public void drawTank(int x,int y,Graphics g,int direct,int type)
	{
		//判断类型
		switch(type)
		{
		case 0:
			g.setColor(Color.cyan);
			break;
		case 1:
			g.setColor(Color.yellow);
			break;
		}
		//判断方向
		switch(direct)
		{
		//向上
		case 0:
			//1.画出左边的矩形
			g.fill3DRect(x, y, 5, 30,false);
			//2.画出右边矩形
			g.fill3DRect(x+15, y, 5, 30,false);
			//3.画出中间矩形
			g.fill3DRect(x+5, y+5, 10, 20,false);
			//4.画出圆形
			g.fillOval(x+4, y+10, 10, 10);
			//5.画出线
			g.drawLine(x+10, y+15, x+10, y);
			break;
		case 1:
			//向右
			//画出上面的矩形
			g.fill3DRect(x, y, 30, 5, false);
			//画出下面的矩形
			g.fill3DRect(x, y+15, 30, 5, false);
			//画出中间的矩形
			g.fill3DRect(x+5, y+5, 20, 10, false);
			//画出圆形
			g.fillOval(x+10, y+5, 10, 10);
			//画出线
			g.drawLine(x+15, y+10, x+30, y+10);
			break;
		case 2:
			//向下
			//1.画出左边的矩形
			g.fill3DRect(x, y, 5, 30,false);
			//2.画出右边矩形
			g.fill3DRect(x+15, y, 5, 30,false);
			//3.画出中间矩形
			g.fill3DRect(x+5, y+5, 10, 20,false);
			//4.画出圆形
			g.fillOval(x+4, y+10, 10, 10);
			//5.画出线
			g.drawLine(x+10, y+15, x+10, y+30);
			break;
		case 3:
			//向左
			//画出上面的矩形
			g.fill3DRect(x, y, 30, 5, false);
			//画出下面的矩形
			g.fill3DRect(x, y+15, 30, 5, false);
			//画出中间的矩形
			g.fill3DRect(x+5, y+5, 20, 10, false);
			//画出圆形
			g.fillOval(x+10, y+5, 10, 10);
			//画出线
			g.drawLine(x+15, y+10, x, y+10);
			break;
		}
	}

	//键按下 w s a d(上下左右)
	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getKeyCode()==KeyEvent.VK_W) {
			//设置我的坦克的方向
			this.hero.setDirect(0);
			this.hero.moveUp();
		}else if(arg0.getKeyCode()==KeyEvent.VK_D) {
			//向右
			this.hero.setDirect(1);
			this.hero.moveRight();
		}else if(arg0.getKeyCode()==KeyEvent.VK_S) {
			//向下
			this.hero.setDirect(2);
			this.hero.moveDown();
		}else if(arg0.getKeyCode()==KeyEvent.VK_A) {
			//向左
			this.hero.setDirect(3);
			this.hero.moveLeft();
		}
		if(arg0.getKeyCode()==KeyEvent.VK_J) {
			//判断玩家是否按下J键
			//开火
			if(this.hero.ss.size()<=4)
			{
				this.hero.shotEnemy();
			}
		}
		//重新绘制Panel
		this.repaint();
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		//每隔100ms去重绘子弹
		while(true)
		{
			try {
				Thread.sleep(100);
			} catch (Exception e) {
				// TODO: handle exception
			}
			//判断是否击中
			for(int i=0;i<hero.ss.size();i++)
			{
				Shot myShot=hero.ss.get(i);
				//判断子弹是否有效
				if(myShot.isLive==true)
				{
					//取出每个敌人坦克与之匹配
					for(int j=0;j<ets.size();j++)
					{
						//取出坦克
						EnemyTank et=ets.get(j);
						if(et.isLive==true)
						{
							this.hitTank(myShot, et);
						}
						this.repaint();
					}
				}
			}
			//是否需要给坦克加入新的子弹
//			for(int i=0;i<ets.size();i++) {
//				EnemyTank et=ets.get(i);
//				if(et.isLive)
//				{
//					if(et.ss.size()<2)
//					{
//						Shot s=null;
//						//没有子弹
//						//添加子弹
//						switch(et.direct) {
//						case 0:
//							//创建一颗子弹
//							s=new Shot(et.x+10,et.y,0);
//							//把子弹加入到向量
//							et.ss.add(s);
//							break;
//						case 1:
//							s=new Shot(et.x+30,et.y+10,1);
//							et.ss.add(s);
//							break;
//						case 2:
//							s=new Shot(et.x+10,et.y+30,2);
//							et.ss.add(s);
//							break;
//						case 3:
//							s=new Shot(et.x,et.y+10,3);
//							et.ss.add(s);
//							break;
//						}
//						//启动子弹
//						Thread t=new Thread(s);
//						t.start();
//					}
//				}
//			}
			this.repaint();
		}
	}
}

