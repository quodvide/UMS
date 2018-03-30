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


public class lectureDB extends JFrame  {

	  final static int NAME_SIZE = 30;
	  final static int ID_SIZE = 30;
	  final static int PROFESSOR_SIZE = 30;
	  final static int DEPARTMENT_SIZE = 30;
	  final static int classroom_SIZE = 30;
	  final static int DAYOFWEEK_SIZE = 2;
	  final static int STARTTIME_SIZE = 2;
	  final static int ENDTIME_SIZE = 2;
	  final static int DAYOFWEEK2_SIZE = 2;
	  final static int STARTTIME2_SIZE = 2;
	  final static int ENDTIME2_SIZE = 2;
	  final static int RECORD_SIZE =
	    (NAME_SIZE + ID_SIZE + DEPARTMENT_SIZE + classroom_SIZE);

	  private int currentPosition;	// ArrayList�� ���� ��ġ
	  private File f;

	  // Text fields
	  private JTextField jtfName = new JTextField(NAME_SIZE);
	  private JTextField jtfID = new JTextField(ID_SIZE);
	  private JTextField jtfProfessor = new JTextField(PROFESSOR_SIZE);
	  private JTextField jtfDepartment = new JTextField(DEPARTMENT_SIZE);
	  private JTextField jtfclassroom = new JTextField(classroom_SIZE);
	  private JTextField jtfDayofweek = new JTextField(DAYOFWEEK_SIZE);
	  private JTextField jtfStarttime = new JTextField(STARTTIME_SIZE);
	  private JTextField jtfEndtime = new JTextField(ENDTIME_SIZE);
	  private JTextField jtfDayofweek2 = new JTextField(DAYOFWEEK2_SIZE);
	  private JTextField jtfStarttime2 = new JTextField(STARTTIME2_SIZE);
	  private JTextField jtfEndtime2 = new JTextField(ENDTIME2_SIZE);
	  
	  List b1 = new List(4, false);
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
	  
	  private ArrayList <lecture> address = new ArrayList<>(); 
	  
