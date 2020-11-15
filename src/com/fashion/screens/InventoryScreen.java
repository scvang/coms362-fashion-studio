package com.fashion.screens;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import com.fashion.Main;
import com.fashion.Studio;
import com.fashion.apparel.Apparel;
import com.fashion.commands.*;

public class InventoryScreen implements Command {
	private Studio studio;
	public InventoryScreen(Studio s) {
		this.studio = s;
	}

	@Override
	public String getDescription() {
		return "Inventory Screen";
	}

	@Override
	public void execute() {
		
		int id = 0;
		String itemName = "";
		String brandName = "";
		String color = "";
		String size = "";
		double price = 0;
		int quantity = 0;
		
		// This retrieves a clothing list from the database.
		// Establish a connection to the database to query data.
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
	      ResultSet rs = st.executeQuery("SELECT * FROM clothing");
	      
	      studio.resetInventory();
	      while(rs.next()) {
	    	  id = rs.getInt("id");
	    	  itemName = rs.getString("itemName");
	    	  brandName = rs.getString("brandName");
	    	  color = rs.getString("color");
	    	  size = rs.getString("size");
	    	  price = rs.getInt("price");
	    	  quantity = rs.getInt("quantity");
	    	  
	    	  studio.storeClothingItem(new Apparel(id,itemName,brandName,color,size,price,quantity));
	      }
	      // close the connection.
	      st.close();
	    }
	    catch (Exception e){
	      System.err.println(e.getMessage()); 
	    }
		Main.InventoryScreen();
	}
}
