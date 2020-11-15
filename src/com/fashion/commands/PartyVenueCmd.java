package com.fashion.commands;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import com.fashion.Main;
import com.fashion.Studio;
import com.fashion.events.Party;

public class PartyVenueCmd implements Command {
	private Studio studio;
	public PartyVenueCmd(Studio s) {
		this.studio = s;
	}

	@Override
	public String getDescription() {

		return "Party Venues";
	}

	@Override
	public void execute() {
		System.out.println("Choose a party event:");
		int count = 1;
		ArrayList<Party> list = new ArrayList<>();
		for(int i = 0; i < studio.getEventList().size(); ++i) {
			if(studio.getEventList().get(i) instanceof Party) {
				System.out.println(count + ") " + studio.getEventList().get(i).getEvent());
				++count;
				list.add((Party)studio.getEventList().get(i));
			}
		}
		if(list.isEmpty()) {
			System.out.println("There are no parties!");
			Main.EventScreen();
		}
		Scanner in3 = new Scanner(System.in);
		int i = in3.nextInt();
		
		String eventName = list.get(i-1).getEvent();
		
		// This retrieves a party venue list from the database.
		// Establish a connection to the database to query data.
		try{
	      // Step 1: "Load" the JDBC driver
			Class.forName("com.mysql.cj.jdbc.Driver");

	      // Step 2: Establish the connection to the database 
	      String url = "jdbc:mysql://localhost/fashion_studio"; 
	      Connection conn = DriverManager.getConnection(url,"root","");
	      //System.out.println("Connected.");
	      
	      // create a Statement from the connection
	      Statement st = conn.createStatement();
	      
	      // query the data
	      ResultSet rs = st.executeQuery("SELECT * FROM party");
	      
	      while(rs.next()) {
	    	  String eventNameD = rs.getString("event");
	    	  String nameD = rs.getString("name");
	    	  String dateD = rs.getString("date");
	    	  String timeD = rs.getString("time");;
	    	  
	    	  if(eventNameD == null)break;
	    	  if(studio.reserveBadge(studio.getEvent(eventNameD),nameD,dateD,timeD));
	      }
	      // close the connection.
	      st.close();
	    }
	    catch (Exception e){
	      System.err.println(e.getMessage()); 
	    }
		
		System.out.println("Event: " + eventName);
		CommandDisplay cmd = new CommandDisplay();
		cmd.addCommand(new DisplayAttendeesCmd(studio,eventName));
		cmd.addCommand(new ReserveBadgeCmd(studio,eventName));
		cmd.addCommand(new CheckBadgeCmd(studio,eventName));
		cmd.addCommand(new RefundBadgeCmd(studio,eventName));
		cmd.addCommand(new EventScreenCmd());
		
		cmd.displayCommands();
	}

}
