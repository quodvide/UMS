import java.io.*;
import java.util.*;

public class lecture
{
	 private String name;
	 private String ID;
	 private String Professor;
	 private String Department;
	 private String classroom;
	 int[][] Time = new int[2][]; 
	
	lecture(){
		Time[0] = new int[3]; 
		Time[1] = new int[3]; 
		Time[1][0] = 5;
	}
	
	public String getName(){return name;}
	public String getID(){return ID;}
	
	public String getProfessor(){return Professor;}
	public String getDepartment(){return Department;}
	public String getclassroom(){return classroom;}
	public int[][] getTime(){return Time;}
	public String getDayofweek(){if(Time[0][0]<0)return ""; return String.valueOf(Time[0][0]);}
	public String getStarttime(){if(Time[0][1]<0)return ""; return String.valueOf(Time[0][1]);}
	public String getEndtime(){if(Time[0][2]<0)return ""; return String.valueOf(Time[0][2]);}
	public String getDayofweek2(){if(Time[1][0]<0)return ""; return String.valueOf(Time[1][0]);}
	public String getStarttime2(){if(Time[1][1]<0)return ""; return String.valueOf(Time[1][1]);}
	public String getEndtime2(){if(Time[1][2]<0)return ""; return String.valueOf(Time[1][2]);}
	
	public void setName(String name){this.name = name;}
	public void setID(String ID){this.ID = ID;}
	public void setProfessor(String Professor){this.Professor = Professor;}
	public void setDepartment(String Department){this.Department = Department;}
	public void setclassroom(String classroom){this.classroom = classroom;}	
	public void setDayofweek(String Dayofweek){if(Dayofweek.equals("")){this.Time[0][0] = 5;} else this.Time[0][0] = Integer.parseInt(Dayofweek);}
	public void setStarttime(String Starttime){if(Starttime.equals("")){this.Time[0][1] = 0;} else this.Time[0][1] = Integer.parseInt(Starttime);}
	public void setEndtime(String Endtime){if(Endtime.equals("")){this.Time[0][2] = 0;}else this.Time[0][2] = Integer.parseInt(Endtime);}
	public void setDayofweek2(String Dayofweek){if(Dayofweek.equals("")){this.Time[1][0] = 5;}else this.Time[1][0] = Integer.parseInt(Dayofweek);}
	public void setStarttime2(String Starttime){if(Starttime.equals("")){this.Time[1][1] = 0;}else this.Time[1][1] = Integer.parseInt(Starttime);}
	public void setEndtime2(String Endtime){if(Endtime.equals("")){this.Time[1][2] = 0;} else this.Time[1][2] = Integer.parseInt(Endtime);}
	
	
	public boolean IDCheck(String a){ if(a==ID)return true; return false; }
	 
	public void write(PrintWriter output){
		 output.print(name + ':');
		 output.print(ID + ':');
		 output.print(Professor + ':');
		 output.print(Department + ':');
		 output.print(classroom + ':');
		 for(int i=0; i<2; i++){
			 for(int j=0; j<3; j++){
				 output.print(Time[i][j]);
				 output.print(':');
			 }	
		 }
		 output.flush();
	 }
	 
	 public void read(Scanner input){
		 input.useDelimiter(":");
			 name = input.next();			 
			 ID = input.next();
			 Professor = input.next();
			 Department = input.next();
			 classroom = input.next();
			 for(int i=0; i<2; i++){
				 for(int j=0; j<3; j++){
					 Time[i][j] = Integer.parseInt(input.next());
				 }	
			 }
	 }
}
