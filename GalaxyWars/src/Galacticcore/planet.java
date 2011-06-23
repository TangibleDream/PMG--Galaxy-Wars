package Galacticcore;

//import javax.swing.JFrame;
//import javax.swing.JOptionPane;

// X is vertical, Y is horizontal
public class planet {
private int coord_x = 10
		  , coord_y = 10
		  , shipprd = 0
		  , shipcnt = 0;
private String owner = "neutral world";
//private JFrame frame;

// getters
public int getCoordX(){
	return coord_x;
}
public int getCoordY(){
	return coord_y;
}

public int getShipPrd(){
	return shipprd;
}
public int getShipCnt(){
	return shipcnt;
}
public String getPlanetOwner(){
	return owner;
}
//setters
public void setOwner(String name){
	owner = name;
}
public void setCoord(int x, int y){
	coord_x = x;
	coord_y = y;
}
public void setGridNum(int value){
	//JOptionPane.showMessageDialog(frame, "value =" + value);
	int val_x, val_y;
	if (value%20==0){
		val_y = 20;
	}
	else {val_y = value%20;}
	val_x = ((value - 1)/20)+1;
	setCoord(val_x,val_y);
}
public void setShipPrd(int value){
	shipprd = value;
}

public void setShipCnt(int value){
	shipcnt = value;
}
//methods
public int getGridNum(){
	int c;
	c = getCoordY()+(20*(getCoordX()-1));
	return c;
}
public boolean isOccupied(int grid) {
	// TODO Auto-generated method stub
	boolean cond = false;
	if (getGridNum() == grid){
		cond = true;
	}
	return cond;
}
}