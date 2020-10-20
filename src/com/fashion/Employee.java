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
	private PayStubInfo payStubInfo;

	public Employee(int eid, String name, String jobTitle, String phoneNum, PayStubInfo payStubInfo) {
		this.eid = eid;
		this.name = name;
		this.jobTitle = jobTitle;
		this.phoneNum = phoneNum;
		this.payStubInfo = payStubInfo;
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

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public PayStubInfo getPayStubInfo() {
		return payStubInfo;
	}

	public void setPayStubInfo(PayStubInfo payStub) {
		this.payStubInfo = payStubInfo;
	}
}
