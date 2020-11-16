package com.fashion.commands;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

import com.fashion.Main;
import com.fashion.Studio;
import com.fashion.screens.EventScreen;

public class ReserveBadgeCmd implements Command {
	private Studio studio;
	private String eventName;
	public ReserveBadgeCmd(Studio s, String e) {
		this.studio = s;
		this.eventName = e;
	}

	@Override
	public String getDescription() {

		return "Reserve Badge";
	}

	@Override
	public void execute() {
		Scanner in = new Scanner(System.in);
		if(studio.isPartyFull(studio.getEvent(eventName))) {
			System.out.println("The venue is full.");
			new EventScreen(studio).execute();
		}
		
		System.out.println("Enter your customer name: ");
		String customerName = in.nextLine();
		System.out.println("Enter your desired date (mm-dd-yy): ");
		String date = in.nextLine();
		System.out.println("Enter your desired time (hh:mm am/pm): ");
		String time = in.nextLine();
		
		studio.reserveBadge(studio.getEvent(eventName),customerName,date,time);
		
		// Establish a connection to the database test.
		try{
	      // Step 1: "Load" the JDBC driver
			Class.forName("com.mysql.cj.jdbc.Driver");

	      // Step 2: Establish the connection to the database 
	      String url = "jdbc:mysql://localhost/fashion_studio"; 
	      Connection conn = DriverManager.getConnection(url,"root","");
	      //System.out.println("Connected.");
	      
	      // create a prepared statement from the connection
	      PreparedStatement ps = conn.prepareStatement("INSERT INTO party (event,name,date,time) VALUES (?,?,?,?)");
	      
	      ps.setString(1, eventName);
	      ps.setString(2, customerName);
	      ps.setString(3, date);
	      ps.setString(4, time);;
	      
	      ps.execute();
	      conn.close();
	    }
	    catch (Exception e){
	      System.err.println(e.getMessage()); 
	    }
		System.out.println("Reservation was added into the database.");
		new EventScreen(studio).execute();
	}

}
