package com.fashion.employees;

import com.fashion.MySQLController;
import com.fashion.pay.PayStubInfo;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Sebastian Vang
 * @author Chad Morrow
 * Employee works in the studio.
 */
public class Employee {
	
	/**
	 * Instance variables.
	 */
	private int eid;
	private String name;
	private String jobTitle;
	private String phoneNum;
	private PayStubInfo payStubInfo;

	public Employee(int eid, String name, String jobTitle, String phoneNum, PayStubInfo payStubInfo) {
		this.eid = eid;
		this.name = name;
		this.jobTitle = jobTitle;
		this.phoneNum = phoneNum;
		this.payStubInfo = payStubInfo;
	}

	public Employee(){}

	/**
	 * @return a random sales employee
	 */
	public Employee getRandomSalesEmployee(){
		MySQLController mySQLController = new MySQLController();
		Employee employee = new Employee();

		try {
			ResultSet rs = mySQLController.runPullCommand("SELECT * FROM `employees` WHERE `jobTitle` = 'Sales General Worker'");

			if(rs != null){
				employee.setEid(rs.getInt("eid"));
				employee.setName(rs.getString("firstName") + " " + rs.getString("lastName"));
				employee.setJobTitle(rs.getString("jobTitle"));
				employee.setPayStubInfo(null);
				return employee;
			} else {
				System.out.println("There are no sales employees to negotiate this contract");
				return null;
			}

		} catch (SQLException throwables) {
			System.out.println("Error creating a shopping session");
		}

		return employee;
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

	public PayStubInfo getPayStubInfo() {
		return payStubInfo;
	}

	public void setPayStubInfo(PayStubInfo payStub) {
		this.payStubInfo = payStub;
	}
}
