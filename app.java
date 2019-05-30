import javax.swing.*;

import java.awt.Container;
import java.awt.LayoutManager;
import java.awt.event.*;
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
	public String getName()
	{
		return name;
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
	
	private	Scanner sc = new Scanner(System.in);
	private HashMap<String, Customer> cus = new HashMap<String, Customer>();
	private HashMap<String, Designer> des = new HashMap<String, Designer>();
	ArrayList<String> list = new ArrayList<>();	//String으로 배열 저장
	
	
	static Manager man = new Manager();
	File f = new File("data.txt");
	static BufferedReader r = null;
	static BufferedWriter w = null;
	int max = 100;
	
	void fileopen() {
		String s;
		String[] sArray = new String[max];
		try {
			r = new BufferedReader(new FileReader("data.txt"));	//파일 불러오기
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
				   cus.put(cm[i].getName(), cm[i]);
			}
			for(int i=0; i<list.size(); i++)
				   System.out.println(cus.get(cm[i].getName()));

		}
		catch(IOException e) {
			System.out.println("파일 입출력 오류");
		}
	}
	
	void reserve() { //예약
		System.out.print("예약하는 분의 이름을 적으세요");
		String name = sc.next();
		System.out.println("예약하는 분의 전화번호를 적으세요");
		int pnumber = sc.nextInt();
		
	}
	
	void run() {
		String[] button = {"0","예약하기","회원모드","미용사모드","관리자모드"};	
		setTitle("미용실 예약앱");	//프레임의 타이틀 달기
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	//프레임의 윈도우를 닫으면 프로그램 종료
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
		JButton b = (JButton)e.getSource();	//선택된 버튼을 알아낸다.
		
		if(b.getText().equals("예약하기"))
			b.setText("");
	}
}


// 앱
public class app {
	public static void main(String[] args) {
		Reserver reserve = new Reserver();
		reserve.fileopen();
		//reserve.run();
	}
}