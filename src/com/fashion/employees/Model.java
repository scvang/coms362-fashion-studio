package com.fashion.employees;

import com.fashion.apparel.Apparel;

/**
 * Model is the information expert that knows about apparel.
 * @author Sebastian Vang
 *
 */
public class Model{
	
	/**
	 * Instance variables.
	 */
	private String agent;
	private String name;
	private String phoneNum;
	private double salary;
	
	/**
	 * Instance variables of what the model wears.
	 */
	private Apparel head;
	private Apparel top;
	private Apparel bot;
	private Apparel legs;
	private Apparel shoes;
	private Apparel acc;
	
	
	/**
	 * Constructor for the model.
	 * @param agent
	 * @param name
	 * @param phone
	 * @param salary
	 */
	public Model(String agent, String name, String phone, double salary) {
		this.agent = agent;
		this.name = name;
		this.phoneNum = phone;
		this.salary = salary;
		
		this.head = new Apparel("none","none","none");
		this.top = new Apparel("none","none","none");
		this.bot = new Apparel("none","none","none");
		this.legs = new Apparel("none","none","none");
		this.shoes = new Apparel("none","none","none");
		this.acc = new Apparel("none","none","none");
	}
	
	public Apparel getHeadPiece() {
		return this.head;
	}
	
	public Apparel getTopPiece() {
		return this.top;
	}
	public Apparel getBotPiece() {
		return this.bot;
	}
	public Apparel getLegsPiece() {
		return this.legs;
	}
	public Apparel getShoes() {
		return this.shoes;
	}
	public Apparel getAcc() {
		return this.acc;
	}
	
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	
	public void setSalary(double salary) {
		this.salary = salary;
	}
	
	public double getSalary() {
		return this.salary;
	}
	
	public String getPhoneNum() {
		return this.phoneNum;
	}
	
	public String getAgent() {
		return agent;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void changeHead(Apparel item) {
		this.head = item;
	}
	
	public void changeTop(Apparel item) {
		this.top = item;
	}
	
	public void changeBot(Apparel item) {
		this.bot = item;
	}
	
	public void changeLegs(Apparel item) {
		this.legs = item;
	}
	
	public void changeShoes(Apparel item) {
		this.shoes = item;
	}
	
	public void changeAcc(Apparel item) {
		this.acc = item;
	}
}
