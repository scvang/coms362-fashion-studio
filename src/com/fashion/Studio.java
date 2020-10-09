package com.fashion;

import java.util.ArrayList;

/**
 * @author Sebastian Vang
 * 
 * Studio is the information expert that knows about the employees and apparel.
 *
 */
public class Studio {
	private String name;
	private String address;
	private String phoneNum;
	
	ArrayList<Employee> employees;
	ArrayList<Apparel> apparel;
	
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
	}
	
	public void addEmployee(String name, String jobTitle, int salary, String phoneNum) {
		Employee e = new Employee(name, jobTitle, salary, phoneNum);
		employees.add(e);
	}
	
	public void getEmployees() {
		for(Employee e : employees) {
			System.out.println(
			"Name: " + e.getName() + "\n" + 
			"Job title: " + e.getJobTitle() + "\n" +
			String.format("Salary: $" + "%,d", e.getSalary()) + "\n" +
			"Phone: " + e.getPhoneNum()
			);
		}
	}
	
	public void getApparelName() {
		for(Apparel n : apparel) n.getItemName();
	}
	
	public void getApparelBrandName() {
		for(Apparel b : apparel) b.getBrandName();
	}
		
	public void getApparelID() {
		for(Apparel i : apparel) i.getItemID();
	}
}
