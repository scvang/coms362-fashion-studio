package com.fashion.screens;

import com.fashion.Studio;
import com.fashion.commands.BeginContractNegCmd;
import com.fashion.commands.Command;
import com.fashion.commands.CommandDisplay;
import com.fashion.commands.ViewCurrentContractCmd;
import com.fashion.commands.ViewOldContractsCmd;
import com.fashion.negotiations.ContractSession;

public class ContractScreen implements Command{
	
	private Studio studio;
	public ContractScreen(Studio s) {
		this.studio = s;
	}
	@Override
	public String getDescription() {

		return "Contract Screen";
	}
	@Override
	public void execute() {
		ContractSession contractSession = new ContractSession();
		
		CommandDisplay cmd = new CommandDisplay();
		cmd.addCommand(new BeginContractNegCmd(studio,contractSession));
		cmd.addCommand(new ViewOldContractsCmd(studio,contractSession));
		cmd.addCommand(new ViewCurrentContractCmd(studio,contractSession));
		cmd.addCommand(new MainScreen(studio));
		
		cmd.displayCommands();
	}

}
