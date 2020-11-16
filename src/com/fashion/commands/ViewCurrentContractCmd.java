package com.fashion.commands;

import com.fashion.Studio;
import com.fashion.negotiations.ContractSession;
import com.fashion.screens.MainScreen;

public class ViewCurrentContractCmd implements Command {

	private Studio studio;
	private ContractSession contractSession;
	public ViewCurrentContractCmd(Studio s, ContractSession cs) {
		this.studio = s;
		this.contractSession = cs;
	}

	@Override
	public String getDescription() {
		return "View Current Contract";
	}

	@Override
	public void execute() {
		contractSession.viewCurrentContract();
		new MainScreen(studio).execute();

	}

}
