package com.fashion.commands;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

import com.fashion.Main;
import com.fashion.Studio;

public class ReserveSeatCmd implements Command {
	private Studio studio;
	private String eventName;
	public ReserveSeatCmd(Studio s, String e) {
		this.studio = s;
		this.eventName = e;
	}

	@Override
	public String getDescription() {

		return "Reserve Seat";
	}

	@Override
	public void execute() {
		Scanner in = new Scanner(System.in);
		if(studio.isShowingFull(studio.getEvent(eventName))) {
			System.out.println("No available seats.");
			Main.Screen();
		}
		System.out.println("Enter your customer name: ");
		String customerName = in.nextLine();
		System.out.println("Enter your desired seat (A1~I9): ");
		String seat = in.nextLine();
		System.out.println("Enter your desired date (mm-dd-yy): ");
		String date = in.nextLine();
		System.out.println("Enter your desired time (hh:mm am/pm): ");
		String time = in.nextLine();
		
		if(studio.reserveSeat(studio.getEvent(eventName),seat,customerName,date,time)) {
			studio.chargeCard(studio.getEvent(eventName),customerName);
			
			// update the database
			 // Establish a connection to the database test.
			try{
		      // Step 1: "Load" the JDBC driver
				Class.forName("com.mysql.cj.jdbc.Driver");

		      // Step 2: Establish the connection to the database 
		      String url = "jdbc:mysql://localhost/fashion_studio"; 
		      Connection conn = DriverManager.getConnection(url,"root","");
		      //System.out.println("Connected.");
		      
		      // create a prepared statement from the connection
		      PreparedStatement ps = conn.prepareStatement("INSERT INTO showing (event,name,date,time,seat) VALUES (?,?,?,?,?)");
		      
		      ps.setString(1, eventName);
		      ps.setString(2, customerName);
		      ps.setString(3, date);
		      ps.setString(4, time);
		      ps.setString(5, seat);
		      
		      ps.execute();
		      conn.close();
		    }
		    catch (Exception e){
		      System.err.println(e.getMessage()); 
		    }
			System.out.println("Reservation was added into the database.");
		}
		else{
			System.out.println("Seat Reservation failed.");
		}
		
		Main.EventScreen();
	}

}
