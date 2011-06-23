package Galacticcore;

public class fleet {
private int distance, fleetcnt, destination;
private String owner = "neutral";
private boolean remove = false;

//getters

public int getDest(){
	return destination;
}

public int getDist(){
	return distance;
}

public int getFleetCnt(){
	return fleetcnt;
}

public String getOwner(){
	return owner;
}

public boolean getRemove(){
	return remove;
}

//setters

public void setDestination(int value){
	destination = value;
}

public void setDist(int value){
	distance = value;
}

public void setFleetCnt(int value){
	fleetcnt = value;
}

public void setOwner(String name){
	owner = name;
}

public void setRemove(boolean value){
	remove = value;
}
}
