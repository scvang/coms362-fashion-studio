package com.fashion.commands;

import java.util.Scanner;

import com.fashion.Main;
import com.fashion.Studio;
import com.fashion.screens.EventScreen;

public class CheckBadgeCmd implements Command {
	private Studio studio;
	private String eventName;
	public CheckBadgeCmd(Studio s, String e) {
		this.studio = s;
		this.eventName = e;
	}

	@Override
	public String getDescription() {

		return "Check Badge";
	}

	@Override
	public void execute() {
		Scanner in = new Scanner(System.in);
		System.out.println("Enter your customer name:");
		String name = in.nextLine();
		if(studio.hasBadgeReservation(name,studio.getEvent(eventName))) {
			System.out.println(
					"Name: " + studio.getBadge(name,studio.getEvent(eventName)).getName() + "\n" +
					"Date: " + studio.getBadge(name,studio.getEvent(eventName)).getDate() + "\n" + 
					"Time: " + studio.getBadge(name,studio.getEvent(eventName)).getTime() + "\n"
					);
		}
		else {
			System.out.println("No reservation found.");
		}
		
		new EventScreen(studio).execute();
	}

}
