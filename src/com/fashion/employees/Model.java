package com.fashion.employees;

import com.fashion.apparel.Apparel;
import com.fashion.pay.PayStubInfo;

/**
 * Model is the information expert that knows about apparel.
 * @author Sebastian Vang
 *
 */
public class Model extends Employee{
	
	/**
	 * Instance variables.
	 */
	private String agent;
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
	public Model(int eid, String agent, String name, String jobTitle, String phone, PayStubInfo payStubInfo) {
		super(eid,name,jobTitle,phone,payStubInfo);
		this.agent = agent;
		
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
	
	public String getAgent() {
		return agent;
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
