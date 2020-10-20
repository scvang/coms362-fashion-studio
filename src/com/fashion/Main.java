package com.fashion;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {
	private static double minPromotionValue = 1000.0;
	private static double maxPromotionValue = 15000.0;
	private static int maxBusinesses = 7;
	private static String[] businessName = {"Joe's", "Mike's", "Dienda's", "Hugh's", "Dior", "Gucci", "Ralph Lauren",
		"Louis Vuitton", "Chanel", "Rolex", "Balenciaga", "Armani", "Yves Saint Laurent", "Tiffany",
			"Burberry", "Hermes", "Cartier", "Prada", "Fendi", "Lancome"};
	private static String[] businessAddresses = {"450 Grope Lane", "39 Fabulous Texan Way", "90 Ha-Ha Road",
			"126 Man Fuk Road", "67 Mad Dog Lane", "1 Boring Road", "666 Bad Route Road", "900 Smellies Lane",
			"12 Butt Street", "879 Break-Me-Neck Hill", "1285 Whip-Ma-Whop-Ma-Gate", "78 Silly Goose Lane"};
	
	/**
	 * Instance variables.
	 */
	public static Studio studio;
	
	/**
	 * Main
	 * @param args
	 */
	public static void main(String[] args) {
		Random random = new Random();

		/*
		Create 3-10 businesses randomly
		 */
		ArrayList<Business> businesses = new ArrayList<>();
		for(int i = 0; i < random.nextInt(maxBusinesses); i++){
			Business business = new Business(businessName[random.nextInt(businessName.length)],
					businessAddresses[random.nextInt(businessAddresses.length)], generateNum());
			businesses.add(business);
		}

		System.out.println(businesses);
		
		// Create a studio
		String company = "Fashion Inc";
		String address = "401 Somewhere Ave";
		String phone = "555-555-5555";
		
		studio = new Studio(company,address,phone);
		
		// Create some new events.
		studio.createShowingEvent("FashionCon 2020", "10-15-20", "04:10PM");
		studio.createPartyEvent("Company Party 2020", "10-15-20", "5:20PM");
		studio.createDiningEvent("Fashion Dining 2020", "10-15-20", "6:20PM");
		
		studio.createShowingEvent("FashionCon 2021", "10-15-21", "04:10PM");
		studio.displayEvents();

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
		studio.getEmployees();
		
		// Go to main screen.
		mainScreen();
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
				case 4:
					mainScreen();
			}
		}
	}
	
	public static void apparelScreen() {
		//TODO
	}
	
	public static void modelScreen() {
		//TODO
	}
	
	/**
	 * Event Screen
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
			"4) Go back \n"
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
					mainScreen();
			}
		}
		in.close();
	}
	
	/**
	 * Showing Screen
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
			"5) Go back \n"
			);
			
			choice = in.next();
			if(choice.equals("q") || choice.equals("'q'")) break;
			
			switch(Integer.parseInt(choice)){
				case 1:
					studio.displaySeats(studio.getEvent("FashionCon 2020"));
				break;
				
				case 2:
					Scanner in2 = new Scanner (System.in);
					System.out.println("Enter your customer name: ");
					String customerName = in2.next();
					System.out.println("Enter your desired seat (A1~I9): ");
					String seat = in2.next();
					System.out.println("Enter your desired date (mm-dd-yy): ");
					String date = in2.next();
					System.out.println("Enter your desired time (hh:mm am/pm): ");
					String time = in2.next();
					
					studio.reserveSeat(studio.getEvent("FashionCon 2020"),seat,customerName,date,time);
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
			"5) Go back \n"
			);
			
			choice = in.next();
			if(choice.equals("q") || choice.equals("'q'")) break;
			
			switch(Integer.parseInt(choice)){
				case 1:
					studio.displayTables(studio.getEvent("Fashion Dining 2020"));
				break;
				
				case 2:
					Scanner in2 = new Scanner (System.in);
					System.out.println("Enter your customer name: ");
					String customerName = in2.next();
					System.out.println("Enter your desired table (1~20): ");
					String table = in2.next();
					System.out.println("Enter your desired date (mm-dd-yy): ");
					String date = in2.next();
					System.out.println("Enter your desired time (hh:mm am/pm): ");
					String time = in2.next();
					
					studio.reserveTable(studio.getEvent("Fashion Dining 2020"),table,customerName,date,time);
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
	
	public static void partyScreen() {
		
	}
	
	public static void advertisementScreen() {
		
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

					if(studio.getEvent(eventNameReserve).addPromotion(businessName, text, location, dollarAmount)) {
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
}