	  lectureDB() throws IOException {

		  f = new File("Lecture.dat");
			
			//������ �����Ҷ��� �ε�
			if(f.exists())
	    		load();
			
	    JPanel p1 = new JPanel();
	    p1.setLayout(new GridLayout(7,1));
	    p1.add(new JLabel("Name"));
	    p1.add(new JLabel("ID"));
	    p1.add(new JLabel("Professor"));
	    p1.add(new JLabel("Department"));
	    p1.add(new JLabel("classroom"));	    
	    p1.add(new JLabel("Dayofweek"));	
	    p1.add(new JLabel("Dayofweek2"));

	    JPanel p2 = new JPanel();
	    p2.setLayout(new GridLayout(7,1));
	    p2.add(jtfName);
	    p2.add(jtfID);
	    p2.add(jtfProfessor);
	    p2.add(jtfDepartment);
	    p2.add(jtfclassroom);   
	    
	    JPanel time = new JPanel(new GridLayout(1,5));
	    time.add(jtfDayofweek);
	    time.add(new JLabel("Starttime", JLabel.CENTER));
	    time.add(jtfStarttime);
	    time.add(new JLabel("Endtime", JLabel.CENTER));
	    time.add(jtfEndtime);
	    JPanel time2 = new JPanel(new GridLayout(1,5));
	    time2.add(jtfDayofweek2);
	    time2.add(new JLabel("Starttime2", JLabel.CENTER));
	    time2.add(jtfStarttime2);
	    time2.add(new JLabel("Endtime2", JLabel.CENTER));
	    time2.add(jtfEndtime2);
	    
	    p2.add(time);
	    p2.add(time2);

	    JPanel jpAddress = new JPanel(new BorderLayout());
	    jpAddress.add(p1,BorderLayout.WEST);
	    jpAddress.add(p2,BorderLayout.CENTER);

	    
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
	    b2.add(jpButton, BorderLayout.CENTER);
	    
	    b0.add(b1, BorderLayout.WEST);
	    b0.add(b2, BorderLayout.CENTER);
	    add(b0);
	    
	    for(int i=0; i<address.size(); i++){
	    	b1.add(address.get(i).getName());
		}	
	    b1.addActionListener(new ActionListener() {	//����Ʈ�� ����Ŭ������ ���
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
		          		}	
		          	catch (IOException ex) {
		          		ex.printStackTrace();
		              }
		          }
		        });
	    
	      
	    jbtAdd.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e){	//Add ��ư�� ������ ArrayList�� ���� ArrayList���� ���Ͽ� ���� --------------
	      	  try {
	      		  if(jtfName.getText().equals("") || jtfID.getText().equals("") || jtfProfessor.getText().equals("") ||
	      				  jtfDepartment.getText().equals("") || jtfclassroom.getText().equals("") ) return;
	      	  writeAddress(address.size());
	      	  save();
	      	  currentPosition=address.size()-1;
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
	              b1.select(currentPosition);
	            }
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
	          			&& jtfProfessor.getText().equals(address.get(currentPosition).getProfessor()) 
	          			&& jtfDepartment.getText().equals(address.get(currentPosition).getDepartment()) 
	          			&& jtfclassroom.getText().equals(address.get(currentPosition).getclassroom())
	          			&& jtfDayofweek.getText().equals(address.get(currentPosition).getDayofweek())
	          			&& jtfStarttime.getText().equals(address.get(currentPosition).getStarttime())
	          			&& jtfEndtime.getText().equals(address.get(currentPosition).getEndtime())
	          			&& jtfDayofweek2.getText().equals(address.get(currentPosition).getDayofweek2())
	          			&& jtfStarttime2.getText().equals(address.get(currentPosition).getStarttime2())
	          			&& jtfEndtime2.getText().equals(address.get(currentPosition).getEndtime2())){
	          				
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
	         					jtfclassroom.setText("");
	         					jtfDayofweek.setText("");
	         					jtfStarttime.setText("");
	         					jtfEndtime.setText("");
	         					jtfDayofweek2.setText("");
	         					jtfStarttime2.setText("");
	         					jtfEndtime2.setText("");
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
	          		jtfProfessor.setText("");
	          		jtfDepartment.setText("");
	          		jtfclassroom.setText("");
	          		jtfDayofweek.setText("");
 					jtfStarttime.setText("");
 					jtfEndtime.setText("");
 					jtfDayofweek2.setText("");
 					jtfStarttime2.setText("");
 					jtfEndtime2.setText("");
	            
	          }
	        });
	      jbtSearch.addActionListener(new ActionListener() {
	          public void actionPerformed(ActionEvent e) {
	          	try {
	          		if(jtfName.getText().equals("") && jtfID.getText().equals("") && jtfDepartment.getText().equals("") &&
	          			jtfDepartment.getText().equals("") && jtfclassroom.getText().equals("") && jtfDayofweek.getText().equals("") && 
		      				jtfStarttime.getText().equals("") && jtfEndtime.getText().equals("") && jtfDayofweek2.getText().equals("") && 
		      					jtfStarttime2.getText().equals("") && jtfEndtime2.getText().equals("")) return;
	          		
	          		int i=0;
	          		while(i < address.size()){
	          			if(address.get(i).getName().equals(jtfName.getText()) || jtfName.getText().equals(""))
	          			if(address.get(i).getID().equals(jtfID.getText()) || jtfID.getText().equals(""))
	          			if(address.get(i).getProfessor().equals(jtfProfessor.getText()) || jtfProfessor.getText().equals(""))
	          			if(address.get(i).getDepartment().equals(jtfDepartment.getText()) || jtfDepartment.getText().equals(""))
	          			if(address.get(i).getclassroom().equals(jtfclassroom.getText()) || jtfclassroom.getText().equals(""))
	          			if(address.get(i).getDayofweek().equals(jtfDayofweek.getText()) || jtfDayofweek.getText().equals(""))
	          			if(address.get(i).getStarttime().equals(jtfStarttime.getText()) || jtfStarttime.getText().equals(""))
	          			if(address.get(i).getEndtime().equals(jtfEndtime.getText()) || jtfEndtime.getText().equals(""))
	          			if(address.get(i).getDayofweek2().equals(jtfDayofweek2.getText()) || jtfDayofweek2.getText().equals(""))
	    	          	if(address.get(i).getStarttime2().equals(jtfStarttime2.getText()) || jtfStarttime2.getText().equals(""))
	    	          	if(address.get(i).getEndtime2().equals(jtfEndtime2.getText()) || jtfEndtime2.getText().equals("")){
	          				currentPosition=i;
	          				readAddress(i);
	          				break;
	          			}
	          			i++;
	          		}	
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
	  
		public void writeAddress(int index) {	//jtf -> ArrayList --------------
			if(index > address.size()-1){
				address.add(new lecture());
				address.get(address.size()-1).setName(jtfName.getText());
				address.get(address.size()-1).setID(jtfID.getText());
				address.get(address.size()-1).setProfessor(jtfProfessor.getText());
				address.get(address.size()-1).setDepartment(jtfDepartment.getText());
				address.get(address.size()-1).setclassroom(jtfclassroom.getText());
				address.get(address.size()-1).setDayofweek(""+dayofweek_Index(jtfDayofweek.getText()));
				address.get(address.size()-1).setStarttime(jtfStarttime.getText());
				address.get(address.size()-1).setEndtime(jtfEndtime.getText());
				address.get(address.size()-1).setDayofweek2(""+dayofweek_Index(jtfDayofweek2.getText()));
				address.get(address.size()-1).setStarttime2(jtfStarttime2.getText());
				address.get(address.size()-1).setEndtime2(jtfEndtime2.getText());
			}
			else{
				address.get(index).setName(jtfName.getText());
				address.get(index).setID(jtfID.getText());
				address.get(index).setProfessor(jtfProfessor.getText());
				address.get(index).setDepartment(jtfDepartment.getText());
				address.get(index).setclassroom(jtfclassroom.getText());
				address.get(index).setDayofweek(""+dayofweek_Index(jtfDayofweek.getText()));
				address.get(index).setStarttime(jtfStarttime.getText());
				address.get(index).setEndtime(jtfEndtime.getText());
				address.get(index).setDayofweek2(""+dayofweek_Index(jtfDayofweek2.getText()));
				address.get(index).setStarttime2(jtfStarttime2.getText());
				address.get(index).setEndtime2(jtfEndtime2.getText());
			}
		}

		public void readAddress(int position) throws IOException {	//ArrayList -> jtf --------------
		    jtfName.setText(address.get(position).getName());
		    jtfID.setText(address.get(position).getID());
		    jtfProfessor.setText(address.get(position).getProfessor());
		    jtfDepartment.setText(address.get(position).getDepartment());
		    jtfclassroom.setText(address.get(position).getclassroom());
		    jtfDayofweek.setText(""+dayofweek_Index(Integer.parseInt(address.get(position).getDayofweek())));
		    jtfStarttime.setText(address.get(position).getStarttime());
		    jtfEndtime.setText(address.get(position).getEndtime());
		    if(address.get(position).getDayofweek2().equals("5")){
		    	jtfDayofweek2.setText("");
		    	jtfStarttime2.setText("");
		    	jtfEndtime2.setText("");
		    }
		    else{
		    	jtfDayofweek2.setText(dayofweek_Index(Integer.parseInt(address.get(position).getDayofweek2())));
		    	jtfStarttime2.setText(address.get(position).getStarttime2());
		    	jtfEndtime2.setText(address.get(position).getEndtime2());
		    }
		}
		
		
		public void load() throws IOException {
			Scanner input = new Scanner(f);
			int i=0;
			while(input.hasNext()){
				address.add(new lecture());
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
	
	public lecture findLecture(int i){	// ������ ID�� ���������� ã�´�
		for(int k=0; k<address.size(); k++){
			if(Integer.parseInt(address.get(k).getID()) == i)
				return address.get(k);
		}
		return address.get(0);
	}
	public int findLecture(String name){	// ���� �̸����� ����IDfmf ã�´�
		for(int k=0; k<address.size(); k++){
			if(address.get(k).getName().equals(name)){
				return Integer.valueOf(address.get(k).getID());
			}
		}
		return Integer.valueOf(address.get(0).getID());
	}
	
	public String nowlectureID(String classroom, int yo, int time){	// �� ���ǽǿ� �� �ð��� �ϴ� ������ ã�´�.
		int start,start2,end,end2;
		for(int i=0; i<address.size(); i++){
			start = Integer.parseInt(address.get(i).getStarttime());
			end = Integer.parseInt(address.get(i).getEndtime());
			start2 = Integer.parseInt(address.get(i).getStarttime2());
			end2 = Integer.parseInt(address.get(i).getEndtime2());

			if(address.get(i).getclassroom().equals(classroom)){
				if(((address.get(i).getDayofweek().equals(yo+"")) && (start <= time) && (time <= end)) ||
					((address.get(i).getDayofweek2().equals(yo+"")) && (start2 <= time) && (time <= end2)))
						return address.get(i).getID();
					}
		}
		return null;
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
}
