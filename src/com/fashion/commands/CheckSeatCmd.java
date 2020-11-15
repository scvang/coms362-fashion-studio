package com.fashion.commands;

import java.util.Scanner;

import com.fashion.Main;
import com.fashion.Studio;

public class CheckSeatCmd implements Command {
	private Studio studio;
	private String eventName;
	public CheckSeatCmd(Studio s, String e) {
		this.studio = s;
		this.eventName = e;
	}

	@Override
	public String getDescription() {

		return "Check Seat";
	}

	@Override
	public void execute() {
		Scanner in = new Scanner(System.in);
		System.out.println("Enter your customer name:");
		String name = in.nextLine();
		if(studio.hasSeatReservation(name,studio.getEvent(eventName))) {
			System.out.println(
			"Name: " + studio.getSeat(name,studio.getEvent(eventName)).getCustomerName() + "\n" +
			"Date: " + studio.getSeat(name,studio.getEvent(eventName)).getDate() + "\n" + 
			"Time: " + studio.getSeat(name,studio.getEvent(eventName)).getTime() + "\n" + 
			"Seat: " + studio.getSeat(name,studio.getEvent(eventName)).getSeatNum() + "\n"
			);
		}
		else {
			System.out.println("No reservation found for " + name);
		}
		Main.EventScreen();
	}

}
