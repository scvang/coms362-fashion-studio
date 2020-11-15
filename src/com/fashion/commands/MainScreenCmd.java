package com.fashion.commands;

import com.fashion.Main;
import com.fashion.Studio;

public class MainScreenCmd implements Command {

	@Override
	public String getDescription() {
		
		return "Main Screen";
	}

	@Override
	public void execute() {
		Main.Screen();
	}

}
