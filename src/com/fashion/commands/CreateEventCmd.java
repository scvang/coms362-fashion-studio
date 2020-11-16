package com.fashion.commands;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

import com.fashion.Main;
import com.fashion.Studio;
import com.fashion.screens.EventScreen;

public class CreateEventCmd implements Command {
	public Studio studio;
	
	public CreateEventCmd(Studio s) {
		this.studio = s;
	}

	@Override
	public String getDescription() {

		return "Create Event";
	}

	@Override
	public void execute() {
		Scanner in = new Scanner(System.in);
		System.out.println("What type of event (showing, dining, party)? ");
		String type = in.nextLine();
		
		System.out.println("Event name?");
		String name = in.nextLine();
		
		System.out.println("What date (mm-dd-yy)? ");
		String date = in.nextLine();
		
		System.out.println("What time (hh:mm am/pm)? ");
		String time = in.nextLine();
		
		studio.createEvent(type,name,date,time);
		
		// Establish a connection to the database test.
		try{
	      // Step 1: "Load" the JDBC driver
			Class.forName("com.mysql.cj.jdbc.Driver");

	      // Step 2: Establish the connection to the database 
	      String url = "jdbc:mysql://localhost/fashion_studio"; 
	      Connection conn = DriverManager.getConnection(url,"root","");
	      //System.out.println("Connected.");
	      
	      // create a prepared statement from the connection
	      PreparedStatement ps = conn.prepareStatement("INSERT INTO events (type,name,date,time) " + "VALUES (?,?,?,?)");
	      
	      ps.setString(1, type);
	      ps.setString(2, name);
	      ps.setString(3, date);
	      ps.setString(4, time);
	      
	      ps.execute();
	      conn.close();
	    }
	    catch (Exception e){
	      System.err.println(e.getMessage()); 
	    }
		
		new EventScreen(studio).execute();
	}

}
