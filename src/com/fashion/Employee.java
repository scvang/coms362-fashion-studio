package com.fashion;

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
	private String name;
	private String jobTitle;
	private String phoneNum;
	private double salary;
	
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
	}
	
	/**
	 * 
	 * @return name
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * 
	 * @return job title
	 */
	public String getJobTitle() {
		return this.jobTitle;
	}
	
	/**
	 * 
	 * @return salary
	 */
	public double getSalary() {
		return this.salary;
	}
	
	/**
	 * 
	 * @return phone number
	 */
	public String getPhoneNum() {
		return this.phoneNum;
	}
	
	/**
	 * Changes the job title of the employee.
	 * @param title
	 */
	public void setJobTitle(String title) {
		this.jobTitle = title;
	}
	
	/**
	 * Changes the salary for the employee.
	 * @param salary
	 */
	public void setSalary(double salary) {
		this.salary = salary;
	}
	
	/**
	 * Changes the phone number for the employee.
	 * @param phoneNum
	 */
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
}
