package com.fashion.commands;

import com.fashion.Main;

public class EventScreenCmd implements Command {

	@Override
	public String getDescription() {

		return "Event Screen";
	}

	@Override
	public void execute() {
		Main.EventScreen();
	}

}
