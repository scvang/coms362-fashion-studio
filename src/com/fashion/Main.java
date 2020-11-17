package com.fashion;

import java.io.*;
import java.sql.*;
import com.fashion.apparel.Apparel;
import com.fashion.employees.EmployeeSession;
import com.fashion.employees.HumanResources;
import com.fashion.events.*;
import com.fashion.events.Event;
import com.fashion.negotiations.ContractSession;
import com.fashion.pay.Card;
import com.fashion.pay.PayStubInfo;
import com.fashion.shopping.ShoppingSession;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

public class Main extends JFrame {
	/**
	 * Instance variables.
	 */
	public static Studio studio;
	public static HumanResources HR = new HumanResources();
	public static Event E;
	
	public static void main(String[] args) {
		 
//		Random random = new Random();
//
//		/*
//		Create 3-10 businesses randomly
//		 */
//		ArrayList<Business> businesses = new ArrayList<>();
//		for(int i = 0; i < random.nextInt(maxBusinesses); i++){
//			Business business = new Business(businessName[random.nextInt(businessName.length)],
//					businessAddresses[random.nextInt(businessAddresses.length)], generateNum());
//			businesses.add(business);
//		}

		//System.out.println(businesses);
		
		// Create a studio
		String company = "Fashion Inc";
		String address = "401 Somewhere Ave";
		String phone = "555-555-5555";
		double balance = 500000;
		

		studio = new Studio(company,address,phone, balance);
//		HumanResources.hireBusiness(1, "name", "loc", "photo", "jack", "333-333-3333", 900.0);
//		HumanResources.getServices();

		studio = new Studio(company,address,phone,balance);

		// Create some new events.
//		studio.createShowingEvent("FashionCon 2020", "10-15-20", "04:10PM");
//		studio.createPartyEvent("Company Party 2020", "10-15-20", "5:20PM");
//		studio.createDiningEvent("Fashion Dining 2020", "10-15-20", "6:20PM");
		
		// Make a display model.
		//studio.createModel("Jack","Eve","555-555-5555",100000);
		
		// Add an employee test
		/*
		String name = "John";
		String jobTitle = "Designer";
		double salary = 50000;
		String phoneNum = "N/A";

		studio.addEmployee(eid, name, jobTitle, phoneNum, salary, 0, 0);
		
		studio.addEmployee(name, jobTitle, phoneNum,salary);
		studio.getEmployees();
		*/

		// Add a shirt test
		/*
		String itemName = "T-Shirt";
		String brandName = "DEUX";
		String color = "White";
		int id = 50021;
		int stock = 1;
		
		studio.addApparel(itemName,brandName,color,id,stock);
		studio.getApparel();
		*/
		
		//Add an Ad test
		int eidAd = 123;
		String eventName = "Spring";
		String loc = "401 Somewhere Ave";
		String time = "11:00 AM - 3:00 PM";
		String contactInfo = "555-555-5555";
		
		//studio.addAd(eidAd, eventName, loc, time, contactInfo);
		//studio.getAd();
		
		//Add a new Model test
//		String modelName = "Jenna";
//		String modNum = "111-111-1111";
//		int audNum = 456;
//
//		studio.addModel(modelName, modNum, audNum);
		//studio.getEmployees();

		HumanResources.hireBusiness(1, "Name", "Somewhere", "Catering", "Bill", "444-444-4444", 800.0);
		// Test adding to database.
		
		
		// Test picture.
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					ShowPicture frame = new ShowPicture();
//					frame.setVisible(true);
//					
//					// This creates a test model.
//					String description =
//							"<html>"
//							+ "Model Name: Testie <br/> Agent: Jack Sparrow <br/> Phone Number: 555-555-5555"
//							+ "</html>";
//					
//					frame.add(new JLabel(description,new ImageIcon("testmodel.jpg"),JLabel.RIGHT));
//					frame.setDefaultCloseOperation(HIDE_ON_CLOSE);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});

		CommandDisplay cd = new CommandDisplay();
		cd.addCommand(new ListEmployeeOptions());
		cd.addSubEmp(new ViewEmployees());
		cd.addSubEmp(new PayEmployee());
		cd.addSubEmp(new Management());
		cd.addSubEmp(new GoBack());
		cd.addManageCommands(new HireEmployee());
		cd.addManageCommands(new FireEmployee());
		cd.addCommand(new ListEventOptions());
		cd.addSubEvent(new ShowingEventCommands());
		cd.addSubEvent(new DiningEventCommands());
		cd.addSubEvent(new PartyEventCommands());
		cd.addSubEvent(new CreateNewEvent());
		cd.addSubEvent(new DisplayEvents());
		cd.addSubShowEvent(new DisplaySeat());
		cd.addSubShowEvent(new ReserveSeat());
		cd.addSubShowEvent(new CheckSeat());
		cd.addSubShowEvent(new Refund());
		cd.addSubDiningEvent(new DisplayTable());
		cd.addSubDiningEvent(new ReserveTable());
		cd.addSubDiningEvent(new CheckTable());
		cd.addSubDiningEvent(new RefundTable());
		cd.addSubPartyEvent(new DisplayAttendees());
		cd.addSubPartyEvent(new ReserveBadge());
		cd.addSubPartyEvent(new CheckBadge());
		cd.addSubPartyEvent(new RefundBadge());
		cd.addCommand(new ListShoppingOptions());
		cd.addSubShopCommands(new ApparelInfo());
		cd.addSubShopCommands(new ShowApparel());
		cd.addSubShopCommands(new AddCart());
		cd.addSubShopCommands(new GoCart());
		cd.addSubShopCommands(new ProcessRefund());
		cd.addSubShopCommands(new ViewRefund());
		cd.addCommand(new ListInventoryOptions());
		cd.addSubInventCommands(new ViewClothes());
		cd.addSubInventCommands(new ViewMakeup());
		cd.addSubInventCommands(new ViewFood());
		cd.addSubInventCommands(new StoreClothes());
		cd.addSubInventCommands(new StoreMakeup());
		cd.addSubInventCommands(new StoreFood());
		cd.addSubInventCommands(new SearchItem());
		cd.addCommand(new ListModelOptions());
		cd.addSubModCommands(new ListModels());
		cd.addSubModCommands(new ChangeApparel());
		cd.addSubModCommands(new UpdateContact());
		cd.addSubModCommands(new UpdatePay());
		cd.addSubModCommands(new AddModel());
		cd.addCommand(new ListAdvertisementOptions());
		cd.addSubAdCommands(new ViewAds());
		cd.addSubAdCommands(new CreateAd());
		cd.addCommand(new ListPromotionOptions());
		cd.addSubPromoCommands(new UpEvents());
		cd.addSubPromoCommands(new CheckAvail());
		cd.addSubPromoCommands(new ReservePromo());
		cd.addCommand(new ListContractOptions());
		cd.addSubContractCommands(new BeginContract());
		cd.addSubContractCommands(new ViewOld());
		cd.addSubContractCommands(new ViewCurrent());
		cd.addCommand(new ListBusinessOptions());
		cd.subBusinessCommands(new ViewRecords());
		cd.subBusinessCommands(new HireBusiness());
		cd.subBusinessCommands(new ConfirmBusiness());
		cd.displaycommands();
		
		//mainScreen();
	}

/**
 * @author Sebastian Vang
 * The main screen that prompts first.
 */
public static void mainScreen() {
	String choice = "";
	Scanner in = new Scanner(System.in);
	while(!choice.equals("q")) {
		System.out.println(
		"Select an option ('q' to exit): \n" +
		"1) Employees \n" +
		"2) Inventory \n" +
		"3) Models \n" +
		"4) Events \n" +
		"5) Advertisements \n" +
		"6) Promotions \n" +
		"7) Shop \n" +
		"8) Negotiate Contract\n" +
		"9) Manage Businesses\n"
		);
		
		choice = in.next();
		if(choice.equals("q") || choice.equals("'q'")) break;
		
		switch(Integer.parseInt(choice)){
			case 1:
				employeeScreen();
			break;
			case 2:
				inventoryScreen();
			break;
			case 3:
				modelScreen();
			break;
			case 4:
				eventScreen();
			break;
			case 5:
				advertisementScreen();
			break;
			case 6:
				promotionScreen();
			break;
			case 7:
				shoppingScreen();
			break;
			case 8:
				contractScreen();
			break;
			case 9:
				businessScreen();
			break;
		}
	}
	in.close();
}

/**
 * @author Sebastian Vang
 * Inventory Screen.
 */
public static void inventoryScreen() {
	String choice = "";
	Scanner in = new Scanner(System.in);
	while(!choice.equals("q")) {
		System.out.println(
		"Select an option ('q' to exit): \n" +
		"1) View clothing listing \n" +
		"2) View makeup listing \n" +
		"3) View food listing \n" +
		"4) Store clothing item \n" +
		"5) Store makeup item \n" +
		"6) Store food item \n" +
		"7) Search clothing item \n" +
		"8) Go back \n"
		);
		
		choice = in.next();
		if(choice.equals("q") || choice.equals("'q'")) break;
		else in.nextLine(); // Clear the buffer.
		
		int id = 0;
		String size = "";
		int price = 0;
		String itemName = "";
		String brandName = "";
		String color = ""; 
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
	      ResultSet rs = st.executeQuery("SELECT * FROM inventory2");
	      
	      studio.resetInventory();
	      while(rs.next()) {
	    	  id = rs.getInt("id");
	    	  size = rs.getString("size");
	    	  price = rs.getInt("price");
	    	  itemName = rs.getString("itemName");
	    	  brandName = rs.getString("brandName");
	    	  color = rs.getString("color");
	    	  quantity = rs.getInt("quantity");
	    	  
	    	  studio.storeClothingItem(new Apparel(id,size,price,itemName,brandName,color,quantity));
	      }
	      // close the connection.
	      st.close();
	    }
	    catch (Exception e){
	      System.err.println(e.getMessage()); 
	    }
		switch(Integer.parseInt(choice)){
			case 1:
				studio.displayClothingInventory();
			break;
			
			case 2:
				//TODO
			break;
			
			case 3:
				//TODO
			break;
			
			case 4:
				System.out.println("Enter the id:");
				id = in.nextInt(); in.nextLine();
				System.out.println("Enter the size:");
				size = in.nextLine();
				System.out.println("Enter the price:");
				price = in.nextInt(); in.nextLine();
				System.out.println("Enter the item name:");
				itemName = in.nextLine();
				System.out.println("Enter the brand name:");
				brandName = in.nextLine();
				System.out.println("Enter the color:");
				color = in.nextLine();
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
			      PreparedStatement ps = conn.prepareStatement("INSERT INTO inventory2 (id,size,price,itemName,brandName,color,quantity) " + "VALUES (?,?,?,?,?,?,?)");
			      
			      ps.setInt(1,id);
			      ps.setString(2, size);
			      ps.setInt(3, price);
			      ps.setString(4,itemName);
			      ps.setString(5,brandName);
			      ps.setString(6, color);
			      ps.setInt(7, quantity);
			      
			      ps.execute();
			      conn.close();
			    }
			    catch (Exception e){
			      System.err.println(e.getMessage()); 
			    }
				System.out.println("Item was added into the database.");
			break;
			
			case 5:
				
			break;
			
			case 6:
				//TODO
			break;
			
			case 7:
				System.out.println("Enter the price:");
				int p = in.nextInt(); in.nextLine();
				
				System.out.println("Enter size");
				String s = in.nextLine();
				
				System.out.println("Enter item name:");
				String n = in.nextLine();
				
				System.out.println("Enter brand name:");
				String b = in.nextLine();
				
				System.out.println("Enter color:");
				String c = in.nextLine();
				
				Apparel apparel = studio.getInventory().search(new Apparel(0,s,p,n,b,c,0));
				
				if(apparel == null) {
					System.out.println("Search results:");
					System.out.println("Item was not found.");
					break;
				}
				System.out.println("Search results:");
				System.out.println(
						"Item name: " + apparel.getItemName() + "\n" +
						"Brand name: " + apparel.getBrandName() + "\n" +
						"Size: " + apparel.getSize() + "\n" +
						"Color: " + apparel.getColor() + "\n" +
						"Price: " + apparel.getPrice() + "\n" +
						"Quantity: " + apparel.getQuantity() + "\n"
						);
			break;
			case 8:
				mainScreen();
			break;
		}
	}
	in.close();
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
						"3) Manager screen \n" +
						"4) Go back \n"
		);

		choice = in.next();
		if(choice.equals("q") || choice.equals("'q'")) break;

		switch(Integer.parseInt(choice)){
			case 1:
				EmployeeSession employeeSession1 = new EmployeeSession();
				employeeSession1.viewEmployees();
				employeeSession1.displayHeadshot();
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

				if(HR.payEmployee(eid, payStubInfo)){
					System.out.println(studio.getEmployee(eid).getName() + " was paid!");
				} else {
					System.out.println("Error paying employee, try again later");
				}
				System.out.println();
				System.out.println();
				break;
			case 3:
				EmployeeSession employeeSession = new EmployeeSession();
				Scanner in3 = new Scanner (System.in);

				System.out.println("Username: ");
				String username = in3.next();
				System.out.println("Password: ");
				String password = in3.next();

				if(employeeSession.getAccessRights(username, password)){
					System.out.println(
							"Select an action: \n" +
									"1) Hire employee \n" +
									"2) Fire employee \n" +
									"3) Back"
					);

					switch(Integer.parseInt(in3.next())){
						case 1:
							employeeSession.hireEmployee();
							break;
						case 2:
							employeeSession.fireEmployee();
							break;
						default:
							System.out.println();
							break;
					}
				}
				break;
			case 4:
				mainScreen();
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
//					studio.addAd(eid, eventName, loc, time, contactInfo);
					System.out.println("Advertisement Created!");
				} else if(adType.equals("video")) {
//					advert.createAdVideo();
//					studio.addAd(eid, eventName, loc, time, contactInfo);
					System.out.println("Advertisement Created!");
				} else {
					System.out.print("We don't currently support that type of advertisement at this time.");
				}
			break;
			
			case 3:
				mainScreen();
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
			mainScreen();
		break;
		}
	}
}

