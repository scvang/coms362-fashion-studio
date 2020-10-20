package com.fashion;

import java.util.List;

/**
 * @author Sebastian Vang
 * 
 * Employee works in the studio.
 * 
 */
public class Employee {
	
	/**
	 * Instance variables.
	 */
	private int eid;
	private String name;
	private String jobTitle;
	private String phoneNum;
	private double salary;
<<<<<<< HEAD
	
	/**
	 * 
	 * Constructs the employee.
	 * 
	 * @param name
	 * @param jobTitle
	 * @param salary
	 * @param phoneNum
	 */
	public Employee(String name, String jobTitle, String phoneNum, double salary) {
		this.name = name;
		this.jobTitle = jobTitle;
		this.phoneNum = phoneNum;
		this.salary = salary;
=======
	private int bankAccountNum;
	private int bankRoutingNum;
	private PayStubInfo payStubInfo;

	public Employee(int eid, String name, String jobTitle, String phoneNum, PayStubInfo payStubInfo) {
		this.eid = eid;
		this.name = name;
		this.jobTitle = jobTitle;
		this.phoneNum = phoneNum;
		this.payStubInfo = payStubInfo;
>>>>>>> 94239a5e9d501949d9da3e0287d4d2ede99306ff
	}

	public int getEid() {
		return eid;
	}

	public void setEid(int eid) {
		this.eid = eid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getJobTitle() {
		return jobTitle;
	}
<<<<<<< HEAD
	
	/**
	 * 
	 * @return salary
	 */
	public double getSalary() {
		return this.salary;
=======

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
>>>>>>> 94239a5e9d501949d9da3e0287d4d2ede99306ff
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
<<<<<<< HEAD
	
	/**
	 * Changes the salary for the employee.
	 * @param salary
	 */
=======

	public double getSalary() {
		return salary;
	}

>>>>>>> 94239a5e9d501949d9da3e0287d4d2ede99306ff
	public void setSalary(double salary) {
		this.salary = salary;
	}

	public int getBankAccountNum() {
		return bankAccountNum;
	}

	public void setBankAccountNum(int bankAccountNum) {
		this.bankAccountNum = bankAccountNum;
	}

	public int getBankRoutingNum() {
		return bankRoutingNum;
	}

	public void setBankRoutingNum(int bankRoutingNum) {
		this.bankRoutingNum = bankRoutingNum;
	}

	public PayStubInfo getPayStubInfo() {
		return payStubInfo;
	}

	public void setPayStubInfo(PayStubInfo payStub) {
		this.payStubInfo = payStubInfo;
	}
}
