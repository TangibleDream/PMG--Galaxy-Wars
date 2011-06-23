package Galacticcore;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import javax.swing.*;

public class gwview {

//	private planet planets[]; //array
	//private ArrayList<fleet> flroster;// Research array list
	
	private JFrame frame;
	private JPanel panel, gridpanel;
//	private fleet newFleet;
	private Container contentPane;
	private JButton plbutton, ebutton, fbutton;
	private boolean eot = true;
	private int endroute = -1;
	
	private int calcDist(int hw, int dest) {
		// TODO Auto-generated method stub
		int distance = 0;
		planet departureWorld = new planet();
		planet arrivalWorld = new planet();
		//JOptionPane.showMessageDialog(frame, "HW =" + hw + ", Dest =" + dest); //debug
		departureWorld.setGridNum(hw);
		arrivalWorld.setGridNum(dest);
		//subtraction
		//JOptionPane.showMessageDialog(frame, "Departure from = " + departureWorld.getCoordX() + "," + departureWorld.getCoordY() +
		//									"\nArrival from =" + arrivalWorld.getCoordX() + "," + arrivalWorld.getCoordY());
		int x = departureWorld.getCoordX() - arrivalWorld.getCoordX();
		int y = departureWorld.getCoordY() - arrivalWorld.getCoordY();
		//JOptionPane.showMessageDialog(frame, "x =" + x + ", y =" + y); //debug
		//absolute value
		//JOptionPane.showMessageDialog(frame, "x =" + Math.abs(x) + ", y =" + Math.abs(y)); //debug
		if (Math.abs(x) > Math.abs(y)){
			distance = Math.abs(x);
		}
		else {
			distance = Math.abs(y);
		}
		//maximum number
		
		return distance;
	}
	public void galaxyform()
	{
		frame = new JFrame("Galaxy Wars");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(260, 273); //260,303
        frame.setResizable(false);
        panel = new JPanel();
    	contentPane = (JPanel)frame.getContentPane();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        gridpanel = new JPanel();
        gridpanel.setLayout(new GridLayout(20,20));
        final planet planets[] = new planet[40];
        for (int i = 0; i < 40; i++){
        	planets[i] = new planet();
        }
        /*Debug only*/
        //planets[10].setGridNum(55); //Debug
        int num;
		long now;
		Random c, pr;
		String flag = "ok";
		
		for(int i = 0; i < 40; i ++){
			now = new Date().getTime();
			//position planets
			c = new Random(now);
			// It is highly likely that 2 or more of the 40 planets will get 
			// scattered to the same coordinates, we need to check for that possibility.
			num = c.nextInt(400);
			for (int pl = 0; pl < i; pl ++)
			{
				//JOptionPane.showMessageDialog(frame, "pl =" + pl + ", num =" + num);
				if (planets[pl].isOccupied(num) == true){
					flag = "nokay";
				}
			}
			if (flag == "ok"){
				//JOptionPane.showMessageDialog(frame, "i =" + i + ", num =" + num);
				planets[i].setGridNum(num);
				}
			else{
				i = i - 1;
				flag = "ok";
			}				
			//set production
			now = new Date().getTime();
			pr = new Random(now);
			planets[i].setShipPrd(pr.nextInt(10) + 1);
			}
		//scatter players
		now = new Date().getTime();
		num = new Random(now).nextInt(39);
		/*for (int i = 1 ; i < 2; i ++){	
		}*/
		final ArrayList<fleet> flroster = new ArrayList<fleet>();
		final JButton pbutton[] = new JButton[400];
		for (int i = 0; i < 400; i ++){
			pbutton[i] = new JButton();
		}
		for (int i = 0; i < 400; i ++){
			gridpanel.add(pbutton[i]);
			pbutton[i].setBackground(Color.black);
			pbutton[i].setBorderPainted(false);
		}
		for (int i = 0; i < 40; i ++){
			final int fi = planets[i].getGridNum();
			pbutton[planets[i].getGridNum()].addActionListener(new ActionListener(){ //add listener to only the 40 planets
	        	public void actionPerformed(ActionEvent e)
	            {
	        		int fight;
	        		String shipStr, planetStr;
	        		int hw = getPlanetNum(fi); 
	            //blue check
	        		if (pbutton[fi].getBackground() == Color.blue){
	        	//Wanna launch a fleet?
	        		fight = JOptionPane.showConfirmDialog(
	      				  frame, "Launch a fleet?",
	      				  "Launch a fleet?", 2, 1, null);		
	        		if (fight == 0){
	        	//How many ships?
	        		shipStr = "-2";
	        		while ((Integer.parseInt(shipStr) < 1) || (Integer.parseInt(shipStr) > planets[hw].getShipCnt())){
	        		shipStr = JOptionPane.showInputDialog(null,
	        			  "How many ships?",
	        			  "How many ships? (" + planets[hw].getShipCnt()+ ") max",
	        			  JOptionPane.QUESTION_MESSAGE);
	        		if (isInteger(shipStr) == false) break;
	        		}
	        		//JOptionPane.showMessageDialog(frame, "shipStr =" + shipStr); //debug
	        		if (shipStr != null && isInteger(shipStr) == true){
		        	//Where ya going?
	        		planetStr = "-2";	
	        		while ((Integer.parseInt(planetStr) < 1) || (Integer.parseInt(planetStr) > 40)){	
	        		planetStr = JOptionPane.showInputDialog(null,
		        		  "To which planet?",
		        		  "To which planet?",
		        	      JOptionPane.QUESTION_MESSAGE);
	        		if (isInteger(shipStr) == false) break;
	        		}
	        		//JOptionPane.showMessageDialog(frame, "planetStr =" + planetStr); //debug
	        		if (planetStr != null && isInteger(planetStr) == true){
	        		
	        		//create fleet object on arraylist
	        		fleet newFleet = new fleet();
	        		newFleet.setOwner("Rob");
	        		//JOptionPane.showMessageDialog(frame, "ships found =" + String.valueOf(hw + 1)+ "," + planets[hw].getShipCnt() + " " + Integer.parseInt(shipStr)); //debug
	        		if (Integer.parseInt(shipStr) <= planets[hw].getShipCnt() && Integer.parseInt(shipStr) > 0)
	        		{
	        			newFleet.setFleetCnt(Integer.parseInt(shipStr));
	        		}
	        		else newFleet.setFleetCnt(planets[hw].getShipCnt());
	        		//delete planet of ships selected
	        		planets[hw].setShipCnt(planets[hw].getShipCnt() - Integer.parseInt(shipStr));
	        		newFleet.setDestination(Integer.parseInt(planetStr)-1);
	        		newFleet.setDist(calcDist(planets[hw].getGridNum() + 1, planets[newFleet.getDest()].getGridNum() + 1));
	        		flroster.add(newFleet);}}
	        		//debug section
	        		//create a look at the coordinates of the 40 worlds to check
	        		//calculations, it looks like player planet is calculated 
	        		//correctly, and all else sucks.
	        		}
	            }}

				private boolean isInteger(String shipStr) {
					// TODO Auto-generated method stub
					try  
					   {   
					      Integer.parseInt( shipStr );   
					      return true;   
					   }   
					   catch( Exception e )   
					   {   
					      return false;   
					   }   

				}

				

				private int getPlanetNum(int fi) {
					// TODO Auto-generated method stub
					int planetx = 0;
					for (int i = 0; i < 40; i ++){
						if (planets[i].getGridNum() == fi){
							planetx = i;
						}
					}
					return planetx;
				}
	        });
			pbutton[planets[i].getGridNum()].setBackground(Color.white);
			pbutton[planets[i].getGridNum()].setBorderPainted(true);
			//pbutton[planets[i].getGridNum()].setToolTipText("Planet " + String.valueOf(i+1));
			pbutton[planets[i].getGridNum()].setToolTipText("Planet " + String.valueOf(i+1) + ", Production: " + String.valueOf(planets[i].getShipPrd()) + ", Ships: " + String.valueOf(planets[i].getShipCnt()));
		}
		planets[num].setOwner("Rob");
		planets[num].setShipPrd(10);
		planets[num].setShipCnt(100);
		pbutton[planets[num].getGridNum()].setBackground(Color.blue);
		pbutton[planets[num].getGridNum()].setToolTipText("Planet " + String.valueOf(num+1) + ", Production: " + String.valueOf(planets[num].getShipPrd()) + ", Ships: " + String.valueOf(planets[num].getShipCnt()));
		while (pbutton[planets[num].getGridNum()].getBackground() != Color.white){
		now = new Date().getTime();
		num = new Random(now).nextInt(39);}
		planets[num].setOwner("Zentradi");
		pbutton[planets[num].getGridNum()].setBackground(Color.green);
		while (pbutton[planets[num].getGridNum()].getBackground() != Color.white){
		now = new Date().getTime();
		num = new Random(now).nextInt(39);}
		planets[num].setOwner("Norconian");
		pbutton[planets[num].getGridNum()].setBackground(Color.red);
		while (pbutton[planets[num].getGridNum()].getBackground() != Color.white){
		now = new Date().getTime();
		num = new Random(now).nextInt(39);}
		planets[num].setOwner("Shmendrak");
		pbutton[planets[num].getGridNum()].setBackground(Color.yellow);
		panel.add(gridpanel);
		//Declarations
		/*JLabel*/
		//planetlbl = new JLabel("1"+", "+"10"+", "+"100" );
		//panel.add(planetlbl);
		fbutton = new JButton("Fleets");
		fbutton.setFont(new Font("Verdana", Font.PLAIN, 8));
		fbutton.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e)
            {
        		String fleetrpt = "Owner   Fleet#   Dest             ETA \n";
        		int cnt = 1;
        		for (fleet f : flroster){ //For loop for battle phase
        			fleetrpt = fleetrpt + f.getOwner() + " Fleet " + cnt + "  to Planet " + (f.getDest() + 1) + " " + f.getDist() + " turns\n";
        			cnt ++;
        		}
        		JOptionPane.showMessageDialog(frame, fleetrpt);
        		
            }});
		plbutton = new JButton("Planets");
		plbutton.setFont(new Font("Verdana", Font.PLAIN, 8));
		plbutton.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e)
            {
        		String planrpt ="Pl Owner Location Prod Garrison Pl Owner Location Prod Garrison\n";
        		for(int i = 0;i < 40; i = i + 2){
        			if (planets[i].getPlanetOwner()=="Rob"){
        			planrpt = planrpt + String.valueOf(i+1) + " " + planets[i].getPlanetOwner() + " " + planets[i].getGridNum() + " " + planets[i].getShipPrd() + " " + planets[i].getShipCnt() + " ";}
        			if (planets[i+1].getPlanetOwner()=="Rob"){
        			planrpt = planrpt + String.valueOf(i+2) + " " + planets[i+1].getPlanetOwner() + " " + planets[i+1].getGridNum() + " " + planets[i+1].getShipPrd() + " " + planets[i+1].getShipCnt() + "\n";}
        		}
        		JOptionPane.showMessageDialog(frame, planrpt);
            }});
		ebutton = new JButton("End Turn");
		ebutton.setFont(new Font("Verdana", Font.PLAIN, 8));
		ebutton.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e)
            {
            eot = true;
            }
        });
		ebutton.setEnabled(false);
		JPanel smallpanel = new JPanel();
		smallpanel.add(fbutton);
		smallpanel.add(plbutton);
		smallpanel.add(ebutton);
		panel.add(smallpanel);
        contentPane.add(panel);
        frame.setVisible(true);
      //***********
      //
      //TURN CYCLES
      //
      //***********
        int turnnum = 1;
		String VICTORY = "false";
		while (VICTORY == "false"){
			// Battle Phase
			//JOptionPane.showMessageDialog(frame, "Battle Phase");
			//************
			//Battle Phase
			//************
			ebutton.setText("Battle");
				//debug area
				//int cnt = 1;
				//boolean removeflag = true;
				for (fleet f : flroster){ //For loop for battle phase
					f.setDist(f.getDist()-1); //Array list fleet reduce 1 turn.
					if (f.getDist() <= 0){ // If fleet has arrived.
						//int hw = getPlanetNum(fi);
						if (f.getOwner() == planets[f.getDest()].getPlanetOwner()) {//if owners match.
							if (f.getOwner() == "Rob") { //my eyes only
							JOptionPane.showMessageDialog(frame, "The Planet "+ f.getDest() + "'s garrison of " + planets[f.getDest()].getShipCnt() + " ships gets reinforced with " + f.getFleetCnt() + " ships");
							}
							planets[f.getDest()].setShipCnt(planets[f.getDest()].getShipCnt() + f.getFleetCnt()); //Strengthen Garrison
							//flroster.remove(newFleet);
						}
						else{//battle!
							if (f.getOwner() == "Rob") { //my eyes only
							JOptionPane.showMessageDialog(frame, "A Battle is occuring between " + f.getOwner() + "'s fleet of " + f.getFleetCnt() + " ships\n" + " and " + planets[f.getDest()].getPlanetOwner() + "'s garrison of " + planets[f.getDest()].getShipCnt() + " ships.");
							}
						int winorlose = planets[f.getDest()].getShipCnt() - f.getFleetCnt();
						if (winorlose < 0){ //Fleet wins!
							planets[f.getDest()].setShipCnt(f.getFleetCnt()-planets[f.getDest()].getShipCnt());
							planets[f.getDest()].setOwner(f.getOwner());
							if (f.getOwner() == "Rob") pbutton[planets[f.getDest()].getGridNum()].setBackground(Color.blue);
							if (f.getOwner() == "Zentradi")pbutton[planets[f.getDest()].getGridNum()].setBackground(Color.green);
							if (f.getOwner() == "Norconian")pbutton[planets[f.getDest()].getGridNum()].setBackground(Color.red);
							if (f.getOwner() == "Shmendrak")pbutton[planets[f.getDest()].getGridNum()].setBackground(Color.yellow);
							JOptionPane.showMessageDialog(frame, planets[f.getDest()].getPlanetOwner() + " wins!\n They now own Planet " + ((f.getDest())+1) + "!");
						}
						else{//Fleet loses!
							planets[f.getDest()].setShipCnt(planets[f.getDest()].getShipCnt() - f.getFleetCnt());
							if (planets[f.getDest()].getPlanetOwner() == "Rob"){
							JOptionPane.showMessageDialog(frame, planets[f.getDest()].getPlanetOwner() + " wins!\n They still hold Planet " + ((f.getDest())+1) + "!");
							}
						}
						//flroster.remove(newFleet);
						}
						f.setRemove(true);
					}					
					else{
					//JOptionPane.showMessageDialog(frame, cnt + ". Destination World: " + (f.getDest()+1) + "\nArriving in " + f.getDist() + " turns!");
					/*cnt ++;*/}
				}
				//try this
				ArrayList<Integer> byebye = new ArrayList<Integer>();
				int cnt = 0;
				for (fleet f2 : flroster){
					if (f2.getRemove() == true ){
						byebye.add(cnt);
					}
					cnt ++;
				}
				Collections.reverse(byebye);
				for (int r1 : byebye){
					flroster.remove(r1);
				}
				byebye.removeAll(byebye);
			//**************
			// Player's Turn
			//**************
			ebutton.setEnabled(true);
			ebutton.setText("End Turn " + turnnum);
			eot = false;
			//JOptionPane.showMessageDialog(frame, "Your Turn");
			while (eot == false){
			String plist = "";
			String[] arr = {"Zentradi","Norconian","Shmendrak","neutral world"};
			Set<String> names = new HashSet<String>(Arrays.asList(arr));
			for (int i = 0;i < 40; i ++){
				if (names.contains(planets[i].getPlanetOwner())==false){
					plist = plist + String.valueOf(i + 1) + ", " + String.valueOf(planets[i].getGridNum()+1) + ", " + String.valueOf(planets[i].getShipPrd()) + ", " + String.valueOf(planets[i].getShipCnt()+ "\n"); 
				}
			}
			for (int i = 0; i < 40; i ++){
				if (pbutton[planets[i].getGridNum()].getBackground() == Color.blue){
					pbutton[planets[i].getGridNum()].setToolTipText("Planet " + String.valueOf(i+1) + ", Production: " + String.valueOf(planets[i].getShipPrd()) + ", Ships: " + String.valueOf(planets[i].getShipCnt()));
				}
				else
				{
					pbutton[planets[i].getGridNum()].setToolTipText("Planet " + String.valueOf(i+1) + ", Production: " + String.valueOf(planets[i].getShipPrd()) + ", Ships: " + String.valueOf(planets[i].getShipCnt()));
				}
			}
			//planetlbl.setText(plist);
				//create fleet
				//end turn
			}
			ebutton.setText("Aliens");
			ebutton.setEnabled(false);
			// Zentradi's Turn
				//list out planets with sufficient fleet strength
			ArrayList<Integer> attackworlds = new ArrayList<Integer>();
			for (int i = 0; i < 40; i ++){
					if (planets[i].getShipCnt() > 9 && planets[i].getPlanetOwner() == "Zentradi"){
						attackworlds.add(i);
					}
			}
				//find nearest worlds not owned by Zentradi
			int ld = 300;
			int vw = 41;
			for (int aw : attackworlds){
				for(int i = 0; i < 40; i ++){
					if (calcDist(planets[aw].getGridNum(), planets[i].getGridNum()) < ld && planets[i].getPlanetOwner() != "Zentradi"){
						ld = (calcDist(planets[aw].getGridNum(), planets[i].getGridNum()));
						vw = i;
					}
				}
			}
			for (int aw : attackworlds){
				//launch fleet
				fleet newFleet = new fleet();
        		newFleet.setOwner("Zentradi");
        		newFleet.setFleetCnt(planets[aw].getShipCnt());
        		newFleet.setDestination(vw);
        		newFleet.setDist(ld);
        		flroster.add(newFleet);
        		planets[aw].setShipCnt(0);
        		//JOptionPane.showMessageDialog(frame, newFleet.getOwner() + " From: " + String.valueOf(aw+1) + " To: " + String.valueOf(vw+1) + " Arriving in " + newFleet.getDist() + " turns.");//debug
			}
			attackworlds.removeAll(attackworlds);
				
			
			// Norconian's Turn
			
			attackworlds = new ArrayList<Integer>();
			for (int i = 0; i < 40; i ++){
					if (planets[i].getShipCnt() > 14 && planets[i].getPlanetOwner() == "Norconian"){
						attackworlds.add(i);
					}
			}
				//find nearest worlds not owned by Zentradi
			ld = 300;
			vw = 41;
			for (int aw : attackworlds){
				for(int i = 0; i < 40; i ++){
					if (calcDist(planets[aw].getGridNum(), planets[i].getGridNum()) < ld && planets[i].getPlanetOwner() != "Norconian"){
						ld = (calcDist(planets[aw].getGridNum(), planets[i].getGridNum()));
						vw = i;
					}
				}
			}
			for (int aw : attackworlds){
				//launch fleet
				fleet newFleet = new fleet();
        		newFleet.setOwner("Norconian");
        		newFleet.setFleetCnt(planets[aw].getShipCnt());
        		newFleet.setDestination(vw);
        		newFleet.setDist(ld);
        		flroster.add(newFleet);
        		planets[aw].setShipCnt(0);
        		//JOptionPane.showMessageDialog(frame, newFleet.getOwner() + " From: " + String.valueOf(aw+1) + " To: " + String.valueOf(vw+1) + " Arriving in " + newFleet.getDist() + " turns.");//debug
			}
			attackworlds.removeAll(attackworlds);
			// Shmendrak's Turn
			
			attackworlds = new ArrayList<Integer>();
			for (int i = 0; i < 40; i ++){
					if (planets[i].getShipCnt() > 19 && planets[i].getPlanetOwner() == "Shmendrak"){
						attackworlds.add(i);
					}
			}
				//find nearest worlds not owned by Zentradi
			ld = 300;
			vw = 41;
			for (int aw : attackworlds){
				for(int i = 0; i < 40; i ++){
					if (calcDist(planets[aw].getGridNum(), planets[i].getGridNum()) < ld && planets[i].getPlanetOwner() != "Shmendrak"){
						ld = (calcDist(planets[aw].getGridNum(), planets[i].getGridNum()));
						vw = i;
					}
				}
			}
			for (int aw : attackworlds){
				//launch fleet
				fleet newFleet = new fleet();
        		newFleet.setOwner("Shmendrak");
        		newFleet.setFleetCnt(planets[aw].getShipCnt());
        		newFleet.setDestination(vw);
        		newFleet.setDist(ld);
        		flroster.add(newFleet);
        		planets[aw].setShipCnt(0);
        		//JOptionPane.showMessageDialog(frame, newFleet.getOwner() + " From: " + String.valueOf(aw+1) + " To: " + String.valueOf(vw+1) + " Arriving in " + newFleet.getDist() + " turns.");//debug
			}
			attackworlds.removeAll(attackworlds);
			// Growth Phase
				for (int i = 0; i < 40; i ++){
					if (planets[i].getPlanetOwner() != "neutral world"){
						planets[i].setShipCnt(planets[i].getShipCnt() + planets[i].getShipPrd());}
					else{
						planets[i].setShipCnt(planets[i].getShipCnt() + ((planets[i].getShipPrd())/2)+1);}
					//JOptionPane.showMessageDialog(frame,planets[i].getShipCnt());
					}
			// Victory Check?
				VICTORY = "true";
				endroute = 0;
				for (int i = 0;i < 40; i++){
					if (pbutton[planets[i].getGridNum()].getBackground() != Color.blue) 
						{
						VICTORY = "false";
						endroute = -1;
						}
				}
				if (VICTORY == "false"){
					VICTORY = "fail";
					endroute = 1;
					for (int i = 0;i < 40; i++){
						if (pbutton[planets[i].getGridNum()].getBackground() == Color.blue) 
							{
							VICTORY = "false";
							endroute = -1;
							}
					}
				}
				turnnum ++;
		}
		if (endroute == 0){
			JOptionPane.showMessageDialog(frame, "Victory!");
		}
		if (endroute == 1){
			JOptionPane.showMessageDialog(frame, "You Lose!");
		}
		System.exit(0);
		
        //planetmaker();
        
	}
}
