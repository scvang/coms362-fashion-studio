package com.fashion;

/**
 * 
 * @author Emily Young
 *
 */

public class Guest {

	/**
	 * Instance variables
	 */
	private String name;
	private String seatNum;

	/**
	 * Constructor
	 * 
	 * @param name
	 * @param seatNum
	 */
	public Guest(String name, String seatNum) {
		this.name = name;
		this.seatNum = seatNum;
	}

	public String getName() {
		return name;
	}

	public String getSeatNum() {
		return seatNum;
	}
}
