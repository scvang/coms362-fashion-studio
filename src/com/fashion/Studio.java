package com.fashion;

import com.fashion.apparel.Apparel;
import com.fashion.employees.Employee;
import com.fashion.employees.Model;
import com.fashion.events.*;
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
 * @author Emily Young
 * @author Chad Morrow
 * 
 * Studio is the information expert that knows about the employees, models, inventory, apparel, event, ad, payStubHistory...
 *
 */
public class Studio {
	
	/**
	 * @author Sebastian Vang
	 * Instance variables.
	 */
	private String name;
	private String address;
	private String phoneNum;
	private double funds;

	private ArrayList<Employee> employees;
	private ArrayList<Model> model;
	private ArrayList<Apparel> apparel;
	private ArrayList<Event> event;
	ArrayList<Advertisement> ad;
	ArrayList<PayStub> payStubHistory;
	
	private Inventory inventory;
	
	/**
	 * @author Sebastian Vang
	 * Constructor for the studio.
	 * 
	 * @param name
	 * @param address
	 * @param phoneNum
	 */
	public Studio(String name, String address, String phoneNum,double funds) {
		this.name = name;
		this.address = address;
		this.phoneNum = phoneNum;
		this.funds = funds;

		employees = new ArrayList<>();
		
		model = new ArrayList<>(); 
		apparel = new ArrayList<>();
		event = new ArrayList<>();
		ad = new ArrayList<>();

		payStubHistory = new ArrayList<>();
		inventory = new Inventory();
	}
	
	public void resetInventory() {
		this.inventory = new Inventory();
	}
	
	public ArrayList<Apparel> getApparelList(){
		return this.apparel;
	}
	
	/**
	 * @author Sebastian Vang
	 */
	public void displayClothingInventory() {
		inventory.displayClothingInventory();
	}
	
	/**
	 * @author Sebastian Vang
	 * @param apparel
	 */
	public void storeClothingItem(Apparel a) {
		inventory.storeClothing(a);
	}
	
	/**
	 * @author Sebastian Vang
	 * @return studio name
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * @author Sebastian Vang
	 * @return studio address
	 */
	public String getAddress() {
		return this.address;
	}
	
	/**
	 * @author Sebastian Vang
	 * @return studio phone number
	 */
	public String getPhoneNum() {
		return this.phoneNum;
	}
	
	/**
	 * @author Sebastian Vang
	 * @return event
	 */
	public ArrayList<Event> getEventList(){
		return this.event;
	}
	
	/**
	 * @author Sebastian Vang
	 * @return event
	 */
	public Event getEvent(String name) {
		
		for(Event e : event) {
			if(e.getEvent().equals(name)) {
				return e;
			}
		}
		return null;
	}
	
	/**
	 * @author Chad Morrow
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
	 * @author Sebastian Vang
	 * @author Chad Morrow
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
	
	/**
	 * @author Sebastian Vang
	 * @param name
	 * @param brand
	 * @param color
	 * @param id
	 * @param stock
	 */
	public void addApparel(int id,String name, String brand, String color, int stock) {
		apparel.add(new Apparel(id,name,brand,color));
	}
	
	/**
	 * @author Sebastian Vang
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
	 * @author Sebastian Vang
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
	 * @author Sebastian Vang
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
	 * @author Sebastian Vang
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
	 * @author Sebastian Vang
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
	 * @author Sebastian Vang
	 * Displays a list of hosted events.
	 */
	public void displayEvents() {
		int count = 1;
		for(Event e : event) {
			System.out.println(count++ + ") " + e.getEvent());
		}
	}
	
	/**
	 * @author Sebastian Vang
	 * Displays the available seats.
	 * @param e event
	 */
	public boolean displaySeats(Event e) {
		Showing s = (Showing)e;
		s.displaySeats();
		System.out.println("Available seats: " + s.getOpenSeats() + "\n");
		
		return true;
	}
	
