package com.fashion.commands;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

import com.fashion.Main;
import com.fashion.Studio;

public class ReserveTableCmd implements Command {
	private Studio studio;
	private String eventName;
	public ReserveTableCmd(Studio s, String e) {
		this.studio = s;
		this.eventName = e;
	}

	@Override
	public String getDescription() {

		return "Reserve Table";
	}

	@Override
	public void execute() {
		Scanner in = new Scanner(System.in);
		if(studio.isDiningFull(studio.getEvent(eventName))) {
			System.out.println("There are no available tables.");
			Main.EventScreen();
		}
		System.out.println("Enter your customer name: ");
		String customerName = in.nextLine();
		System.out.println("Enter your desired table (1~20): ");
		String tableNum = in.nextLine();
		System.out.println("Enter your desired date (mm-dd-yy): ");
		String date = in.nextLine();
		System.out.println("Enter your desired time (hh:mm am/pm): ");
		String time = in.nextLine();
		
		if(studio.reserveTable(studio.getEvent(eventName),tableNum,customerName,date,time)) {
			studio.chargeCard(studio.getEvent(eventName),customerName);
			
			 // Establish a connection to the database test.
			try{
		      // Step 1: "Load" the JDBC driver
				Class.forName("com.mysql.cj.jdbc.Driver");

		      // Step 2: Establish the connection to the database 
		      String url = "jdbc:mysql://localhost/fashion_studio"; 
		      Connection conn = DriverManager.getConnection(url,"root","");
		      //System.out.println("Connected.");
		      
		      // create a prepared statement from the connection
		      PreparedStatement ps = conn.prepareStatement("INSERT INTO dining (event,name,date,time,tableNum) VALUES (?,?,?,?,?)");
		      
		      ps.setString(1, eventName);
		      ps.setString(2, customerName);
		      ps.setString(3, date);
		      ps.setString(4, time);
		      ps.setString(5, tableNum);
		      
		      ps.execute();
		      conn.close();
		    }
		    catch (Exception e){
		      System.err.println(e.getMessage());
		      return;
		    }
			System.out.println("Reservation was added into the database.");
			
		}
		else {
			System.out.println("Table reservation failed.");
		}
		
		Main.EventScreen();
	}

}
