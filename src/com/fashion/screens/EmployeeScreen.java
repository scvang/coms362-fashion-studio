package com.fashion.screens;

import com.fashion.Studio;
import com.fashion.commands.*;

public class EmployeeScreen implements Command{
	private Studio studio;
	public EmployeeScreen(Studio s) {
		this.studio = s;
	}

	@Override
	public String getDescription() {

		return "Employee Screen";
	}

	@Override
	public void execute() {
		
		CommandDisplay cmd = new CommandDisplay();
		cmd.addCommand(new ViewEmployeesCmd(studio));
		cmd.addCommand(new PayEmployeesCmd(studio));
		cmd.addCommand(new ManagerScreenCmd(studio));
		cmd.addCommand(new MainScreen(studio));
		cmd.displayCommands();
	}

}
