package Struck;

interface Link
{
	void add(Object obj);
	boolean remove(int index);
	boolean contains(Object obj);
	int indexOf(Object obj);
	boolean set(int index,Object obj);
	Object get(int index);
	int length();
	void clear();
	Object[] toArray();
	void printLink();
}
class Factory
{
	private Factory() {}
	public static Link getLinkInstance()
	{
		return new LinkImpl();
	}
}
class LinkImpl implements Link
{
	private Node first;
	private Node last;
	private int size;
	private class Node
	{
		private Object data;
		private Node prev;
		private Node next;
		private Node(Object data,Node prev,Node next)
		{
			this.data=data;
			this.prev=prev;
			this.next=next;
		}
	}

	@Override
	public void add(Object obj) {
		// TODO Auto-generated method stub
		Node newNode=new Node(obj,null,null);
		if(this.first==null)
		{
			this.first=this.last=newNode;
		}
		else 
		{
			Node temp=this.last;
			temp.next=newNode;
			newNode.prev=temp;
			this.last=newNode;
		}
		this.size++;
	}

	@Override
	public boolean remove(int index) {
		// TODO Auto-generated method stub
		if(index < 0 || index > this.size-1)
		{
			//下标超出范围
			return false;
		}
		else if(index == 0)//要删除的为第一个元素
		{
			if(index == this.size-1)//链表只有一个元素
			{
				this.first = this.last = null;
				this.size--;
				return true;
			}
			Node toDelete=this.first;
			this.first=toDelete.next;
			this.first.prev=this.last;
			this.last.next=this.first;
			toDelete.next=null;
			toDelete.prev=null;
			this.size--;
			return true;
		}
		//删除的为最后一个元素
		else if(index == this.size-1)
		{
			Node toDelete=this.last;
			Node newLast=toDelete.prev;
			this.last=newLast;
			newLast.next=this.first;
			this.first.prev=newLast;			
			toDelete.next=null;
			toDelete.prev=null;
			this.size--;
			return true;		
		}
		//删除的为中间的节点
		else if(index > 0 && index <this.size-1)
		{
			Node toDelete=this.first;
			//找到下标index对应的点
			while(index!=0)
			{
				toDelete=toDelete.next;
				index--;
			}
			Node prev=toDelete.prev;
			Node next=toDelete.next;
			prev.next=next;
			next.prev=prev;
			this.size--;
			return true;
		}
		return false;
	}

	@Override
	public boolean contains(Object obj) {
		// TODO Auto-generated method stub
		if(this.first == null)
		{
			//空链表
			return false;
		}
		Node cur=this.first;
		for(;cur!=null;cur=cur.next)
		{
			if(cur.data == obj)
			{
				return true;
			}
		}
		return false;
	}

	@Override
	public int indexOf(Object obj) {
		// TODO Auto-generated method stub
		if(this.first == null)
		{
			//空链表
			return 0;
		}
		Node cur = this.first;
		int index = 0;
		while(cur!=null)
		{
			if(cur.data == obj)
			{
				return index;
			}
			cur=cur.next;
			index++;
		}
		return 0;
	}

	@Override
	public boolean set(int index, Object obj) {
		// TODO Auto-generated method stub
		if(index<0 || index>this.size-1)
		{
			return false;
		}		
		Node cur=this.first;
		while(index!=0)
		{
			cur=cur.next;
			index--;
		}
		cur.data=obj;
		return true;
	}

	@Override
	public Object get(int index) {
		// TODO Auto-generated method stub
		if(index < 0 || index >this.size-1)
		{
			//非法位置
			return null;
		}
		if(index<this.size/2)
		{
			Node cur=this.first;
			while(index!=0)
			{
				cur=cur.next;
				index--;
			}
			return cur.data;
		}
		else
		{
			Node cur=this.last;
			int loop=this.size-index-1;
			while(loop!=0)
			{
				cur=cur.prev;
				loop--;
			}
			return cur.data;
		}
	}

	@Override
	public int length() {
		// TODO Auto-generated method stub
		if(this.first == null)
		{
			//空链表
			return 0;
		}
		return this.size;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		Node cur = this.first;
		while(cur!=null)
		{
			cur.next=null;
			cur.prev=null;
			cur.data=0;
			cur=cur.next;
		}
		this.first=null;
		this.last=null;
		this.size=0;
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		if(this.first == null)
		{
			//空链表
			return null;
		}
		Node cur=this.first;
		Object[] arry=new Object[this.size];
		int i=0;
		for(;cur!=null;cur=cur.next)
		{
			arry[i]=cur.data;
			i++;
		}
		return arry;
	}

	@Override
	public void printLink() {
		// TODO Auto-generated method stub
		Node cur=this.first;
		for(;cur!=null;cur=cur.next)
		{
			System.out.println(cur.data);
		}
		System.out.println();
	}
	
	
}
public class dbLinkList {
	public static void main(String[] args) {
		Link link=Factory.getLinkInstance();
		link.add("------start-----");
		link.add("a");
		link.add("b");
		link.add("c");
		link.add("d");
		link.add("------ end ------");
		link.printLink();
		System.out.println("删除d");
		link.remove(4);
		link.printLink();
		System.out.println("查看a是否存在，希望返回true，实际返回"+link.contains("a"));
		System.out.println("查看a的位置，希望返回1，实际返回"+link.indexOf("a"));
		System.out.println();
		System.out.println("在位置2插入元素x");
		link.set(2, "x");
		link.printLink();
		System.out.println("获取下标为3的元素，希望返回c，实际返回"+link.get(3));
		System.out.println("链表的长度希望为5，实际返回"+link.length());
		System.out.println("链表置空");
		link.clear();
		link.printLink();
		link.add("------start-----");
		link.add("a");
		link.add("b");
		link.add("c");
		link.add("d");
		link.add("------ end ------");
		link.toArray();
		Object[] obj=link.toArray();
		for(int i=0;i<link.length();i++)
		{
			System.out.println(obj[i]);
		} 
	}

}
