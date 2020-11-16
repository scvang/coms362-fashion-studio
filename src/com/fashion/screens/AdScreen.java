package com.fashion.screens;

import com.fashion.Studio;
import com.fashion.commands.*;

public class AdScreen implements Command{
	private Studio studio;
	public AdScreen(Studio s) {
		this.studio = s;
	}
	@Override
	public String getDescription() {

		return "Advertisement Screen";
	}
	@Override
	public void execute() {
		CommandDisplay cmd = new CommandDisplay();
		cmd.addCommand(new ViewAdsCmd(studio));
		cmd.addCommand(new CreateAdCmd(studio));
		cmd.addCommand(new MainScreen(studio));
		
		cmd.displayCommands();
	}
}
