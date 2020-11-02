package com.fashion;

public class Service extends Business{

	private Business b;
	private String repName;
	private String service;
	private int sid;
	
	public Service(int sid, Business b, String repName, String service) {
		this.sid=sid;
		this.b=b;
		this.repName=repName;
		this.service=service;
	}
	
	public String getName() {
		return b.getName();
	}
	
	public String getAddress() {
		return b.getAddress();
	}
	
	public String getPhoneNum() {
		return b.getPhoneNum();
	}
	
	public String getRepName() {
		return repName;
	}
	
	public String getServiceType() {
		return service;
	}
	
	public int getServiceID() {
		return sid;
	}
	
	public boolean confirmBusiness() {
		return false;
	}
}
