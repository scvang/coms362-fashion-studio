package com.fashion;
/**
 * @author Sebastian Vang
 * 
 * Generic Events are hosted by the studio.
 * 
 */
public class Event {
	
	/**
	 * Instance variables.
	 */
	private String name;
	private String date;
	private String time;

	/**
	 * Constructor for the event.
	 * @param name
	 */
	public Event(String name, String date, String time) {
		this.name = name;
		this.date = date;
		this.time = time;
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
	
	public boolean payReservation(String customerName, int cardNum) {
		return true;
	}
}
