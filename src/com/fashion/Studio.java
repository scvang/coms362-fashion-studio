package com.fashion;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

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

	private ArrayList<Employee> employees;
	private ArrayList<Apparel> apparel;
	private ArrayList<Event> event;
	ArrayList<Advertisement> ad;
	ArrayList<PayStub> payStubHistory;
	
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
		event = new ArrayList<>();
		ad = new ArrayList<>();
		payStubHistory = new ArrayList<>();
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
	 * @param eid
	 * @param name
	 * @param title
	 * @param phone
	 */
	public void addEmployee(int eid, String name, String title, String phone, double salary, int bankAccount, int bankRouting) {
		employees.add(new Employee(eid, name,title,phone, new PayStubInfo(salary, 0, bankAccount, bankRouting)));
	}
	
	/**
	 * Lists the employees information.
	 */
	public void getEmployees() {
		for(Employee e : employees) {
			System.out.println(
			"EID: " + e.getEid() + "\n" +
			"Employee Name: " + e.getName() + "\n" +
			"Job title: " + e.getJobTitle() + "\n" +
			"Salary: $" + e.getPayStubInfo().getSalary() + "\n" +
			"Phone: " + e.getPhoneNum() + "\n"
			);
		}
	}

	/**
	 * @author Chad Morrow
	 * gets the employee's paystubinfo.
	 */
	public Employee getEmployee(int eid) {
		for(Employee e : employees) {
			if(e.getEid() == eid){
				return e;
			}
		}

		System.out.println("Employee doesn't exist");
		return null;
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
	 * @param n seatNum
	 * @param c customerName
	 * @param d date
	 * @param t time
	 */
	public void reserveSeat(Event e, String n, String c, String d, String t) {
		((Showing) e).reserveSeat(n,c,d,t);
		
	}
	
	public void displayTables(Event e) {
		((Dining)e).displayTables();
		System.out.println("Available tables: " + ((Dining) e).getOpenTables() + "\n");
	}
	public void reserveTable(Event e, String n, String c, String d, String t) {
		((Dining)e).reserveTable(n,c,d,t);
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

	/**
	 @author Chad Morrow
	 pay the employee
	 */
	public boolean payEmployee(int eid, PayStubInfo p){
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();

		PayStub payStub = new PayStub(eid, dtf.format(now), p);
		return payStubHistory.add(payStub);
	}
	
	public void addModel(String name, String phoneNum, int audNum) {
		ModelAudition model = new ModelAudition(name, phoneNum, audNum);
		employees.add(new Employee(nextEID(), model.getName(), "Model", model.getPhoneNum(), new PayStubInfo(50000, 0, 0, 0)));
	}

	/**
	 * @author Chad Morrow
	 * @return the next eid for indexing the employees
	 */
	private int nextEID(){
		int nextEID = 0;

		for(Employee employee : employees){
			if(nextEID < employee.getEid()){
				nextEID = employee.getEid();
			}
		}

		return nextEID + 1;
	}
}

