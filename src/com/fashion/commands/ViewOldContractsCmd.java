package com.fashion.commands;

import com.fashion.Studio;
import com.fashion.negotiations.ContractSession;
import com.fashion.screens.MainScreen;

public class ViewOldContractsCmd implements Command {
	private Studio studio;
	private ContractSession contractSession;
	public ViewOldContractsCmd(Studio s, ContractSession cs) {
		this.studio = s;
		this.contractSession = cs;
	}

	@Override
	public String getDescription() {
		return "View Old Contracts";
	}

	@Override
	public void execute() {
		contractSession.viewOldContracts();
		new MainScreen(studio).execute();
	}

}
