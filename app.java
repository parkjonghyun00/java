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
// ��
class Customer extends person{
	boolean choose = true;
	void customer(boolean choose) {
		this.choose = choose;
	}
	public Customer(String name, String number) {
		this.name = name;
		this.number = number;
	}
	public String getName()
	{
		return name;
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
	ArrayList<String> list = new ArrayList<>();	//String���� �迭 ����
	
	
	static Manager man = new Manager();
	File f = new File("data.txt");
	static BufferedReader r = null;
	static BufferedWriter w = null;
	int max = 100;
	
	void fileopen() {
		String s;
		String[] sArray = new String[max];
		try {
			r = new BufferedReader(new FileReader("data.txt"));	//���� �ҷ�����
			while((s=r.readLine()) != null) {	// ���� ���پ� �����鼭 �� ���� ����
				list.add(s);
				System.out.println(s);
			}
			r.close();
			
			
			Customer[] cm = new Customer[list.size()];
			for(int i=0; i<list.size(); i++){
				   String tok=(String)list.get(i);
				   String[] token=tok.split(", ");
				   cm[i]=new Customer(token[0], token[1]);
				   cus.put(cm[i].getName(), cm[i]);
			}
			for(int i=0; i<list.size(); i++)
				   System.out.println(cus.get(cm[i].getName()));

		}
		catch(IOException e) {
			System.out.println("���� ����� ����");
		}
	}
	
	void reserve() { //����
		System.out.print("�����ϴ� ���� �̸��� ��������");
		String name = sc.next();
		System.out.println("�����ϴ� ���� ��ȭ��ȣ�� ��������");
		int pnumber = sc.nextInt();
		
	}
	
	void run() {
		String[] button = {"0","�����ϱ�","ȸ�����","�̿����","�����ڸ��"};	
		setTitle("�̿�� �����");	//�������� Ÿ��Ʋ �ޱ�
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	//�������� �����츦 ������ ���α׷� ����
		setLayout(null);
		
		reserveListener reserve = new reserveListener();
		for(int i=1;i<5;i++) {
			JButton b = new JButton(button[i]);
			b.setLocation(50+200*(i%2),50+(50*(i/3)));
			b.setSize(150,30);
			b.addActionListener(reserve);
			add(b);
		}
		
		setSize(500,200);
		setVisible(true);
	}
}
class reserveListener implements ActionListener{
	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton)e.getSource();	//���õ� ��ư�� �˾Ƴ���.
		
		if(b.getText().equals("�����ϱ�"))
			b.setText("");
	}
}


// ��
public class app {
	public static void main(String[] args) {
		Reserver reserve = new Reserver();
		reserve.fileopen();
		//reserve.run();
	}
}