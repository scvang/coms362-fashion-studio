package com.fashion.screens;

import com.fashion.Studio;
import com.fashion.commands.CheckAvailablePromotionsCmd;
import com.fashion.commands.Command;
import com.fashion.commands.CommandDisplay;
import com.fashion.commands.DisplayFutureEventsCmd;
import com.fashion.commands.ReservePromotionSpotCmd;

public class PromotionScreen implements Command{
	private Studio studio;
	public PromotionScreen(Studio s) {
		this.studio = s;
	}
	@Override
	public String getDescription() {

		return "Promotion Screen";
	}
	@Override
	public void execute() {
		CommandDisplay cmd = new CommandDisplay();
		cmd.addCommand(new DisplayFutureEventsCmd(studio));
		cmd.addCommand(new CheckAvailablePromotionsCmd(studio));
		cmd.addCommand(new ReservePromotionSpotCmd(studio));
		cmd.addCommand(new MainScreen(studio));
		
		cmd.displayCommands();
	}

}
