package com.fashion.commands;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

import com.fashion.Main;
import com.fashion.Studio;

public class UploadPhotoCmd implements Command {
	private Studio studio;
	public UploadPhotoCmd(Studio s) {
		this.studio = s;
	}

	@Override
	public String getDescription() {
		return "Upload Photo";
	}

	@Override
	public void execute() {
		Scanner in = new Scanner(System.in);
		System.out.println("Enter the model's name:");
		String name = in.nextLine();
		
		if(studio.getModel(name) != null) {
			System.out.println("Enter image path:");
			String img = in.nextLine();
			
			studio.getModel(name).setImage(img);
			System.out.println("Photo updated.");
			
			try{
			      // Step 1: "Load" the JDBC driver
					Class.forName("com.mysql.cj.jdbc.Driver");

			      // Step 2: Establish the connection to the database 
			      String url = "jdbc:mysql://localhost/fashion_studio"; 
			      Connection conn = DriverManager.getConnection(url,"root","");
			      //System.out.println("Connected.");
			      
			      // create a prepared statement from the connection
			      PreparedStatement ps = conn.prepareStatement("UPDATE models SET image = ? WHERE name = ?");
			      
			      ps.setString(1,img);
			      ps.setString(2,name);
			      
			      ps.execute();
			      conn.close();
			    }
			catch (Exception e){
			      System.err.println(e.getMessage()); 
			    }
		}
		else {
			System.out.println("Model wasn't found.");
		}
		Main.ModelScreen();

	}

}
