/*
 * 加深对事件处理机制的理解
 * 1.通过上下左右键，来控制一个小球的位置
 */
package com.test4;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
public class Demo9_4 extends JFrame{
	MyPanel mp=null;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Demo9_4 demo9_4=new Demo9_4();
	}
	public Demo9_4() {
		mp=new MyPanel();
		//加入到JFrame
		this.add(mp);
		
		this.addKeyListener(mp);
		
		this.setSize(400, 300);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
class MyPanel extends JPanel implements KeyListener
{
	int x=10;
	int y=10;
	public void paint(Graphics g) {
		super.paint(g);
		
		g.fillOval(x,y, 10, 10);
	}
//press表示按压键，比如F1
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		//System.out.println("键被按下"+(char)e.getKeyCode());
		
		if(e.getKeyCode()==KeyEvent.VK_DOWN)
		{
			y++;
		}else if(e.getKeyCode()==KeyEvent.VK_UP) {
			y--;
		}else if(e.getKeyCode()==KeyEvent.VK_LEFT) {
			x--;
		}else if(e.getKeyCode()==KeyEvent.VK_RIGHT) {
			x++;
		}
		//repaint
		this.repaint();
	}
//键被释放
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
//type表示显示具体数值，比如数字键
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}