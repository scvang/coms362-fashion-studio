package com.fashion;

/**
 * @author Sebastian Vang
 * 
 * Employee works in the studio.
 * 
 */
public class Employee {
	private String name;
	private String jobTitle;
	private int salary;
	private String phoneNum;
	
	/**
	 * 
	 * Constructs the employee.
	 * 
	 * @param name
	 * @param jobTitle
	 * @param salary
	 * @param phoneNum
	 */
	public Employee(String name, String jobTitle, int salary, String phoneNum) {
		this.name = name;
		this.jobTitle = jobTitle;
		this.salary = salary;
		this.phoneNum = phoneNum;
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
	public int getSalary() {
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
	public void setSalary(int salary) {
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
