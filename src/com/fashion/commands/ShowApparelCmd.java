package com.fashion.commands;

import java.util.Scanner;

import com.fashion.Studio;
import com.fashion.screens.ShoppingScreen;
import com.fashion.shopping.ShoppingSession;

public class ShowApparelCmd implements Command {
	private Studio studio;
	private ShoppingSession shoppingSession;
	public ShowApparelCmd(Studio s, ShoppingSession ss) {
		this.studio = s;
		this.shoppingSession = ss;
	}

	@Override
	public String getDescription() {

		return "Show Apparel";
	}

	@Override
	public void execute() {
		System.out.println();
		Scanner in2 = new Scanner (System.in);
		System.out.println("What apparel item would you like to view? ('q' to exit): ");
		String itemName = in2.nextLine().trim();
		if(itemName.equals("q")) {
			System.out.println();
			new ShoppingScreen(studio).execute();
		}
		shoppingSession.apparelImage(itemName);
		System.out.println();
		
		new ShoppingScreen(studio).execute();
	}

}
