package com.fashion.commands;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import com.fashion.Main;
import com.fashion.Studio;
import com.fashion.events.Showing;

public class ShowingVenueCmd implements Command {
	private Studio studio;
	public ShowingVenueCmd(Studio s) {
		this.studio = s;
	}

	@Override
	public String getDescription() {

		return "Showing Venues";
	}

	@Override
	public void execute() {
		System.out.println("Choose a showing event:");
		int count = 1;
		ArrayList<Showing> showingList = new ArrayList<>();
		for(int i = 0; i < studio.getEventList().size(); ++i) {
			if(studio.getEventList().get(i) instanceof Showing) {
				System.out.println(count + ": " + studio.getEventList().get(i).getEvent());
				++count;
				showingList.add((Showing)studio.getEventList().get(i));
			}
		}
		if(showingList.isEmpty()) {
			System.out.println("There are no showings!");
			Main.EventScreen();
		}
		Scanner in = new Scanner(System.in);
		int i = in.nextInt(); in.nextLine();
		
		String eventName = showingList.get(i-1).getEvent();
		
		// This retrieves a showing venue list from the database.
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
	      ResultSet rs = st.executeQuery("SELECT * FROM showing");
	      
	      while(rs.next()) {
	    	  String eventNameD = rs.getString("event");
	    	  String nameD = rs.getString("name");
	    	  String dateD = rs.getString("date");
	    	  String timeD = rs.getString("time");
	    	  String seatD = rs.getString("seat");
	    	  
	    	  if(studio.reserveSeat(studio.getEvent(eventNameD),seatD,nameD,dateD,timeD));
	      }
	      // close the connection.
	      st.close();
	    }
	    catch (Exception e){
	      System.err.println(e.getMessage()); 
	    }

		System.out.println("Event: " + eventName);
		CommandDisplay cmd = new CommandDisplay();
		cmd.addCommand(new DisplaySeatsCmd(studio,eventName));
		cmd.addCommand(new ReserveSeatCmd(studio,eventName));
		cmd.addCommand(new CheckSeatCmd(studio,eventName));
		cmd.addCommand(new RefundSeatCmd(studio,eventName));
		cmd.addCommand(new EventScreenCmd());
		
		cmd.displayCommands();
	}

}
