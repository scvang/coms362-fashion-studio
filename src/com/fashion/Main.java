package com.fashion;

import java.sql.*;
import com.fashion.apparel.Apparel;
import com.fashion.commands.*;
import com.fashion.employees.HumanResources;
import com.fashion.events.*;
import com.fashion.negotiations.ContractSession;
import com.fashion.pay.*;
import com.fashion.screens.*;
import com.fashion.shopping.ShoppingSession;
import java.util.ArrayList;
import javax.swing.*;
import java.util.Scanner;

public class Main extends JFrame {

	public static Studio studio;
	public static void main(String[] args) {
		
		// Create a studio.
		String company = "Fashion Inc";
		String address = "401 Somewhere Ave";
		String phone = "555-555-5555";
		double balance = 500000;

		studio = new Studio(company,address,phone, balance);
		
		// Execute the program.
		Screen();
	}
	
	/**
	 * @author Sebastian Vang
	 * The main screen that prompts first.
	 */
	public static void Screen() {
		CommandDisplay cmd = new CommandDisplay();
		cmd.addCommand(new InventoryScreen(studio));
		cmd.addCommand(new EventScreen(studio));
		cmd.displayCommands();
	}
	
	/**
	 * @author Sebastian Vang
	 * Inventory Screen.
	 */
	public static void InventoryScreen() {
		CommandDisplay cmd = new CommandDisplay();
		cmd.addCommand(new ViewClothingCmd(studio));
		cmd.addCommand(new StoreClothingCmd(studio));
		cmd.addCommand(new UpdateClothingCmd(studio));
		cmd.addCommand(new SearchClothingCmd(studio));
		cmd.addCommand(new RemoveClothingCmd(studio));
		cmd.addCommand(new MainScreenCmd());
		cmd.displayCommands();
	}
	
	/**
	 * @author Sebastian Vang
	 * Event Screen.
	 */
	public static void EventScreen() {
		CommandDisplay cmd = new CommandDisplay();
		cmd.addCommand(new ShowingVenueCmd(studio));
		cmd.addCommand(new DiningVenueCmd(studio));
		cmd.addCommand(new PartyVenueCmd(studio));
		cmd.addCommand(new CreateEventCmd(studio));
		cmd.addCommand(new DisplayEventsCmd(studio));
		cmd.addCommand(new MainScreenCmd());
		cmd.displayCommands();
	}

	/**
	 * @author Chad Morrow
	 * Employee Screen
	 */
	public static void employeeScreen() {
		String choice = "";
		Scanner in = new Scanner(System.in);
		while(!choice.equals("q")) {
			System.out.println(
					"Select an event ('q' to exit): \n" +
							"1) View Employees \n" +
							"2) Pay Employee \n" +
							"3) Go back \n"
			);

			choice = in.next();
			if(choice.equals("q") || choice.equals("'q'")) break;

			switch(Integer.parseInt(choice)){
				case 1:
					studio.getEmployees();
					System.out.println();
					System.out.println();
					break;
				case 2:
					Scanner in2 = new Scanner (System.in);
					System.out.println("Enter the employee id ('q' to exit): ");
					int eid = -1;
					while(eid == -1){
						String temp = in2.next();
						if(temp.equals("q")) {
							System.out.println();
							System.out.println();
							employeeScreen();
						}

						try {
							eid = Integer.parseInt(temp);
						} catch (NumberFormatException e) {
							System.out.println("Employee id must be a number ('q' to exit): ");
						}
					}

					PayStubInfo payStubInfo = studio.getEmployee(eid).getPayStubInfo();

					System.out.println("Did this employee recieve a bonus? (y/n) ('q' to exit)");
					String yesno = in2.next();

					double bonus = -1;
					if(yesno.equals("y")) {
						System.out.println("How much did they recieve? ('q' to exit)");
						while(bonus == -1){
							String temp = in2.next();
							if(temp.equals("q")) {
								System.out.println();
								System.out.println();
								employeeScreen();
							}

							try {
								bonus = Double.parseDouble(temp);
							} catch (NumberFormatException e) {
								System.out.println("Bonus must be a number ('q' to exit): ");
							}
						}
					}
					payStubInfo.setBonus(bonus);

					if(studio.payEmployee(eid, payStubInfo)){
						System.out.println(studio.getEmployee(eid).getName() + " was paid!");
					} else {
						System.out.println("Error paying employee, try again later");
					}
					System.out.println();
					System.out.println();
					break;
				case 3:
					//mainScreen();
			}
		}
	}
	
	/**
	 * @author Emily Young
	 * Advertisement Screen
	 */
	public static void advertisementScreen() {
		String choice = "";
		Scanner in = new Scanner(System.in);
		while(!choice.equals("q")) {
			System.out.println(
					"What do you need to do? ('q' to exit): \n" +
							"1) View Current Ads \n" +
							"2) Create New Ad \n" +
							"3) Go back \n"
			);
			choice = in.next();
			if(choice.equals("q") || choice.equals("'q'")) break;
			
			switch(Integer.parseInt(choice)){
				case 1:
					studio.getAd();
				break;
				
				case 2:
					System.out.println("What is the event ID?: ");
					int eid = in.nextInt();
					System.out.println("What type of advertisement do you need? (paper or video)");
					String adType = in.next();
					System.out.println("What is the name of the event?: ");
					String eventName = in.next() + in.next();
					System.out.println("Where is the location of the event?: ");
					String loc = in.next();
					System.out.println("What is the time of the event?: ");
					String time = in.next();
					System.out.println("What is the the number we should contact?: ");
					String contactInfo = in.next();
					Advertisement advert = new Advertisement(eid, eventName, loc, time, contactInfo);
					if(adType.equals("paper")) {
//						studio.addAd(eid, eventName, loc, time, contactInfo);
						System.out.println("Advertisement Created!");
					} else if(adType.equals("video")) {
//						advert.createAdVideo();
//						studio.addAd(eid, eventName, loc, time, contactInfo);
						System.out.println("Advertisement Created!");
					} else {
						System.out.print("We don't currently support that type of advertisement at this time.");
					}
				break;
				
				case 3:
					//mainScreen();
			}
		}
		in.close();
	}
	
