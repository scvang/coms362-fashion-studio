package com.fashion;

public class Main {
	public static void main(String[] args) {
		
		String company = "Fashion Inc";
		String address = "401 Somewhere Ave";
		String phone = "555-555-5555";
		
		Studio studio = new Studio(company,address,phone);
		
		String name = "John";
		String jobTitle = "Designer";
		int salary = 50000;
		String phoneNum = "N/A";
		
		// Add an employee test.
		studio.addEmployee(name, jobTitle, salary, phoneNum);
		studio.getEmployees();
	}
}
