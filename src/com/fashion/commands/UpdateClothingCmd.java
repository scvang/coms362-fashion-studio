package com.fashion.commands;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

import com.fashion.Main;
import com.fashion.Studio;
import com.fashion.screens.InventoryScreen;

public class UpdateClothingCmd implements Command{
	private Studio studio;
	public UpdateClothingCmd(Studio s) {
		this.studio = s;
	}

	@Override
	public String getDescription() {
		return "Update Clothing";
	}

	@Override
	public void execute() {
		
		Scanner in = new Scanner(System.in);
		System.out.println("Enter the id:");
		int id = in.nextInt();in.nextLine();
		System.out.println("Enter the item name:");
		String itemName = in.nextLine();
		System.out.println("Enter the brand name:");
		String brandName = in.nextLine();
		System.out.println("Enter the color:");
		String color = in.nextLine();
		System.out.println("Enter the size:");
		String size = in.nextLine();
		System.out.println("Enter the price:");
		double price = in.nextDouble(); in.nextLine();
		System.out.println("Enter the quantity:");
		int quantity = in.nextInt(); in.nextLine();
		
		studio.resetInventory();
		//studio.storeClothingItem(new Apparel(itemName,brandName,color));
		
		 // Establish a connection to the database test.
		try{
	      // Step 1: "Load" the JDBC driver
			Class.forName("com.mysql.cj.jdbc.Driver");

	      // Step 2: Establish the connection to the database 
	      String url = "jdbc:mysql://localhost/fashion_studio"; 
	      Connection conn = DriverManager.getConnection(url,"root","");
	      //System.out.println("Connected.");
	      
	      // create a prepared statement from the connection
	      PreparedStatement ps = conn.prepareStatement("UPDATE clothing SET itemName = ?, brandName = ?, color = ?, size = ?, price = ?, quantity = ? WHERE id = ?");
	      
	      ps.setString(1,itemName);
	      ps.setString(2,brandName);
	      ps.setString(3, color);
	      ps.setString(4, size);
	      ps.setDouble(5, price);
	      ps.setInt(6, quantity);
	      ps.setInt(7, id);
	      
	      ps.execute();
	      conn.close();
	    }
	    catch (Exception e){
	      System.err.println(e.getMessage()); 
	    }
		System.out.println("Item was updated in the database.");
		
		new InventoryScreen(studio).execute();
		
	}

}