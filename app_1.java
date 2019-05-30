/*	name : 박종현
 * 	date : 05.30
 * 	contents : 미용실 예약 앱
 * 	update version : 1.0.3
 * 	update 이유 : 코드 추가 
 */
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
	public Customer(String name, String number) {
		this.name = name;
		this.number = number;
	}
	public String getName(){
		return name;
	}
}
// 디자이너
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
// 관리자
class Manager extends person{
	void Manager() {
		System.out.println("관리자");
	}
}
// 예약 프로그램
class Reserver extends JFrame{
	Container contentPane;		
	JButton b1, b2, b3, b4, yes, cancle;	//버튼
	
	private	Scanner sc = new Scanner(System.in);
	private HashMap<String, Customer> cus = new HashMap<String, Customer>();	//고객
	private HashMap<String, Designer> des = new HashMap<String, Designer>();	//디자이너
	Customer[] cm;	//고객
	Designer[] dn;	//디자이너
	ArrayList<String> clist = new ArrayList<>();	//String으로 배열 저장
	ArrayList<String> dlist = new ArrayList<>();
	static Manager man = new Manager();
	File f = new File("C:\\Users\\pjh\\Desktop\\javatext\\javacustomer.txt");
	static BufferedReader r = null;
	static BufferedWriter w = null;
	int max = 100;
	
	void fileopen() {
		String s;
		try {
			r = new BufferedReader(new FileReader("C:\\Users\\pjh\\Desktop\\javatext\\javacustomer.txt"));	//파일 불러오기
			while((s=r.readLine()) != null)	clist.add(s); // 파일 한줄씩 읽으면서 고객 정보 저장
			r.close();
			
			cm = new Customer[clist.size()];
			for(int i=0; i<clist.size(); i++){
				   String tok=(String)clist.get(i);
				   String[] token=tok.split(",");
				   cm[i]=new Customer(token[0], token[1]);
				   cus.put(cm[i].getName(), cm[i]);
			}
			r = new BufferedReader(new FileReader("C:\\Users\\pjh\\Desktop\\javatext\\javadesigner.txt"));
			while((s=r.readLine()) != null) dlist.add(s);	// 파일 한줄씩 읽으면서 고객 정보 저장
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
			System.out.println("파일 입출력 오류");
		}
	}
		
	void run() {	//실행
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
	
	void designer() {	// 예약하기 설정
		String[] cmname = new String[cm.length];
		String[] dnname = new String[dn.length];
		
		for(int i = 0;i<cm.length;i++) 	cmname[i] = cm[i].getName();
		for(int i = 0;i<dn.length;i++) 	dnname[i] = dn[i].getName();
		
		contentPane = getContentPane();
		contentPane.setLayout(new FlowLayout());
		JComboBox designercombo = new JComboBox(dnname);	//미용사 설정
		contentPane.add(designercombo);
		JComboBox dateCombo = new JComboBox(cmname);	//날짜 설정
		contentPane.add(dateCombo);
		JComboBox timeCombo = new JComboBox();	//시간 설정
		contentPane.add(timeCombo);
		JComboBox hairCombo = new JComboBox();	//머리 설정
		contentPane.add(hairCombo);
		
		yes = new JButton("예약하기");
		yes.addActionListener(new MyButtonListener());
		contentPane.add(yes);
		
		cancle = new JButton("취소하기");
		cancle.addActionListener(new MyButtonListener());
		contentPane.add(cancle);
		
		setSize(500,200);
		setVisible(true);
	}

	class MyButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			Object source = e.getSource();
			//source가 어떤 버튼인지 만들어 줌.
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
// 앱
public class app {
	public static void main(String[] args) {
		Reserver reserve = new Reserver();
		reserve.fileopen();
		reserve.run();
	}
}
