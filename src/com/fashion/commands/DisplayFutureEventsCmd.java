package com.fashion.commands;

import com.fashion.Studio;
import com.fashion.screens.MainScreen;

public class DisplayFutureEventsCmd implements Command {
	private Studio studio;
	public DisplayFutureEventsCmd(Studio s) {
		this.studio = s;
	}

	@Override
	public String getDescription() {

		return "Display Upcoming Events";
	}

	@Override
	public void execute() {
		studio.displayEvents();
		System.out.println();
		System.out.println();
		
		new MainScreen(studio).execute();
	}

}
