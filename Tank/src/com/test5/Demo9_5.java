package com.test5;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
public class Demo9_5 extends JFrame{
	MyPanel mp=null;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Demo9_5 demo9_5=new Demo9_5();
	}
	public Demo9_5()
	{
		mp=new MyPanel();
		
		this.add(mp);
		//注册监听
		this.addMouseListener(mp);
		this.addKeyListener(mp);
		this.addMouseMotionListener(mp);
		this.addWindowListener(mp);
		
		this.setSize(400,300);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
//1.让MyPanel知道鼠标按下的消息，并且能知道点击坐标
//2.让MyPanel知道哪个键按下了
//3.让MyPanel知道鼠标移动拖拽
//4.让MyPanel知道窗口的变化（关闭，最小化，最大化）
class MyPanel extends JPanel implements WindowListener,MouseListener,KeyListener,MouseMotionListener
{
	public void paint(Graphics g)
	{
		super.paint(g);
	}
//鼠标被点击
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("点击了x="+arg0.getX()+"y="+arg0.getY());
	}
//鼠标移动到panel中
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		//System.out.println("鼠标来了");
	}
//鼠标离开
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		//System.out.println("鼠标走了");
	}
//鼠标按下去（没松开）
	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
//鼠标松开了
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	//键按下
	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println(arg0.getKeyChar()+"键被按下");
	}
	//键松开
	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	//键输入
	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println("鼠标当前坐标为 x="+e.getX()+" y="+e.getY());
	}
	//激活
	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("Activated");
	}
	//关闭了
	@Override
	public void windowClosed(WindowEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("窗口关闭");
	}
	//正在关闭
	@Override
	public void windowClosing(WindowEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("closing");
	}
	//最小化
	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("最小化了");
	}
	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	//打开了
	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}