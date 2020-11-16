package com.fashion.screens;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import com.fashion.Studio;
import com.fashion.commands.*;
import com.fashion.pay.PayStubInfo;

public class ModelScreen implements Command {
	private Studio studio;
	public ModelScreen(Studio s) {
		this.studio = s;
	}
	@Override
	public String getDescription() {

		return "Model Screen";
	}
	@Override
	public void execute() {
		
		// This retrieves a model list from the database.
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
	      ResultSet rs = st.executeQuery("SELECT * FROM models");
	      
	      studio.resetInventory();
	      while(rs.next()) {
	    	  int eid = rs.getInt("eid");
	    	  String agent = rs.getString("agent");
	    	  String name = rs.getString("name");
	    	  String jobTitle = rs.getString("jobTitle");
	    	  String phoneNum  = rs.getString("phoneNum");
	    	  String image  = rs.getString("image");
	    	  
	    	  studio.createModel(eid,agent,name,"Fashion Model",phoneNum,new PayStubInfo(0, 0, 0, 0));
	    	  studio.getModel(name).setImage(image);
	      }
	      // close the connection.
	      st.close();
	    }
	    catch (Exception e){
	      System.err.println(e.getMessage()); 
	    }
		
		CommandDisplay cmd = new CommandDisplay();
		cmd.addCommand(new ListModelsCmd(studio));
		cmd.addCommand(new ChangeApparelCmd(studio));
		cmd.addCommand(new UploadPhotoCmd(studio));
		cmd.addCommand(new AddModelCmd(studio));
		cmd.addCommand(new DisplayModelInfoCmd(studio));
		cmd.addCommand(new MainScreenCmd());
		cmd.displayCommands();
	}

}
