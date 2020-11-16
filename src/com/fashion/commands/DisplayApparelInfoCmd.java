package com.fashion.commands;

import com.fashion.Studio;
import com.fashion.screens.ShoppingScreen;
import com.fashion.shopping.ShoppingSession;

public class DisplayApparelInfoCmd implements Command {

	private Studio studio;
	private ShoppingSession shoppingSession;
	public DisplayApparelInfoCmd(Studio s, ShoppingSession ss) {
		this.studio = s;
		this.shoppingSession = ss;
	}

	@Override
	public String getDescription() {

		return "Display Apparel Information";
	}

	@Override
	public void execute() {
		System.out.println();
		shoppingSession.displayApparel();
		System.out.println();
		
		new ShoppingScreen(studio).execute();
	}

}
