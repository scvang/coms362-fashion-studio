package com.fashion;

/**
 * 
 * @author Sebastian Vang
 *
 */
public class Employee {
	String name;
	String jobTitle;
	int salary;
	int phoneNum;
	
	/**
	 * Constructs the employee.
	 * 
	 * @param name
	 * @param jobTitle
	 * @param salary
	 * @param phoneNum
	 */
	public Employee(String name, String jobTitle, int salary, int phoneNum) {
		this.name = name;
		this.jobTitle = jobTitle;
		this.salary = salary;
		this.phoneNum = phoneNum;
	}
	
	/**
	 * Changes the job title of the employee.
	 * @param title
	 */
	public void changeJobTitle(String title) {
		this.jobTitle = title;
	}
	
	/**
	 * Changes the salary for the employee.
	 * @param salary
	 */
	public void changeSalary(int salary) {
		this.salary = salary;
	}
	
	/**
	 * Changes the phone number for the employee.
	 * @param phoneNum
	 */
	public void changePhoneNum(int phoneNum) {
		this.phoneNum = phoneNum;
	}
}
