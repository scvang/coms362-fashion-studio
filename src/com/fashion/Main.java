package com.fashion;

import com.fashion.pay.Card;
import com.fashion.pay.PayStubInfo;
import com.fashion.apparel.Apparel;
import com.fashion.events.*;

import java.awt.EventQueue;
import java.util.Random;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Main extends JFrame {
//	private static double minPromotionValue = 1000.0;
//	private static double maxPromotionValue = 15000.0;
//	private static int maxBusinesses = 7;
//	private static String[] businessName = {"Joe's", "Mike's", "Dienda's", "Hugh's", "Dior", "Gucci", "Ralph Lauren",
//		"Louis Vuitton", "Chanel", "Rolex", "Balenciaga", "Armani", "Yves Saint Laurent", "Tiffany",
//			"Burberry", "Hermes", "Cartier", "Prada", "Fendi", "Lancome"};
//	private static String[] businessAddresses = {"450 Grope Lane", "39 Fabulous Texan Way", "90 Ha-Ha Road",
//			"126 Man Fuk Road", "67 Mad Dog Lane", "1 Boring Road", "666 Bad Route Road", "900 Smellies Lane",
//			"12 Butt Street", "879 Break-Me-Neck Hill", "1285 Whip-Ma-Whop-Ma-Gate", "78 Silly Goose Lane"};
	
	/**
	 * Instance variables.
	 */
	public static Studio studio;

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
		
		studio = new Studio(company,address,phone);
		
		// Create some new events.
		studio.createShowingEvent("FashionCon 2020", "10-15-20", "04:10PM");
		studio.createPartyEvent("Company Party 2020", "10-15-20", "5:20PM");
		studio.createDiningEvent("Fashion Dining 2020", "10-15-20", "6:20PM");
		
		// Make a display model.
		studio.createModel("Jack","Eve","555-555-5555",100000);
		
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
		String modelName = "Jenna";
		String modNum = "111-111-1111";
		int audNum = 456;

		studio.addModel(modelName, modNum, audNum);
		//studio.getEmployees();
		
		// Go to main screen.
		// I think later it should be changed so that the screens
		// are handled by a display controller so we don't have bloat.
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ShowPicture frame = new ShowPicture();
					frame.setVisible(true);
					
					// This creates a test model.
					String description =
							"<html>"
							+ "Model Name: Testie <br/> Agent: Jack Sparrow <br/> Phone Number: 555-555-5555"
							+ "</html>";
					
					frame.add(new JLabel(description,new ImageIcon("testmodel.jpg"),JLabel.RIGHT));
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		mainScreen();
	}
	
	/**
	 * Test screen.
	 * @param description
	 * @param commands
	 */
	public static void Screen(String description, String commands) {
		
	}
	
	/**
	 * The main screen that prompts first.
	 */
	public static void mainScreen() {
		String choice = "";
		Scanner in = new Scanner(System.in);
		while(!choice.equals("q")) {
			System.out.println(
			"Select an option ('q' to exit): \n" +
			"1) Employees \n" +
			"2) Apparel \n" +
			"3) Models \n" +
			"4) Events \n" +
			"5) Advertisements \n" +
			"6) Promotions \n"
			);
			
			choice = in.next();
			if(choice.equals("q") || choice.equals("'q'")) break;
			
			switch(Integer.parseInt(choice)){
				case 1:
					employeeScreen();
				break;
				case 2:
					apparelScreen();
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
					mainScreen();
			}
		}
		in.close();
	}
	
	public static void apparelScreen() {
		//TODO
	}
	
	/**
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
			"5) Go back \n" +
			"6) Add model \n"
			);
			
			choice = in.next();
			if(choice.equals("q") || choice.equals("'q'")) break;
			
			switch(Integer.parseInt(choice)){
				case 1:
					studio.getModels();
				break;
				
				case 2:
					Scanner in2 = new Scanner(System.in);
					System.out.println("Which model do you want to change?");
					//studio.getModels();
					String name = in2.next();
					if(!studio.findModel(name)) {
						System.out.println("Model was not found, try again.");
						break;
					}
					changeApparelScreen(name);
					
				break;
				
				case 3:

				break;
				
				case 4:
					
				break;
				
				case 5:
					mainScreen();
				break;
				
				case 6:
					System.out.println("Enter agent name:");
					String agent = in.next();
					System.out.println("Enter model name:");
					String model = in.next();
					System.out.println("Enter phone number:");
					String number = in.next();
					System.out.println("Enter salary:");
					int salary = in.nextInt();
					studio.createModel(agent,model,model,salary);
				break;
			}
		}
		in.close();
		
	}
	
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
			
			Scanner in2 = new Scanner(System.in);
			String name;
			String brand;
			String color;
			
			switch(Integer.parseInt(choice)){
				case 1:
					
					System.out.println("Enter the item name:");
					name = in2.nextLine();
					
					System.out.println("Enter the brand name:");
					brand = in2.nextLine();
					
					System.out.println("Enter the color:");
					color = in2.nextLine();
					
					Apparel item = new Apparel(name, brand, color);
					studio.changeHead(modelName,item);
				break;
				
				case 2:
					System.out.println("Enter the item name:");
					name = in2.nextLine();
					
					System.out.println("Enter the brand name:");
					brand = in2.nextLine();
					
					System.out.println("Enter the color:");
					color = in2.nextLine();
					
					item = new Apparel(name, brand, color);
					studio.changeTop(modelName,item);
				break;
				
				case 3:
					System.out.println("Enter the item name:");
					name = in2.nextLine();
					
					System.out.println("Enter the brand name:");
					brand = in2.nextLine();
					
					System.out.println("Enter the color:");
					color = in2.nextLine();
					
					item = new Apparel(name, brand, color);
					studio.changeBot(modelName,item);
				break;
				
				case 4:
					System.out.println("Enter the item name:");
					name = in2.nextLine();
					
					System.out.println("Enter the brand name:");
					brand = in2.nextLine();
					
					System.out.println("Enter the color:");
					color = in2.nextLine();
					
					item = new Apparel(name, brand, color);
					studio.changeLegs(modelName,item);
				break;
				
				case 5:
					System.out.println("Enter the item name:");
					name = in2.nextLine();
					
					System.out.println("Enter the brand name:");
					brand = in2.nextLine();
					
					System.out.println("Enter the color:");
					color = in2.nextLine();
					
					item = new Apparel(name, brand, color);
					studio.changeShoes(modelName,item);
				break;
				
				case 6:
					System.out.println("Enter the item name:");
					name = in2.nextLine();
					
					System.out.println("Enter the brand name:");
					brand = in2.nextLine();
					
					System.out.println("Enter the color:");
					color = in2.nextLine();
					
					item = new Apparel(name, brand, color);
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
			"5) Go back \n" +
			"6) Display events"
			);
			
			choice = in.next();
			if(choice.equals("q") || choice.equals("'q'")) break;
			
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
					Scanner in2 = new Scanner(System.in);
					System.out.println("What type of event (showing, dining, party)? ");
					String type = in2.next();
					
					System.out.println("Event name?");
					String name = in2.next() + " " + in2.next(); // gotta fix this.
					
					System.out.println("What date (mm-dd-yy)? ");
					String date = in2.next();
					
					System.out.println("What time (hh:mm am/pm)? ");
					String time = in2.next();
					
					studio.createEvent(type,name,date,time);
				break;
				
				case 5:
					mainScreen();
				break;
				
				case 6:
					studio.displayEvents();
			}
		}
		in.close();
	}
	
	/**
	 * Showing screen.
	 */
	public static void showingScreen() {
		String choice = "";
		Scanner in = new Scanner(System.in);
		while(!choice.equals("q")) {
			System.out.println(
			"Select a choice ('q' to exit): \n" +
			"1) Display available seats \n" +
			"2) Reserve a seat \n" +
			"3) Check a seat \n" +
			"4) Refund \n" +
			"5) Go back \n" +
			"6) Fill Seats (Test)"
			);
			
			choice = in.next();
			if(choice.equals("q") || choice.equals("'q'")) break;
			
			switch(Integer.parseInt(choice)){
				case 1:
					studio.displaySeats(studio.getEvent("FashionCon 2020"));
				break;
				
				case 2:
					if(studio.isShowingFull(studio.getEvent("FashionCon 2020"))) {
						System.out.println("No available seats.");
						break;
					}
					System.out.println("Enter your customer name: ");
					String customerName = in.next();
					System.out.println("Enter your desired seat (A1~I9): ");
					String seat = in.next();
					System.out.println("Enter your desired date (mm-dd-yy): ");
					String date = in.next();
					System.out.println("Enter your desired time (hh:mm am/pm): ");
					String time = in.next();
					
					if(studio.reserveSeat(studio.getEvent("FashionCon 2020"),seat,customerName,date,time)) {
						studio.chargeCard(studio.getEvent("FashionCon 2020"),customerName);
					}
					else{
						System.out.println("Seat Reservation failed.");
					}
				break;
				
				case 3:
					System.out.println("Enter your customer name");
					String name = in.next();
					if(studio.hasSeatReservation(name,studio.getEvent("FashionCon 2020"))) {
						System.out.println(
						"Name: " + studio.getSeat(name,studio.getEvent("FashionCon 2020")).getCustomerName() + "\n" +
						"Date: " + studio.getSeat(name,studio.getEvent("FashionCon 2020")).getDate() + "\n" + 
						"Time: " + studio.getSeat(name,studio.getEvent("FashionCon 2020")).getTime() + "\n" + 
						"Seat: " + studio.getSeat(name,studio.getEvent("FashionCon 2020")).getSeatNum() + "\n"
						);
					}
					else {
						System.out.println("No reservation found for " + name);
					}
				break;
				
				case 4:
				
				break;
				
				case 5:
					eventScreen();
				break;
				case 6:
					studio.fillSeats(studio.getEvent("FashionCon 2020"));
				break;
			}
		}
		in.close();
	}
	
	/**
	 * Dining screen.
	 */
	public static void diningScreen() {
		String choice = "";
		Scanner in = new Scanner(System.in);
		while(!choice.equals("q")) {
			System.out.println(
			"Select a choice ('q' to exit): \n" +
			"1) Display available tables \n" +
			"2) Reserve a table \n" +
			"3) Check a table \n" +
			"4) Refund \n" +
			"5) Go back \n" +
			"6) Fill tables (test) \n"
			);
			
			choice = in.next();
			if(choice.equals("q") || choice.equals("'q'")) break;
			
			switch(Integer.parseInt(choice)){
				case 1:
					studio.displayTables(studio.getEvent("Fashion Dining 2020"));
				break;
				
				case 2:
					if(studio.isDiningFull(studio.getEvent("Fashion Dining 2020"))) {
						System.out.println("There are no available tables.");
						break;
					}
					System.out.println("Enter your customer name: ");
					String customerName = in.next();
					System.out.println("Enter your desired table (1~20): ");
					String table = in.next();
					System.out.println("Enter your desired date (mm-dd-yy): ");
					String date = in.next();
					System.out.println("Enter your desired time (hh:mm am/pm): ");
					String time = in.next();
					
					if(studio.reserveTable(studio.getEvent("Fashion Dining 2020"),table,customerName,date,time)) {
						studio.chargeCard(studio.getEvent("Fashion Dining 2020"),customerName);
					}
					else {
						System.out.println("Table reservation failed.");
					}
					
					
				break;
				
				case 3:
					System.out.println("Enter your customer name");
					String name = in.next();
					if(studio.hasTableReservation(name,studio.getEvent("Fashion Dining 2020"))) {
						System.out.println(
						"Name: " + studio.getTable(name,studio.getEvent("Fashion Dining 2020")).getCustomerName() + "\n" +
						"Date: " + studio.getTable(name,studio.getEvent("Fashion Dining 2020")).getDate() + "\n" + 
						"Time: " + studio.getTable(name,studio.getEvent("Fashion Dining 2020")).getTime() + "\n" + 
						"Seat: " + studio.getTable(name,studio.getEvent("Fashion Dining 2020")).getTableNum() + "\n"
						);
					}
					else {
						System.out.println("No reservation found for " + name);
					}
				break;
				
				case 4:
					
				break;
				
				case 5:
					eventScreen();
				break;
				
				case 6:
					studio.fillTables(studio.getEvent("Fashion Dining 2020"));
				break;
			}
		}
		in.close();
	}
	
	/**
	 * Party screen.
	 */
	public static void partyScreen() {
		String choice = "";
		Scanner in = new Scanner(System.in);
		while(!choice.equals("q")) {
			System.out.println(
			"Select a choice ('q' to exit): \n" +
			"1) Display number of attendees \n" +
			"2) Reserve a badge \n" +
			"3) Refund \n" +
			"4) Go back \n"
			);
			
			choice = in.next();
			if(choice.equals("q") || choice.equals("'q'")) break;
			
			switch(Integer.parseInt(choice)){
				case 1:
					if(studio.isPartyFull(studio.getEvent("Company Party 2020"))) {
						System.out.println("The venue is full.");
					}
				break;
				
				case 2:
					if(studio.isPartyFull(studio.getEvent("Company Party 2020"))) {
						System.out.println("The venue is full.");
						break;
					}
					
					Scanner in2 = new Scanner (System.in);
					System.out.println("Enter your customer name: ");
					String customerName = in2.next();
					System.out.println("Enter your desired date (mm-dd-yy): ");
					String date = in2.next();
					System.out.println("Enter your desired time (hh:mm am/pm): ");
					String time = in2.next();
					
					studio.reserveBadge(studio.getEvent("Company Party 2020"),customerName,date,time);
				break;
				
				case 3:
					
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
					//TODO
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
					while(cardNum.isEmpty()){
						String temp = in3.next();
						if(temp.equals("q")) {
							System.out.println();
							System.out.println();
							promotionScreen();
						}

						if(temp.length() < 16) {
							System.out.println("Please enter a valid card number ('q' to exit).");
						}
						cardNum = temp;
//						else  {
//							try {
//								cardNum = Integer.parseInt(temp);
//							} catch (NumberFormatException e) {
//								System.out.println("Please enter a valid card number ('q' to exit).");
//							}
//						}
					}

					System.out.println("What is your card month ('q' to exit): ");
					int cardMonth = 0;
					while(cardMonth == 0){
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
								cardMonth = Integer.parseInt(temp);
							} catch (NumberFormatException e) {
								System.out.println("Please enter a valid card month ('q' to exit).");
							}
						}
					}

					System.out.println("What is your card year ('q' to exit): ");
					int cardYear = 0;
					while(cardYear == 0){
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
								cardYear = Integer.parseInt(temp);
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
						} 
//						else  {
//							try {
//								cardCode = Integer.parseInt(temp);
//							} catch (NumberFormatException e) {
//								System.out.println("Please enter a valid card code ('q' to exit).");
//							}
//						}
					}

					if(studio.getEvent(eventNameReserve).addPromotion(businessName, text, location, dollarAmount, new Card(cardNum, cardMonth, cardYear, cardCode))) {
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

	private static String generateNum() {
		String phoneNum = "";
		Random random = new Random();

		for(int i = 0; i < 12; i++){
			if(i == 3 || i == 7){
				phoneNum += "-";
			} else {
				phoneNum += random.nextInt(9);
			}
		}

		return phoneNum;
	}

//	private static Card generateCard() {
//		Random random = new Random();
//		StringBuilder cardNum = new StringBuilder();
//		StringBuilder endMonth = new StringBuilder();
//		StringBuilder endYear = new StringBuilder();
//		StringBuilder code = new StringBuilder();
//
//		for(int i = 0; i < 16; i++){
//			 cardNum.append(random.nextInt(10));
//		}
//
//		endMonth.append(random.nextInt(2));
//		if(endMonth.equals("1")){
//			endMonth.append(random.nextInt(3));
//		} else {
//			endMonth.append(random.nextInt(10));
//		}
//
//		for(int i = 0; i < 2; i++){
//			endYear.append(random.nextInt(10));
//		}
//
//		for(int i = 0; i < 3; i++){
//			code.append(random.nextInt(10));
//		}
//
//		return new Card(Integer.parseInt(cardNum.toString()), Integer.parseInt(endMonth.toString()),
//				Integer.parseInt(endYear.toString()), Integer.parseInt(code.toString()));
//	}
}
