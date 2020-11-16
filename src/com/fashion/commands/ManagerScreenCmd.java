package com.fashion.commands;

import java.util.Scanner;

import com.fashion.Studio;
import com.fashion.employees.EmployeeSession;
import com.fashion.screens.EmployeeScreen;

public class ManagerScreenCmd implements Command {
	
	private Studio studio;
	public ManagerScreenCmd(Studio studio) {
		this.studio = studio;
	}

	@Override
	public String getDescription() {

		return "Manager Screen";
	}

	@Override
	public void execute() {
		EmployeeSession employeeSession = new EmployeeSession();
		Scanner in3 = new Scanner (System.in);

		System.out.println("Username: ");
		String username = in3.next();
		System.out.println("Password: ");
		String password = in3.next();
		if(employeeSession.getAccessRights(username, password)){
			System.out.println(
					"Select an action: \n" +
							"1: Hire employee \n" +
							"2: Fire employee \n" +
							"3: Back"
			);

			switch(Integer.parseInt(in3.next())){
				case 1:
					employeeSession.hireEmployee();
					break;
				case 2:
					employeeSession.fireEmployee();
					break;
				default:
					System.out.println();
					break;
			}
		}
		
		new EmployeeScreen(studio).execute();
	}

}
