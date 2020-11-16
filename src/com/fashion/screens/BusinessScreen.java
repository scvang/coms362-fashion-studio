package com.fashion.screens;

import com.fashion.Studio;
import com.fashion.commands.*;

public class BusinessScreen implements Command {
	private Studio studio;
	public BusinessScreen(Studio s) {
		this.studio = s;
	}
	@Override
	public String getDescription() {

		return "Business Screen";
	}
	@Override
	public void execute() {
		CommandDisplay cmd = new CommandDisplay();
		cmd.addCommand(new ViewBusinessRecordsCmd(studio));
		cmd.addCommand(new HireBusinessCmd(studio));
		cmd.addCommand(new ConfirmBusinessCmd(studio));
		cmd.addCommand(new MainScreen(studio));
		
		cmd.displayCommands();
		
	}

}
