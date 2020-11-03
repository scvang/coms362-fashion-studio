package com.fashion;

import java.util.ArrayList;

public class Service extends Business{

	private Business b;
	private String repName;
	private String service;
	private int sid;
	private boolean isContacted;
	
	public Service(int sid, Business b, String repName, String service) {
		this.sid=sid;
		this.b=b;
		this.repName=repName;
		this.service=service;
		isContacted = false;
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
	
	public boolean hasBeenContacted() {
		return isContacted;
	}
	
	public boolean confirmBusiness(ArrayList<Service> list, boolean contact) {
		for (int i = 0; i <= list.size() - 1; i++) {
			if(!contact) {
				isContacted = contactBusiness(list.get(i));
			}
			else {
				isContacted = false;
			}
		}
		return isContacted;
	}
	
	public boolean contactBusiness(Service business) {
		return business.isContacted = true;
	}
}
