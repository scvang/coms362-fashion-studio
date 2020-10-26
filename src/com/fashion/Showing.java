package com.fashion;

import java.util.HashMap;
import java.util.Random;

/**
 * Showing is the information expert that knows about seats.
 * @author Sebastian Vang
 *
 */
public class Showing extends Event{
	
	/**
	 * Instance variables.
	 */
	private int openSeats;
	private Seat[][] seat = new Seat[9][9];
	private HashMap<String,Integer> whitelist = new HashMap<>();
	
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
				seat[row][col].customer = "customer";
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
				if(seat[i][j].customer.equals("")) ++count;
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
            	if(seat[row][col].customer.equals(""))
                System.out.print(seat[row][col].num+"|");
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
				if(name.equals(seat[row][col].customer.toLowerCase())) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	public Seat getShowingCustomer(String name) {
		name = name.toLowerCase();
		Seat s = new Seat();
		for(int row = 0; row < seat.length; ++row) {
			for(int col = 0; col < seat[0].length; ++col) {
				if(name.equals(seat[row][col].customer.toLowerCase())) {
					s = this.seat[row][col];
					return s;
				}
			}
		}
		return s;
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
		if(seatNum.length() !=2 || seatNum.charAt(0) >= 'A' && seatNum.charAt(0) <= 'I'
				&& Integer.parseInt(String.valueOf(seatNum.charAt(1))) >= 1
				&& Integer.parseInt(String.valueOf(seatNum.charAt(1))) <= 9
				){
			
			// Checks if the seat is already reserved.
			if(whitelist.containsKey(seatNum)) {
				System.out.println(seatNum);
				System.out.println("Seat " + seatNum + " is already reserved.");
				return false;
			}
			
			// Finds the seat to reserve.
			for(int row = 0; row < seat.length; ++row) {
	            for(int col = 0; col < seat[0].length; ++col) {
	            	if(seatNum.equals(seat[row][col].num)) {
	            		seat[row][col].customer = customer;
	            		seat[row][col].date = date;
	            		seat[row][col].time = time;
	            	}
	            }
	        }
			
			// Places the reserved seat into memory.
			whitelist.put(seatNum,1);
		}
		else {
			System.out.println("Not a valid seat number.");
			return false;
		}
		
		// Update number of open seats.
		openSeats = countSeats();
		
		return true;
	}
}
