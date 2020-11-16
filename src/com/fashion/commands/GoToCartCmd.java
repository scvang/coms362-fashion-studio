package com.fashion.commands;

import java.util.Scanner;

import com.fashion.Studio;
import com.fashion.apparel.Apparel;
import com.fashion.screens.ShoppingScreen;
import com.fashion.shopping.ShoppingSession;

public class GoToCartCmd implements Command {
	private Studio studio;
	private ShoppingSession shoppingSession;
	public GoToCartCmd(Studio s, ShoppingSession ss) {
		this.studio = s;
		this.shoppingSession = ss;
	}

	@Override
	public String getDescription() {

		return "Go To Cart";
	}

	@Override
	public void execute() {
		System.out.println(shoppingSession.getCart().toString());
		System.out.println();

		Scanner in4 = new Scanner (System.in);

		if(!shoppingSession.getCart().getItems().isEmpty()) {
			System.out.println("Edit Cart? (y/n): ");

			String response = in4.nextLine().trim();
			if (response.equals("y")) {
				System.out.println("What item would you like to remove from your cart? ('q' to exit): ");
				String itemName = in4.nextLine().trim();
				if(itemName.equals("q")) {
					System.out.println();
					new ShoppingScreen(studio).execute();
				}

				System.out.println("What size? ('q' to exit): ");
				String size = in4.nextLine().trim();
				if(size.equals("q")) {
					System.out.println();
					new ShoppingScreen(studio).execute();
				}
				shoppingSession.getCart().removeItem(new Apparel(size, itemName));

				if(shoppingSession.getCart().getItems().size() == 0)
					System.out.println();
					new ShoppingScreen(studio).execute();
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
					new ShoppingScreen(studio).execute();
				}
			} else if (response.equals("n")) {
				System.out.println();
				new ShoppingScreen(studio).execute();
			}
		}
		new ShoppingScreen(studio).execute();
	}

}
