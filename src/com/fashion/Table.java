package com.fashion;

/**
 * The dining room has tables.
 * 
 * @author Sebastian Vang
 *
 */
public class Table {
	
	/**
	 * Instance variables.
	 */
	String num;
	String customer;
	String date;
	String time;
	
	/**
	 * Constructor for the table.
	 * @param num
	 * @param customer
	 * @param date
	 * @param time
	 */
	public Table(String num, String customer, String date, String time) {
		this.num = num;
		this.customer = customer;
		this.date = date;
		this.time = time;
	}
}
