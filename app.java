import javax.swing.*;

import java.awt.event.*;
import java.awt.*;
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
	Container contentPane;		
	JButton b1, b2, b3, b4;	//4���� ��ư
	
	private	Scanner sc = new Scanner(System.in);
	private HashMap<String, Customer> cus = new HashMap<String, Customer>();
	private HashMap<String, Designer> des = new HashMap<String, Designer>();
	ArrayList<String> list = new ArrayList<>();	//String���� �迭 ����
	
	
	static Manager man = new Manager();
	File f = new File("C:\\Users\\pjh\\Desktop\\javatext\\javacustomer.txt");
	static BufferedReader r = null;
	static BufferedWriter w = null;
	int max = 100;
	
	void fileopen() {
		String s;
		String[] sArray = new String[max];
		try {
			r = new BufferedReader(new FileReader("C:\\Users\\pjh\\Desktop\\javatext\\javacustomer.txt"));	//���� �ҷ�����
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
			}

			for(int i=0; i<list.size(); i++)
				   System.out.println(cm[i]);

		}
		catch(IOException e) {
			System.out.println("���� ����� ����");
		}
	}
		
	void run() {
		setTitle("�̿�� �����");	//�������� Ÿ��Ʋ �ޱ�
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	//�������� �����츦 ������ ���α׷� ����
		contentPane = getContentPane();
		contentPane.setLayout(new FlowLayout());
		
		b1 = new JButton("�����ϱ�");
		b1.addActionListener(new MyButtonListener());
		contentPane.add(b1);
		
		b2 = new JButton("ȸ�����");
		b2.addActionListener(new MyButtonListener());
		contentPane.add(b2);
		
		b3 = new JButton("�̿����");
		b3.addActionListener(new MyButtonListener());
		contentPane.add(b3);
		
		b4 = new JButton("�����ڸ��");
		b4.addActionListener(new MyButtonListener());
		contentPane.add(b4);
		
		setSize(500,200);
		setVisible(true);
	}
	
	void reserve() {
		contentPane.setLayout(new FlowLayout());
		contentPane.add(new JLabel("�̸� : "));
		contentPane.add(JTextfield(10));
		contentPane.add(new JLabel("��ȭ��ȣ : "));
		contentPane.add(JTextfield(20));
		setSize(500,200);
		setVisible(true);
	}
	
	
	private Component JTextfield(int i) {
		// TODO Auto-generated method stub
		return null;
	}


	class MyButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			Object source = e.getSource();
			
			//source�� � ��ư���� ����� ��.
			if(source == b1) {
				b1.setVisible(false);	b2.setVisible(false);
				b3.setVisible(false);	b4.setVisible(false);
				reserve();
			}
		}
	}
}



// ��
public class app {
	public static void main(String[] args) {
		Reserver reserve = new Reserver();
		reserve.run();
	}
}
