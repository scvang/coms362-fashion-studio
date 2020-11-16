package com.fashion.screens;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import com.fashion.Studio;
import com.fashion.commands.*;

public class EventScreen implements Command {
	
	private Studio studio;
	public EventScreen(Studio s) {
		this.studio = s;
	}

	@Override
	public String getDescription() {
		return "Event Screen";
	}

	@Override
	public void execute() {

		// Fetch the data from the database.
		String type;
		String name;
		String date;
		String time;
		
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
	      ResultSet rs = st.executeQuery("SELECT * FROM events");
	      
	      // Clear the event list before fetching to avoid duplicating.
	      studio.resetEventList();
	      while(rs.next()) {
	    	  type = rs.getString("type");
	    	  name = rs.getString("name");
	    	  date = rs.getString("date");
	    	  time = rs.getString("time");
	    	  
	    	  studio.createEvent(type,name,date,time);
	      }
	      // close the connection.
	      st.close();
	    }
		catch (Exception e){
		      System.err.println(e.getMessage()); 
		      }
		
		CommandDisplay cmd = new CommandDisplay();
		cmd.addCommand(new ShowingVenueCmd(studio));
		cmd.addCommand(new DiningVenueCmd(studio));
		cmd.addCommand(new PartyVenueCmd(studio));
		cmd.addCommand(new CreateEventCmd(studio));
		cmd.addCommand(new DisplayEventsCmd(studio));
		cmd.addCommand(new MainScreen(studio));
		cmd.displayCommands();
	}
}