	public static void businessScreen() {
		String choice = "";
		Scanner in = new Scanner(System.in);

		while (!choice.equals("q")) {
			System.out.println(
					"Select an event ('q' to exit): \n" + "1) View Business Records \n" + "2) Hire a Business \n"
							+ "3) Confirm a Business \n" + "5) Go back \n");

			choice = in.next();
			if (choice.equals("q") || choice.equals("'q'"))
				break;

			switch (Integer.parseInt(choice)) {
			case 1:
				HumanResources.getServices();
			break;

			case 2:
				System.out.println("What is the service ID?");
				int sid = in.nextInt();
				in.nextLine();
				System.out.println("What is the name of the Business?");
				String name = in.nextLine();
				System.out.println("What is the address?");
				String loc = in.nextLine();
				System.out.println("What is the service requested?");
				String service = in.nextLine();
				System.out.println("Who do we contact?");
				String repName = in.nextLine();
				System.out.println("Please provice their phone number.");
				String contactInfo = in.nextLine();
				System.out.println("How much are they charging?");
				double salary = in.nextDouble();
				in.nextLine();
				HumanResources.hireBusiness(sid, name, loc, service, repName, contactInfo, salary);
			break;

			case 3:
				for (int i = 0; i < HumanResources.servicesUsed.size(); i++) {
					if (HumanResources.servicesUsed.get(i).hasBeenContacted() == false) {
						HumanResources.getServiceRequests();
						System.out.println("Would you like to contact them now? ('y' or 'n')\n");
						String yorn = in.next();
						in.nextLine();
						if (yorn.equals("y")) {
							HumanResources.servicesUsed.get(i).contactBusiness(HumanResources.servicesUsed.get(i));
							System.out.println("Service confirmed!\n");
						} else {
							System.out.println("Please be sure to contact them at a different date.\n");
						}
					} else {
						HumanResources.getServiceRequests();
					}
				}
			break;
			
			case 4:
				//TODO
			break;
			
			case 5:
				//mainScreen();
			break;
			}
		}
	}
	
	/**
	 * @author Sebastian Vang
	 * Model screen.
	 */
	public static void modelScreen() {
		String choice = "";
		Scanner in = new Scanner(System.in);
		while(!choice.equals("q")) {
			System.out.println(
			"Select an option ('q' to exit): \n" +
			"1) Check list of models \n" +
			"2) Change apparel \n" +
			"3) Upload photo \n" +
			"4) Add model \n" +
			"5) Display model information \n" +
			"6) Go back \n"
			);
			
			choice = in.next();
			if(choice.equals("q") || choice.equals("'q'")) break;
			else in.nextLine(); // Clear the buffer.
			
			int eid = 0;
			String agent = "";
			String name = "";
			String jobTitle = "";
			String phoneNum = "";
			
			switch(Integer.parseInt(choice)){
				case 1:
					studio.displayModels();
				break;
				
				case 2:
					System.out.println("Which model do you want to change?");
					name = in.next();
					if(!studio.doesModelExist(name)) {
						System.out.println("Model was not found, try again.");
						break;
					}
					changeApparelScreen(name);
					
				break;
				
				case 3:
					System.out.println("Enter the model's name:");
					name = in.nextLine();
					
					if(studio.getModel(name) != null) {
						System.out.println("Enter image path:");
						String img = in.nextLine();
						
						studio.getModel(name).setImage(img);
					}
					else {
						System.out.println("Model wasn't found.");
					}
					
					System.out.println("Information updated.");
				break;
				
				case 4:
					System.out.println("Enter agent name:");
					agent = in.nextLine();
					System.out.println("Enter model name:");
					name = in.nextLine();
					System.out.println("Enter phone number:");
					phoneNum = in.nextLine();
					studio.createModel(eid,agent,name,"Fashion Model",phoneNum,new PayStubInfo(0, 0, 0, 0)); // Probably need to change this.
				break;
				
				case 5:
					System.out.println("Enter the model's name:");
					name = in.nextLine();
					
					if(studio.getModel(name) != null) {
						String description = "Model Name: " + studio.getModel(name).getName() + "<br/>" + 
								"Agent: " + studio.getModel(name).getAgent() + "<br/>" +
								"Phone: " + studio.getModel(name).getPhoneNum() + "<br/>" +
								"Salary: " + studio.getModel(name).getPayStubInfo().getSalary() + "<br/>" +
								"Head: " + studio.getModel(name).getHeadPiece().getItemName() + ", Brand: " + studio.getModel(name).getHeadPiece().getBrandName() + ", Color: " + studio.getModel(name).getHeadPiece().getColor() + "<br/>" +
								"Top: " + studio.getModel(name).getTopPiece().getItemName() + ", Brand: " + studio.getModel(name).getTopPiece().getBrandName() + ", Color: " + studio.getModel(name).getTopPiece().getColor() + "<br/>" +
								"Bottoms: " + studio.getModel(name).getBotPiece().getItemName() + ", Brand: " + studio.getModel(name).getBotPiece().getBrandName() + ", Color: " + studio.getModel(name).getBotPiece().getColor() + "<br/>" +
								"Leggings: " + studio.getModel(name).getLegsPiece().getItemName() + ", Brand: " + studio.getModel(name).getLegsPiece().getBrandName() + ", Color: " + studio.getModel(name).getLegsPiece().getColor() + "<br/>" +
								"Shoes: " + studio.getModel(name).getShoes().getItemName() + ", Brand: " + studio.getModel(name).getShoes().getBrandName() + ", Color: " + studio.getModel(name).getShoes().getColor() + "<br/>" +
								"Accessory: " + studio.getModel(name).getAcc().getItemName() + ", Brand: " + studio.getModel(name).getAcc().getBrandName() + ", Color: " + studio.getModel(name).getAcc().getColor() + "<br/>";
						studio.getModel(name).setDescription(description);
						studio.getModel(name).displayInfo();
					}
					else {
						System.out.println("Model wasn't found.");
					}
					
				break;
				
				case 6:
					//mainScreen();
				break;
			}
		}
		in.close();
		
	}
	
