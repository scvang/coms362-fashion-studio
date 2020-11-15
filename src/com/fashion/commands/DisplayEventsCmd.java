package com.fashion.commands;

import com.fashion.Main;
import com.fashion.Studio;

public class DisplayEventsCmd implements Command {
	private Studio studio;
	public DisplayEventsCmd(Studio s) {
		this.studio = s;
	}

	@Override
	public String getDescription() {

		return "Display Events";
	}

	@Override
	public void execute() {
		studio.displayEvents();
		Main.EventScreen();
	}

}
