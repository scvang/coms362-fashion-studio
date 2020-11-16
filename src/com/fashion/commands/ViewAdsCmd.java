package com.fashion.commands;

import com.fashion.Studio;
import com.fashion.screens.MainScreen;

public class ViewAdsCmd implements Command{
	
	private Studio studio;
	public ViewAdsCmd(Studio s) {
		this.studio = s;
	}

	@Override
	public String getDescription() {
		return "View Current Ads";
	}

	@Override
	public void execute() {
		studio.getAd();
		new MainScreen(studio).execute();
	}

}