	/**
	 * @author Sebastian Vang
	 * Helper method to create apparel.
	 * @return
	 */
	public static Apparel makeApparel() {
		System.out.println("Enter the item id:");
		Scanner in = new Scanner(System.in);
		int id = in.nextInt(); in.nextLine();
		
		System.out.println("Enter the item name:");
		String name = in.nextLine();
		
		System.out.println("Enter the brand name:");
		String brand = in.nextLine();
		
		System.out.println("Enter the color:");
		String color = in.nextLine();
		
		System.out.println("Enter the size:");
		String size = in.nextLine();
		
		System.out.println("Enter the price:");
		double price = in.nextDouble(); in.nextLine();
		
		System.out.println("Enter the quantity:");
		int quantity = in.nextInt(); in.nextLine();
		
		Apparel item = new Apparel(id,name, brand, color,size,price,quantity);
		
		return item;
	}
	
	/**
	 * @author Sebastian Vang
	 * Change Apparel Screen
	 * @param modelName
	 */
	public static void changeApparelScreen(String modelName) {
		String choice = "";
		Scanner in = new Scanner(System.in);
		while(!choice.equals("q")) {
			System.out.println(
			"Select an option ('q' to exit): \n" +
			"1) Head \n" +
			"2) Top \n" +
			"3) Bottom \n" +
			"4) Leggings \n" +
			"5) Shoes \n" +
			"6) Accessory \n" +
			"7) Go back \n"
			);
			
			choice = in.next();
			if(choice.equals("q") || choice.equals("'q'")) break;
			else in.nextLine();
			
			int id = 0;
			String name = "";
			String brand = "";
			String color = "";
			String size = "";
			double price = 0;
			int quantity = 0;
			Apparel item;
			
			switch(Integer.parseInt(choice)){
				case 1:
					item = makeApparel();
					studio.changeHead(modelName,item);
				break;
				
				case 2:
					item = makeApparel();
					studio.changeTop(modelName,item);
				break;
				
				case 3:
					item = makeApparel();
					studio.changeBot(modelName,item);
				break;
				
				case 4:
					item = makeApparel();
					studio.changeLegs(modelName,item);
				break;
				
				case 5:
					item = makeApparel();
					studio.changeShoes(modelName,item);
				break;
				
				case 6:
					item = makeApparel();
					studio.changeAcc(modelName,item);
				break;
				
				case 7:
					modelScreen();
				break;
			}
		}
		in.close();
		
	}
	
	/**
	 * @author Sebastian Vang
	 * Event screen.
	 */
	public static void eventScreen() {
		
		// Fetch the data from the database.
		String type;
		String name;
		String date;
		String time;
		
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
		      ResultSet rs = st.executeQuery("SELECT * FROM events");
		      
		      // Clear the event list before fetching to avoid duplicating.
		      studio.resetEventList();
		      while(rs.next()) {
		    	  type = rs.getString("type");
		    	  name = rs.getString("name");
		    	  date = rs.getString("date");
		    	  time = rs.getString("time");
		    	  
		    	  studio.createEvent(type,name,date,time);
		      }
		      // close the connection.
		      st.close();
		    }
		catch (Exception e){
		      System.err.println(e.getMessage()); 
		    }
		
