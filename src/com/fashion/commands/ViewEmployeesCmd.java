package com.fashion.commands;

import com.fashion.Studio;
import com.fashion.employees.EmployeeSession;
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
		EmployeeSession employeeSession1 = new EmployeeSession();
		employeeSession1.viewEmployees();
		employeeSession1.displayHeadshot();
		new EmployeeScreen(studio).execute();
	}

}
