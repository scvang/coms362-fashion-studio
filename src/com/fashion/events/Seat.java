package com.fashion.events;

public class Seat {
	
	private String num;
	private String customer;
	private String date;
	private String time;
	
	public Seat() {};
	public Seat(String num, String customer, String date, String time) {
		this.num = num;
		this.customer = customer;
		this.date = date;
		this.time = time;
	}
	
	public String getSeatNum() {
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
	
	public void setCustomerName(String name) {
		this.customer = name;
	}
	
	public void setSeatNum(String num) {
		this.num = num;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public void setTime(String time) {
		this.time = time;
	}
}