		String choice = "";
		Scanner in = new Scanner(System.in);
		while(!choice.equals("q")) {
			System.out.println(
			"Select an event ('q' to exit): \n" +
			"1) Showing \n" +
			"2) Dining \n" +
			"3) Party \n" +
			"4) Create new Event \n" +
			"5) Display events \n" +
			"6) Go back"
			);
			
			choice = in.next();
			if(choice.equals("q") || choice.equals("'q'")) break;
			else in.nextLine(); // Clear the buffer.
			
			switch(Integer.parseInt(choice)){
				case 1:
					showingScreen();
				break;
				
				case 2:
					diningScreen();
				break;
				
				case 3:
					partyScreen();
				break;
				
				case 4:
					System.out.println("What type of event (showing, dining, party)? ");
					type = in.nextLine();
					
					System.out.println("Event name?");
					name = in.nextLine();
					
					System.out.println("What date (mm-dd-yy)? ");
					date = in.nextLine();
					
					System.out.println("What time (hh:mm am/pm)? ");
					time = in.nextLine();
					
					studio.createEvent(type,name,date,time);
					
					// Establish a connection to the database test.
					try{
				      // Step 1: "Load" the JDBC driver
						Class.forName("com.mysql.cj.jdbc.Driver");

				      // Step 2: Establish the connection to the database 
				      String url = "jdbc:mysql://localhost/fashion_studio"; 
				      Connection conn = DriverManager.getConnection(url,"root","");
				      //System.out.println("Connected.");
				      
				      // create a prepared statement from the connection
				      PreparedStatement ps = conn.prepareStatement("INSERT INTO events (type,name,date,time) " + "VALUES (?,?,?,?)");
				      
				      ps.setString(1, type);
				      ps.setString(2, name);
				      ps.setString(3, date);
				      ps.setString(4, time);
				      
				      ps.execute();
				      conn.close();
				    }
				    catch (Exception e){
				      System.err.println(e.getMessage()); 
				    }
					
				break;
				
				case 6:
					//mainScreen();
				break;
				
				case 5:
					studio.displayEvents();
			}
		}
		in.close();
	}
	
	/**
	 * @author Sebastian Vang
	 * Showing screen.
	 */
	public static void showingScreen() {

		System.out.println("Choose a showing event:");
		
		int count = 1;
		ArrayList<Showing> showingList = new ArrayList<>();
		for(int i = 0; i < studio.getEventList().size(); ++i) {
			if(studio.getEventList().get(i) instanceof Showing) {
				System.out.println(count + ") " + studio.getEventList().get(i).getEvent());
				++count;
				showingList.add((Showing)studio.getEventList().get(i));
			}
		}
		if(showingList.isEmpty()) {
			System.out.println("There are no showings!");
			eventScreen();
		}
		Scanner in3 = new Scanner(System.in);
		int i = in3.nextInt();
		
		String eventName = showingList.get(i-1).getEvent();
		
		// This retrieves a showing venue list from the database.
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
	      ResultSet rs = st.executeQuery("SELECT * FROM showing");
	      
	      while(rs.next()) {
	    	  String eventNameD = rs.getString("event");
	    	  String nameD = rs.getString("name");
	    	  String dateD = rs.getString("date");
	    	  String timeD = rs.getString("time");
	    	  String seatD = rs.getString("seat");
	    	  
	    	  if(studio.reserveSeat(studio.getEvent(eventNameD),seatD,nameD,dateD,timeD));
	      }
	      // close the connection.
	      st.close();
	    }
	    catch (Exception e){
	      System.err.println(e.getMessage()); 
	    }
		
		String choice = "";
		Scanner in = new Scanner(System.in);
		while(!choice.equals("q")) {
			System.out.println(
			"Select a choice ('q' to exit): \n" +
			"1) Display available seats \n" +
			"2) Reserve a seat \n" +
			"3) Check a seat \n" +
			"4) Refund \n" +
			"5) Go back \n"
			);
			
			choice = in.next();
			if(choice.equals("q") || choice.equals("'q'")) break;
			else in.nextLine(); // Clear the buffer.
			
			String name = "";
			
			switch(Integer.parseInt(choice)){
				case 1:
					studio.displaySeats(studio.getEvent(eventName));
				break;
				
				case 2:
					if(studio.isShowingFull(studio.getEvent(eventName))) {
						System.out.println("No available seats.");
						break;
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
					      PreparedStatement ps = conn.prepareStatement("INSERT INTO showing (event,name,date,time,seat)" + "VALUES (?,?,?,?,?)");
					      
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
				break;
				
				case 3:
					System.out.println("Enter your customer name:");
					name = in.nextLine();
					if(studio.hasSeatReservation(name,studio.getEvent(eventName))) {
						System.out.println(
						"Name: " + studio.getSeat(name,studio.getEvent(eventName)).getCustomerName() + "\n" +
						"Date: " + studio.getSeat(name,studio.getEvent(eventName)).getDate() + "\n" + 
						"Time: " + studio.getSeat(name,studio.getEvent(eventName)).getTime() + "\n" + 
						"Seat: " + studio.getSeat(name,studio.getEvent(eventName)).getSeatNum() + "\n"
						);
					}
					else {
						System.out.println("No reservation found for " + name);
					}
				break;
				
				case 4:
					System.out.println("Enter the customer name:");
					name = in.nextLine();
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
					
				break;
				
				case 5:
					eventScreen();
				break;
			}
		}
		in.close();
	}
	
	/**
	 * @author Sebastian Vang
	 * Dining screen.
	 */
	public static void diningScreen() {
		
		System.out.println("Choose a dining event:");
		
		int count = 1;
		ArrayList<Dining> list = new ArrayList<>();
		for(int i = 0; i < studio.getEventList().size(); ++i) {
			if(studio.getEventList().get(i) instanceof Dining) {
				System.out.println(count + ") " + studio.getEventList().get(i).getEvent());
				++count;
				list.add((Dining)studio.getEventList().get(i));
			}
		}
		if(list.isEmpty()) {
			System.out.println("There are no dinings!");
			eventScreen();
		}
		Scanner in = new Scanner(System.in);
		int i = in.nextInt();
		
		String eventName = list.get(i-1).getEvent();
		
		// This retrieves a dining venue list from the database.
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
	      ResultSet rs = st.executeQuery("SELECT * FROM dining");
	      
	      while(rs.next()) {
	    	  String eventNameD = rs.getString("event");
	    	  String nameD = rs.getString("name");
	    	  String dateD = rs.getString("date");
	    	  String timeD = rs.getString("time");
	    	  String tableD = rs.getString("table");
	    	  
	    	  if(studio.reserveTable(studio.getEvent(eventNameD),tableD,nameD,dateD,timeD));
	      }
	      // close the connection.
	      st.close();
	    }
	    catch (Exception e){
	      System.err.println(e.getMessage()); 
	    }
				
		String choice = "";
		while(!choice.equals("q")) {
			System.out.println(
			"Select a choice ('q' to exit): \n" +
			"1) Display available tables \n" +
			"2) Reserve a table \n" +
			"3) Check a table \n" +
			"4) Refund \n" +
			"5) Go back \n"
			);
			
			choice = in.next();
			if(choice.equals("q") || choice.equals("'q'")) break;
			else in.nextLine();
			
			String name = "";
			
			switch(Integer.parseInt(choice)){
				case 1:
					studio.displayTables(studio.getEvent(eventName));
				break;
				
				case 2:
					if(studio.isDiningFull(studio.getEvent(eventName))) {
						System.out.println("There are no available tables.");
						break;
					}
					System.out.println("Enter your customer name: ");
					String customerName = in.nextLine();
					System.out.println("Enter your desired table (1~20): ");
					String table = in.nextLine();
					System.out.println("Enter your desired date (mm-dd-yy): ");
					String date = in.nextLine();
					System.out.println("Enter your desired time (hh:mm am/pm): ");
					String time = in.nextLine();
					
					if(studio.reserveTable(studio.getEvent(eventName),table,customerName,date,time)) {
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
					      PreparedStatement ps = conn.prepareStatement("INSERT INTO dining (eventName,name,date,time,table)" + "VALUES (?,?,?,?)");
					      
					      ps.setString(1, eventName);
					      ps.setString(2, customerName);
					      ps.setString(3, date);
					      ps.setString(4, time);
					      ps.setString(5, table);
					      
					      ps.execute();
					      conn.close();
					    }
					    catch (Exception e){
					      System.err.println(e.getMessage()); 
					    }
						System.out.println("Reservation was added into the database.");
						
					}
					else {
						System.out.println("Table reservation failed.");
					}
					
				break;
				
				case 3:
					System.out.println("Enter your customer name:");
					name = in.nextLine();
					if(studio.hasTableReservation(name,studio.getEvent(eventName))) {
						System.out.println(
						"Name: " + studio.getTable(name,studio.getEvent(eventName)).getCustomerName() + "\n" +
						"Date: " + studio.getTable(name,studio.getEvent(eventName)).getDate() + "\n" + 
						"Time: " + studio.getTable(name,studio.getEvent(eventName)).getTime() + "\n" + 
						"Table: " + studio.getTable(name,studio.getEvent(eventName)).getTableNum() + "\n"
						);
					}
					else {
						System.out.println("No reservation found for " + name);
					}
				break;
				
				case 4:
					System.out.println("Enter the customer name:");
					name = in.nextLine();
					if(studio.hasTableReservation(name,studio.getEvent(eventName))) {
						System.out.println("Reservation found.");
						studio.removeTableReservation(name,studio.getEvent(eventName));
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
						      PreparedStatement ps = conn.prepareStatement("DELETE FROM party WHERE event = ? AND name = ?");
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
				break;
				
				case 5:
					eventScreen();
				break;
			}
		}
		in.close();
	}
	
	/**
	 * @author Sebastian Vang
	 * Party screen.
	 */
	public static void partyScreen() {
		
System.out.println("Choose a party event:");
		
		int count = 1;
		ArrayList<Party> list = new ArrayList<>();
		for(int i = 0; i < studio.getEventList().size(); ++i) {
			if(studio.getEventList().get(i) instanceof Party) {
				System.out.println(count + ") " + studio.getEventList().get(i).getEvent());
				++count;
				list.add((Party)studio.getEventList().get(i));
			}
		}
		if(list.isEmpty()) {
			System.out.println("There are no parties!");
			eventScreen();
		}
		Scanner in3 = new Scanner(System.in);
		int i = in3.nextInt();
		
		String eventName = list.get(i-1).getEvent();
		
		// This retrieves a party venue list from the database.
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
			      ResultSet rs = st.executeQuery("SELECT * FROM party");
			      
			      while(rs.next()) {
			    	  String eventNameD = rs.getString("event");
			    	  String nameD = rs.getString("name");
			    	  String dateD = rs.getString("date");
			    	  String timeD = rs.getString("time");;
			    	  
			    	  if(eventNameD == null)break;
			    	  if(studio.reserveBadge(studio.getEvent(eventNameD),nameD,dateD,timeD));
			      }
			      // close the connection.
			      st.close();
			    }
			    catch (Exception e){
			      System.err.println(e.getMessage()); 
			    }
		
		String choice = "";
		Scanner in = new Scanner(System.in);
		while(!choice.equals("q")) {
			System.out.println(
			"Select a choice ('q' to exit): \n" +
			"1) Display number of attendees \n" +
			"2) Reserve a badge \n" +
			"3) Check reservation \n" +
			"4) Refund \n" +
			"5) Go back \n"
			);
			
			choice = in.next();
			if(choice.equals("q") || choice.equals("'q'")) break;
			else in.nextLine();
			
			String name = "";
			
			switch(Integer.parseInt(choice)){
				case 1:
					System.out.println("Attendees:");
					ArrayList<String> attList = studio.getAttendeesList(studio.getEvent(eventName));
					for(String s : attList) {
						System.out.println(s);
					}
					System.out.println("There are: " + studio.getNumOfAttendees(studio.getEvent(eventName)) + " number of attendees.");
				break;
				
				case 2:
					if(studio.isPartyFull(studio.getEvent(eventName))) {
						System.out.println("The venue is full.");
						break;
					}
					
					System.out.println("Enter your customer name: ");
					String customerName = in.nextLine();
					System.out.println("Enter your desired date (mm-dd-yy): ");
					String date = in.nextLine();
					System.out.println("Enter your desired time (hh:mm am/pm): ");
					String time = in.nextLine();
					
					studio.reserveBadge(studio.getEvent(eventName),customerName,date,time);
					
					// Establish a connection to the database test.
					try{
				      // Step 1: "Load" the JDBC driver
						Class.forName("com.mysql.cj.jdbc.Driver");

				      // Step 2: Establish the connection to the database 
				      String url = "jdbc:mysql://localhost/fashion_studio"; 
				      Connection conn = DriverManager.getConnection(url,"root","");
				      //System.out.println("Connected.");
				      
				      // create a prepared statement from the connection
				      PreparedStatement ps = conn.prepareStatement("INSERT INTO party (event,name,date,time)" + "VALUES (?,?,?,?)");
				      
				      ps.setString(1, eventName);
				      ps.setString(2, customerName);
				      ps.setString(3, date);
				      ps.setString(4, time);;
				      
				      ps.execute();
				      conn.close();
				    }
				    catch (Exception e){
				      System.err.println(e.getMessage()); 
				    }
					System.out.println("Reservation was added into the database.");
					
				break;
				
				case 3:
					System.out.println("Enter your customer name:");
					name = in.nextLine();
					if(studio.hasBadgeReservation(name,studio.getEvent(eventName))) {
						System.out.println(
								"Name: " + studio.getBadge(name,studio.getEvent(eventName)).getName() + "\n" +
								"Date: " + studio.getBadge(name,studio.getEvent(eventName)).getDate() + "\n" + 
								"Time: " + studio.getBadge(name,studio.getEvent(eventName)).getTime() + "\n"
								);
					}
					else {
						System.out.println("No reservation found.");
					}
				break;
				
				case 4:
					System.out.println("Enter the customer name:");
					name = in.nextLine();
					if(studio.removeBadgeReservation(name,studio.getEvent(eventName))) {
					
					// Remove the entry from database.
					try{
					      // Step 1: "Load" the JDBC driver
							Class.forName("com.mysql.cj.jdbc.Driver");

					      // Step 2: Establish the connection to the database 
					      String url = "jdbc:mysql://localhost/fashion_studio"; 
					      Connection conn = DriverManager.getConnection(url,"root","");
					      //System.out.println("Connected.");
					      
					      // create a prepared statement from the connection
					      PreparedStatement ps = conn.prepareStatement("DELETE FROM party WHERE event = ? AND name = ?");
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
						System.out.println("Reservation was not found.");
					}
				break;
				
				case 5:
					eventScreen();
				break;
			}
		}
		in.close();
		
	}

	/**
	 * @author Chad Morrow
	 * Promotion Screen
	 */
	public static void promotionScreen() {
		String choice = "";
		Scanner in = new Scanner(System.in);
		while(!choice.equals("q")) {
			System.out.println(
					"Select a choice ('q' to exit): \n" +
							"1) Display upcoming events \n" +
							"2) Check available promotions \n" +
							"3) Reserve a promotion spot \n" +
							"4) Go back \n"
			);

			choice = in.next();
			if(choice.equals("q") || choice.equals("'q'")) break;

			switch(Integer.parseInt(choice)){
				case 1:
					studio.displayEvents();
					System.out.println();
					System.out.println();
					break;
				case 2:
					Scanner in2 = new Scanner (System.in);
					System.out.println("Enter the event you'd like to view open promotion spots ('q' to exit): ");
					String eventName = in2.nextLine().trim();
					if(eventName.equals("q")) {
						System.out.println();
						System.out.println();
						promotionScreen();
					}

					while(studio.getEvent(eventName) == null){
						System.out.println("Sorry! we could not find your event, please re-enter a new event ('q' to exit): ");
						eventName = in2.nextLine().trim();
						if(eventName.equals("q")) {
							System.out.println();
							System.out.println();
							promotionScreen();
						}
					}

					if(studio.getEvent(eventName).isPromotionSpotOpen(1)){
						System.out.println("1:  Open");
					} else {
						System.out.println("1:  Taken");
					}

					if(studio.getEvent(eventName).isPromotionSpotOpen(2)){
						System.out.println("2:  Open");
					} else {
						System.out.println("2:  Taken");
					}

					if(studio.getEvent(eventName).isPromotionSpotOpen(3)){
						System.out.println("3:  Open");
					} else {
						System.out.println("3:  Taken");
					}

					if(studio.getEvent(eventName).isPromotionSpotOpen(4)){
						System.out.println("4:  Open");
					} else {
						System.out.println("4:  Taken");
					}

					if(studio.getEvent(eventName).isPromotionSpotOpen(5)){
						System.out.println("5:  Open");
					} else {
						System.out.println("5:  Taken");
					}

					if(studio.getEvent(eventName).isPromotionSpotOpen(6)){
						System.out.println("6:  Open");
					} else {
						System.out.println("6:  Taken");
					}

					if(studio.getEvent(eventName).isPromotionSpotOpen(7)){
						System.out.println("7:  Open");
					} else {
						System.out.println("7:  Taken");
					}

					if(studio.getEvent(eventName).isPromotionSpotOpen(8)){
						System.out.println("8:  Open");
					} else {
						System.out.println("8:  Taken");
					}

					if(studio.getEvent(eventName).isPromotionSpotOpen(9)){
						System.out.println("9:  Open");
					} else {
						System.out.println("9:  Taken");
					}

					if(studio.getEvent(eventName).isPromotionSpotOpen(10)){
						System.out.println("10: Open");
					} else {
						System.out.println("10: Taken");
					}

					System.out.println();
					System.out.println();
					break;
				case 3:
					Scanner in3 = new Scanner (System.in);
					System.out.println("Enter the event  ('q' to exit): ");
					String eventNameReserve = in3.nextLine();
					if(eventNameReserve.equals("q")) {
						System.out.println();
						System.out.println();
						promotionScreen();
					}
					while(studio.getEvent(eventNameReserve) == null) {
						System.out.println("Sorry! we could not find your event, please re-enter a new event  ('q' to exit): ");
						eventNameReserve = in3.nextLine().trim();
						if(eventNameReserve.equals("q")) {
							System.out.println();
							System.out.println();
							promotionScreen();
						}
					}

					System.out.println("Enter your business name ('q' to exit): ");
					String businessName = in3.nextLine();
					if(businessName.equals("q")) {
						System.out.println();
						System.out.println();
						promotionScreen();
					}

					System.out.println("What would you like it to say? ('q' to exit): ");
					String text = in3.nextLine();
					if(text.equals("q")) {
						System.out.println();
						System.out.println();
						promotionScreen();
					}

					System.out.println("Enter your desired promotion location ('q' to exit): ");
					int location = 0;
					while(location == 0){
						String temp = in3.next();
						if(temp.equals("q")) {
							System.out.println();
							System.out.println();
							promotionScreen();
						}

						try {
							location = Integer.parseInt(temp);
						} catch (NumberFormatException e) {
							System.out.println("Please enter a location (1-10). Open spots are viewable from " +
									"the promotion start screen ('q' to exit)");
						}
					}

					System.out.println("What is your offer ('q' to exit): ");
					double dollarAmount = 0.0;
					while(dollarAmount == 0.0){
						String temp = in3.next();
						if(temp.equals("q")) {
							System.out.println();
							System.out.println();
							promotionScreen();
						}

						try {
							dollarAmount = Double.parseDouble(temp);
						} catch (NumberFormatException e) {
							System.out.println("Please enter a valid dollar amount ('q' to exit).");
						}
					}

					System.out.println("What is your card number ('q' to exit): ");
					String cardNum = "";
					while(cardNum.isEmpty()) {
						String temp = in3.next();
						if (temp.equals("q")) {
							System.out.println();
							System.out.println();
							promotionScreen();
						}

						if (temp.length() < 16) {
							System.out.println("Please enter a valid card number ('q' to exit).");
						}
						cardNum = temp;
					}

					System.out.println("What is your card month ('q' to exit): ");
					String cardMonth = "";
					while(cardMonth.isEmpty()){
						String temp = in3.next();
						if(temp.equals("q")) {
							System.out.println();
							System.out.println();
							promotionScreen();
						}

						if(temp.length() < 2) {
							System.out.println("Please enter a valid card month ('q' to exit).");
						} else  {
							try {
								cardMonth = temp;
							} catch (NumberFormatException e) {
								System.out.println("Please enter a valid card month ('q' to exit).");
							}
						}
					}

					System.out.println("What is your card year ('q' to exit): ");
					String cardYear = "";
					while(cardYear.isEmpty()){
						String temp = in3.next();
						if(temp.equals("q")) {
							System.out.println();
							System.out.println();
							promotionScreen();
						}

						if(temp.length() < 2) {
							System.out.println("Please enter a valid card year ('q' to exit).");
						} else  {
							try {
								cardYear = temp;
							} catch (NumberFormatException e) {
								System.out.println("Please enter a valid card year ('q' to exit).");
							}
						}
					}

					System.out.println("What is your card code ('q' to exit): ");
					String cardCode = "";
					while(cardCode.isEmpty()){
						String temp = in3.next();
						if(temp.equals("q")) {
							System.out.println();
							System.out.println();
							promotionScreen();
						}

						if(temp.length() != 3) {
							System.out.println("Please enter a valid card code ('q' to exit).");
						} else  {
							try {
								cardCode = temp;
							} catch (NumberFormatException e) {
								System.out.println("Please enter a valid card code ('q' to exit).");
							}
						}
					}

					if(studio.getEvent(eventNameReserve).addPromotion(businessName, text, location, dollarAmount, new Card(cardNum, cardMonth, cardYear, cardCode, null))) {
						System.out.println("Promotion added!");
					}

					System.out.println();
					System.out.println();
					break;
				case 4:
					//mainScreen();
			}
		}
	}

	/**
	 * @author Chad Morrow
	 * Shopping Screen
	 */
	public static void shoppingScreen() {
		ShoppingSession shoppingSession = new ShoppingSession();

		String choice = "";
		Scanner in = new Scanner(System.in);
		while(!choice.equals("q")) {
			System.out.println(
					"Select a choice ('q' to exit): \n" +
							"1) Display apparel information \n" +
							"2) Show apparel \n" +
							"3) Add item to your cart \n" +
							"4) Go to cart \n" +
							"5) Go back \n"
			);

			choice = in.next();
			if(choice.equals("q") || choice.equals("'q'")) break;

			switch(Integer.parseInt(choice)){
				case 1:
					System.out.println();
					shoppingSession.displayApparel();
					System.out.println();
					break;
				case 2:
					System.out.println();
					Scanner in2 = new Scanner (System.in);
					System.out.println("What apparel item would you like to view? ('q' to exit): ");
					String itemName = in2.nextLine().trim();
					if(itemName.equals("q")) {
						System.out.println();
						shoppingScreen();
					}
					shoppingSession.apparelImage(itemName);
					System.out.println();
					break;
				case 3:
					System.out.println();
					Scanner in3 = new Scanner (System.in);

					System.out.println("What item would you like to add to your cart? ('q' to exit): ");
					itemName = in3.nextLine().trim();
					if(itemName.equals("q")) {
						System.out.println();
						shoppingScreen();
					}

					System.out.println("What size? ('q' to exit): ");
					String size = in3.nextLine().trim();
					if(size.equals("q")) {
						System.out.println();
						shoppingScreen();
					}
					shoppingSession.getCart().addItem(new Apparel(size, itemName));
					System.out.println();
					break;
				case 4:
					System.out.println(shoppingSession.getCart().toString());
					System.out.println();

					Scanner in4 = new Scanner (System.in);

					if(!shoppingSession.getCart().getItems().isEmpty()) {
						System.out.println("Edit Cart? (y/n): ");

						String response = in4.nextLine().trim();
						if (response.equals("y")) {
							System.out.println("What item would you like to remove from your cart? ('q' to exit): ");
							itemName = in4.nextLine().trim();
							if(itemName.equals("q")) {
								System.out.println();
								shoppingScreen();
							}

							System.out.println("What size? ('q' to exit): ");
							size = in4.nextLine().trim();
							if(size.equals("q")) {
								System.out.println();
								shoppingScreen();
							}
							shoppingSession.getCart().removeItem(new Apparel(size, itemName));

							if(shoppingSession.getCart().getItems().size() == 0)
								System.out.println();
								break;
						} else if (response.equals("n")) {
							System.out.println();
						}

						System.out.println("Checkout? (y/n): ");
						response = in4.nextLine().trim();
						if (response.equals("y")) {
							System.out.println("Card Number: ");
							response = in4.nextLine().trim();
							shoppingSession.getCard().setCardNum(response);

							System.out.println("Card Exp Month: ");
							response = in4.nextLine().trim();
							shoppingSession.getCard().setEndMonth(response);

							System.out.println("Card Exp Year: ");
							response = in4.nextLine().trim();
							shoppingSession.getCard().setEndYear(response);

							System.out.println("Card Security Code: ");
							response = in4.nextLine().trim();
							shoppingSession.getCard().setCode(response);

							System.out.println("Shipping Address: ");
							response = in4.nextLine().trim();
							shoppingSession.setShippingAddress(response);

							System.out.println("Billing Address: ");
							response = in4.nextLine().trim();
							shoppingSession.getCard().setBillingAddress(response);
							shoppingSession.setBillingAddress(response);

							System.out.println("Purchase? (y/n): ");
							response = in4.nextLine().trim();
							if (response.equals("y")) {
								shoppingSession.updateInventory();
								System.out.println();
							} else if (response.equals("n")) {
								System.out.println();
								shoppingScreen();
							}
						} else if (response.equals("n")) {
							System.out.println();
							shoppingScreen();
						}
					}

					break;
				case 5:
					//mainScreen();
			}
		}
	}

	/**
	 * @author Chad Morrow
	 * Contract Screen
	 */
	public static void contractScreen() {
		ContractSession contractSession = new ContractSession();

		String choice = "";
		Scanner in = new Scanner(System.in);
		while(!choice.equals("q")) {
			System.out.println(
					"Select a choice ('q' to exit): \n" +
							"1) Begin contract negotiation \n" +
							"2) View old contracts \n" +
							"3) View current contract \n" +
							"4) Go back \n"
			);

			choice = in.next();
			if(choice.equals("q") || choice.equals("'q'")) break;

			switch(Integer.parseInt(choice)){
				case 1:
					contractSession.negotiate();
					break;
				case 2:
					contractSession.viewOldContracts();
					break;
				case 3:
					contractSession.viewCurrentContract();
					break;
				case 4:
					//mainScreen();
			}
		}
	}
}
