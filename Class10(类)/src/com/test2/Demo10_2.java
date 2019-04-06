package com.test2;

public class Demo10_2 {
	public static void main(String[] args)
	{
		//注意启动
		Dog dog=new Dog();
		//创建一个Thread对象
		Thread t=new Thread(dog);
		t.start();
	}
}
class Dog implements Runnable
{
	int times=0;
	public void run()
	{
		while(true)
		{
			try {
				//休眠
				Thread.sleep(1000);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			times++;
			System.out.println("hello,world!"+times);
			if(times==10)
				break;
		}
	}
}