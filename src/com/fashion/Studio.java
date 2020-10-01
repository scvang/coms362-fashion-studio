package com.fashion;

import java.util.ArrayList;

public class Studio {
	String name;
	String address;
	int phoneNum;
	
	ArrayList<Employee> employees;
	ArrayList<Apparel> apparel;
	
	public Studio(String name, String address, int phoneNum) {
		this.name = name;
		this.address = address;
		this.phoneNum = phoneNum;
		
	}
	
	public void addEmployee(String name, String jobTitle, int salary, int phoneNum) {
		
	}
}
