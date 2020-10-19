package com.fashion;

import java.util.HashMap;

/**
 * The studio hosts dining events.
 * @author Sebastian Vang
 *
 */
public class Dining extends Event{
	
	/**
	 * Instance variables.
	 */
	
	private int openTables;
	private Table[] table = new Table[20];
	private HashMap<String,Integer> whitelist = new HashMap<>();
	
	/**
	 * Constructor for the dining event.
	 * @param name
	 * @param date
	 * @param time
	 */
	public Dining(String name, String date, String time) {
		super(name, date, time);
		
		// Initializes empty tables.
		int tableNum = 1;
		for(int i = 0; i < table.length; ++i) {
			table[i] = new Table(Integer.toString(tableNum),"","","");
			++tableNum;
		}
		openTables = countTables();
	}
	
	/**
	 * Gets number of available seats.
	 * @return number of open seats
	 */
	public int countTables() {
		int count = 0;
		for(int i = 0; i < table.length; ++i){
			if(table[i].customer == "") ++count;
		}
		return count;
	}
	
	/**
	 * 
	 * @return open tables
	 */
	public int getOpenTables() {
		return openTables;
	}
	
	/**
	 * Displays the available tables.
	 */
	public void displayTables() {
        for(int i = 0; i < table.length; ++i) {
        	System.out.print(table[i].num + " ");
        	if(i == 5) System.out.println("");
        }
	}

	/**
	 * Reserves a table for the dining event.
	 * @param num
	 * @param customer
	 * @param date
	 * @param time
	 */
	public void reserveTable(String num, String customer, String date, String time) {
		// Convert to upper case before processing.
		int tableNum = Integer.parseInt(num);
		
		// Checks if the seat is in range 1 to 20.
		if(tableNum < 1 || tableNum > 20){
			
			if(whitelist.containsKey(num)) {
				System.out.println("Table " + num + " is already reserved.");
				return;
			}
			
			// Finds the table to reserve.
			for(int i = 0; i < table.length; ++i) {
	            	if(num == table[i].num) {
	            		table[i].num = "RR";
	            		table[i].customer = customer;
	            		table[i].date = date;
	            		table[i].time = time;
	            }
	        }
			whitelist.put(Integer.toString(tableNum),1);
		}
		else {
			System.out.println("Not a valid table number.");
			return;
		}
		
		// Update number of open tables.
		openTables = countTables();
	}
}
