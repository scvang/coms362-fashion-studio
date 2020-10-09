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
		studio.getEmployees();
		
		// Add a shirt test
		String itemName = "T-Shirt";
		String brandName = "DEUX";
		String color = "White";
		int id = 50021;
		int stock = 1;
		
		studio.addApparel(itemName,brandName,color,id,stock);
		studio.getApparel();
		
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
}
