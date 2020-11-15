package com.fashion.commands;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

import com.fashion.Main;
import com.fashion.Studio;

public class RemoveClothingCmd implements Command {
	private Studio studio;
	public RemoveClothingCmd(Studio s) {
		this.studio = s;
	}

	@Override
	public String getDescription() {
		return "Remove Clothing";
	}

	@Override
	public void execute() {
		Scanner in = new Scanner(System.in);
		System.out.println("Enter the item name:");
		String itemName = in.nextLine();
		
		System.out.println("Enter the brand name:");
		String brandName = in.nextLine();
		
		System.out.println("Enter the color:");
		String color = in.nextLine();
		
		System.out.println("Enter the size:");
		String size = in.nextLine();
		
		// Remove the entry from database.
		try{
		      // Step 1: "Load" the JDBC driver
				Class.forName("com.mysql.cj.jdbc.Driver");

		      // Step 2: Establish the connection to the database 
		      String url = "jdbc:mysql://localhost/fashion_studio"; 
		      Connection conn = DriverManager.getConnection(url,"root","");
		      //System.out.println("Connected.");
		      
		      // create a prepared statement from the connection
		      PreparedStatement ps = conn.prepareStatement("DELETE FROM clothing WHERE itemName = ? AND brandName = ? AND color = ? AND size = ?");
		      ps.setString(1, itemName);
		      ps.setString(2, brandName);
		      ps.setString(3, color);
		      ps.setString(4, size);
		      
		      ps.execute();
		      conn.close();
		    }
		catch (Exception e){
		      System.err.println(e.getMessage()); 
		    }
		
		Main.InventoryScreen();
	}
}
