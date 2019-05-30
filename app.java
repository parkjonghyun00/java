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
			r = new BufferedReader(new FileReader("C:\\Users\\pjh\\Desktop\\javatext\\javacustomer.txt"));	//파일 불러오기
			while((s=r.readLine()) != null) {	// 파일 한줄씩 읽으면서 고객 정보 저장
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
			System.out.println("파일 입출력 오류");
		}
	}
	
	void reserve() { //예약
		System.out.print("예약하는 분의 이름을 적으세요");
		String name = sc.next();
		System.out.println("예약하는 분의 전화번호를 적으세요(-는 빼고 적으세요)");
		int pnumber = sc.nextInt();
		
	}
	
	void run() {
		String[] button = {"0","예약하기","회원모드","미용사모드","관리자모드"};	
		setTitle("미용실 예약앱");	//프레임의 타이틀 달기
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	//프레임의 윈도우를 닫으면 프로그램 종료
		
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


// 앱
public class app {
	public static void main(String[] args) {
		Reserver reserve = new Reserver();
		reserve.run();
	}
}
