package com.fashion.commands;

import java.util.Scanner;

import com.fashion.Main;
import com.fashion.Studio;

public class CheckTableCmd implements Command {
	private Studio studio;
	private String eventName;
	public CheckTableCmd(Studio s, String e) {
		this.studio = s;
		this.eventName = e;
	}

	@Override
	public String getDescription() {

		return "Check Table";
	}

	@Override
	public void execute() {
		Scanner in = new Scanner(System.in);
		System.out.println("Enter your customer name:");
		String name = in.nextLine();
		if(studio.hasTableReservation(name,studio.getEvent(eventName))) {
			System.out.println(
			"Name: " + studio.getTable(name,studio.getEvent(eventName)).getCustomerName() + "\n" +
			"Date: " + studio.getTable(name,studio.getEvent(eventName)).getDate() + "\n" + 
			"Time: " + studio.getTable(name,studio.getEvent(eventName)).getTime() + "\n" + 
			"Table: " + studio.getTable(name,studio.getEvent(eventName)).getTableNum() + "\n"
			);
		}
		else {
			System.out.println("No reservation found for " + name);
		}
		
		Main.eventScreen();
	}

}
