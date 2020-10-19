package com.fashion;

import java.util.Scanner;

public class Main {
	
	/**
	 * Instance variables.
	 */
	public static Studio studio;
	
	/**
	 * Main
	 * @param args
	 */
	public static void main(String[] args) {
		
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
		
		/*
		String name = "John";
		String jobTitle = "Designer";
		int salary = 50000;
		String phoneNum = "N/A";
		
		studio.addEmployee(name, jobTitle, salary, phoneNum);
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
		int eid = 123;
		String eventName = "Spring";
		String loc = "401 Somewhere Ave";
		String time = "11:00 AM - 3:00 PM";
		String contactInfo = "555-555-5555";
		
		//studio.addAd(eid, eventName, loc, time, contactInfo);
		//studio.getAd();
		
		//Add a new Model test
		String modelName = "Jenna";
		String modNum = "111-111-1111";
		int audNum = 456;

		studio.addModel(modelName, modNum, audNum);
		studio.getEmployees();
		
		
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
					System.out.println("Go to advertisement Screen");
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
			"3) Party \n"
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
			"3) Refund \n"
			);
			
			choice = in.next();
			if(choice.equals("q") || choice.equals("'q'")) break;
			
			switch(Integer.parseInt(choice)){
				case 1:
					studio.displaySeats(studio.getEvent("FashionCon 2020"));
				break;
				
				case 2:
					Scanner in2 = new Scanner (System.in);
					
					System.out.println("Enter your desired seat (A1~I9): ");
					String seat = in2.next();
					System.out.println("Enter your desired date (mm-dd-yy): ");
					String date = in2.next();
					System.out.println("Enter your desired time (hh:mm am/pm): ");
					String time = in2.next();
					
					studio.reserveSeat(studio.getEvent("FashionCon 2020"),seat,date,time);
				break;
				
				case 3:
					
				break;
				
				case 4:
					
				break;
			}
		}
		in.close();
	}
	
	public static void diningScreen() {
		
	}
	
	public static void partyScreen() {
		
	}
	
	public static void advertisementScreen() {
		
	}
}
