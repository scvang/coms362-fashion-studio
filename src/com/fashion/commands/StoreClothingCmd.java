package com.fashion.commands;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

import com.fashion.Main;
import com.fashion.Studio;

public class StoreClothingCmd implements Command {
	private Studio studio;
	public StoreClothingCmd(Studio s) {
		this.studio=s;
	}

	@Override
	public String getDescription() {
		return "Store Clothing";
	}

	@Override
	public void execute() {
		int id = 0;
		String itemName;
		String brandName;
		String color;
		String size;
		double price;
		int quantity;
		
		Scanner in = new Scanner(System.in);
		System.out.println("Enter the id:");
		id = in.nextInt();in.nextLine();
		System.out.println("Enter the item name:");
		itemName = in.nextLine();
		System.out.println("Enter the brand name:");
		brandName = in.nextLine();
		System.out.println("Enter the color:");
		color = in.nextLine();
		System.out.println("Enter the size:");
		size = in.nextLine();
		System.out.println("Enter the price:");
		price = in.nextDouble(); in.nextLine();
		System.out.println("Enter the quantity:");
		quantity = in.nextInt(); in.nextLine();
		
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
	      PreparedStatement ps = conn.prepareStatement("INSERT INTO clothing (id,itemName,brandName,color,size,price,quantity) " + "VALUES (?,?,?,?,?,?,?)");
	      
	      ps.setInt(1, id);
	      ps.setString(2,itemName);
	      ps.setString(3,brandName);
	      ps.setString(4, color);
	      ps.setString(5, size);
	      ps.setDouble(6, price);
	      ps.setInt(7, quantity);
	      
	      ps.execute();
	      conn.close();
	    }
	    catch (Exception e){
	      System.err.println(e.getMessage()); 
	    }
		System.out.println("Item was added into the database.");
		
		Main.InventoryScreen();
	}
}
