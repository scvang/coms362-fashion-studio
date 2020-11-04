package com.fashion.employees;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import com.fashion.Business;
import com.fashion.ModelAudition;
import com.fashion.Service;
import com.fashion.Studio;
import com.fashion.events.Event;
import com.fashion.pay.PayStub;
import com.fashion.pay.PayStubInfo;

/**
 * This class represents the entirety of the Human Resources of the Fashion Studio
 * @author Emily Young
 *
 */

public class HumanResources {

	/**
	 * Instance variables
	 */
	private Employee emp;
	private Business bus;
	private ArrayList<Employee> employees;
	private Service service;
	private ArrayList<Service> servicesUsed;
	private ArrayList<Service> servicesRequested;
	ArrayList<PayStub> payStubHistory;
	PayStubInfo p;
	
	/**
	 * Constructor
	 */
	public HumanResources() {
		
	}
	
	public Employee modelAudition(String name, String phoneNum, int audNum) {
		ModelAudition ma = new ModelAudition(name, phoneNum, audNum);
		return ma.hireModel(ma.checkItems(audNum));
	}
	
	public Employee hiringPhotographer(String name, String company, String phoneNum) {
		return null;		
	}
	
	public void hireEmployee(int eid, String name, String jobTitle, String phoneNum, PayStubInfo payStubInfo) {
		emp = new Employee(eid, name, jobTitle, phoneNum, payStubInfo);
		addEmployee(emp.getEid(), emp.getName(), emp.getPhoneNum(), emp.getJobTitle(), emp.getPayStubInfo().getSalary(), emp.getPayStubInfo().getBankAccountNum(), emp.getPayStubInfo().getBankRoutingNum());
	}
	
	public void fireEmployee(int eid) {
		employees.remove(getEmployee(eid));
	}
	
	public void hireBusiness(int sid, String businessName, String loc, String service, String repName, String contactInfo, double salary) {
		bus.setName(businessName);
		bus.setAddress(loc);
		bus.setPhoneNum(contactInfo);
		p = new PayStubInfo(salary);
		addService(sid, repName, service);
	}
	
	public boolean payBusiness(int eid, PayStubInfo p){
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();

		PayStub payStub = new PayStub(eid, dtf.format(now), p);
		return payStubHistory.add(payStub);
	}

	public void addEmployee(int eid, String name, String title, String phone, double salary, int bankAccount, int bankRouting) {
		employees.add(new Employee(eid, name,title,phone, new PayStubInfo(salary, 0, bankAccount, bankRouting)));
	}
	
	public void addService(int sid, String repName, String service) {
			this.service = new Service(sid, bus, repName, service);	
			servicesUsed.add(this.service);
	}
	
	public void getServices() {
		for(Service s : servicesUsed) {
			System.out.println(
			"Business Name: " + s.getName() + "\n" +
			"Business Location: " + s.getAddress() + "\n" +
			"Representative: " + s.getRepName() + "\n" +
			"Rep Contact" + s.getPhoneNum() + "\n" +
			"Service Provided: " + s.getServiceType() + "\n" +
			"Money Owed: $" + payBusiness(s.getServiceID(), p)
			);
		}
	}
	
	public ArrayList<Service> checkEventRequests(ArrayList<Service> needs){
		ArrayList<Service> newServices = new ArrayList<Service>();
		for(int i = 0; i < needs.size() - 1; i++) {
			if(!servicesUsed.contains(needs.get(i))) {
				newServices.add(needs.get(i));
			}
		}
		return newServices ;
	}

	public ArrayList<Service> getServiceRequests(){
		return servicesUsed;
	}
	
	public Employee getEmployee(int eid) {
		for(Employee e : employees) {
			if(e.getEid() == eid){
				return e;
			}
		}

		System.out.println("Employee doesn't exist");
		return null;
	}
	
	/**
	 * Lists the employees information.
	 */
	public void getEmployees() {
		for(Employee e : employees) {
			System.out.println(
			"EID: " + e.getEid() + "\n" +
			"Employee Name: " + e.getName() + "\n" +
			"Job title: " + e.getJobTitle() + "\n" +
			"Salary: $" + e.getPayStubInfo().getSalary() + "\n" +
			"Phone: " + e.getPhoneNum() + "\n"
			);
		}
	}
}