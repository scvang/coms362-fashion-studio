package com.fashion;

import java.util.HashMap;

public class Party extends Event {
	
	HashMap<Badge,Integer> whitelist = new HashMap<>();
	int attendees;
	
	public Party(String name, String date, String time) {
		super(name, date, time);
		
		attendees = 0;
	}
	
	public void reserveBadge(Badge id, String customer, String date, String time) {
		
	}

}
