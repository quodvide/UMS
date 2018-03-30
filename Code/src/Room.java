import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Room{
	 private String name;
	 private int capacity;
	 private int building_num;
	 private int locationX;
	 private int locationY;
	 
	
	 
	 Room(){ 
	 }
	 
	 public String getName(){return name;}
	 public int getcapacity(){return capacity;}
	 public int getbuilding_num(){return building_num;}
	 public int getlocationX(){return locationX;}
	 public int getlocationY(){return locationY;}
	 
	 public void setName(String name){this.name = name;}
	 public void setcapacity(int capacity){this.capacity = capacity;}
	 public void setbuilding_num(int building_num){this.building_num = building_num;}
	 public void setlocation(int x, int y){this.locationX = x; this.locationY = y;}
}
