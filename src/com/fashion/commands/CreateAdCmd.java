package com.fashion.commands;

import java.util.Scanner;

import com.fashion.Advertisement;
import com.fashion.Studio;
import com.fashion.screens.MainScreen;

public class CreateAdCmd implements Command {
	private Studio studio;
	public CreateAdCmd(Studio s) {
		this.studio = s;
	}

	@Override
	public String getDescription() {

		return "Create New Ad";
	}

	@Override
	public void execute() {
		Scanner in = new Scanner(System.in);
		System.out.println("What is the event ID?: ");
		int eid = in.nextInt();
		System.out.println("What type of advertisement do you need? (paper or video)");
		String adType = in.next();
		System.out.println("What is the name of the event?: ");
		String eventName = in.next() + in.next();
		System.out.println("Where is the location of the event?: ");
		String loc = in.next();
		System.out.println("What is the time of the event?: ");
		String time = in.next();
		System.out.println("What is the the number we should contact?: ");
		String contactInfo = in.next();
		Advertisement advert = new Advertisement(eid, eventName, loc, time, contactInfo);
		if(adType.equals("paper")) {
//			studio.addAd(eid, eventName, loc, time, contactInfo);
			System.out.println("Advertisement Created!");
		} else if(adType.equals("video")) {
//			advert.createAdVideo();
//			studio.addAd(eid, eventName, loc, time, contactInfo);
			System.out.println("Advertisement Created!");
		} else {
			System.out.print("We don't currently support that type of advertisement at this time.");
		}
		
		new MainScreen(studio).execute();
	}

}
