package com.fashion;

import java.util.ArrayList;

/**
 * @author Sebastian Vang
 * 
 * Studio is the information expert that knows about the employees and apparel.
 *
 */
public class Studio {
	
	/**
	 * Instance variables.
	 */
	private String name;
	private String address;
	private String phoneNum;
	
	private ArrayList<Employee> employees;
	private ArrayList<Apparel> apparel;
	
	private String eventName;
	private String date;
	private String time;
	
	private Event showing;
	private Event party;
	private Event dining;
	
	/**
	 * 
	 * Constructor for the studio.
	 * 
	 * @param name
	 * @param address
	 * @param phoneNum
	 */
	public Studio(String name, String address, String phoneNum) {
		this.name = name;
		this.address = address;
		this.phoneNum = phoneNum;
		
		employees = new ArrayList<>();
		apparel = new ArrayList<>();
		
		// Probably will change this later to be extensible and add more events.
		date = "10-15-20";
		time = "4:10PM";
		
		eventName = "FasionCon 2020";
		showing = new Showing(eventName,date,time);
		
		eventName = "Company Party 2020";
		//party = new Party(eventName,date,time);
		
		eventName = "Company Dining 2020";
		dining = new Dining(eventName,date,time);
	}
	
	/**
	 * 
	 * @return studio name
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * 
	 * @return studio address
	 */
	public String getAddress() {
		return this.address;
	}
	
	/**
	 * 
	 * @return studio phone number
	 */
	public String getPhoneNum() {
		return this.phoneNum;
	}
	
	/**
	 * 
	 * @return party event
	 */
	public Event getPartyEvent() {
		return this.party;
	}
	
	/**
	 * 
	 * @return showing event
	 */
	public Event getShowingEvent() {
		return this.showing;
	}
	
	/**
	 * 
	 * @return dining event
	 */
	public Event getDiningEvent() {
		return this.dining;
	}
	
	/**
	 * Adds an employee to the studio.
	 * @param name
	 * @param jobTitle
	 * @param salary
	 * @param phoneNum
	 */
	public void addEmployee(String name, String title, int salary, String phone) {
		employees.add(new Employee(name,title,salary,phone));
	}
	
	/**
	 * Lists the employees information.
	 */
	public void getEmployees() {
		for(Employee e : employees) {
			System.out.println(
			"Employee Name: " + e.getName() + "\n" + 
			"Job title: " + e.getJobTitle() + "\n" +
			String.format("Salary: $" + "%,d", e.getSalary()) + "\n" +
			"Phone: " + e.getPhoneNum()
			);
		}
	}
	
	public void addApparel(String name, String brand, String color, int id, int stock) {
		apparel.add(new Apparel(name,brand,color,id,stock));
	}
	
	/**
	 * Lists the apparel in stock.
	 */
	public void getApparel() {
		for(Apparel a : apparel) {
			System.out.println(
			"Item name: " + a.getItemName() + "\n" +
			"Brand name: " + a.getBrandName() + "\n" +
			"Color: " + a.getColor() + "\n" +
			"ID: " + a.getItemID() + "\n" +
			"In-stock: " + a.getStock()
			);
		}
	}
	
	/**
	 * Displays the available seats.
	 */
	public void displaySeats(Event e) {
		((Showing) e).displaySeats();
		System.out.println("Available seats: " + ((Showing) e).getOpenSeats() + "\n");
	}

	/**
	 * Reserves a seat for the customer.
	 * @param e event
	 * @param s seatNum
	 * @param c customerName
	 * @param d date
	 */
	public void reserveSeat(Event e, String s, String c, String d) {
		((Showing) e).reserveSeat(s,c,d);
		
	}
}
