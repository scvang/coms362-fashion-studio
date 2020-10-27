package com.fashion.events;

import java.util.HashMap;

/**
 * Dining is the information expert that knows about tables.
 * @author Sebastian Vang
 *
 */
public class Dining extends Event{
	
	/**
	 * Instance variables.
	 */
	
	private int openTables;
	private Table[] table = new Table[20];
	private HashMap<String,String> whitelist = new HashMap<>();
	
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
			table[i] = new Table(String.format("%02d",tableNum),"","","");
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
			if(table[i].getCustomerName().equals("")) ++count;
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
        	if(table[i].getCustomerName().equals("")) {
        	System.out.print(table[i].getTableNum() + " ");
        	}
        	else {
        		System.out.print("RR" + " ");
        	}
        }
        System.out.println("");
	}
	
	public boolean hasTableReservation(String name) {
		name = name.toLowerCase();
		if(whitelist.containsValue(name)) return true;
		return false;
	}
	
	public Table getTable(String name) {
		name = name.toLowerCase();
		Table t = new Table();
		
		for(int i = 0; i < table.length; ++i) {
			if(table[i].getCustomerName().toLowerCase().equals(name)){
				t = this.table[i];
			}
		}
		
		return t;
	}

	/**
	 * Reserves a table for the dining event.
	 * @param num
	 * @param customer
	 * @param date
	 * @param time
	 */
	public boolean reserveTable(String num, String customer, String date, String time) {
		// Convert to upper case before processing.
		int tableNum = Integer.parseInt(num);
		
		// Checks if the seat is in range 1 to 20.
		if(tableNum >= 1 || tableNum <= 20){
			
			if(whitelist.containsKey(num)) {
				System.out.println("Table " + num + " is already reserved.");
				return false;
			}
			
			// Finds the table to reserve.
			for(int i = 0; i < table.length; ++i) {
	            	if(num.equals(table[i].getTableNum())) {
	            		table[i].setCustomerName(customer);
	            		table[i].setDate(date);
	            		table[i].setTime(time);
	            }
	        }
			whitelist.put(Integer.toString(tableNum),customer.toLowerCase());
		}
		else {
			System.out.println("Not a valid table number.");
			return false;
		}
		
		// Update number of open tables.
		openTables = countTables();
		
		return true;
	}

	public void fillTables() {
		for(int i = 0; i < table.length; ++i) {
			table[i].setCustomerName("customer");
		}
		// Update the available seats.
		openTables = countTables();
		
	}
}
