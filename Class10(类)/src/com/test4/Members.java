package com.test4;

import java.util.Vector;

//炸弹类
class Bomb
{
	//定义炸弹坐标
	int x,y;
	//炸弹的生命
	int life=9;
	boolean isLive=true;
	public Bomb(int x,int y)
	{
		this.x=x;
		this.y=y;
	}
	//减少生命值
	public void lifeDown()
	{
		if(life>0)
		{
			life--;
		}else {
			this.isLive=false;
		}
	}
}
//子弹类
class Shot implements Runnable
{
	int x;
	int y;
	int direct;
	int speed=2;
	//是否还活着
	boolean isLive=true;
	public Shot(int x,int y,int direct)
	{
		this.x=x;
		this.y=y;
		this.direct=direct;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true)
		{
			try {
				Thread.sleep(50);
			} catch (Exception e) {
				// TODO: handle exception
			}
			switch(direct)
			{
			case 0:
				//上
				y-=speed;
				break;
			case 1:
				//右
				x+=speed;
				break;
			case 2:
				//下
				y+=speed;
				break;
			case 3:
				//左
				x-=speed;
				break;
			}
			//System.out.println(x+" "+y);
			//子弹何时死亡
			//判断该子弹是否碰到边缘
			if(x<0||y<0||x>400||y>300)
			{
				this.isLive=false;
				break;
			}
		}
	}
}
//坦克类
class Tank
{
	//设置坦克的速度
	int speed=1;
	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	//表示x的横坐标
	int x=0;
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	//颜色
	int color;
	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}

	//坦克纵坐标
	int y=0;
	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	//坦克的方向
	//0表示上，1表示右，2表示下，3表示左
	int direct=0;
	public int getDirect() {
		return direct;
	}

	public void setDirect(int direct) {
		this.direct = direct;
	}

	public Tank(int x,int y)
	{
		this.x=x;
		this.y=y;
	}
}

//敌人的坦克
class EnemyTank extends Tank implements Runnable
{
	boolean isLive=true;
	int times=0;
	//存放敌人子弹
	Vector<Shot> ss=new Vector<Shot>();
	//敌人添加子弹应当在刚刚创建坦克和敌人的坦克子弹死亡过后
	public EnemyTank(int x,int y)
	{
		super(x,y);
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true)
		{
			
			switch(this.direct)
			{
			case 0:
				for(int i=0;i<30;i++) {
					if(y>0)
					{
						y-=speed;
					}
					try {
						Thread.sleep(50);
					} catch (Exception e) {
						e.printStackTrace();
						// TODO: handle exception
					}
				}
				break;
			case 1:
				for(int i=0;i<30;i++) {
					if(x<400) {
						x+=speed;
					}
					try {
						Thread.sleep(50);
					} catch (Exception e) {
						e.printStackTrace();
						// TODO: handle exception
					}
				}
				break;
			case 2:
				for(int i=0;i<30;i++) {
					if(y<300) {
						y+=speed;
					}
					try {
						Thread.sleep(50);
					} catch (Exception e) {
						e.printStackTrace();
						// TODO: handle exception
					}
				}
				break;
			case 3:
				for(int i=0;i<30;i++) {
					if(x>0) {
						x-=speed;
					}
					try {
						Thread.sleep(50);
					} catch (Exception e) {
						e.printStackTrace();
						// TODO: handle exception
					}
				}
				break;
			}
			this.times++;
			if(times%2==0)
			{
				if(isLive)
				{
					if(ss.size()<3)
					{
						System.out.println("11");
						Shot s=null;
						//没有子弹
						//添加子弹
						switch(direct) {
						case 0:
							//创建一颗子弹
							s=new Shot(x+10,y,0);
							//把子弹加入到向量
							break;
						case 1:
							s=new Shot(x+30,y+10,1);
							break;
						case 2:
							s=new Shot(x+10,y+30,2);
							break;
						case 3:
							s=new Shot(x,y+10,3);
							break;
						}
						ss.add(s);
						//启动子弹
						Thread t=new Thread(s);
						t.start();
					}
				}
			}
			//让坦克随机产生一个新的方向
			this.direct=(int)(Math.random()*4);
			//判断敌人是否死亡
			if(!this.isLive)
			{
				break;
			}
		}
	}
}
//我的坦克
class Hero extends Tank
{
	//子弹
	//Shot s=null;
	Vector<Shot> ss=new Vector<Shot>();
	Shot s=null;
	public Hero(int x,int y)
	{
		super(x,y);
	}
	//开火
	public void shotEnemy()
	{
		switch(this.direct) {
		case 0:
			//创建一颗子弹
			s=new Shot(x+10,y,0);
			//把子弹加入到向量
			ss.add(s);
			break;
		case 1:
			s=new Shot(x+30,y+10,1);
			ss.add(s);
			break;
		case 2:
			s=new Shot(x+10,y+30,2);
			ss.add(s);
			break;
		case 3:
			s=new Shot(x,y+10,3);
			ss.add(s);
			break;
		}
		Thread t=new Thread(s);
		t.start();
	}
	//坦克向上移动
	public void moveUp() {
		y-=this.speed;
	}
	//向下移动
	public void moveDown() {
		y+=this.speed;
	}
	//向右移动
	public void moveRight() {
		x+=this.speed;
	}
	//向左移动
	public void moveLeft() {
		x-=this.speed;
	}
}
