package com.fashion.screens;

import com.fashion.Studio;
import com.fashion.commands.AddCartItemCmd;
import com.fashion.commands.Command;
import com.fashion.commands.CommandDisplay;
import com.fashion.commands.DisplayApparelInfoCmd;
import com.fashion.commands.GoToCartCmd;
import com.fashion.commands.ProcessRefundCmd;
import com.fashion.commands.ShowApparelCmd;
import com.fashion.commands.ViewLastRefundCmd;
import com.fashion.shopping.ShoppingSession;

public class ShoppingScreen implements Command{
	private Studio studio;
	public ShoppingScreen(Studio s) {
		this.studio = s;
	}

	@Override
	public String getDescription() {

		return "Shopping Screen";
	}

	@Override
	public void execute() {
		ShoppingSession shoppingSession = new ShoppingSession();
		
		CommandDisplay cmd = new CommandDisplay();
		cmd.addCommand(new DisplayApparelInfoCmd(studio,shoppingSession));
		cmd.addCommand(new ShowApparelCmd(studio,shoppingSession));
		cmd.addCommand(new AddCartItemCmd(studio,shoppingSession));
		cmd.addCommand(new GoToCartCmd(studio,shoppingSession));
		cmd.addCommand(new ProcessRefundCmd(studio,shoppingSession));
		cmd.addCommand(new ViewLastRefundCmd(studio,shoppingSession));
		cmd.addCommand(new MainScreen(studio));
		
		cmd.displayCommands();
	}

}
