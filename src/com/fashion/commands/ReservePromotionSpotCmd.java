package com.fashion.commands;

import java.util.Scanner;

import com.fashion.Studio;
import com.fashion.pay.Card;
import com.fashion.screens.PromotionScreen;

public class ReservePromotionSpotCmd implements Command {
	private Studio studio;
	public ReservePromotionSpotCmd(Studio s) {
		this.studio = s;
	}

	@Override
	public String getDescription() {
		return "Reserve Promotion Spot";
	}

	@Override
	public void execute() {
		Scanner in3 = new Scanner (System.in);
		System.out.println("Enter the event  ('q' to exit): ");
		String eventNameReserve = in3.nextLine();
		if(eventNameReserve.equals("q")) {
			System.out.println();
			System.out.println();
			new PromotionScreen(studio).execute();
		}
		while(studio.getEvent(eventNameReserve) == null) {
			System.out.println("Sorry! we could not find your event, please re-enter a new event  ('q' to exit): ");
			eventNameReserve = in3.nextLine().trim();
			if(eventNameReserve.equals("q")) {
				System.out.println();
				System.out.println();
				new PromotionScreen(studio).execute();
			}
		}

		System.out.println("Enter your business name ('q' to exit): ");
		String businessName = in3.nextLine();
		if(businessName.equals("q")) {
			System.out.println();
			System.out.println();
			new PromotionScreen(studio).execute();
		}

		System.out.println("What would you like it to say? ('q' to exit): ");
		String text = in3.nextLine();
		if(text.equals("q")) {
			System.out.println();
			System.out.println();
			new PromotionScreen(studio).execute();
		}

		System.out.println("Enter your desired promotion location ('q' to exit): ");
		int location = 0;
		while(location == 0){
			String temp = in3.next();
			if(temp.equals("q")) {
				System.out.println();
				System.out.println();
				new PromotionScreen(studio).execute();
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
				new PromotionScreen(studio).execute();
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
				new PromotionScreen(studio).execute();
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
				new PromotionScreen(studio).execute();
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
				new PromotionScreen(studio).execute();
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
				new PromotionScreen(studio).execute();
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
		
		new PromotionScreen(studio).execute();
	}

}
