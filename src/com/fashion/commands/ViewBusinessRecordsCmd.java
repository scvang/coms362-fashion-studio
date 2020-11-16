package com.fashion.commands;

import com.fashion.Studio;
import com.fashion.employees.HumanResources;
import com.fashion.screens.MainScreen;

public class ViewBusinessRecordsCmd implements Command {
	private Studio studio;
	public ViewBusinessRecordsCmd(Studio s) {
		this.studio = s;
	}

	@Override
	public String getDescription() {

		return "View Business Records";
	}

	@Override
	public void execute() {
		HumanResources.getServices();
		new MainScreen(studio).execute();
	}

}
