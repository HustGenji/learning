/*
 * 开发线程(Thread)
 */
public class Demo10_1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//创建一个Cat对象
		Cat cat=new Cat();
		//启动线程，会导致run函数的运行
		cat.start();
	}

}
class Cat extends Thread{
	int times=0;
	//重写run函数
	public void run() 
	{
		while(true) {
			try {
				//休眠一秒
				//1000表示1000ms
				//sleep让该线程进入到Blocked状态并释放资源
				Thread.sleep(1000);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			times++;
			System.out.println("hello,world!"+times);
			if(times==10)
				//退出
				break;
		}
	}
}