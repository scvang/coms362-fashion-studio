package com.fashion.events;

import java.util.HashMap;

/**
 * Party is the information expert that knows about badges.
 * @author Sebastian Vang
 *
 */
public class Party extends Event {
	
	private HashMap<String,Badge> whitelist = new HashMap<>();
	private int capacity;
	private int attendees;
	
	public Party(String name, String date, String time) {
		super(name, date, time);
		
		attendees = 0;
		capacity = 200;
	}
	
	public boolean reserveBadge(String customer, String date, String time) {
		Badge b = new Badge(customer,date,time);
		
		// Add to whitelist.
		if(attendees != capacity) {
			whitelist.put(customer,b);
		}
		
		++attendees;
		return true;
	}
	
	public int getCapacity() {
		return this.capacity;
	}
	
	public int getAttendees() {
		return this.attendees;
	}
	
	public boolean onWhiteList(String name) {
		
		if(whitelist.containsKey(name)) {
			return true;
		}
		
		return false;
	}
}
