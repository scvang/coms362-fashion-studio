package com.fashion.commands;

import com.fashion.Studio;
import com.fashion.negotiations.ContractSession;
import com.fashion.screens.ContractScreen;
import com.fashion.screens.MainScreen;

public class BeginContractNegCmd implements Command {

	private Studio studio;
	private ContractSession contractSession;
	public BeginContractNegCmd(Studio s, ContractSession cs) {
		this.studio = s;
		this.contractSession = cs;
	}

	@Override
	public String getDescription() {

		return "Begin Contract Negotiation";
	}

	@Override
	public void execute() {
		contractSession.negotiate();
		new ContractScreen(studio).execute();
	}

}