	/**
	 * @author Sebastian Vang
	 * @param event
	 * @return boolean
	 */
	public boolean isShowingFull(Event e) {
		Showing s = (Showing)e;
		
		if(s.getOpenSeats() == 0) return true;
		
		return false;
	}

	/**
	 * @author Sebastian Vang
	 * Reserves a seat for the customer.
	 * @param e event
	 * @param n seatNum
	 * @param c customerName
	 * @param d date
	 * @param t time
	 */
	public boolean reserveSeat(Event e, String number, String customer, String date, String time) {
		Showing s = (Showing)e;
		return s.reserveSeat(number,customer,date,time);
	}
	
	/**
	 * @author Sebastian Vang
	 * @param name
	 * @param e
	 * @return
	 */
	public boolean removeSeatReservation(String name,Event e) {
		Showing s = (Showing)e;
		return s.removeSeatReservation(name);
	}
	
	/**
	 * @author Sebastian Vang
	 * @param customerName
	 * @param e
	 * @return
	 */
	public boolean hasSeatReservation(String customerName,Event e) {
		Showing s = (Showing)e;
		return s.hasSeatReservation(customerName);
	}
	
	/**
	 * @author Sebastian Vang
	 * Displays the tables.
	 * @param e
	 */
	public boolean displayTables(Event e) {
		Dining d = (Dining)e;
		d.displayTables();
		System.out.println("Available tables: " + d.getOpenTables() + "\n");
		
		return true;
	}
	
	/**
	 * @author Sebastian Vang
	 * @param e
	 * @return
	 */
	public boolean isDiningFull(Event e) {
		Dining d = (Dining)e;
		if(d.getOpenTables() == 0) {
			return true;
		}
		return false;
	}

	/**
	 * @author Sebastian Vang
	 * Reserves a table for the customer.
	 * @param e event
	 * @param n tableNum
	 * @param c customerName
	 * @param d date
	 * @param t time
	 */
	public boolean reserveTable(Event e, String number, String customer, String date, String time) {
		Dining d = (Dining)e;
		return d.reserveTable(number, customer, date, time);
	}
	
	/**
	 * @author Sebastian Vang
	 * @param customerName
	 * @param e
	 * @return
	 */
	public boolean hasTableReservation(String customerName, Event e) {
		Dining d = (Dining)e;
		return d.hasTableReservation(customerName);
	}
	
	/**
	 * @author Sebastian Vang
	 * @param name
	 * @param e
	 * @return
	 */
	public boolean removeTableReservation(String name, Event e) {
		Dining d = (Dining)e;
		return d.removeTableReservation(name);
	}
	
	/**
	 * @author Sebastian Vang
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
	 * @author Sebastian Vang
	 * @param name
	 * @param e
	 * @return
	 */
	public boolean hasBadgeReservation(String name, Event e) {
		Party p = (Party)e;
		return p.hasBadgeReservaton(name);
	}
	
	/**
	 * @author Sebastian Vang
	 * @param name
	 * @param event
	 * @return badge
	 */
	public Badge getBadge(String name, Event e) {
		Party p = (Party)e;
		return p.getBadge(name);
	}
	
	/**
	 * @author Sebastian Vang
	 * Checks if party is full.
	 * @param e
	 * @return
	 */
	public boolean isPartyFull(Event e) {
		Party p = (Party)e;
		if(p.getAttendees() == p.getCapacity()) return true;
		return false;
	}
	
	/**
	 * @author Sebastian Vang
	 * @param event
	 * @return number of attendees
	 */
	public int getNumOfAttendees(Event e) {
		Party p = (Party)e;
		return p.getAttendees();
	}
	
	/**
	 * @author Sebastian Vang
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
	
	/**
	 * @author Sebastian Vang
	 * @param customer
	 * @param e
	 * @return
	 */
	public Seat getSeat(String customer,Event e) {
		Showing s = (Showing)e;
		return s.getSeat(customer);
	}
	
