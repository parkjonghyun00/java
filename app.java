import javax.swing.*;

import java.awt.Container;
import java.awt.LayoutManager;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.io.*;

// ���
abstract class person{
	String name;
	String number;
	void person(String name, String number) {
		this.name = name;
		this.number = number;
	}
}
// ����
class Customer extends person{
	boolean choose = true;
	void customer(boolean choose) {
		this.choose = choose;
	}
	public Customer(String name, String number) {
		this.name = name;
		this.number = number;
	}
}
// �����̳�
class Designer extends person{
	String contents;
	void Designer(String contents) {
		this.contents = contents;
	}
}
// ������
class Manager extends person{
	void Manager() {
		System.out.println("������");
	}
}
// ���� ���α׷�
class Reserver extends JFrame{
	
	private	Scanner sc = new Scanner(System.in);
	private HashMap<String, Customer> cus = new HashMap<String, Customer>();
	private HashMap<String, Designer> des = new HashMap<String, Designer>();
	ArrayList list = new ArrayList();
	
	
	static Manager man = new Manager();
	File f = new File("C:\\Users\\pjh\\Desktop\\javatext\\javacustomer.txt");
	static BufferedReader r = null;
	static BufferedWriter w = null;
	int max = 100;
	
	void fileopen() {
		String s;
		int n = 0;
		String[] sArray = new String[max];
		try {
			r = new BufferedReader(new FileReader("C:\\Users\\pjh\\Desktop\\javatext\\javacustomer.txt"));	//���� �ҷ�����
			while((s=r.readLine()) != null) {	// ���� ���پ� �����鼭 ���� ���� ����
				list.add(s);	n++;
				System.out.println(s);
			}
			r.close();
			
			Customer[] cm = new Customer[n];
			
			for(int i=0; i<n; i++){
				   String tok=(String)list.get(i);
				   String[] token=tok.split(", ");
				   cm[i]=new Customer(token[0], token[1]);
			}

			for(int i=0; i<n; i++)
				   System.out.println(cm[i]);

		}
		catch(IOException e) {
			System.out.println("���� ����� ����");
		}
	}
	
	void reserve() { //����
		System.out.print("�����ϴ� ���� �̸��� ��������");
		String name = sc.next();
		System.out.println("�����ϴ� ���� ��ȭ��ȣ�� ��������(-�� ���� ��������)");
		int pnumber = sc.nextInt();
		
	}
	
	void run() {
		String[] button = {"0","�����ϱ�","ȸ�����","�̿����","�����ڸ��"};	
		setTitle("�̿�� �����");	//�������� Ÿ��Ʋ �ޱ�
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	//�������� �����츦 ������ ���α׷� ����
		
		setLayout(null);
		for(int i=1;i<5;i++) {
			JButton b = new JButton(button[1]);
			b.setLocation(40,40);
			b.setSize(30,30);
			add(b);
		}
		
		setSize(300,200);
		setVisible(true);
		
	}
}


// ��
public class app {
	public static void main(String[] args) {
		Reserver reserve = new Reserver();
		reserve.run();
	}
}