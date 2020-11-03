package com.fashion.events;

/**
 * 
 * Venue is the generic class used to describe any venue that an event would be
 * held at
 * 
 * @author Emily Young
 *
 */

public class Venue {

	/**
	 * Instance variables
	 */
	private String name;
	private String loc;
	private String contactInfo;
	private int capacity;

	/**
	 * Constructor for Venue
	 * 
	 * @param name-venue name
	 * @param loc-location of venue
	 * @param contactInfo-contact information for venue
	 * @param capacity-capacity of venue
	 */
	public Venue(String name, String loc, String contactInfo, int capacity) {
		this.name = name;
		this.loc = loc;
		this.contactInfo = contactInfo;
		this.capacity = capacity;
	}

	public String getName() {
		return name;
	}

	public String getLoc() {
		return loc;
	}

	public String getContact() {
		return contactInfo;
	}

	public int getCapacity() {
		return capacity;
	}
	
	
}
