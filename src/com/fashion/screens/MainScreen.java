package com.fashion.screens;

import com.fashion.Studio;
import com.fashion.commands.*;

public class MainScreen implements Command{
	private Studio studio;
	public MainScreen(Studio s) {
		this.studio = s;
	}
	@Override
	public String getDescription() {

		return "Main Screen";
	}
	@Override
	public void execute() {
		CommandDisplay cmd = new CommandDisplay();
		cmd.addCommand(new InventoryScreen(studio));
		cmd.addCommand(new EventScreen(studio));
		cmd.addCommand(new ModelScreen(studio));
		cmd.addCommand(new EmployeeScreen(studio));
		cmd.addCommand(new BusinessScreen(studio));
		cmd.addCommand(new PromotionScreen(studio));
		cmd.addCommand(new AdScreen(studio));
		cmd.addCommand(new ShoppingScreen(studio));
		
		cmd.displayCommands();
	}
}
