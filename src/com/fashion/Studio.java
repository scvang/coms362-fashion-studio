package com.fashion;

import com.fashion.employees.Employee;
import com.fashion.employees.Model;
import com.fashion.pay.PayStub;
import com.fashion.pay.PayStubInfo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Sebastian Vang
 * @author Emily Young: getAd(), addAd()
 * 
 * Studio is the information expert that knows about the employees, models, apparel, event, ad, payStubHistory...
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
	private ArrayList<Model> model;
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
		
		model = new ArrayList<>(); 
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
	public Event getEventName(String name) {
		
		for(Event e : event) {
			if(e.getEventName().equals(name)) {
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
		apparel.add(new Apparel(name,brand,color));
	}
	
	/**
	 * Lists the apparel.
	 */
	public void getApparel() {
		for(Apparel a : apparel) {
			System.out.println(
			"Item name: " + a.getItemName() + "\n" +
			"Brand name: " + a.getBrandName() + "\n" +
			"Color: " + a.getColor() + "\n"
			);
		}
	}
	
	/**
	 * Create one of three events.
	 * @param type
	 * @param eventName
	 * @param date
	 * @param time
	 */
	public void createEvent(String type, String eventName,String date,String time) {
		type = type.toLowerCase();
		switch(type) {
		case "showing":
			createShowingEvent(eventName,date,time);
			System.out.println("Showing event successfully created.");
		break;
		
		case "party":
			createPartyEvent(eventName,date,time);
			System.out.println("Dining event successfully created.");
		break;
		
		case "dining":
			createDiningEvent(eventName,date,time);
			System.out.println("Dining event successfully created.");
		break;
		
		default:
			System.out.println("Event was not created. " + type + " is not a valid event type. \n");
		}
	}
	
	/**
	 * Creates a showing event.
	 * @param name
	 * @param date
	 * @param time
	 */
	public void createShowingEvent(String name, String date, String time) {
		Event e = new Showing(name,date,time);
		this.event.add(e);
	}
	
	/**
	 * Creates a party event.
	 * @param name
	 * @param date
	 * @param time
	 */
	public void createPartyEvent(String name, String date, String time) {
		Event e = new Party(name,date,time);
		this.event.add(e);
	}
	
	/**
	 * Creates a dining event.
	 * @param name
	 * @param date
	 * @param time
	 */
	public void createDiningEvent(String name, String date, String time) {
		Event e = new Dining(name,date,time);
		this.event.add(e);
	}
	
	/**
	 * Displays a list of hosted events.
	 */
	public void displayEvents() {
		for(Event e : event) {
			System.out.println(e.getEventName());
		}
	}
	
	/**
	 * Displays the available seats.
	 * @param e event
	 */
	public boolean displaySeats(Event e) {
		Showing s = (Showing)e;
		s.displaySeats();
		System.out.println("Available seats: " + s.getOpenSeats() + "\n");
		
		return true;
	}
	
	public boolean isShowingFull(Event e) {
		Showing s = (Showing)e;
		
		if(s.getOpenSeats() == 0) return true;
		
		return false;
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
		if(((Showing) e).reserveSeat(n,c,d,t)) {
			System.out.println("Success.");
			chargeCard(e,c);
		}
	}
	
	public boolean hasSeatReservation(String customerName,Event e) {
		Showing s = (Showing)e;
		return s.hasSeatReservation(customerName);
	}
	
	/**
	 * Displays the tables.
	 * @param e
	 */
	public boolean displayTables(Event e) {
		Dining d = (Dining)e;
		d.displayTables();
		System.out.println("Available tables: " + d.getOpenTables() + "\n");
		
		return true;
	}
	public boolean isDiningFull(Event e) {
		Dining d = (Dining)e;
		if(d.getOpenTables() == 0) {
			return true;
		}
		return false;
	}
	/**
	 * Reserves a table for the customer.
	 * @param e event
	 * @param n tableNum
	 * @param c customerName
	 * @param d date
	 * @param t time
	 */
	public void reserveTable(Event e, String n, String c, String d, String t) {
		if(((Dining)e).reserveTable(n,c,d,t));
		{
			System.out.println("Success.");
			chargeCard(e,c);
		}
	}
	
	/**
	 * Reserves a badge.
	 * @param e event
	 * @param name
	 * @param date
	 * @param time
	 */
	public void reserveBadge(Event e, String name, String date, String time) {
		if(((Party)e).reserveBadge(name, date, time)) System.out.println("Success.");
	}
	
	/**
	 * Checks number of attendees.
	 * @param e
	 * @return
	 */
	public boolean isPartyFull(Event e) {
		Party p = (Party)e;
		//System.out.println("There are: " + ((Party)e).getAttendees() + " attendees.");
		if(p.getAttendees() == p.getCapacity()) return false;
		return true;
	}
	
	/**
	 * Charges the card according to the event.
	 * @param e event
	 * @param c customer
	 */
	public boolean chargeCard(Event e, String c) {
		
		Scanner in = new Scanner(System.in);
		System.out.println("Enter last 4 digits of card information: ");
		
		// Wait for the next integer input.
		while(in.hasNext() && !in.hasNextInt()) {
			in.next();
		}
		int cardNum = in.nextInt();
		
		if(Integer.toString(cardNum).length() != 4) {
			System.out.println("Not a valid card number. Try again.");
			return false;
		}
		
		if(e.payReservation(c, cardNum)) System.out.println("Payment successful.");
		
		return true;
	}
	
	public Seat getShowingCustomer(String name,Event e) {
		Showing s = (Showing)e;
		return s.getShowingCustomer(name);
	}
	
	// Populates the seat for a test.
	public void fillSeats(Event e) {
		Showing s = (Showing)e;
		s.fillSeats();
	}
	public void fillTables(Event e) {
		Dining d = (Dining)e;
		d.fillTables();
	}
	
	/**
	 * @author Emily Young
	 * @param eid-event id
	 * @param eventName-event name
	 * @param loc-location of event
	 * @param time-time of event
	 * @param contactInfo-ticket office contact information
	 * 
	 * This method adds a new ad to the list of ads in circulation
	 */
	public void addAd(int eid, String eventName, String loc, String time, String contactInfo) {
		ad.add(new Advertisement(eid, eventName, loc, time, contactInfo));
	}

	/**
	 * @author Emily Young
	 * This method retrieves an ad from a list of ads that are currently in circulation
	 */
	public ArrayList<Advertisement> getAd() {
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
		return ad;
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
	
	/**
	 * @author Emily Young
	 * @param name-model name
	 * @param phoneNum-model phone number
	 * @param audNum-model audition number
	 * 
	 * This method adds a new model to a list of existing employees
	 */
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
	
	/**
	 * Finds the model.
	 * @param name
	 * @return
	 */
	public boolean findModel(String name) {
		for(Model m: model) {
			if(m.getName().equals(name)) return true;
		}
		
		return false;
	}
	
	/**
	 * @author Emily Young
	 * This method retrieves the existing list of models 
	 */
	public void getModels() {
		for(Model m : model) {
			System.out.println(
			"Model Name: " + m.getName() + "\n" + 
			"Agent: " + m.getAgent() + "\n" +
			"Phone: " + m.getPhoneNum() + "\n" +
			"Salary: " + m.getSalary() + "\n" +
			"Head: " + m.getHeadPiece().getItemName() + " Brand: " + m.getHeadPiece().getBrandName() + " Color: " + m.getHeadPiece().getColor()
			);
		}
	}
	
	/**
	 * @author Emily Young
	 * @param agent-agent name
	 * @param name-model name
	 * @param phoneNum-model phone number
	 * @param salary-model salary
	 * 
	 * This method adds a new model to a list of existing models
	 */
	public void createModel(String agent, String name, String phoneNum, double salary) {
		model.add(new Model(agent,name,phoneNum,salary));
	}
	
	public void changeHead(String modelName, Apparel item) {
		for(Model m : model) {
			if(m.getName().equals(modelName)) m.changeHead(item);
		}
	}
	
	public void changeTop(String modelName, Apparel item) {
		for(Model m : model) {
			if(m.getName().equals(modelName)) m.changeTop(item);
		}
	}
	
	public void changeBot(String modelName, Apparel item) {
		for(Model m : model) {
			if(m.getName().equals(modelName)) m.changeBot(item);
		}
	}
	
	public void changeLegs(String modelName, Apparel item) {
		for(Model m : model) {
			if(m.getName().equals(modelName)) m.changeLegs(item);
		}
	}
	
	public void changeShoes(String modelName, Apparel item) {
		for(Model m : model) {
			if(m.getName().equals(modelName)) m.changeShoes(item);
		}
	}
	
	public void changeAcc(String modelName, Apparel item) {
		for(Model m : model) {
			if(m.getName().equals(modelName)) m.changeAcc(item);
		}
	}
}