public static void apparelScreen() {
	//TODO
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
		"3) Update contact information \n" +
		"4) Update salary \n" +
		"5) Add model \n" +
		"6) Go back \n"
		);
		
		choice = in.next();
		if(choice.equals("q") || choice.equals("'q'")) break;
		else in.nextLine(); // Clear the buffer.
		
		String name = "";
		double salary = 0;
		
		switch(Integer.parseInt(choice)){
			case 1:
				HR.displayModels();
			break;
			
			case 2:
				System.out.println("Which model do you want to change?");
				name = in.next();
				if(!HR.doesModelExist(name)) {
					System.out.println("Model was not found, try again.");
					break;
				}
				changeApparelScreen(name);
				
			break;
			
			case 3:
				System.out.println("Enter model name:");
				name = in.nextLine();
				
				if(!HR.doesModelExist(name)) {
					System.out.println("Model was not found, try again.");
					break;
				}
				
				System.out.println("Enter contact information:");
				String phoneNum = in.nextLine();
				
				HR.getModel(name).setPhoneNum(phoneNum);
				
			break;
			
			case 4:
				System.out.println("Enter model name:");
				name = in.nextLine();
				
				if(!HR.doesModelExist(name)) {
					System.out.println("Model was not found, try again.");
					break;
				}
				
				System.out.println("Enter salary:");
				salary = in.nextDouble();
				PayStubInfo p = new PayStubInfo(salary,0,0,0);
				HR.getModel(name).setPayStubInfo(p);;
			break;
			
			case 6:
				mainScreen();
			break;
			
			case 5:
				System.out.println("Enter EID: ");
				int eid = in.nextInt(); in.nextLine();
				System.out.println("Enter agent name:");
				String agent = in.nextLine();
				System.out.println("Enter model name:");
				String model = in.nextLine();
				System.out.println("Enter phone number:");
				String number = in.nextLine();
				System.out.println("Enter salary:");
				salary = in.nextDouble();
				HR.createModel(eid,agent,model,"Fashion Model",number,new PayStubInfo(salary, 0, 0, 0)); // Probably need to change this.
			break;
		}
	}
	in.close();
	
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
		
		switch(Integer.parseInt(choice)){
			case 1:
				System.out.println("Enter the item id:");
				id = in.nextInt(); in.nextLine();
				
				System.out.println("Enter the item name:");
				name = in.nextLine();
				
				System.out.println("Enter the brand name:");
				brand = in.nextLine();
				
				System.out.println("Enter the color:");
				color = in.nextLine();
				
				Apparel item = new Apparel(id,name, brand, color);
				studio.changeHead(modelName,item);
			break;
			
			case 2:
				System.out.println("Enter the item name:");
				name = in.nextLine();
				
				System.out.println("Enter the brand name:");
				brand = in.nextLine();
				
				System.out.println("Enter the color:");
				color = in.nextLine();
				
				item = new Apparel(id,name, brand, color);
				studio.changeTop(modelName,item);
			break;
			
			case 3:
				System.out.println("Enter the item name:");
				name = in.nextLine();
				
				System.out.println("Enter the brand name:");
				brand = in.nextLine();
				
				System.out.println("Enter the color:");
				color = in.nextLine();
				
				item = new Apparel(id,name, brand, color);
				studio.changeBot(modelName,item);
			break;
			
			case 4:
				System.out.println("Enter the item name:");
				name = in.nextLine();
				
				System.out.println("Enter the brand name:");
				brand = in.nextLine();
				
				System.out.println("Enter the color:");
				color = in.nextLine();
				
				item = new Apparel(id,name, brand, color);
				studio.changeLegs(modelName,item);
			break;
			
			case 5:
				System.out.println("Enter the item name:");
				name = in.nextLine();
				
				System.out.println("Enter the brand name:");
				brand = in.nextLine();
				
				System.out.println("Enter the color:");
				color = in.nextLine();
				
				item = new Apparel(id,name, brand, color);
				studio.changeShoes(modelName,item);
			break;
			
			case 6:
				System.out.println("Enter the item name:");
				name = in.nextLine();
				
				System.out.println("Enter the brand name:");
				brand = in.nextLine();
				
				System.out.println("Enter the color:");
				color = in.nextLine();
				
				item = new Apparel(id,name, brand, color);
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
				String type = in.nextLine();
				
				System.out.println("Event name?");
				String name = in.nextLine();
				
				System.out.println("What date (mm-dd-yy)? ");
				String date = in.nextLine();
				
				System.out.println("What time (hh:mm am/pm)? ");
				String time = in.nextLine();
				
				E.createEvent(type,name,date,time);
			break;
			
			case 6:
				mainScreen();
			break;
			
			case 5:
				E.displayEvents();
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
	for(int i = 0; i < E.getEventList().size(); ++i) {
		if(E.getEventList().get(i) instanceof Showing) {
			System.out.println(count + ") " + E.getEventList().get(i).getEvent());
			++count;
			showingList.add((Showing)E.getEventList().get(i));
		}
	}
	if(showingList.isEmpty()) {
		System.out.println("There are no showings!");
		eventScreen();
	}
	Scanner in3 = new Scanner(System.in);
	int i = in3.nextInt();
	
	String eventName = showingList.get(i-1).getEvent();
	
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
				E.displaySeats(E.getEvent(eventName));
			break;
			
			case 2:
				if(E.isShowingFull(E.getEvent(eventName))) {
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
				
				if(E.reserveSeat(E.getEvent(eventName),seat,customerName,date,time)) {
					E.chargeCard(E.getEvent(eventName),customerName);
					
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
				      PreparedStatement ps = conn.prepareStatement("INSERT INTO showing (name,date,time,seat)" + "VALUES (?,?,?,?)");
				      
				      ps.setString(1, customerName);
				      ps.setString(2, date);
				      ps.setString(3, time);
				      ps.setString(4, seat);
				      
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
				if(E.hasSeatReservation(name,E.getEvent(eventName))) {
					System.out.println(
					"Name: " + E.getSeat(name,E.getEvent(eventName)).getCustomerName() + "\n" +
					"Date: " + E.getSeat(name,E.getEvent(eventName)).getDate() + "\n" + 
					"Time: " + E.getSeat(name,E.getEvent(eventName)).getTime() + "\n" + 
					"Seat: " + E.getSeat(name,E.getEvent(eventName)).getSeatNum() + "\n"
					);
				}
				else {
					System.out.println("No reservation found for " + name);
				}
			break;
			
			case 4:
				System.out.println("Enter the customer name:");
				name = in.nextLine();
				if(E.hasSeatReservation(name,E.getEvent(eventName))) {
					System.out.println("Reservation found.");
					E.removeSeatReservation(name,E.getEvent(eventName));
					System.out.println("Reservation removed.");
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
	for(int i = 0; i < E.getEventList().size(); ++i) {
		if(E.getEventList().get(i) instanceof Dining) {
			System.out.println(count + ") " + E.getEventList().get(i).getEvent());
			++count;
			list.add((Dining)E.getEventList().get(i));
		}
	}
	if(list.isEmpty()) {
		System.out.println("There are no dinings!");
		eventScreen();
	}
	Scanner in = new Scanner(System.in);
	int i = in.nextInt();
	
	String eventName = list.get(i-1).getEvent();
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
				E.displayTables(E.getEvent(eventName));
			break;
			
			case 2:
				if(E.isDiningFull(E.getEvent(eventName))) {
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
				
				if(E.reserveTable(E.getEvent(eventName),table,customerName,date,time)) {
					E.chargeCard(E.getEvent(eventName),customerName);
				}
				else {
					System.out.println("Table reservation failed.");
				}
				
				
			break;
			
			case 3:
				System.out.println("Enter your customer name:");
				name = in.nextLine();
				if(E.hasTableReservation(name,E.getEvent(eventName))) {
					System.out.println(
					"Name: " + E.getTable(name,E.getEvent(eventName)).getCustomerName() + "\n" +
					"Date: " + E.getTable(name,E.getEvent(eventName)).getDate() + "\n" + 
					"Time: " + E.getTable(name,E.getEvent(eventName)).getTime() + "\n" + 
					"Table: " + E.getTable(name,E.getEvent(eventName)).getTableNum() + "\n"
					);
				}
				else {
					System.out.println("No reservation found for " + name);
				}
			break;
			
			case 4:
				System.out.println("Enter the customer name:");
				name = in.nextLine();
				if(E.hasTableReservation(name,E.getEvent(eventName))) {
					System.out.println("Reservation found.");
					E.removeTableReservation(name,E.getEvent(eventName));
					System.out.println("Reservation removed.");
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
	for(int i = 0; i < E.getEventList().size(); ++i) {
		if(E.getEventList().get(i) instanceof Party) {
			System.out.println(count + ") " + E.getEventList().get(i).getEvent());
			++count;
			list.add((Party)E.getEventList().get(i));
		}
	}
	if(list.isEmpty()) {
		System.out.println("There are no parties!");
		eventScreen();
	}
	Scanner in3 = new Scanner(System.in);
	int i = in3.nextInt();
	
	String eventName = list.get(i-1).getEvent();
	
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
				System.out.println("There are: " + E.getNumOfAttendees(E.getEvent(eventName)) + " number of attendees.");
			break;
			
			case 2:
				if(E.isPartyFull(E.getEvent(eventName))) {
					System.out.println("The venue is full.");
					break;
				}
				
				System.out.println("Enter your customer name: ");
				String customerName = in.nextLine();
				System.out.println("Enter your desired date (mm-dd-yy): ");
				String date = in.nextLine();
				System.out.println("Enter your desired time (hh:mm am/pm): ");
				String time = in.nextLine();
				
				E.reserveBadge(E.getEvent(eventName),customerName,date,time);
			break;
			
			case 3:
				System.out.println("Enter your customer name:");
				name = in.nextLine();
				if(E.hasBadgeReservation(name,E.getEvent(eventName))) {
					System.out.println(
							"Name: " + E.getBadge(name,E.getEvent(eventName)).getName() + "\n" +
							"Date: " + E.getBadge(name,E.getEvent(eventName)).getDate() + "\n" + 
							"Time: " + E.getBadge(name,E.getEvent(eventName)).getTime() + "\n"
							);
				}
				else {
					System.out.println("No reservation found.");
				}
			break;
			
			case 4:
				
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
				E.displayEvents();
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

				while(E.getEvent(eventName) == null){
					System.out.println("Sorry! we could not find your event, please re-enter a new event ('q' to exit): ");
					eventName = in2.nextLine().trim();
					if(eventName.equals("q")) {
						System.out.println();
						System.out.println();
						promotionScreen();
					}
				}

				if(E.getEvent(eventName).isPromotionSpotOpen(1)){
					System.out.println("1:  Open");
				} else {
					System.out.println("1:  Taken");
				}

				if(E.getEvent(eventName).isPromotionSpotOpen(2)){
					System.out.println("2:  Open");
				} else {
					System.out.println("2:  Taken");
				}

				if(E.getEvent(eventName).isPromotionSpotOpen(3)){
					System.out.println("3:  Open");
				} else {
					System.out.println("3:  Taken");
				}

				if(E.getEvent(eventName).isPromotionSpotOpen(4)){
					System.out.println("4:  Open");
				} else {
					System.out.println("4:  Taken");
				}

				if(E.getEvent(eventName).isPromotionSpotOpen(5)){
					System.out.println("5:  Open");
				} else {
					System.out.println("5:  Taken");
				}

				if(E.getEvent(eventName).isPromotionSpotOpen(6)){
					System.out.println("6:  Open");
				} else {
					System.out.println("6:  Taken");
				}

				if(E.getEvent(eventName).isPromotionSpotOpen(7)){
					System.out.println("7:  Open");
				} else {
					System.out.println("7:  Taken");
				}

				if(E.getEvent(eventName).isPromotionSpotOpen(8)){
					System.out.println("8:  Open");
				} else {
					System.out.println("8:  Taken");
				}

				if(E.getEvent(eventName).isPromotionSpotOpen(9)){
					System.out.println("9:  Open");
				} else {
					System.out.println("9:  Taken");
				}

				if(E.getEvent(eventName).isPromotionSpotOpen(10)){
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
				while(E.getEvent(eventNameReserve) == null) {
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

				if(E.getEvent(eventNameReserve).addPromotion(businessName, text, location, dollarAmount, new Card(cardNum, cardMonth, cardYear, cardCode, null))) {
					System.out.println("Promotion added!");
				}

				System.out.println();
				System.out.println();
				break;
			case 4:
				mainScreen();
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
		shoppingSession.initSessionId();
		System.out.println(
				"Select a choice ('q' to exit): \n" +
						"1) Display apparel information \n" +
						"2) Show apparel \n" +
						"3) Add item to your cart \n" +
						"4) Go to cart \n" +
						"5) Process refund \n" +
						"6) View last refund \n" +
						"7) Go back \n"
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
							break;
						}

						System.out.println("What size? ('q' to exit): ");
						size = in4.nextLine().trim();
						if(size.equals("q")) {
							System.out.println();
							break;
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
							shoppingSession.getCart().clearCart();
							System.out.println();
						} else if (response.equals("n")) {
							System.out.println();
							break;
						}
					} else if (response.equals("n")) {
						System.out.println();
						break;
					}
				}

				break;
			case 5:
				if(shoppingSession.displayRefundOrders()){
					Scanner in5 = new Scanner (System.in);

					System.out.println("What would you like to return? (enter id number): ");
					String responseRefund = in5.nextLine().trim();
					int refundShoppingSession = Integer.parseInt(responseRefund);
					shoppingSession.getRefundCart().addItem(refundShoppingSession);
					System.out.println();

					System.out.println("Please select an image for verification: ");
					JFileChooser jfc = new JFileChooser();
					jfc.showDialog(null,"Please Select the File");
					jfc.setVisible(true);
					File image = jfc.getSelectedFile();
					System.out.println(image.getName());

					shoppingSession.pushRefund(image, refundShoppingSession);
				} else {
					System.out.println("No eligible items to return :(\n");
				}
				break;
			case 6:
				shoppingSession.viewLastRefund();
				System.out.println();
				break;
			case 7:
				mainScreen();
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
				mainScreen();
		}
	}
}
}
