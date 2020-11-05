package com.fashion.employees;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

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
	private static Business bus;
	private ArrayList<Employee> employees;
	private static Service serv;
	public static ArrayList<Service> servicesUsed = new ArrayList<>();
	private ArrayList<Service> servicesRequested;
	static ArrayList<PayStub> payStubHistory;
	private static PayStubInfo p;

	
	/**
	 * Constructor
	 */
	public HumanResources() {
		employees = new ArrayList<>();
		servicesUsed = new ArrayList<>();
		servicesRequested = new ArrayList<>();
		payStubHistory = new ArrayList<>();
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
	
	public static void hireBusiness(int sid, String businessName, String loc, String service, String repName, String contactInfo, double salary) {
		bus = new Business(sid, businessName, loc, contactInfo, service);
		p = new PayStubInfo(salary);
		addService(repName);
	}
	
	public static boolean payBusiness(int eid, PayStubInfo p){
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();

		PayStub payStub = new PayStub(eid, dtf.format(now), p);
		return payStubHistory.add(payStub);
	}

	public void addEmployee(int eid, String name, String title, String phone, double salary, int bankAccount, int bankRouting) {
		employees.add(new Employee(eid, name,title,phone, new PayStubInfo(salary, 0, bankAccount, bankRouting)));
	}
	
	public static void addService(String repName) {
			serv = new Service(bus.getBid(), bus, repName, bus.getBusType());	
			servicesUsed.add(serv);
	}
	
	public static void getServices() {
		for(Service s : servicesUsed) {
			System.out.println(
			"Business Name: " + s.getName() + "\n" +
			"Business Location: " + s.getAddress() + "\n" +
			"Representative: " + s.getRepName() + "\n" +
			"Rep Contact: " + s.getPhoneNum() + "\n" +
			"Service Provided: " + s.getServiceType() + "\n" //+
			//"Money Owed: $" + payBusiness(s.getServiceID(), p)
			);
		}
	}
	
	public static ArrayList<Service> checkEventRequests(ArrayList<Service> needs){
		ArrayList<Service> newServices = new ArrayList<Service>();
		for(int i = 0; i < needs.size() - 1; i++) {
			if(!servicesUsed.contains(needs.get(i))) {
				newServices.add(needs.get(i));
			}
		}
		return newServices ;
	}

	public static void getServiceRequests(){
		for(Service s : servicesUsed) {
			System.out.println(
			"Business Name: " + s.getName() + "\n" +
			"Business Location: " + s.getAddress() + "\n" +
			"Representative: " + s.getRepName() + "\n" +
			"Rep Contact: " + s.getPhoneNum() + "\n" +
			"Service Provided: " + s.getServiceType() + "\n" +
			"Have they been contacted? " + s.hasBeenContacted() + "\n"
			);
		}
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
