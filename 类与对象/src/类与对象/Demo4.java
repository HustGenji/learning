package 类与对象;

public class Demo4 
{
	public static void main(String[] args)
	{
		//创建一只猫
		//Cat cat1=new Cat();
		//访问属性的 对象名.属性名
		/*Cat cat1;
		cat1=new Cat();
		cat1.age=3;
		cat1.name="小白";
		cat1.color="白色";
		Cat cat2=new Cat();
		cat2.age=100;
		cat2.name="小花";
		cat2.color="花色";
		Cat cat3;*/
		
		/*Person a=new Person();
		a.age=10;
		a.name="小明";
		Person b;
		b=a;
		System.out.println(b.age);
		Person c;
		c=b;
		System.out.println("c.name="+c.name);
		c.age=9;
		System.out.println("a.age="+a.age);
		System.out.println("b.age="+b.age);
		System.out.println("c.age="+c.age);*/
		
		Person p1=new Person(1,"小明");
		System.out.println(p1.name);
		//调用speak方法
		p1.speak();
		//调用计算方法
		p1.jiSuan();
		//调用可以传入参数的计算方法
		p1.jiSuan(3);
		//计算两个数的和
		p1.add(3, 4);
		//三个数
		System.out.println("add3="+p1.add3(2, 2.2, 3.3));
	}
}
//java中如何定义一个类(猫)(类名首字母要大写)
/*class Cat
{
	int age;
	String name;
	String color;
	Master myMaster;
}
class Master
{
	int age;
	String name;
	String address;
}*/
class Person
{
	int age;
	String name;
	
	//构造方法
	public Person(int Age,String Name)
	{
		age=Age;
		name=Name;
	}
	//1.可以输出我是好人
	//方法名首字母小写
	//如何定名字1.驼峰法(匈牙利法)myCry.2下划线法 my_cry
	public void speak(){
		System.out.println("我是一个好人");
	}
	public void jiSuan() {
		int result=0;
		for(int i=1;i<=1000;i++)
		{
			result+=i;
		}
		System.out.println("结果是"+result);
	}
	//带参数的成员方法
	public void jiSuan(int n)
	{
		int result=0;
		for(int i=1;i<=n;i++)
		{
			result+=i;
		}
		System.out.println("结果是："+result);
	}
	public void add(int num1,int num2)
	{
		System.out.println("相加结果是"+(num1+num2));
	}
	//计算两个数的和，并且把结果返回给调用它的函数
	//注意：返回类型和返回结果的的类型要一致
	//2.在调用某个成员方法的时候，给出的具体数值的个数，
	//和类型要相匹配
	public double add3(int num1,double num2,double num3)
	{
		return num1+num2+num3;
	}
}