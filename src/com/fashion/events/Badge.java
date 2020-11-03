package com.fashion.events;

/**
 * 
 * @author Sebastian Vang
 * 
 * Party has badges they reserve to attendees.
 *
 */
public class Badge {
	
	private String name;
	private String date;
	private String time;
	
	public Badge(String name, String date, String time) {
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
}
