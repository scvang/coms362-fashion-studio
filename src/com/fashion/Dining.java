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
	
	private int openSeats;
	private Table[][] table = new Table[4][4];
	private HashMap<String,Integer> oracle = new HashMap<>();
	
	/**
	 * Constructor for the dining event.
	 * @param name
	 * @param date
	 * @param time
	 */
	public Dining(String name, String date, String time) {
		super(name, date, time);
		
		// Initializes empty seats.
		String[] isle = {"A","B","C","D","E","F","G","H","I"};
		int num = 1;
		
		for(int row = 0; row < table.length; ++row) {
			for(int col = 0; col < table[0].length; ++col) {
				String tableNum = isle[row] + num;
				table[row][col] = new Table(tableNum,"",date,time);
				
				++num;
				if(col == 8) num = 1;
			}
		}
		
		//fillSeats();
		openSeats = countSeats();
	}
	
	/**
	 * Gets number of available seats.
	 * @return number of open seats
	 */
	public int countSeats() {
		int count = 0;
		for(int i = 0; i < table.length; ++i){
			for(int j = 0; j < table[0].length; ++j) {
				if(!table[i][j].num.equals("RR")) ++count;
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
        for(int row = 0; row < table.length; ++row) {
            System.out.print("|");
            for(int col = 0; col < table[0].length; ++col) {
                System.out.print(table[row][col].num+"|");
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
			
			if(oracle.containsKey(seatNum)) {
				System.out.println(seatNum);
				System.out.println("That seat is already reserved.");
				return;
			}
			
			for(int row = 0; row < table.length; ++row) {
	            for(int col = 0; col < table[0].length; ++col) {
	            	if(seatNum.equals(table[row][col].num)) {
	            		table[row][col].num = "RR";
	            		table[row][col].customer = customer;
	            	}
	            }
	        }
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
