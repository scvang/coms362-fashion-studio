package com.fashion.events;

import java.util.HashMap;

/**
 * @author Sebastian Vang
 * 
 * Party is the information expert that knows about badges.
 *
 */
public class Party extends Event {
	
	private HashMap<String,Badge> whitelist = new HashMap<>();
	private int capacity;
	private int attendees;
	
	public Party(String name, String date, String time) {
		super(name, date, time);
		
		this.attendees = 0;
		this.capacity = 200;
	}
	
	public boolean removeBadgeReservation(String name) {
		if(whitelist.containsKey(name)) {
			whitelist.remove(name);
			--attendees;
			return true;
		}
		return false;
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
	
	public Badge getBadge(String name) {
		return whitelist.get(name);
	}
	
	public boolean hasBadgeReservaton(String name) {
		if(whitelist.containsKey(name)) {
			return true;
		}
		return false;
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
