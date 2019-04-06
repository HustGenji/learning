
public class Josephu {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CycLink cyclink=new CycLink();
		cyclink.setLen(5);
		cyclink.createLink();
		cyclink.setK(2);
		cyclink.setM(2);
		cyclink.show();
		cyclink.play();

	}

}
class Child{
	int no;
	Child nextChild=null;
	public Child(int no)
	{
		//赋编号
		this.no=no;
	}
}
//环形链表
class CycLink{
	//先定义一个指向链表第一个小孩的引用
	//指向第一个小孩的移动，不能动
	Child firstChild=null;
	Child temp=null;
	int len=0;//表示共有多少个小孩
	int k=0;
	int m=0;
	//设置链表大小
	public void setLen(int len)
	{
		this.len=len;
	}
	//设置从第几个人开始数数
	public void setK(int k)
	{
		this.k=k;
	}
	public void setM(int m)
	{
		this.m=m;
	}
	//开始
	public void play()
	{
		Child temp=this.firstChild;
		//1.找到开始数数的人
		for(int i=1;i<k;i++)
		{
			temp=temp.nextChild;
		}
		while(this.len!=1) {
		//2.数m下
		for(int j=1;j<m;j++)
		{
			temp=temp.nextChild;
		}
		//找到出圈的前一个小孩
		Child temp2=temp;
		while(temp2.nextChild!=temp)
		{
			temp2=temp2.nextChild;
		}
		//3.将其从队列中删除
		temp2.nextChild=temp.nextChild;
		//让temp指向下一个数数的小孩
		temp=temp.nextChild;
		this.len--;
		}
		//最后一个
		System.out.println(temp.no);
	}
	
	//初始化环境链表
	public void createLink()
	{
		for(int i=1;i<=len;i++)
		{
			if(i==1) {
			//创建第一个小孩
			Child ch=new Child(i);
			this.firstChild=ch;
			this.temp=ch;
			}
			else{
				if(i==len)
				{
					//继续创建小孩
					Child ch=new Child(i);
					temp.nextChild=ch;
					temp=ch;
					temp.nextChild=this.firstChild;
				}else {
				//继续创建小孩
				Child ch=new Child(i);
				temp.nextChild=ch;
				temp=ch;}
			}
		}
	}
	//打印
	public void show()
	{
		Child temp=this.firstChild;
		do {
			System.out.print(temp.no+" ");
			temp=temp.nextChild;
		}while(temp!=this.firstChild);
	}
}
