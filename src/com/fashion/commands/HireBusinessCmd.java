package com.fashion.commands;

import java.util.Scanner;

import com.fashion.Studio;
import com.fashion.employees.HumanResources;
import com.fashion.screens.MainScreen;

public class HireBusinessCmd implements Command {
	private Studio studio;
	public HireBusinessCmd(Studio s) {
		this.studio = s;
	}

	@Override
	public String getDescription() {

		return "Hire Business";
	}

	@Override
	public void execute() {
		Scanner in = new Scanner(System.in);
		System.out.println("What is the service ID?");
		int sid = in.nextInt();
		in.nextLine();
		System.out.println("What is the name of the Business?");
		String name = in.nextLine();
		System.out.println("What is the address?");
		String loc = in.nextLine();
		System.out.println("What is the service requested?");
		String service = in.nextLine();
		System.out.println("Who do we contact?");
		String repName = in.nextLine();
		System.out.println("Please provice their phone number.");
		String contactInfo = in.nextLine();
		System.out.println("How much are they charging?");
		double salary = in.nextDouble();
		in.nextLine();
		HumanResources.hireBusiness(sid, name, loc, service, repName, contactInfo, salary);
		
		new MainScreen(studio).execute();
	}

}
