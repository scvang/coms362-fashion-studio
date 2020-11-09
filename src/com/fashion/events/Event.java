package com.fashion.events;

import com.fashion.Promotion;
import com.fashion.pay.Card;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

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
	private String event;
	private String date;
	private String time;
	private ArrayList<Event> event2;

	Promotion[] promotions;

	/**
	 * Constructor for the event.
	 * @param
	 */
	public Event() {
		event2 = new ArrayList<>();
	}
	protected Event(String event, String date, String time) {
		this.event = event;
		this.date = date;
		this.time = time;

		promotions = new Promotion[11];
	}
	
	public String getEvent() {
		return this.event;
	}
	
	public String getDate() {
		return this.date;
	}
	
	public String getTime() {
		return this.time;
	}
	/**
	 * @author Sebastian Vang
	 * @return event
	 */
	public ArrayList<Event> getEventList(){
		return this.event2;
	}
	
	/**
	 * @author Sebastian Vang
	 * @return event
	 */
	public Event getEvent(String name) {
		
		for(Event e : event2) {
			if(e.getEvent().equals(name)) {
				return e;
			}
		}
		return null;
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
	 * @author Sebastian Vang
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
			return random.nextInt(100) <= 98;
		}
		return false;
	}

	public boolean evaluateOffer(){
		Random random = new Random();
		return random.nextInt(100) <= 90;
	}
	
	
	/**
	 * @author Sebastian Vang
	 * Create one of three events.
	 * @param type
	 * @param eventName
	 * @param date
	 * @param time
	 */
	public void createEvent(String type, String eventName,String date,String time) {
		type = type.toLowerCase();
		switch(type) {
		case "showing":
			createShowingEvent(eventName,date,time);
			System.out.println("Showing event successfully created.");
		break;
		
		case "party":
			createPartyEvent(eventName,date,time);
			System.out.println("Dining event successfully created.");
		break;
		
		case "dining":
			createDiningEvent(eventName,date,time);
			System.out.println("Dining event successfully created.");
		break;
		
		default:
			System.out.println("Event was not created. " + type + " is not a valid event type. \n");
		}
	}
	
	/**
	 * @author Sebastian Vang
	 * Creates a showing event.
	 * @param name
	 * @param date
	 * @param time
	 */
	public void createShowingEvent(String name, String date, String time) {
		Event e = new Showing(name,date,time);
		this.event2.add(e);
	}
	
	/**
	 * @author Sebastian Vang
	 * Creates a party event.
	 * @param name
	 * @param date
	 * @param time
	 */
	public void createPartyEvent(String name, String date, String time) {
		Event e = new Party(name,date,time);
		this.event2.add(e);
	}
	
	/**
	 * @author Sebastian Vang
	 * Creates a dining event.
	 * @param name
	 * @param date
	 * @param time
	 */
	public void createDiningEvent(String name, String date, String time) {
		Event e = new Dining(name,date,time);
		this.event2.add(e);
	}
	
	/**
	 * @author Sebastian Vang
	 * Displays a list of hosted events.
	 */
	public void displayEvents() {
		int count = 1;
		for(Event e : event2) {
			System.out.println(count++ + ") " + e.getEvent());
		}
	}
	
	/**
	 * @author Sebastian Vang
	 * Displays the available seats.
	 * @param e event
	 */
	public boolean displaySeats(Event e) {
		Showing s = (Showing)e;
		s.displaySeats();
		System.out.println("Available seats: " + s.getOpenSeats() + "\n");
		
		return true;
	}
	
	/**
	 * @author Sebastian Vang
	 * @param event
	 * @return boolean
	 */
	public boolean isShowingFull(Event e) {
		Showing s = (Showing)e;
		
		if(s.getOpenSeats() == 0) return true;
		
		return false;
	}

	/**
	 * @author Sebastian Vang
	 * Reserves a seat for the customer.
	 * @param e event
	 * @param n seatNum
	 * @param c customerName
	 * @param d date
	 * @param t time
	 */
	public boolean reserveSeat(Event e, String number, String customer, String date, String time) {
		Showing s = (Showing)e;
		return s.reserveSeat(number,customer,date,time);
	}
	
	/**
	 * @author Sebastian Vang
	 * @param name
	 * @param e
	 * @return
	 */
	public boolean removeSeatReservation(String name,Event e) {
		Showing s = (Showing)e;
		return s.removeSeatReservation(name);
	}
	
	/**
	 * @author Sebastian Vang
	 * @param customerName
	 * @param e
	 * @return
	 */
	public boolean hasSeatReservation(String customerName,Event e) {
		Showing s = (Showing)e;
		return s.hasSeatReservation(customerName);
	}
	
	/**
	 * @author Sebastian Vang
	 * Displays the tables.
	 * @param e
	 */
	public boolean displayTables(Event e) {
		Dining d = (Dining)e;
		d.displayTables();
		System.out.println("Available tables: " + d.getOpenTables() + "\n");
		
		return true;
	}
	
	/**
	 * @author Sebastian Vang
	 * @param e
	 * @return
	 */
	public boolean isDiningFull(Event e) {
		Dining d = (Dining)e;
		if(d.getOpenTables() == 0) {
			return true;
		}
		return false;
	}

	/**
	 * @author Sebastian Vang
	 * Reserves a table for the customer.
	 * @param e event
	 * @param n tableNum
	 * @param c customerName
	 * @param d date
	 * @param t time
	 */
	public boolean reserveTable(Event e, String number, String customer, String date, String time) {
		Dining d = (Dining)e;
		return d.reserveTable(number, customer, date, time);
	}
	
	/**
	 * @author Sebastian Vang
	 * @param customerName
	 * @param e
	 * @return
	 */
	public boolean hasTableReservation(String customerName, Event e) {
		Dining d = (Dining)e;
		return d.hasTableReservation(customerName);
	}
	
	/**
	 * @author Sebastian Vang
	 * @param name
	 * @param e
	 * @return
	 */
	public boolean removeTableReservation(String name, Event e) {
		Dining d = (Dining)e;
		return d.removeTableReservation(name);
	}
	
	/**
	 * @author Sebastian Vang
	 * Reserves a badge.
	 * @param e event
	 * @param name
	 * @param date
	 * @param time
	 */
	public void reserveBadge(Event e, String name, String date, String time) {
		if(((Party)e).reserveBadge(name, date, time)) System.out.println("Success.");
	}
	
	/**
	 * @author Sebastian Vang
	 * @param name
	 * @param e
	 * @return
	 */
	public boolean hasBadgeReservation(String name, Event e) {
		Party p = (Party)e;
		return p.hasBadgeReservaton(name);
	}
	
	/**
	 * @author Sebastian Vang
	 * @param name
	 * @param event
	 * @return badge
	 */
	public Badge getBadge(String name, Event e) {
		Party p = (Party)e;
		return p.getBadge(name);
	}
	
	/**
	 * @author Sebastian Vang
	 * Checks if party is full.
	 * @param e
	 * @return
	 */
	public boolean isPartyFull(Event e) {
		Party p = (Party)e;
		if(p.getAttendees() == p.getCapacity()) return true;
		return false;
	}
	
	/**
	 * @author Sebastian Vang
	 * @param event
	 * @return number of attendees
	 */
	public int getNumOfAttendees(Event e) {
		Party p = (Party)e;
		return p.getAttendees();
	}
	
	/**
	 * @author Sebastian Vang
	 * Charges the card according to the event.
	 * @param e event
	 * @param c customer
	 */
	public boolean chargeCard(Event e, String c) {
		
		Scanner in = new Scanner(System.in);
		System.out.println("Enter last 4 digits of card information: ");
		
		// Wait for the next integer input.
		while(in.hasNext() && !in.hasNextInt()) {
			in.next();
		}
		int cardNum = in.nextInt();
		
		if(Integer.toString(cardNum).length() != 4) {
			System.out.println("Not a valid card number. Try again.");
			return false;
		}
		
		if(e.payReservation(c, cardNum)) System.out.println("Payment successful.");
		
		return true;
	}
	
	/**
	 * @author Sebastian Vang
	 * @param customer
	 * @param e
	 * @return
	 */
	public Seat getSeat(String customer,Event e) {
		Showing s = (Showing)e;
		return s.getSeat(customer);
	}
	
	/**
	 * @author Sebastian Vang
	 * @param customer
	 * @param e
	 * @return
	 */
	public Table getTable(String customer, Event e) {
		Dining d = (Dining)e;
		return d.getTable(customer);
	}
}
