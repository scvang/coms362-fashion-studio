package com.fashion.commands;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import com.fashion.Main;
import com.fashion.Studio;
import com.fashion.pay.PayStubInfo;
import com.fashion.screens.ModelScreen;

public class AddModelCmd implements Command {
	private Studio studio;
	public AddModelCmd(Studio s) {
		this.studio = s;
	}

	@Override
	public String getDescription() {

		return "Add New Model";
	}

	@Override
	public void execute() {
		
		int eid = 0;
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
	      ResultSet rs = st.executeQuery("SELECT eid FROM models ORDER BY eid DESC LIMIT 1");
	      if(rs.next()) eid = rs.getInt("eid");
	      
	      // close the connection.
	      st.close();
	    }
	    catch (Exception e){
	      System.err.println(e.getMessage()); 
	    }
		++eid;
		Scanner in = new Scanner(System.in);
		
		System.out.println("Enter agent name:");
		String agent = in.nextLine();
		System.out.println("Enter model name:");
		String name = in.nextLine();
		System.out.println("Enter phone number:");
		String phoneNum = in.nextLine();
		studio.createModel(eid,agent,name,"Fashion Model",phoneNum,new PayStubInfo(0, 0, 0, 0));
		
		try{
		      // Step 1: "Load" the JDBC driver
				Class.forName("com.mysql.cj.jdbc.Driver");

		      // Step 2: Establish the connection to the database 
		      String url = "jdbc:mysql://localhost/fashion_studio"; 
		      Connection conn = DriverManager.getConnection(url,"root","");
		      //System.out.println("Connected.");
		      
		      // create a prepared statement from the connection
		      PreparedStatement ps = conn.prepareStatement("INSERT INTO models (eid,agent,name,jobTitle,phoneNum,image) " + "VALUES (?,?,?,?,?,?)");
		      
		      ps.setInt(1, eid);
		      ps.setString(2,agent);
		      ps.setString(3,name);
		      ps.setString(4, "Fashion Model");
		      ps.setString(5, phoneNum);
		      ps.setString(6, "default.jpg");
		      
		      ps.execute();
		      conn.close();
		    }
		    catch (Exception e){
		      System.err.println(e.getMessage()); 
		    }
		new ModelScreen(studio).execute();
	}

}
