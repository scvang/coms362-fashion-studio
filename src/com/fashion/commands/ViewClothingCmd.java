package com.fashion.commands;

import com.fashion.Main;
import com.fashion.Studio;
import com.fashion.screens.*;

public class ViewClothingCmd implements Command{
	private Studio studio;
	public ViewClothingCmd(Studio s) {
		this.studio = s;
	}

	@Override
	public String getDescription() {
		return "View Clothing";
	}

	@Override
	public void execute() {
		studio.displayClothingInventory();
		new InventoryScreen(studio).execute();
	}
}
