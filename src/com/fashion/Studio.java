package com.fashion;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
	
	ArrayList<Employee> employees;
	ArrayList<Apparel> apparel;
	ArrayList<Advertisement> ad;
	Promotion[] promotions;
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
		ad = new ArrayList<>();
		promotions = new Promotion[10];
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
			"Employee Name: " + e.getName() + "\n" + 
			"Job title: " + e.getJobTitle() + "\n" +
			String.format("Salary: $" + "%,d", e.getPayStubInfo().getSalary()) + "\n" +
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
	 Adds a promotion to this studio for an event
	 */
	public boolean addPromotion(String businessName, String text, int loc, double dollarAmount){
		Promotion p = new Promotion(businessName, text, dollarAmount);

		/*
		if the number of promotions at the studio is less than 10 than we have room for the new promotion
		 */
		if(promotions.length < 10) {
			/*
			if the promotion location isn't filled by another company or the same company then we can add
			the new promotion to this location.
			 */
			if(isPromotionSpotOpen(loc)){
				p.setLocation(loc);
			} else { //otherwise, if the promotion location is filled, then the business must look for
				//another location to place the promotion
				System.out.println("Promotions location is filled! Please look for another spot!");
				return false;
			}
		} else { //if we are at 10 promotions then the studio event is filled
			System.out.println("Promotion spots are filled! Good luck next event!");
			return false;
		}

		//increment the pid +1 more the highest value
		if(promotions.length != 0){
			int nextPid = findNextPid(promotions);
			p.setPid(nextPid);
		} else {
			p.setPid(1);
		}

		promotions[loc] = p;

		return promotions[loc] == p;
	}

	/**
	 * @author Chad Morrow
	 * @param promotions is the list of promotions at this studio event
	 * @return the next pid value for then new promotion
	 */
	private int findNextPid(Promotion[] promotions){
		int max = 0;
		for(Promotion promotion : promotions){
			if(max < promotion.getPid()){
				max = promotion.getPid();
			}
		}
		return max + 1;
	}

	/**
	 @author Chad Morrow
	  * @param loc is the location of the where were checking if a promotion is
	 * @return the Promotion information at the requested promotion location
	 Grabs promotion information from the studio for an event
	 */
	public Promotion getPromotion(int loc){
		return promotions[loc];
	}

	/**
	 * @author Chad Morrow
	 * @param loc is the location of the where were checking if a promotion is
	 * @return the boolean value if the promotion spot is taken for this studio
	 */
	private boolean isPromotionSpotOpen(int loc){
		return promotions[loc] == null;
	}

	/**
	 @author Chad Morrow
	 pay the employee
	 */
	public boolean payEmployee(int eid, PayStubInfo p){
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		System.out.println(dateFormat.format(cal));

		PayStub payStub = new PayStub(eid, dateFormat.format(cal), p);
		return payStubHistory.add(payStub);
	}
	
	public void addModel(String name, String phoneNum, int audNum) {
		ModelAudition model = new ModelAudition(name, phoneNum, audNum);
		employees.add(new Employee(1, model.getName(), "Model", model.getPhoneNum(), new PayStubInfo(50000, 0, 0, 0)));
	}
}

