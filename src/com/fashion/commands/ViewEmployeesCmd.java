package com.fashion.commands;

import com.fashion.Studio;
import com.fashion.screens.EmployeeScreen;

public class ViewEmployeesCmd implements Command {
	private Studio studio;
	public ViewEmployeesCmd(Studio s) {
		this.studio = s;
	}

	@Override
	public String getDescription() {
		return "View Employees";
	}

	@Override
	public void execute() {
		studio.getEmployees();
		System.out.println();
		System.out.println();
		new EmployeeScreen(studio).execute();
	}

}
