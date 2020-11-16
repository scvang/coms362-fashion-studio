package com.fashion.commands;

import com.fashion.Main;
import com.fashion.Studio;
import com.fashion.screens.EventScreen;

public class DisplaySeatsCmd implements Command {
	private Studio studio;
	private String eventName;
	public DisplaySeatsCmd(Studio s, String e) {
		this.studio = s;
		this.eventName = e;
	}

	@Override
	public String getDescription() {

		return "Display Seats";
	}

	@Override
	public void execute() {
		studio.displaySeats(studio.getEvent(eventName));
		new EventScreen(studio).execute();
	}

}