package com.fashion.commands;

import com.fashion.Main;
import com.fashion.Studio;
import com.fashion.screens.EventScreen;

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
		new EventScreen(studio).execute();
	}

}
