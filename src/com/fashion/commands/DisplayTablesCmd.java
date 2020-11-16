package com.fashion.commands;

import com.fashion.Main;
import com.fashion.Studio;
import com.fashion.screens.EventScreen;

public class DisplayTablesCmd implements Command {
	private Studio studio;
	private String eventName;
	public DisplayTablesCmd(Studio s, String e) {
		this.studio = s;
		this.eventName = e;
	}

	@Override
	public String getDescription() {

		return "Display Tables";
	}

	@Override
	public void execute() {
		studio.displayTables(studio.getEvent(eventName));
		new EventScreen(studio).execute();
	}

}
