package com.fashion.commands;

import java.util.ArrayList;

import com.fashion.Main;
import com.fashion.Studio;
import com.fashion.screens.EventScreen;

public class DisplayAttendeesCmd implements Command {
	private Studio studio;
	private String eventName;
	public DisplayAttendeesCmd(Studio s, String e) {
		this.studio = s;
		this.eventName = e;
	}

	@Override
	public String getDescription() {

		return "Display Attendees";
	}

	@Override
	public void execute() {
		System.out.println("Attendees:");
		ArrayList<String> attList = studio.getAttendeesList(studio.getEvent(eventName));
		for(String s : attList) {
			System.out.println(s);
		}
		System.out.println("There are: " + studio.getNumOfAttendees(studio.getEvent(eventName)) + " number of attendees.");
		new EventScreen(studio).execute();
	}

}
