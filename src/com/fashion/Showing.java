package com.fashion;

import java.util.HashMap;
import java.util.Random;

/**
 * The studio hosts showing event.
 * @author Sebastian Vang
 *
 */
public class Showing extends Event{
	
	/**
	 * Instance variables.
	 */
	private int openSeats;
	private Seat[][] seat = new Seat[9][9];
	private HashMap<String,Integer> oracle = new HashMap<>();
	
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
	 * Probably better for testing purposes.
	 */
	
	/*
	public void fillSeats() {
		String[] isle = {"A","B","C","D","E","F","G","H","I"};
		Random ran = new Random();
		
		for(int row = 0; row < seat.length; ++row) {
			for(int col = 0; col < seat[0].length; ++col) {
				
				int num = ran.nextInt(9)+1;
				String letter = isle[row];
				String s = letter + num;
				
				if(!oracle.containsKey(s)) {
					seat[row][col].num = "RR";
					seat[row][col].customer = "customer" + new Random().nextInt(99)+1;
					oracle.put(s,1);
				}
			}
		}
	}*/
	
	/**
	 * Gets number of available seats.
	 * @return number of open seats
	 */
	public int countSeats() {
		int count = 0;
		for(int i = 0; i < seat.length; ++i){
			for(int j = 0; j < seat[0].length; ++j) {
				if(!seat[i][j].num.equals("RR")) ++count;
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
                System.out.print(seat[row][col].num+"|");
            }
            System.out.println();
            System.out.println("+--+--+--+--+--+--+--+--+--+");
        }
	}

	public void reserveSeat(String seatNum, String customer, String date) {
		// Convert to upper case before processing.
		seatNum = seatNum.toUpperCase();
		
		// Checks if the seat is in range A1 to I9.
		if(seatNum.length() !=2 || seatNum.charAt(0) >= 'A' && seatNum.charAt(0) <= 'I'
				&& Integer.parseInt(String.valueOf(seatNum.charAt(1))) >= 1
				&& Integer.parseInt(String.valueOf(seatNum.charAt(1))) <= 9
				){
			
			// Checks if the seat is already reserved.
			if(oracle.containsKey(seatNum)) {
				System.out.println(seatNum);
				System.out.println("That seat is already reserved.");
				return;
			}
			
			// Finds the seat to reserve.
			for(int row = 0; row < seat.length; ++row) {
	            for(int col = 0; col < seat[0].length; ++col) {
	            	if(seatNum.equals(seat[row][col].num)) {
	            		seat[row][col].num = "RR";
	            		seat[row][col].customer = customer;
	            	}
	            }
	        }
			
			// Places the reserved seat into memory.
			oracle.put(seatNum,1);
		}
		else {
			System.out.println("Not a valid seat number.");
			return;
		}
		
		// Update number of open seats.
		openSeats = countSeats();
	}
}
