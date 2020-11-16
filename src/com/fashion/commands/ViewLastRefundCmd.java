package com.fashion.commands;

import com.fashion.Studio;
import com.fashion.screens.ShoppingScreen;
import com.fashion.shopping.ShoppingSession;

public class ViewLastRefundCmd implements Command {
	private Studio studio;
	private ShoppingSession shoppingSession;
	public ViewLastRefundCmd(Studio s, ShoppingSession ss) {
		this.studio = s;
		this.shoppingSession = ss;
	}

	@Override
	public String getDescription() {
		return "View Last Refund";
	}

	@Override
	public void execute() {
		shoppingSession.viewLastRefund();
		System.out.println();
		new ShoppingScreen(studio).execute();
	}

}
