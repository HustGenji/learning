package com.test6;

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
class EnemyTank extends Tank
{
	public EnemyTank(int x,int y)
	{
		super(x,y);
	}
}
//我的坦克
class Hero extends Tank
{
	public Hero(int x,int y)
	{
		super(x,y);
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
