package com.fashion;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Sebastian Vang-Studio Class, addEmployees(), getEmployees(), addApparel(), getApparel()
 * 		   Emily Young-getAd(), addAd()

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

	private ArrayList<Employee> employee;
	private ArrayList<Apparel> apparel;
	private ArrayList<Event> event;
	ArrayList<Advertisement> ad;
	
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
		
		employee = new ArrayList<>();
		apparel = new ArrayList<>();
		event = new ArrayList<>();
		ad = new ArrayList<>();

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
	public Event getEvent(String name) {
		
		for(Event e : event) {
			if(e.getName().equals(name)) {
				return e;
			}
		}
		System.out.println("Event was not found.");
		return null;
	}
	
	/**
	 * Adds an employee to the studio.
	 * @param name
	 * @param jobTitle
	 * @param salary
	 * @param phoneNum
	 */
	public void addEmployee(String name, String title, int salary, String phone) {
		employee.add(new Employee(name,title,salary,phone));
	}
	
	/**
	 * Lists the employees information.
	 */
	public void getEmployees() {
		for(Employee e : employee) {
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
	

	public void createShowingEvent(String name, String date, String time) {
		Event e = new Showing(name,date,time);
		this.event.add(e);
	}
	public void createPartyEvent(String name, String date, String time) {
		Event e = new Party(name,date,time);
		this.event.add(e);
	}
	public void createDiningEvent(String name, String date, String time) {
		Event e = new Dining(name,date,time);
		this.event.add(e);
	}
	
	public void displayEvents() {
		for(Event e : event) {
			System.out.println(e.getName());
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
	
	public void addAd(int eid, String eventName, String loc, String time, String contactInfo) {
		ad.add(new Advertisement(eid, eventName, loc, time, contactInfo));
	}

	public void getAd() {
		for (Advertisement a : ad) {
			System.out.println("Event ID: " + a.getEventID());
			BufferedReader input = null;
			try {
				input = new BufferedReader(new FileReader(a.getFileName()));
				String Line = null;
				while ((Line = input.readLine()) != null) {
					System.out.println(Line);
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public void addModel(String name, String phoneNum, int audNum) {
		ModelAudition model = new ModelAudition(name, phoneNum, audNum);
		employee.add(new Employee(model.getName(), "Model", 29000, model.getPhoneNum()));

	}
}

