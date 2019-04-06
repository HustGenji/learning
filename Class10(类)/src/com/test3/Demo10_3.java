/*
 * 两个线程同时运行
 */
package com.test3;

public class Demo10_3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Pig pig=new Pig(5);
		Bird bird=new Bird(5);
		Thread t1=new Thread(pig);
		Thread t2=new Thread(bird);
		t1.start();
		t2.start();
	}

}
//打印
class Pig implements Runnable
{
	int n=0;
	int times=0;
	public Pig(int n)
	{
		this.n=n;
	}
	public void run()
	{
		while(true)
		{
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
				// TODO: handle exception
			}
			times++;
			System.out.println("Hello,world!"+" "+times);
			if(times==n)
			{
				break;
			}
		}
		
	}
}
//算数学
class Bird implements Runnable
{
	int n=0;
	int res=0;
	int times=0;
	public Bird(int n)
	{
		this.n=n;
	}
	public void run() {
		while(true) {
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
				// TODO: handle exception
			}
			res+=(++times);
			System.out.println("当前结果是"+res);
			if(times==n)
			{
				System.out.println("最后结果是"+res);
				break;
			}
		}
	}
}