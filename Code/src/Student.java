import java.util.ArrayList;
import java.io.*;
import java.util.Scanner;

public class Student {
	 private String name;
	 private String ID;
	 private String Department;
	 private String Tel;
	 private int[] Schedule = new int[10];
	 
	 public String getName(){return name;}
	 public String getID(){return ID;}
	 public String getDepartment(){return Department;}
	 public String getTel(){return Tel;}
	 public int getSchedule(int index){return Schedule[index];}
	 
	 public void setName(String name){this.name = name;}
	 public void setID(String ID){this.ID = ID;}
	 public void setDepartment(String Department){this.Department = Department;}
	 public void setTel(String Tel){this.Tel = Tel;}
	 public void setSchedule(int Schedule, int index){this.Schedule[index] = Schedule;}
	
	Student(){
		for(int i=0; i<10; i++)
		{
			Schedule[i]=0;
		}
	}

	
	 public void write(PrintWriter output){
		 output.print(name + ':');
		 output.print(ID + ':');
		 output.print(Department + ':');
		 output.print(Tel + ':');
		 for(int i=0; i<10; i++){
			 output.print(Schedule[i]);
			 output.print(':');
		 }
		 output.flush();
	 }
	 
	 public void read(Scanner input){
		 input.useDelimiter(":");
			 name = input.next();			 
			 ID = input.next();
			 Department = input.next();
			 Tel = input.next();
			 for(int i=0; i<10; i++){
				 Schedule[i] = Integer.parseInt(input.next());
			 }
			
	 }
}