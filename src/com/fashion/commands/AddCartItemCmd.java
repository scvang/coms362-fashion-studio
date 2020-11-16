package com.fashion.commands;

import java.util.Scanner;

import com.fashion.Studio;
import com.fashion.apparel.Apparel;
import com.fashion.screens.ShoppingScreen;
import com.fashion.shopping.ShoppingSession;

public class AddCartItemCmd implements Command {
	private Studio studio;
	private ShoppingSession shoppingSession;
	
	public AddCartItemCmd(Studio s, ShoppingSession ss) {
		this.studio = s;
		this.shoppingSession = ss;
	}

	@Override
	public String getDescription() {

		return "Add Item To Your Cart";
	}

	@Override
	public void execute() {
		System.out.println();
		Scanner in3 = new Scanner (System.in);

		System.out.println("What item would you like to add to your cart? ('q' to exit): ");
		String itemName = in3.nextLine().trim();
		if(itemName.equals("q")) {
			System.out.println();
			new ShoppingScreen(studio).execute();
		}

		System.out.println("What size? ('q' to exit): ");
		String size = in3.nextLine().trim();
		if(size.equals("q")) {
			System.out.println();
			new ShoppingScreen(studio).execute();
		}
		shoppingSession.getCart().addItem(new Apparel(size, itemName));
		System.out.println();
		
		new ShoppingScreen(studio).execute();
	}

}
