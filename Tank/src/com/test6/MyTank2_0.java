/*
 * 1.画出坦克
 */
package com.test6;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
public class MyTank2_0 extends JFrame{
	MyPanel mp=null;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyTank2_0 mt=new MyTank2_0();
	}
	//构造函数
	public MyTank2_0()
	{
		mp=new MyPanel();
		
		this.add(mp);
		//注册监听
		this.addKeyListener(mp);
		this.setSize(400, 300);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
//移动面板
class MyPanel extends JPanel implements KeyListener
{
	//定义一个我的坦克
	Hero hero=null;
	
	//定义敌人坦克组
	Vector<EnemyTank> ets=new Vector<EnemyTank>();
	
	int enSize=3;
	
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
			//加入到Vector中
			ets.add(et);
		}
	}
	
	//重写paint
	public void paint(Graphics g)
	{
		super.paint(g);
		//填充背景色
		g.fillRect(0, 0, 400, 300);
//		g.setColor(Color.CYAN);
		//画出我的坦克(到时再封装成一个函数)
		this.drawTank(hero.getX(), hero.getY(), g, this.hero.direct, 1);
		//画出敌人的坦克
		for(int i=0;i<ets.size();i++) {
			this.drawTank(ets.get(i).getX(), ets.get(i).getY(), g, ets.get(i).getDirect(), 0);
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
}

