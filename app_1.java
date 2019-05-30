/*	name : ������
 * 	date : 05.30
 * 	contents : �̿�� ���� ��
 * 	update version : 1.0.3
 * 	update ���� : �ڵ� �߰� 
 */
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
	public Customer(String name, String number) {
		this.name = name;
		this.number = number;
	}
	public String getName(){
		return name;
	}
}
// �����̳�
class Designer extends person{
	String contents;
	void Designer(String contents) {
		this.contents = contents;
	}
	public Designer(String name, String number) {
		this.name = name;
		this.number = number;
	}
	public String getName() {
		return name;
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
	JButton b1, b2, b3, b4, yes, cancle;	//��ư
	
	private	Scanner sc = new Scanner(System.in);
	private HashMap<String, Customer> cus = new HashMap<String, Customer>();	//��
	private HashMap<String, Designer> des = new HashMap<String, Designer>();	//�����̳�
	Customer[] cm;	//��
	Designer[] dn;	//�����̳�
	ArrayList<String> clist = new ArrayList<>();	//String���� �迭 ����
	ArrayList<String> dlist = new ArrayList<>();
	static Manager man = new Manager();
	File f = new File("C:\\Users\\pjh\\Desktop\\javatext\\javacustomer.txt");
	static BufferedReader r = null;
	static BufferedWriter w = null;
	int max = 100;
	
	void fileopen() {
		String s;
		try {
			r = new BufferedReader(new FileReader("C:\\Users\\pjh\\Desktop\\javatext\\javacustomer.txt"));	//���� �ҷ�����
			while((s=r.readLine()) != null)	clist.add(s); // ���� ���پ� �����鼭 �� ���� ����
			r.close();
			
			cm = new Customer[clist.size()];
			for(int i=0; i<clist.size(); i++){
				   String tok=(String)clist.get(i);
				   String[] token=tok.split(",");
				   cm[i]=new Customer(token[0], token[1]);
				   cus.put(cm[i].getName(), cm[i]);
			}
			r = new BufferedReader(new FileReader("C:\\Users\\pjh\\Desktop\\javatext\\javadesigner.txt"));
			while((s=r.readLine()) != null) dlist.add(s);	// ���� ���پ� �����鼭 �� ���� ����
			r.close();
			
			dn = new Designer[dlist.size()];
			for(int i=0; i<dlist.size(); i++){
				   String tok=(String)dlist.get(i);
				   String[] token=tok.split(",");
				   dn[i]=new Designer(token[0], token[1]);
				   des.put(dn[i].getName(), dn[i]);
			}
		}
		catch(IOException e) {
			System.out.println("���� ����� ����");
		}
	}
		
	void run() {	//����
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
	
	void designer() {	// �����ϱ� ����
		String[] cmname = new String[cm.length];
		String[] dnname = new String[dn.length];
		
		for(int i = 0;i<cm.length;i++) 	cmname[i] = cm[i].getName();
		for(int i = 0;i<dn.length;i++) 	dnname[i] = dn[i].getName();
		
		contentPane = getContentPane();
		contentPane.setLayout(new FlowLayout());
		JComboBox designercombo = new JComboBox(dnname);	//�̿�� ����
		contentPane.add(designercombo);
		JComboBox dateCombo = new JComboBox(cmname);	//��¥ ����
		contentPane.add(dateCombo);
		JComboBox timeCombo = new JComboBox();	//�ð� ����
		contentPane.add(timeCombo);
		JComboBox hairCombo = new JComboBox();	//�Ӹ� ����
		contentPane.add(hairCombo);
		
		yes = new JButton("�����ϱ�");
		yes.addActionListener(new MyButtonListener());
		contentPane.add(yes);
		
		cancle = new JButton("����ϱ�");
		cancle.addActionListener(new MyButtonListener());
		contentPane.add(cancle);
		
		setSize(500,200);
		setVisible(true);
	}

	class MyButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			Object source = e.getSource();
			//source�� � ��ư���� ����� ��.
			if(source == b1) {
				b1.setVisible(false);	b2.setVisible(false);
				b3.setVisible(false);	b4.setVisible(false);
				designer();
			}
			else if(source == b2) {
				b1.setVisible(false);	b2.setVisible(false);
				b3.setVisible(false);	b4.setVisible(false);
			}
		}
	}
}
// ��
public class app {
	public static void main(String[] args) {
		Reserver reserve = new Reserver();
		reserve.fileopen();
		reserve.run();
	}
}
