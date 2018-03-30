import java.io.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.*;


public class Server extends JFrame{
	
	final static int DAYOFWEEK_SIZE = 3;
	final static int TIME_SIZE = 3;
	
	// �ؽ�Ʈ �ʵ�
	private JTextField jtfDayofweek = new JTextField(DAYOFWEEK_SIZE);
	private JTextField jtfTime = new JTextField(TIME_SIZE);
	
	// ��ư
	private JButton jbtnextMonth = new JButton(">");
	private JButton jbtpreviousMonth = new JButton("<");
	private JButton jbtgoDay = new JButton("Go");	
	private JButton[] jbtDB = new JButton[2];
	private JButton[] jbtroom = new JButton[36];
	private JLabel today_Stu= new JLabel();
	
	// �ð� ������ ������ ������
	private int Dayofweek;
	private int Time;	
	
	private lectureDB Lec_DB = new lectureDB();
	private StudentDB SM = new StudentDB(Lec_DB);		
	private ArrayList <Room> room_List = new ArrayList<>(); 
	

	public Server() throws IOException {
		java.util.Calendar cal = java.util.Calendar.getInstance();
		Dayofweek = 0; //cal.get(cal.DAY_OF_WEEK-1);
		Time = 0; //cal.get(cal.HOUR);
		
		jtfDayofweek.setText("요일");
		jtfTime.setText(""+Time);
		
		room_List.add(new Room());
		room_List.add(new Room());
		room_List.add(new Room());
		room_List.add(new Room());
		room_List.add(new Room());
		room_List.add(new Room());
		room_List.add(new Room());
		room_List.add(new Room());
		room_List.add(new Room());
		room_List.add(new Room());
		room_List.add(new Cafeteria());
		room_List.add(new Library());
		
		room_List.get(0).setName("515");
		room_List.get(0).setbuilding_num(208);
		room_List.get(0).setlocation(1, 1);
		room_List.get(1).setName("514");
		room_List.get(1).setbuilding_num(208);
		room_List.get(1).setlocation(1, 2);
		room_List.get(2).setName("513");
		room_List.get(2).setbuilding_num(208);
		room_List.get(2).setlocation(1, 3);
		room_List.get(3).setName("512");
		room_List.get(3).setbuilding_num(208);
		room_List.get(3).setlocation(2, 1);
		room_List.get(4).setName("511");
		room_List.get(4).setbuilding_num(208);
		room_List.get(4).setlocation(2, 2);
		room_List.get(5).setName("425");
		room_List.get(5).setbuilding_num(205);
		room_List.get(5).setlocation(4, 4);
		room_List.get(6).setName("424");
		room_List.get(6).setbuilding_num(205);
		room_List.get(6).setlocation(4, 3);
		room_List.get(7).setName("423");
		room_List.get(7).setbuilding_num(205);
		room_List.get(7).setlocation(4, 2);
		room_List.get(8).setName("422");
		room_List.get(8).setbuilding_num(205);
		room_List.get(8).setlocation(3, 4);
		room_List.get(9).setName("421");
		room_List.get(9).setbuilding_num(205);
		room_List.get(9).setlocation(3, 3);
		room_List.get(10).setName("�Ĵ�");
		room_List.get(10).setbuilding_num(107);
		room_List.get(10).setlocation(0, 0);
		room_List.get(11).setName("������");
		room_List.get(11).setbuilding_num(105);
		room_List.get(11).setlocation(5, 5);
		
		for(int i=0; i<2; i++){
			jbtDB[i] = new JButton();
		}
		
		for(int i=0; i<36; i++){
			jbtroom[i] = new JButton(" ");
		}
		
		
		
		jtfDayofweek.setFont(new Font("Serif", Font.PLAIN, 20));
		jtfDayofweek.setHorizontalAlignment(SwingConstants.CENTER);
		jtfTime.setFont(new Font("Serif", Font.PLAIN, 20));
		jtfTime.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel jpTime = new JPanel();
		jpTime.add(jtfDayofweek);
		jpTime.add(new JLabel("����     ", JLabel.CENTER));
		jpTime.add(jbtpreviousMonth);
		jpTime.add(jtfTime);
		jpTime.add(new JLabel("����", JLabel.CENTER));
		jpTime.add(jbtnextMonth);
		jpTime.add(jbtgoDay);
		
		JLabel title = new JLabel("University Management System", JLabel.CENTER);
		title.setFont(new Font("Serif", Font.PLAIN, 20));
		JPanel jpCenter = new JPanel(new BorderLayout());
		jpCenter.add(title, BorderLayout.NORTH);
		today_Stu.setText("   Today Student : "+SM.today_StuNum(Dayofweek));
		jpCenter.add(today_Stu, BorderLayout.WEST);
		jpCenter.add(jpTime,  BorderLayout.EAST);
		
		JPanel Map= new JPanel(new GridLayout(7,2,10,20));
		JPanel room[]= new JPanel[12];
		for(int i=0; i<12; i++){
			room[i] = new JPanel(new GridLayout(1,2,10,0));
		}	
		
		for(int i=0; i<36; i++){
			room[i/3].add(jbtroom[i]);
		}	
		for(int i=0; i<12; i++){
			Map.add(room[i]);
		}
		
		for(int i=0; i<2; i++){
			Map.add(jbtDB[i]);
		}
		jbtDB[0].setText("Student Management");
		jbtDB[1].setText("Lecture Management");
		
		setRoom();
		
	    add(jpCenter, BorderLayout.NORTH);
	    add(new JPanel(), BorderLayout.WEST);
	    add(new JPanel(), BorderLayout.EAST);
	    add(new JPanel(), BorderLayout.SOUTH);
	    add(Map, BorderLayout.CENTER);
	    
	    
	    jbtnextMonth.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e){
	        	if(Time < 9)
	        		jtfTime.setText("" + ++Time);
	        	else{
	        		Time = 0;
	        		Dayofweek = (Dayofweek + 1)%5 ;
	        		jtfDayofweek.setText(dayofweek_Index(Dayofweek));
	        		jtfTime.setText("" + Time);
	        	}
	        	setRoom();
	    		today_Stu.setText("   Today Student : "+SM.today_StuNum(Dayofweek));
	        }
	      });
	    
	    jbtpreviousMonth.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e){
	        	if(Time > 0)
	        		jtfTime.setText("" + --Time);
	        	else{
	        		Time = 9;
	        		Dayofweek = (Dayofweek - 1)%5;
	        		if(Dayofweek == -1) Dayofweek=4;
	        		jtfDayofweek.setText(dayofweek_Index(Dayofweek));
	        		jtfTime.setText("" + Time);
	        	}
	        	setRoom();
	    		today_Stu.setText("   Today Student : "+SM.today_StuNum(Dayofweek));
	        }
	      });
	    
	    jbtgoDay.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e){
	        	Dayofweek = dayofweek_Index(jtfDayofweek.getText());
	        	Time = Integer.parseInt(jtfTime.getText());
	        	
	        	if(Time < 0)
	        		Time = 0;
	        	else if(Time > 9)
	        		Time = 9;
	        	
	        	if(Dayofweek < 0)
	        		Dayofweek = 0;
	        	else if(Dayofweek > 4)
	        		Dayofweek = 4;
	        	
	        	jtfTime.setText("" + Time);
	        	jtfDayofweek.setText(dayofweek_Index(Dayofweek));
	        	setRoom();
	    		today_Stu.setText("   Today Student : "+SM.today_StuNum(Dayofweek));
	        }
	      });
	    
	    jbtDB[0].addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e){
				SM.pack();
				SM.setLocation(200, 300);
				SM.setTitle("Student_DB");
				SM.setVisible(true);
				SM.setResizable(false);
	        }
	      });
	    jbtDB[1].addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e){
	        	Lec_DB.pack();
	        	Lec_DB.setLocation(800, 500);
	        	Lec_DB.setTitle("Lecture_DB");
	        	Lec_DB.setVisible(true);
	        	Lec_DB.setResizable(false);
	        }
	      });

	}
	
	public void setRoom(){ //�� ������ ������� �����ش�
		int index,cafe_num,lib_num;
		float per=0.02f;
		for(int i=0; i<room_List.size(); i++ ){
			index = room_List.get(i).getlocationY()*6 + room_List.get(i).getlocationX();
			room_List.get(i).setcapacity(SM.student_Num(Lec_DB.nowlectureID(room_List.get(i).getName(),Dayofweek,Time)));
			jbtroom[index].setText(room_List.get(i).getName()+" : "+room_List.get(i).getcapacity()+"��");
				
			switch(Time){
			case 2:
				per =0.1f;
				break;
			case 3:
				per =0.5f;
				break;
			case 4:
				per =0.60f;
				break;
			case 5:
				per =0.40f;
				break;
			case 6:
				per =0.30f;
				break;
			case 7:
				per =0.20f;
				break;
			case 8:
				per =0.20f;
				break;
			case 9:
				per =0.50f;
				break;
			}
			cafe_num = (int)((SM.today_StuNum(Dayofweek) - SM.goHome_StuNum(Dayofweek, Time)) * per);
			jbtroom[0].setText(room_List.get(room_List.size()-2).getName()+" : "+cafe_num+"��");
			switch(Time){
			case 1:
				per =0.05f;
				break;
			case 2:
				per =0.1f;
				break;
			case 3:
				per =0.05f;
				break;
			case 4:
				per =0.02f;
				break;
			case 5:
				per =0.15f;
				break;
			case 6:
				per =0.25f;
				break;
			case 7:
				per =0.22f;
				break;
			case 8:
				per =0.20f;
				break;
			case 9:
				per =0.1f;
				break;
			}
			lib_num = (int)((SM.today_StuNum(Dayofweek) - (SM.goHome_StuNum(Dayofweek, Time) * 0.3) ) * per);
			jbtroom[35].setText(room_List.get(room_List.size()-1).getName()+":"+lib_num+"��");
		}
				
				
	}
	
	public String dayofweek_Index(int index){
		switch(index){
		case 0: 
			return "월";
		case 1:
			return "화";
		case 2:
			return "수";
		case 3:
			return "목";
		case 4:
			return "금";
		}
		return "에러";
	}
	public int dayofweek_Index(String day){
		switch(day){
		case "월":
			return 0;
		case "화":
			return 1;
		case "수":
			return 2;
		case "목":
			return 3;
		case "금":
			return 4;
		}
		return 0;
	}


	
	public static void main(String[] args) throws IOException  {
		// â ����
		Server frame = new Server();	
	    frame.pack();
	    frame.setTitle("Server");
	    frame.setSize(700, 640);
	    frame.setLocation(300, 300);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setVisible(true);
	    frame.setResizable(false);
	  }
	



}
