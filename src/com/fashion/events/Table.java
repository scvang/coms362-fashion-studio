package com.fashion.events;

/**
 * The dining room has tables.
 * 
 * @author Sebastian Vang
 *
 */
public class Table {
	
	/**
	 * Instance variables.
	 */
	private String num;
	private String customer;
	private String date;
	private String time;
	
	// Empty constructor.
	public Table() {};
	
	/**
	 * Constructor for the table.
	 * @param num
	 * @param customer
	 * @param date
	 * @param time
	 */
	public Table(String tableNum, String customer, String date, String time) {
		this.num = tableNum;
		this.customer = customer;
		this.date = date;
		this.time = time;
	}
	public String getTableNum() {
		return this.num;
	}
	public String getCustomerName() {
		return this.customer;
	}
	public String getDate() {
		return this.date;
	}
	public String getTime() {
		return this.time;
	}
	public void setTableNum(String tableNum) {
		this.num = tableNum;
	}
	public void setCustomerName(String name) {
		this.customer = name;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public void setTime(String time) {
		this.time = time;
	}
}
