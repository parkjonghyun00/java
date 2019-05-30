import javax.swing.*;

import java.awt.event.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.io.*;

// 사람
abstract class person{
	String name;
	String number;
	void person(String name, String number) {
		this.name = name;
		this.number = number;
	}
}
// 고객
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
// 디자이너
class Designer extends person{
	String contents;
	void Designer(String contents) {
		this.contents = contents;
	}
}
// 관리자
class Manager extends person{
	void Manager() {
		System.out.println("관리자");
	}
}
// 예약 프로그램
class Reserver extends JFrame{
	Container contentPane;		
	JButton b1, b2, b3, b4;	//4개의 버튼
	
	private	Scanner sc = new Scanner(System.in);
	private HashMap<String, Customer> cus = new HashMap<String, Customer>();
	private HashMap<String, Designer> des = new HashMap<String, Designer>();
	ArrayList<String> list = new ArrayList<>();	//String으로 배열 저장
	
	
	static Manager man = new Manager();
	File f = new File("C:\\Users\\pjh\\Desktop\\javatext\\javacustomer.txt");
	static BufferedReader r = null;
	static BufferedWriter w = null;
	int max = 100;
	
	void fileopen() {
		String s;
		String[] sArray = new String[max];
		try {
			r = new BufferedReader(new FileReader("C:\\Users\\pjh\\Desktop\\javatext\\javacustomer.txt"));	//파일 불러오기
			while((s=r.readLine()) != null) {	// 파일 한줄씩 읽으면서 고객 정보 저장
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
			System.out.println("파일 입출력 오류");
		}
	}
		
	void run() {
		setTitle("미용실 예약앱");	//프레임의 타이틀 달기
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	//프레임의 윈도우를 닫으면 프로그램 종료
		contentPane = getContentPane();
		contentPane.setLayout(new FlowLayout());
		
		b1 = new JButton("예약하기");
		b1.addActionListener(new MyButtonListener());
		contentPane.add(b1);
		
		b2 = new JButton("회원모드");
		b2.addActionListener(new MyButtonListener());
		contentPane.add(b2);
		
		b3 = new JButton("미용사모드");
		b3.addActionListener(new MyButtonListener());
		contentPane.add(b3);
		
		b4 = new JButton("관리자모드");
		b4.addActionListener(new MyButtonListener());
		contentPane.add(b4);
		
		setSize(500,200);
		setVisible(true);
	}
	
	void reserve() {
		contentPane.setLayout(new FlowLayout());
		contentPane.add(new JLabel("이름 : "));
		contentPane.add(JTextfield(10));
		contentPane.add(new JLabel("전화번호 : "));
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
			
			//source가 어떤 버튼인지 만들어 줌.
			if(source == b1) {
				b1.setVisible(false);	b2.setVisible(false);
				b3.setVisible(false);	b4.setVisible(false);
				reserve();
			}
		}
	}
}



// 앱
public class app {
	public static void main(String[] args) {
		Reserver reserve = new Reserver();
		reserve.run();
	}
}
