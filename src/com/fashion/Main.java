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
		
		Studio studio = new Studio(company,address,phone);
		
		// Add an employee test
		int eid = 1;
		String name = "John";
		String jobTitle = "Designer";
		double salary = 50000;
		String phoneNum = "N/A";

		studio.addEmployee(eid, name, jobTitle, phoneNum, salary, 0, 0);

		// Add a shirt test
		String itemName = "T-Shirt";
		String brandName = "DEUX";
		String color = "White";
		int id = 50021;
		int stock = 1;
		
		studio.addApparel(itemName,brandName,color,id,stock);
		studio.getApparel();
		
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
