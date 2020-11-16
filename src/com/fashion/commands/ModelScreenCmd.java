package com.fashion.commands;

import com.fashion.Main;

public class ModelScreenCmd implements Command {
	@Override
	public String getDescription() {

		return "Model Screen";
	}

	@Override
	public void execute() {
		Main.ModelScreen();
	}

}
