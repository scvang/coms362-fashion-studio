package com.fashion.commands;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

import com.fashion.Studio;

public class RefundBadgeCmd implements Command {
	private Studio studio;
	private String eventName;
	public RefundBadgeCmd(Studio s, String e) {
		this.studio = s;
		this.eventName = e;
	}

	@Override
	public String getDescription() {

		return "Refund Badge";
	}

	@Override
	public void execute() {
		Scanner in = new Scanner(System.in);
		System.out.println("Enter the customer name:");
		String name = in.nextLine();
		if(studio.removeBadgeReservation(name,studio.getEvent(eventName))) {
		
		// Remove the entry from database.
		try{
		      // Step 1: "Load" the JDBC driver
				Class.forName("com.mysql.cj.jdbc.Driver");

		      // Step 2: Establish the connection to the database 
		      String url = "jdbc:mysql://localhost/fashion_studio"; 
		      Connection conn = DriverManager.getConnection(url,"root","");
		      //System.out.println("Connected.");
		      
		      // create a prepared statement from the connection
		      PreparedStatement ps = conn.prepareStatement("DELETE FROM party WHERE event = ? AND name = ?");
		      ps.setString(1, eventName);
		      ps.setString(2, name);
		      
		      ps.execute();
		      conn.close();
		    }
		catch (Exception e){
		      System.err.println(e.getMessage()); 
		    }
		}
		else {
			System.out.println("Reservation was not found.");
		}
	}

}