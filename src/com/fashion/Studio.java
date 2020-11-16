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
	private double balance;

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
	public Studio(String name, String address, String phoneNum,double balance) {
		this.name = name;
		this.address = address;
		this.phoneNum = phoneNum;
		this.balance = balance;

		employees = new ArrayList<>();
		
		model = new ArrayList<>(); 
		apparel = new ArrayList<>();
		event = new ArrayList<>();
		ad = new ArrayList<>();

		payStubHistory = new ArrayList<>();
		inventory = new Inventory();
	}
	public Studio () {
		employees = new ArrayList<>();
		
		model = new ArrayList<>(); 
		apparel = new ArrayList<>();
		event = new ArrayList<>();
		ad = new ArrayList<>();

		payStubHistory = new ArrayList<>();
		inventory = new Inventory();
	}
	
	public Inventory getInventory(){
		return this.inventory;
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

