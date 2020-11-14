package com.fashion.events;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

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
	
	public ArrayList<String> getAttendeesList(){
		ArrayList<String> nameList = new ArrayList<>();
		
		Iterator iter = whitelist.entrySet().iterator();
		while(iter.hasNext()) {
			Map.Entry map = (Map.Entry) iter.next();
			String name = (String) map.getKey();
			nameList.add(name);
		}
		return nameList;
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
