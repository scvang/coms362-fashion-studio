package com.fashion.commands;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

import com.fashion.Main;
import com.fashion.Studio;
import com.fashion.screens.EventScreen;

public class RefundSeatCmd implements Command {
	private Studio studio;
	private String eventName;
	public RefundSeatCmd(Studio s, String e) {
		this.studio = s;
		this.eventName = e;
	}

	@Override
	public String getDescription() {

		return "Refund Seat";
	}

	@Override
	public void execute() {
		Scanner in = new Scanner(System.in);
		System.out.println("Enter the customer name:");
		String name = in.nextLine();
		if(studio.hasSeatReservation(name,studio.getEvent(eventName))) {
			System.out.println("Reservation found.");
			studio.removeSeatReservation(name,studio.getEvent(eventName));
			System.out.println("Reservation removed.");
			
			// Remove the entry from database.
			try{
			      // Step 1: "Load" the JDBC driver
					Class.forName("com.mysql.cj.jdbc.Driver");

			      // Step 2: Establish the connection to the database 
			      String url = "jdbc:mysql://localhost/fashion_studio"; 
			      Connection conn = DriverManager.getConnection(url,"root","");
			      //System.out.println("Connected.");
			      
			      // create a prepared statement from the connection
			      PreparedStatement ps = conn.prepareStatement("DELETE FROM showing WHERE event = ? AND name = ?");
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
			System.out.println("Could not find reservaton.");
		}
		
		new EventScreen(studio).execute();
	}

}