package com.fashion.commands;

import com.fashion.Main;
import com.fashion.Studio;
import com.fashion.screens.ModelScreen;

public class ListModelsCmd implements Command {
	private Studio studio;
	public ListModelsCmd(Studio s) {
		this.studio = s;
	}

	@Override
	public String getDescription() {

		return "List Models";
	}

	@Override
	public void execute() {
		studio.displayModels();
		new ModelScreen(studio).execute();
	}

}
