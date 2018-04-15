import test;

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
		private Node next;
		private Node prev;
		private Node(Node prev,Object obj,Node next)
		{
			this.data=obj;
			this.prev=prev;
			this.next=next;
		}
	}
	@Override
	public void add(Object obj) {
		Node newNode=new Node(null,obj,null);
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
		if(index<0||index>=this.size)
			return false;
		//ɾ����һ���ڵ�
		if(index==0)
		{
			//����ɾ���������һ���ڵ�
			if(index==this.size-1)
			{
				this.first=null;
				this.last=null;
			}
			//ɾ���Ĳ������һ���ڵ�
			else
			{
				Node delete=this.first;
				this.first=delete.next;
				this.first.prev=null;
				//ά��delete��prev��next
				delete.next=null;
				delete.data=null;
			}
		}
		//ɾ���������һ���ڵ�
		else if(index==this.size-1)
		{
			Node delete=this.last;
			this.last=delete.prev;
			this.last.next=null;
			//ά��delete��ָ��
			delete.prev=null;
			delete.data=null;
		}
		//ɾ�������м�ڵ�
		else 
		{
			//���ҵ�Ҫɾ���Ľڵ�
			Node delete=this.first;
			while(index!=0)
			{
				delete=delete.next;
				index--;
			}
			//�޸�ָ��
			Node cur=delete.prev;
			Node next=delete.next;
			cur.next=next;
			next.prev=cur;
		}
		this.size--;
		return true;
	}
	@Override
	public boolean contains(Object obj) {
		Node cur=this.first;
		for(;cur!=null;cur=cur.next)
		{
			if(cur.data==obj)
				return true;
		}
		return false;
	}
	@Override
	public int indexOf(Object obj) {
		if(this.first==null)
			return 0;
		Node cur=this.first;
		int index=0;
		while(cur!=null)
		{
			if(cur.data==obj)
				return index;
			index++;
			cur=cur.next;
		}
		return -1;
	}
	@Override
	public boolean set(int index, Object obj) {
		if(index<0||index>=this.size)
			return false;
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
		if(index<0||index>=this.size)
			return null;
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
		if(this.first==null)
			return 0;
		return this.size;
	}
	@Override
	public void clear() {
		Node cur=this.first;
		for(;cur!=null;)
		{
			Node next=cur.next;
			cur.prev=null;
			cur.next=null;
			cur.data=null;
			cur=next;
		}
		//ɾ���������first��last��ָ��Ϊnull,sizeΪ0
		this.first=null;
		this.last=null;
		this.size=0;
	}
	@Override
	public Object[] toArray() {
		Node cur=this.first;
		if(cur==null)
			return null;
		int i=0;
		Object[] obj=new Object[this.size];
		for(;cur!=null;cur=cur.next)
		{
			obj[i]=cur.data;
			i++;
		}
		return obj;
	}
	@Override
	public void printLink() {
		Node cur=this.first;
		for(;cur!=null;cur=cur.next)
		{
			System.out.println(cur.data);
		}
		
	}
}
public class Test {
	public static void main(String[] args) {
		Link link=Factory.getLinkInstance();
		link.add("��ͷ");
		link.add("����1");
		link.add("����2");
		link.add("����3");
		link.add("����4");
		link.add("����5");
		link.set(5,"hahaha");
		link.remove(3);
		Object[] obj=link.toArray();
		for(int i=0;i<link.length();i++)
		{
			System.out.println(obj[i]);
		} 
		
	}
}