	/**
	 * @author Sebastian Vang
	 * @param customer
	 * @param e
	 * @return
	 */
	public Table getTable(String customer, Event e) {
		Dining d = (Dining)e;
		return d.getTable(customer);
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
	 * @author Sebastian Vang
	 * Returns a single model.
	 * @param name
	 * @return
	 */
	public Model getModel(String name) {
		for(Model m : model) {
			if(name.equals(m.getName())) {
				return m;
			}
		}
		return null;
	}
	
	/**
	 * @author Sebastian Vang
	 * Finds the model.
	 * @param name
	 * @return
	 */
	public boolean doesModelExist(String name) {
		for(Model m: model) {
			if(m.getName().equals(name)) return true;
		}
		return false;
	}
	
	/**
	 * @author Sebastian Vang
	 * @author Emily Young
	 * This method retrieves the existing list of models.
	 */
	public void displayModels() {
		for(Model m : model) {
			System.out.println(
			"Model Name: " + m.getName() + "\n" + 
			"Agent: " + m.getAgent() + "\n" +
			"Phone: " + m.getPhoneNum() + "\n" +
			"Salary: " + m.getPayStubInfo().getSalary() + "\n" +
			"Head: " + m.getHeadPiece().getItemName() + ", Brand: " + m.getHeadPiece().getBrandName() + ", Color: " + m.getHeadPiece().getColor() + "\n" +
			"Top: " + m.getTopPiece().getItemName() + ", Brand: " + m.getTopPiece().getBrandName() + ", Color: " + m.getTopPiece().getColor() + "\n" +
			"Bottoms: " + m.getBotPiece().getItemName() + ", Brand: " + m.getBotPiece().getBrandName() + ", Color: " + m.getBotPiece().getColor() + "\n" +
			"Leggings: " + m.getLegsPiece().getItemName() + ", Brand: " + m.getLegsPiece().getBrandName() + ", Color: " + m.getLegsPiece().getColor() + "\n" +
			"Shoes: " + m.getShoes().getItemName() + ", Brand: " + m.getShoes().getBrandName() + ", Color: " + m.getShoes().getColor() + "\n" +
			"Accessory: " + m.getAcc().getItemName() + ", Brand: " + m.getAcc().getBrandName() + ", Color: " + m.getAcc().getColor() + "\n"
			
			);
		}
	}
	
	/**
	 * @author Emily Young
	 * @param agent-agent name
	 * @param name-model name
	 * @param phoneNum-model phone number
	 * @param payStubInfo-model pay info
	 * 
	 * This method adds a new model to a list of existing models
	 */
	public void createModel(int eid,String agent, String name, String jobTitle, String phoneNum, PayStubInfo payStubInfo) {
		model.add(new Model(eid,agent,name,jobTitle,phoneNum,payStubInfo));
	}
	
	/**
	 * @author Sebastian Vang
	 * @param modelName
	 * @param item
	 */
	public void changeHead(String modelName, Apparel item) {
		for(Model m : model) {
			if(m.getName().equals(modelName)) m.changeHead(item);
		}
	}
	/**
	 * @author Sebastian Vang
	 * @param modelName
	 * @param item
	 */
	public void changeTop(String modelName, Apparel item) {
		for(Model m : model) {
			if(m.getName().equals(modelName)) m.changeTop(item);
		}
	}
	/**
	 * @author Sebastian Vang
	 * @param modelName
	 * @param item
	 */
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
	/**
	 * @author Sebastian Vang
	 * @param modelName
	 * @param item
	 */
	public void changeShoes(String modelName, Apparel item) {
		for(Model m : model) {
			if(m.getName().equals(modelName)) m.changeShoes(item);
		}
	}
	/**
	 * @author Sebastian Vang
	 * @param modelName
	 * @param item
	 */
	public void changeAcc(String modelName, Apparel item) {
		for(Model m : model) {
			if(m.getName().equals(modelName)) m.changeAcc(item);
		}
	}
}

