package com.fashion;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		
		// Create a studio
		String company = "Fashion Inc";
		String address = "401 Somewhere Ave";
		String phone = "555-555-5555";
		
		Studio studio = new Studio(company,address,phone);
		
		// Add an employee test
		String name = "John";
		String jobTitle = "Designer";
		int salary = 50000;
		String phoneNum = "N/A";
		
		studio.addEmployee(name, jobTitle, salary, phoneNum);

		// Add a shirt test
		String itemName = "T-Shirt";
		String brandName = "DEUX";
		String color = "White";
		int id = 50021;
		int stock = 1;
		
		studio.addApparel(itemName,brandName,color,id,stock);
		studio.getApparel();
		
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
			"3) Agents \n"
			);
			
			choice = in.next();
			if(choice.equals("q") || choice.equals("'q'")) break;
			
			switch(Integer.parseInt(choice)){
				case 1:
					System.out.println("Go to employee screen");
					employeeScreen();
				break;
				
				case 2:
					System.out.println("Go to apparel screen");
					apparelScreen();
				break;
				
				case 3:
					System.out.println("Go to agents screen");
					agentScreen();
				break;
				
				case 4:
					System.out.println("Go to advertisement Screen");
					advertisementScreen();
				break;
			}
		}
		in.close();
	}
	
	public static void employeeScreen() {
		
	}
	
	public static void apparelScreen() {
		
	}
	
	public static void agentScreen() {
		
	}
	
	public static void advertisementScreen() {
		
	}
}
