package com.fashion.commands;

import java.util.Scanner;

import com.fashion.Studio;
import com.fashion.screens.PromotionScreen;

public class CheckAvailablePromotionsCmd implements Command {
	private Studio studio;
	public CheckAvailablePromotionsCmd(Studio s) {
		this.studio = s;
	}

	@Override
	public String getDescription() {

		return "Check Available Promotions";
	}

	@Override
	public void execute() {
		Scanner in2 = new Scanner (System.in);
		System.out.println("Enter the event you'd like to view open promotion spots ('q' to exit): ");
		String eventName = in2.nextLine().trim();
		if(eventName.equals("q")) {
			System.out.println();
			System.out.println();
			new PromotionScreen(studio).execute();
		}

		while(studio.getEvent(eventName) == null){
			System.out.println("Sorry! we could not find your event, please re-enter a new event ('q' to exit): ");
			eventName = in2.nextLine().trim();
			if(eventName.equals("q")) {
				System.out.println();
				System.out.println();
				new PromotionScreen(studio).execute();
			}
		}

		if(studio.getEvent(eventName).isPromotionSpotOpen(1)){
			System.out.println("1:  Open");
		} else {
			System.out.println("1:  Taken");
		}

		if(studio.getEvent(eventName).isPromotionSpotOpen(2)){
			System.out.println("2:  Open");
		} else {
			System.out.println("2:  Taken");
		}

		if(studio.getEvent(eventName).isPromotionSpotOpen(3)){
			System.out.println("3:  Open");
		} else {
			System.out.println("3:  Taken");
		}

		if(studio.getEvent(eventName).isPromotionSpotOpen(4)){
			System.out.println("4:  Open");
		} else {
			System.out.println("4:  Taken");
		}

		if(studio.getEvent(eventName).isPromotionSpotOpen(5)){
			System.out.println("5:  Open");
		} else {
			System.out.println("5:  Taken");
		}

		if(studio.getEvent(eventName).isPromotionSpotOpen(6)){
			System.out.println("6:  Open");
		} else {
			System.out.println("6:  Taken");
		}

		if(studio.getEvent(eventName).isPromotionSpotOpen(7)){
			System.out.println("7:  Open");
		} else {
			System.out.println("7:  Taken");
		}

		if(studio.getEvent(eventName).isPromotionSpotOpen(8)){
			System.out.println("8:  Open");
		} else {
			System.out.println("8:  Taken");
		}

		if(studio.getEvent(eventName).isPromotionSpotOpen(9)){
			System.out.println("9:  Open");
		} else {
			System.out.println("9:  Taken");
		}

		if(studio.getEvent(eventName).isPromotionSpotOpen(10)){
			System.out.println("10: Open");
		} else {
			System.out.println("10: Taken");
		}

		System.out.println();
		System.out.println();
		
		new PromotionScreen(studio).execute();
	}

}
