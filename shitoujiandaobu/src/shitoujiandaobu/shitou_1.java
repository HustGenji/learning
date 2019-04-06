package shitoujiandaobu;
import java.util.*;
public class shitou_1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Computer c=new Computer("computer");
		Tom t=new Tom("tom");
		Game game1=new Game(c,t);
		game1.start();
	}

}
class Computer{
	String name;
	public Computer(String name) {
		this.name=name;
	}
	public int show() {
		Random xx=new Random();
		int number=xx.nextInt(3);
		System.out.println("电脑的数是："+number);
		return number;
	}
}
class Tom{
	String name;
	public Tom(String name) {
		this.name=name;
	}
	int show() {
		System.out.println("0代表石头，1代表剪刀，2代表布");
		System.out.println("请输入你要猜的数");
		Scanner sc=new Scanner(System.in);
		int shu=sc.nextInt();
		System.out.println("你输入的数是："+shu);
		return shu;
	}
}
class Game{
	Computer computer;
	Tom tom;
	int score=0;
	public Game(Computer computer,Tom tom) {
		this.computer=computer;
		this.tom=tom;
	}
	public void start() {
		int t_show=tom.show();
		int c_show=computer.show();
		if(c_show==0&&t_show==1||c_show==1&&t_show==2||c_show==2&&t_show==0) {
			System.out.println("计算机赢了");
		}else if(c_show==t_show) {
			System.out.println("平局");
		}else {
			System.out.println("你赢了！");
			score++;
		}
		System.out.println("你的分数是："+score);
		gcontinue();
	}
	public void gcontinue() {
		System.out.println("要继续吗？y/n");
		Scanner sc1=new Scanner(System.in);
		String str=sc1.next();
		if(str.equals("y")) {
			start();
		}else {
			System.out.println("下次见~~~");
			System.exit(0);
		}
	}
}
