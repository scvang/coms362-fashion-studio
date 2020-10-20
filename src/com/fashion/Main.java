package com.fashion;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {
<<<<<<< HEAD
	private static double minPromotionValue = 1000.0;
	private static double maxPromotionValue = 15000.0;
	private static int maxBusinesses = 7;
	private static String[] businessName = {"Joe's", "Mike's", "Dienda's", "Hugh's", "Dior", "Gucci", "Ralph Lauren",
		"Louis Vuitton", "Chanel", "Rolex", "Balenciaga", "Armani", "Yves Saint Laurent", "Tiffany",
			"Burberry", "Hermes", "Cartier", "Prada", "Fendi", "Lancome"};
	private static String[] businessAddresses = {"450 Grope Lane", "39 Fabulous Texan Way", "90 Ha-Ha Road",
			"126 Man Fuk Road", "67 Mad Dog Lane", "1 Boring Road", "666 Bad Route Road", "900 Smellies Lane",
			"12 Butt Street", "879 Break-Me-Neck Hill", "1285 Whip-Ma-Whop-Ma-Gate", "78 Silly Goose Lane"};

=======
	
	/**
	 * Instance variables.
	 */
	public static Studio studio;
	
	/**
	 * Main
	 * @param args
	 */
>>>>>>> 4a9a6e1e7ce572c34954945c8ac0bcc171618e3e
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
		
		// Add an employee test
<<<<<<< HEAD
		int eid = 1;
=======
		
		/*
>>>>>>> 4a9a6e1e7ce572c34954945c8ac0bcc171618e3e
		String name = "John";
		String jobTitle = "Designer";
		double salary = 50000;
		String phoneNum = "N/A";
<<<<<<< HEAD

		studio.addEmployee(eid, name, jobTitle, phoneNum, salary, 0, 0);

=======
		
		studio.addEmployee(name, jobTitle, salary, phoneNum);
		studio.getEmployees();
    
		*/
		
>>>>>>> 4a9a6e1e7ce572c34954945c8ac0bcc171618e3e
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
			"4) Events \n"
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
			}
		}
		in.close();
	}
	
	public static void employeeScreen() {
		//TODO
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
