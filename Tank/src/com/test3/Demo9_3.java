/*
 * 讲解事件处理机制
 */
package com.test3;
import java.awt.*;
import javax.swing.*; 
import java.awt.event.*;
public class Demo9_3 extends JFrame implements ActionListener{
	//定义一个panel
	JPanel mp=null;
	JButton jb1=null;
	JButton jb2=null;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Demo9_3 demo9_3=new Demo9_3();
	}
	public Demo9_3()
	{
		mp=new JPanel();
		jb1=new JButton("黑色");
		
		jb2=new JButton("红色");
		
		this.add(jb1,BorderLayout.NORTH);
		mp.setBackground(Color.black);
		this.add(mp);
		this.add(jb2,BorderLayout.SOUTH);
		
		//注册监听
		jb1.addActionListener(this);
		//指定action命令
		jb1.setActionCommand("黑色");
		jb2.addActionListener(this);
		jb2.setActionCommand("红色");
		this.setSize(200, 150);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	@Override
	//对事件处理的方法
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		//判断是哪个按钮被点击
		if(e.getActionCommand().equals("黑色"))
		{
			System.out.println("你点击黑色按钮了");
		}
		else if(e.getActionCommand().equals("红色"))
		{
			System.out.println("你点击红色按钮了");
		}
	}
	
	
}
class MyPanel extends JPanel
{
	public void paint(Graphics g)
	{
		super.paint(g);
		
	}
}