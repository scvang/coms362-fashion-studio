package com.fashion.commands;

import java.util.Scanner;

import com.fashion.Studio;
import com.fashion.employees.HumanResources;
import com.fashion.screens.MainScreen;

public class ConfirmBusinessCmd implements Command {
	
	private Studio studio;
	public ConfirmBusinessCmd(Studio s) {
		this.studio = s;
	}

	@Override
	public String getDescription() {
		return "Confirm Business";
	}

	@Override
	public void execute() {
		Scanner in = new Scanner(System.in);
		for (int i = 0; i < HumanResources.servicesUsed.size(); i++) {
			if (HumanResources.servicesUsed.get(i).hasBeenContacted() == false) {
				HumanResources.getServiceRequests();
				System.out.println("Would you like to contact them now? ('y' or 'n')\n");
				String yorn = in.next();
				in.nextLine();
				if (yorn.equals("y")) {
					HumanResources.servicesUsed.get(i).contactBusiness(HumanResources.servicesUsed.get(i));
					System.out.println("Service confirmed!\n");
				} else {
					System.out.println("Please be sure to contact them at a different date.\n");
				}
			} else {
				HumanResources.getServiceRequests();
			}
		}
		
		new MainScreen(studio).execute();
	}

}
