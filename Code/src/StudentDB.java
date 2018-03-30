import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class StudentDB extends JFrame {

	  final static int NAME_SIZE = 30;
	  final static int ID_SIZE = 30;
	  final static int DEPARTMENT_SIZE = 30;
	  final static int TEL_SIZE = 30;
	  final static int SCHEDULE_SIZE = 8;
	  final static int RECORD_SIZE =
	    (NAME_SIZE + ID_SIZE + DEPARTMENT_SIZE + TEL_SIZE);

	  private int currentPosition;	// ArrayList에 현재 위치
	  private File f;

	  // Text fields
	  private JTextField jtfName = new JTextField(NAME_SIZE);
	  private JTextField jtfID = new JTextField(ID_SIZE);
	  private JTextField jtfDepartment = new JTextField(DEPARTMENT_SIZE);
	  private JTextField jtfTel = new JTextField(TEL_SIZE);
	  private JTextField[] jtfSchedule = new JTextField[10];

	  // Buttons
	  private JButton jbtAdd = new JButton("Add");
	  private JButton jbtFirst = new JButton("First");
	  private JButton jbtNext = new JButton("Next");
	  private JButton jbtPrevious = new JButton("Previous");
	  private JButton jbtLast = new JButton("Last");
	  
	  private JButton jbtDelete = new JButton("Delete");
	  private JButton jbtClear = new JButton("Clear");
	  private JButton jbtSearch = new JButton("Search");
	  private JButton jbtSave = new JButton("Save");  
	  private JButton[] jbtDays = new JButton[50];
	  
	  private List b1 = new List(4, false);
	  
	  private ArrayList <Student> address = new ArrayList<>(); // ArrayList --------------
	  private lectureDB lec_DB;

	  public StudentDB(lectureDB lec_DB) throws IOException {

		  f = new File("StudentInfo.dat");
			
			//파일이 존재할때만 로드
			if(f.exists())
	    		load();
		
		this.lec_DB = lec_DB;
		
	    JPanel p1 = new JPanel();
	    p1.setLayout(new GridLayout(6,1));
	    p1.add(new JLabel("  Name"));
	    p1.add(new JLabel("  ID"));
	    p1.add(new JLabel("  Department   "));
	    p1.add(new JLabel("  Tel"));
	    p1.add(new JLabel("  Schedule"));
	    p1.add(new JLabel(""));
	    
	    for(int i=0; i<50; i++){
			jbtDays[i] = new JButton(" ");
			if(i<10)
				jtfSchedule[i] = new JTextField(SCHEDULE_SIZE);
		}
	    setTimetable();

	    JPanel p2 = new JPanel();
	    p2.setLayout(new GridLayout(6,1));
	    JPanel Schedule = new JPanel(new GridLayout(1,5));
	    for(int i=0; i<5; i++){
	    	Schedule.add(jtfSchedule[i]);
		}
	    JPanel Schedule2 = new JPanel(new GridLayout(1,5));
	    for(int i=5; i<10; i++){
	    	Schedule2.add(jtfSchedule[i]);
		}
	    p2.add(jtfName);
	    p2.add(jtfID);
	    p2.add(jtfDepartment);
	    p2.add(jtfTel);
	    p2.add(Schedule);   
	    p2.add(Schedule2);   

	    JPanel jpAddress = new JPanel(new BorderLayout());
	    jpAddress.add(p1,BorderLayout.WEST);
	    jpAddress.add(p2,BorderLayout.CENTER);


	    JPanel jpSchedule= new JPanel(new BorderLayout());
	    JPanel jpSchedule1= new JPanel(new GridLayout(11,1));
	    jpSchedule1.add(new JLabel("", JLabel.CENTER));
	    for(int i=0; i<10; i++)
	    {
	    	jpSchedule1.add(new JLabel(" "+i+" ", JLabel.CENTER));
	    }   
	    JPanel jpSchedule2= new JPanel(new GridLayout(11,5));
	    jpSchedule2.add(new JLabel("MON", JLabel.CENTER));
	    jpSchedule2.add(new JLabel("TUE", JLabel.CENTER));
	    jpSchedule2.add(new JLabel("WEN", JLabel.CENTER));
	    jpSchedule2.add(new JLabel("THU", JLabel.CENTER));
	    jpSchedule2.add(new JLabel("FRI", JLabel.CENTER));
		for(int i=0; i<50; i++){
			jpSchedule2.add(jbtDays[i]);
		}	  
		jpSchedule.add(jpSchedule1,BorderLayout.WEST);
		jpSchedule.add(jpSchedule2,BorderLayout.CENTER);
	    
	    JPanel jpButton1 = new JPanel();
	    jpButton1.add(jbtAdd);
	    jpButton1.add(jbtFirst);
	    jpButton1.add(jbtNext);
	    jpButton1.add(jbtPrevious);
	    jpButton1.add(jbtLast);
	    
	    JPanel jpButton2 = new JPanel();
	    jpButton2.add(jbtDelete);
	    jpButton2.add(jbtClear);
	    jpButton2.add(jbtSearch);
	    jpButton2.add(jbtSave);
	    
	    JPanel jpButton = new JPanel(new GridLayout(2,1));
	    jpButton.add(jpButton1);
	    jpButton.add(jpButton2);
	    
	   
	    JPanel b0 = new JPanel(new BorderLayout());
	    
	    JPanel b2 = new JPanel(new BorderLayout());
	    b2.add(jpAddress, BorderLayout.NORTH);
	    b2.add(jpSchedule, BorderLayout.CENTER);
	    b2.add(jpButton, BorderLayout.SOUTH);
	    
	    b0.add(b1, BorderLayout.WEST);
	    b0.add(b2, BorderLayout.CENTER);
	    add(b0);
	    
	    for(int i=0; i<address.size(); i++){
	    	b1.add(address.get(i).getName());
		}	
	    
	    b1.addActionListener(new ActionListener() {	//리스트를 더블클릭했을 경우
	          public void actionPerformed(ActionEvent e) {
		          	try {
		          		int i=0;
		          		while(i < address.size()){
		          			if(address.get(i).getName().equals(b1.getSelectedItem())){
		          				currentPosition=i;
		          				readAddress(i);
		          				break;
		          			}
		          			i++;
		          			}
		          		setTimetable();
		          		}	
		          	catch (IOException ex) {
		          		ex.printStackTrace();
		              }
		          }
		        });
	    jbtAdd.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e){	//Add 버튼을 누르면 ArrayList에 쓰고 ArrayList에서 파일에 쓴다 --------------
	      	  try {
	      		  if(jtfName.getText().equals("") || jtfID.getText().equals("") || jtfDepartment.getText().equals("") 
	      				  || jtfTel.getText().equals("")) return;
	      	  writeAddress(address.size());
	      	  save();
	      	  currentPosition=address.size()-1;
	      	  setTimetable();
	      	  b1.add(address.get(address.size()-1).getName());
	      	  b1.select(address.size()-1);
	      	  }
	            catch (IOException ex) {
	              ex.printStackTrace();
	            }
	        }
	      });
	      jbtFirst.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	          try {
	            if (address.size() > 0){
	          	readAddress(0);
	          	currentPosition = 0;
	          	setTimetable();
	          	b1.select(currentPosition);
	            }
	          }
	          catch (IOException ex) {
	            ex.printStackTrace();
	          }
	        }
	      });
	      jbtNext.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	          try {
	            if (currentPosition < address.size()-1){
	              currentPosition++;
	              readAddress(currentPosition);
	            }
	            setTimetable();
	            b1.select(currentPosition);
	          }
	          catch (IOException ex) {
	            ex.printStackTrace();
	          }
	        }
	      });
	      jbtPrevious.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	          try {
	            if (currentPosition > 0){
	              currentPosition--;
	              readAddress(currentPosition);
	            }
	            else if(address.size() > 0){
	              readAddress(0);
	              currentPosition = 0;
	            }
	            b1.select(currentPosition);
	            setTimetable();
	          }
	          catch (IOException ex) {
	            ex.printStackTrace();
	          }
	        }
	      });
	      jbtLast.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	          try {
	            int lastPosition = address.size()-1;
	            if (lastPosition > 0)
	              readAddress(lastPosition);
	            currentPosition = lastPosition;
	            setTimetable();
	            b1.select(currentPosition);
	          }
	          catch (IOException ex) {
	            ex.printStackTrace();
	          }
	        }
	      });
	      
	      jbtDelete.addActionListener(new ActionListener() {
	          public void actionPerformed(ActionEvent e) {
	          	try {
	          		if(address.size() > 0
	          			&& jtfName.getText().equals(address.get(currentPosition).getName()) 
	          			&& jtfID.getText().equals(address.get(currentPosition).getID()) 
	          			&& jtfDepartment.getText().equals(address.get(currentPosition).getDepartment()) 
	          			&& jtfTel.getText().equals(address.get(currentPosition).getTel())){
	          				
	          			address.remove(currentPosition);
	          			b1.delItem(currentPosition);
	         				save();
	          			
	         				if(currentPosition>0){
	         					currentPosition--;
	         					readAddress(currentPosition);
	         				}
	         				else{
	         					jtfName.setText("");
	         					jtfID.setText("");
	         					jtfDepartment.setText("");
	         					jtfTel.setText("");
	         				}
	        	        b1.select(currentPosition);
	          		}
	          	}
	          	catch (IOException ex) {
	                ex.printStackTrace();
	              }
	          }
	        });
	      jbtClear.addActionListener(new ActionListener() {
	          public void actionPerformed(ActionEvent e) {
	          		jtfName.setText("");
	          		jtfID.setText("");
	          		jtfDepartment.setText("");
	          		jtfTel.setText("");
	          		for(int i=0; i<10; i++){
	          			jtfSchedule[i].setText(""); 
	          		}
	            
	          }
	        });
	      jbtSearch.addActionListener(new ActionListener() {
	          public void actionPerformed(ActionEvent e) {
	          	try {
	          		if(jtfName.getText().equals("") && jtfID.getText().equals("") && jtfDepartment.getText().equals("") 
	          				&& jtfTel.getText().equals("")) return;
	          		
	          		int i=0;
	          		while(i < address.size()){
	          			if(address.get(i).getName().equals(jtfName.getText()) || jtfName.getText().equals(""))
	          			if(address.get(i).getID().equals(jtfID.getText()) || jtfID.getText().equals(""))
	          			if(address.get(i).getDepartment().equals(jtfDepartment.getText()) || jtfDepartment.getText().equals(""))
	          			if(address.get(i).getTel().equals(jtfTel.getText()) || jtfTel.getText().equals("")){
	          				currentPosition=i;
	          				readAddress(i);
	          				break;
	          			}
	          			i++;
	          		}	
	          		setTimetable();
	          	}
	          	catch (IOException ex) {
	          		ex.printStackTrace();
	              }
	          }
	        });
	      jbtSave.addActionListener(new ActionListener() {
	          public void actionPerformed(ActionEvent e) {
	            try {
	              writeAddress(currentPosition);
	              save();
	              setTimetable();
	            }
	            catch (IOException ex) {
	              ex.printStackTrace();
	            }
	          }
	        });

	      // Display the first record if exists
	      try {
	        if (address.size() > 0) readAddress(0);
	      }
	      catch (IOException ex) {
	        ex.printStackTrace();
	      }
	    }
	  
		public void writeAddress(int position) {	//jtf -> ArrayList --------------
			if(position> address.size()-1){
				address.add(new Student());
				address.get(address.size()-1).setName(jtfName.getText());
				address.get(address.size()-1).setID(jtfID.getText());
				address.get(address.size()-1).setDepartment(jtfDepartment.getText());
				address.get(address.size()-1).setTel(jtfTel.getText());
				for(int i=0; i<10; i++){
					if(jtfSchedule[i].getText().equals(""))
						address.get(position).setSchedule(0,i);
					else
						address.get(position).setSchedule(lec_DB.findLecture(jtfSchedule[i].getText()),i);
				}
			}
			else{
				address.get(position).setName(jtfName.getText());
				address.get(position).setID(jtfID.getText());
				address.get(position).setDepartment(jtfDepartment.getText());
				address.get(position).setTel(jtfTel.getText());
				for(int i=0; i<10; i++){
					if(jtfSchedule[i].getText().equals(""))
						address.get(position).setSchedule(0,i);
					else
						address.get(position).setSchedule(lec_DB.findLecture(jtfSchedule[i].getText()),i);
				}
			}
		}
		public void readAddress(int position) throws IOException {	//ArrayList -> jtf --------------
		    jtfName.setText(address.get(position).getName());
		    jtfID.setText(address.get(position).getID());
		    jtfDepartment.setText(address.get(position).getDepartment());
		    jtfTel.setText(address.get(position).getTel());
		    for(int i=0; i<10; i++){
		    	if(address.get(position).getSchedule(i) != 0){
		    		jtfSchedule[i].setText(lec_DB.findLecture(address.get(position).getSchedule(i)).getName()); 
		    	}
		    	else
		    		jtfSchedule[i].setText(""); 
		    	
			}
		   
		}
		
		
		public void load() throws IOException {
			Scanner input = new Scanner(f);
			int i=0;
			while(input.hasNext()){
				address.add(new Student());
				address.get(i++).read(input);
			}
			input.close();
		}

		public void save() throws IOException {
		  PrintWriter output = new PrintWriter(f);
		  for(int i=0; i<address.size(); i++){
			  address.get(i).write(output);
		  }
		  output.close();
		}
		
		public void setTimetable(){	// 학생의 시간표를 표시
			lecture li;
			int[][] time;
			int Schedule;
			for(int i=0; i<50; i++){
				jbtDays[i].setText(" ");
			}
			for(int i=0; i<10; i++ ){
				Schedule = address.get(currentPosition).getSchedule(i);
				li = lec_DB.findLecture(Schedule);
				if(Schedule != 0){
					time =  li.getTime();
					if(time[0][0]<0 || (Integer.parseInt(li.getID()) != Schedule)){ 
						return;
					}
					
					for(int j=0; j<time[0][2]-time[0][1]+1; j++)
						jbtDays[time[0][0]+ (time[0][1] + j)*5].setText(li.getName());
					
					if(time[1][0]>-1 && time[1][0]<5){
						for(int j=0; j<time[1][2]-time[1][1]+1; j++)
							jbtDays[time[1][0]+ (time[1][1] + j)*5].setText(li.getName());
					}
				}
				
				
			}
			
		}
		
		public int student_Num(String lectureID){	//강의를 듣는 학생의 수
			int num=0;
			if(lectureID == null) return 0;
			for(int i=0; i<address.size(); i++){
				for(int j=0; address.get(i).getSchedule(j)!=0; j++){
					if(lectureID.equals(address.get(i).getSchedule(j)+""))
						num++;
				}
			}
			return num;
		}
		public int today_StuNum(int day){	//오늘 강의가 있는 학생의 수
			int num=0,time[][];
			for(int i=0; i<address.size(); i++){
				for(int j=0; address.get(i).getSchedule(j)!=0; j++){
					time = lec_DB.findLecture(address.get(i).getSchedule(j)).getTime();
					if((time[0][0] == day) || (time[1][0] == day))
					{
						num++;
						break;
					}
				}
			}
			return num;
		}
		
		public int goHome_StuNum(int day, int time){	// 더 이상 강의가 없어 집에 갔을듯한 학생수 
			int num=0,Schedule[][];
			for(int i=0; i<address.size(); i++){
				for(int j=0; address.get(i).getSchedule(j)!=0; j++){
					Schedule = lec_DB.findLecture(address.get(i).getSchedule(j)).getTime();
					if(Schedule[0][0] == day && (Integer.parseInt(lec_DB.findLecture(address.get(i).getSchedule(j)).getEndtime())+1 < time)){
						num++;
						break;
					}
					else if(Schedule[1][0] == day && (Integer.parseInt(lec_DB.findLecture(address.get(i).getSchedule(j)).getEndtime2())+1 < time)){
						num++;
						break;
					}
				}
			}
			return num;
		}
		
}
