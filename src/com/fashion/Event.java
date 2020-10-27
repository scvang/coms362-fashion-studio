package com.fashion;

import com.fashion.pay.Card;

import java.util.Random;

/**
 * @author Sebastian Vang
 * 
 * Events is the information expert that knows about promotions.
 * 
 */
public class Event {
	
	/**
	 * Instance variables.
	 */
	private String name;
	private String date;
	private String time;

	Promotion[] promotions;

	/**
	 * Constructor for the event.
	 * @param name
	 */
	public Event(String name, String date, String time) {
		this.name = name;
		this.date = date;
		this.time = time;

		promotions = new Promotion[11];
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getDate() {
		return this.date;
	}
	
	public String getTime() {
		return this.time;
	}

	/**
	 @author Chad Morrow
	 Adds a promotion to this studio for an event
	 */
	public boolean addPromotion(String businessName, String text, int loc, double dollarAmount, Card card){
		Promotion p = new Promotion(businessName, text, dollarAmount);

		/*
		if the number of promotions at the studio is less than 10 than we have room for the new promotion
		 */
		if(numPromotionsOpen() > 0) {
			/*
			if the promotion location isn't filled by another company or the same company then we can add
			the new promotion to this location.
			 */
			if(isPromotionSpotOpen(loc)){
				//fake payment system with a 97% chance of acceptance
				if(payPromotion(card)){
					//fake
					if(evaluateOffer()) {
						p.setLocation(loc);
					} else  {
						System.out.println("Offer denied. Please re-offer!");
						return false;
					}
				} else {
					System.out.println("Card denied. Try again later");
					return false;
				}
			} else { //otherwise, if the promotion location is filled, then the business must look for
				//another location to place the promotion
				System.out.println("Promotions location is filled! Please look for another spot!");
				return false;
			}
		} else { //if we are at 10 promotions then the studio event is filled
			System.out.println("Promotion spots are filled! Good luck next event!");
			return false;
		}

		//increment the pid +1 more the highest value
		if(promotions.length != 0){
			int nextPid = findNextPid(promotions);
			p.setPid(nextPid);
		} else {
			p.setPid(1);
		}

		promotions[loc] = p;

		return promotions[loc] == p;
	}

	/**
	 * @author Chad Morrow
	 * @param promotions is the list of promotions at this studio event
	 * @return the next pid value for then new promotion
	 */
	private int findNextPid(Promotion[] promotions){
		int max = 0;
		if(numPromotionsOpen() < 10){
			for(Promotion promotion : promotions){
				if(max < promotion.getPid()){
					max = promotion.getPid();
				}
			}
		}

		return max + 1;
	}

	/**
	 @author Chad Morrow
	  * @param loc is the location of the where were checking if a promotion is
	 * @return the Promotion information at the requested promotion location
	Grabs promotion information from the studio for an event
	 */
	public Promotion getPromotion(int loc){
		return promotions[loc];
	}

	/**
	 * @author Chad Morrow
	 * @param loc is the location of the where were checking if a promotion is
	 * @return the boolean value if the promotion spot is taken for this studio
	 */
	public boolean isPromotionSpotOpen(int loc){
		return promotions[loc] == null;
	}

	/**
	 * @author Chad Morrow
	 * @return the number of promotion positions open for an event
	 */
	private int numPromotionsOpen() {
		int numFilled = 0;
		for (int i = 0; i < promotions.length; i++) {
			if (isPromotionSpotOpen(i)) {
				numFilled++;
			}
		}
		return numFilled;
	}
	
	/**
	 * 
	 * @param customerName
	 * @param cardNum
	 * @return
	 */
	public boolean payReservation(String customerName, int cardNum) {
		return true;
	}

	/**
	 * @param card info to pay for promotion
	 * @return true if payment completed, false otherwise
	 */
	public boolean payPromotion(Card card) {
		Random random = new Random();
		if(card != null) {
			return random.nextInt(100) <= 97;
		}
		return false;
	}

	public boolean evaluateOffer(){
		Random random = new Random();
		return random.nextInt(100) <= 90;
	}
}
