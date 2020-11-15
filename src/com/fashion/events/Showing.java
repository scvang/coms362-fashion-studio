package com.fashion.events;

import java.util.HashMap;
import java.util.Random;

/**
 * @author Sebastian Vang
 * 
 * Showing is the information expert that knows about seats.
 *
 */
public class Showing extends Event{
	
	/**
	 * Instance variables.
	 */
	private int openSeats;
	private Seat[][] seat = new Seat[9][9];
	private HashMap<String,String> whitelist = new HashMap<>();
	
	/**
	 * Constructor for the showing event.
	 * @param name
	 * @param date
	 * @param time
	 */
	public Showing(String name, String date, String time) {
		super(name, date, time);
		
		// Initializes empty seats.
		String[] isle = {"A","B","C","D","E","F","G","H","I"};
		int num = 1;
		
		for(int row = 0; row < seat.length; ++row) {
			for(int col = 0; col < seat[0].length; ++col) {
				String seatNum = isle[row] + num;
				seat[row][col] = new Seat(seatNum,"",date,time);
				
				++num;
				if(col == 8) num = 1;
			}
		}
		
		//fillSeats();
		openSeats = countSeats();
	}
	
	/**
	 * Randomly fills available seats.
	 * For testing purposes.
	 */
	
	public void fillSeats() {
		for(int row = 0; row < seat.length; ++row) {
			for(int col = 0; col < seat[0].length; ++col) {
				seat[row][col].setCustomerName("customer");
			}
		}
		// Update the available seats.
		openSeats = countSeats();
	}
	
	/**
	 * Gets number of available seats.
	 * @return number of open seats
	 */
	public int countSeats() {
		int count = 0;
		for(int i = 0; i < seat.length; ++i){
			for(int j = 0; j < seat[0].length; ++j) {
				if(seat[i][j].getCustomerName().equals("")) ++count;
			}
		}
		return count;
	}
	
	/**
	 * 
	 * @return open seats
	 */
	public int getOpenSeats() {
		return openSeats;
	}
	
	/**
	 * Displays the available seats.
	 */
	public void displaySeats() {
		System.out.println("+--+--+--+--+--+--+--+--+--+");
        for(int row = 0; row < seat.length; ++row) {
            System.out.print("|");
            for(int col = 0; col < seat[0].length; ++col) {
            	if(seat[row][col].getCustomerName().equals(""))
                System.out.print(seat[row][col].getSeatNum()+"|");
            	else {
            		System.out.print("RR"+"|");
            	}
            }
            System.out.println();
            System.out.println("+--+--+--+--+--+--+--+--+--+");
        }
	}
	
	public boolean hasSeatReservation(String name) {
		name = name.toLowerCase();
		for(int row = 0; row < seat.length; ++row) {
			for(int col = 0; col < seat[0].length; ++col) {
				if(name.equals(seat[row][col].getCustomerName().toLowerCase())) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	public Seat getSeat(String name) {
		name = name.toLowerCase();
		Seat s = new Seat();
		for(int row = 0; row < seat.length; ++row) {
			for(int col = 0; col < seat[0].length; ++col) {
				if(name.equals(seat[row][col].getCustomerName().toLowerCase())) {
					s = this.seat[row][col];
				}
			}
		}
		return s;
	}
	
	public boolean removeSeatReservation(String name) {
		String[] isle = {"A","B","C","D","E","F","G","H","I"};
		int num = 1;
		
		for(int row = 0; row < seat.length; ++row) {
			for(int col = 0; col < seat[0].length; ++col) {
				String seatNum = isle[row] + num;
				if(name.equals(seat[row][col].getCustomerName())) {
				seat[row][col] = new Seat(seatNum,"","","");
				openSeats = countSeats();
				return true;
				}
				++num;
			}
		}
		openSeats = countSeats();
		return false;
	}

	/**
	 * Reserves a seat for the showing event.
	 * @param seatNum
	 * @param customer
	 * @param date
	 */
	public boolean reserveSeat(String seatNum, String customer, String date, String time) {
		// Convert to upper case before processing.
		seatNum = seatNum.toUpperCase();
		
		// Checks if the seat is in range A1 to I9.
		if(seatNum.length() == 2 && seatNum.charAt(0) >= 'A' && seatNum.charAt(0) <= 'I'
				&& Integer.parseInt(String.valueOf(seatNum.charAt(1))) >= 1
				&& Integer.parseInt(String.valueOf(seatNum.charAt(1))) <= 9
				){
			
			// Checks if the seat is already reserved.
			if(whitelist.containsKey(seatNum)) {
				//System.out.println(seatNum);
				return false;
			}
			
			// Finds the seat to reserve.
			for(int row = 0; row < seat.length; ++row) {
	            for(int col = 0; col < seat[0].length; ++col) {
	            	if(seatNum.equals(seat[row][col].getSeatNum())) {
	            		seat[row][col].setCustomerName(customer);
	            		seat[row][col].setDate(date);
	            		seat[row][col].setTime(time);
	            	}
	            }
	        }
			
			// Places the reserved seat into memory.
			whitelist.put(seatNum,customer.toLowerCase());
		}
		else {
			//System.out.println("Not a valid seat number.");
			return false;
		}
		
		// Update number of open seats.
		openSeats = countSeats();
		
		return true;
	}
}
