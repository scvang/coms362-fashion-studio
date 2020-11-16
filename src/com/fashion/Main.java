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
		cmd.addCommand(new ModelScreen(studio));
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
	 * @author Sebastian Vang
	 * Model Screen.
	 */
	public static void ModelScreen() {
		CommandDisplay cmd = new CommandDisplay();
		cmd.addCommand(new ListModelsCmd(studio));
		cmd.addCommand(new ChangeApparelCmd(studio));
		cmd.addCommand(new UploadPhotoCmd(studio));
		cmd.addCommand(new AddModelCmd(studio));
		cmd.addCommand(new DisplayModelInfoCmd(studio));
		cmd.addCommand(new MainScreenCmd());
		cmd.displayCommands();
	}
	
	public static void EmployeeScreen() {
		
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
