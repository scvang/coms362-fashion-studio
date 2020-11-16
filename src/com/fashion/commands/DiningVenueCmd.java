package com.fashion.commands;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import com.fashion.Main;
import com.fashion.Studio;
import com.fashion.events.Dining;
import com.fashion.screens.EventScreen;

public class DiningVenueCmd implements Command {
	private Studio studio;
	public DiningVenueCmd(Studio s) {
		this.studio = s;
	}

	@Override
	public String getDescription() {

		return "Dining Venues";
	}

	@Override
	public void execute() {
		System.out.println("Choose a dining event:");
		int count = 1 ;
		ArrayList<Dining> list = new ArrayList<>();
		for(int i = 0; i < studio.getEventList().size(); ++i) {
			if(studio.getEventList().get(i) instanceof Dining) {
				System.out.println(count + ": " + studio.getEventList().get(i).getEvent());
				++count;
				list.add((Dining)studio.getEventList().get(i));
			}
		}
		if(list.isEmpty()) {
			System.out.println("There are no dinings!");
			new EventScreen(studio).execute();
		}
		Scanner in = new Scanner(System.in);
		int i = in.nextInt();
		
		String eventName = list.get(i-1).getEvent();
		
		// This retrieves a dining venue list from the database.
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
	      ResultSet rs = st.executeQuery("SELECT * FROM dining");
	      
	      while(rs.next()) {
	    	  String eventNameD = rs.getString("event");
	    	  String nameD = rs.getString("name");
	    	  String dateD = rs.getString("date");
	    	  String timeD = rs.getString("time");
	    	  String tableD = rs.getString("tableNum");
	    	  
	    	  if(studio.reserveTable(studio.getEvent(eventNameD),tableD,nameD,dateD,timeD));
	      }
	      // close the connection.
	      st.close();
	    }
	    catch (Exception e){
	      System.err.println(e.getMessage()); 
	    }
		System.out.println("Event: " + eventName);
		CommandDisplay cmd = new CommandDisplay();
		cmd.addCommand(new DisplayTablesCmd(studio,eventName));
		cmd.addCommand(new ReserveTableCmd(studio,eventName));
		cmd.addCommand(new CheckTableCmd(studio,eventName));
		cmd.addCommand(new RefundTableCmd(studio,eventName));
		cmd.addCommand(new EventScreen(studio));
		
		cmd.displayCommands();
	}

}
