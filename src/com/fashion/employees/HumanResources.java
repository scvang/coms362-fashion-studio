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
	private ArrayList<Model> model;
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
		for(int i = 0; i < needs.size(); i++) {
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
	
	/**
	 @author Chad Morrow
	 pay the employee
	 */
	public boolean payEmployee(int eid, PayStubInfo p){
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();

		PayStub payStub = new PayStub(eid, dtf.format(now), p);
		return payStubHistory.add(payStub);
	}
	/**
	 * @author Emily Young
	 * @param name-model name
	 * @param phoneNum-model phone number
	 * @param audNum-model audition number
	 * 
	 * This method adds a new model to a list of existing employees
	 */
	public void addModel(String name, String phoneNum, int audNum) {
		ModelAudition model = new ModelAudition(name, phoneNum, audNum);
		employees.add(new Employee(nextEID(), model.getName(), "Model", model.getPhoneNum(), new PayStubInfo(50000, 0, 0, 0)));
	}
	
	/**
	 * @author Chad Morrow
	 * @return the next eid for indexing the employees
	 */
	private int nextEID(){
		int nextEID = 0;

		for(Employee employee : employees){
			if(nextEID < employee.getEid()){
				nextEID = employee.getEid();
			}
		}

		return nextEID + 1;
	}
	
	/**
	 * @author Sebastian Vang
	 * Returns a single model.
	 * @param name
	 * @return
	 */
	public Model getModel(String name) {
		for(Model m : model) {
			if(name.equals(m.getName())) {
				return m;
			}
		}
		return null;
	}
	
	/**
	 * @author Sebastian Vang
	 * Finds the model.
	 * @param name
	 * @return
	 */
	public boolean doesModelExist(String name) {
		for(Model m: model) {
			if(m.getName().equals(name)) return true;
		}
		return false;
	}
	
	/**
	 * @author Sebastian Vang
	 * @author Emily Young
	 * This method retrieves the existing list of models.
	 */
	public void displayModels() {
		for(Model m : model) {
			System.out.println(
			"Model Name: " + m.getName() + "\n" + 
			"Agent: " + m.getAgent() + "\n" +
			"Phone: " + m.getPhoneNum() + "\n" +
			"Salary: " + m.getPayStubInfo().getSalary() + "\n" +
			"Head: " + m.getHeadPiece().getItemName() + ", Brand: " + m.getHeadPiece().getBrandName() + ", Color: " + m.getHeadPiece().getColor() + "\n" +
			"Top: " + m.getTopPiece().getItemName() + ", Brand: " + m.getTopPiece().getBrandName() + ", Color: " + m.getTopPiece().getColor() + "\n" +
			"Bottoms: " + m.getBotPiece().getItemName() + ", Brand: " + m.getBotPiece().getBrandName() + ", Color: " + m.getBotPiece().getColor() + "\n" +
			"Leggings: " + m.getLegsPiece().getItemName() + ", Brand: " + m.getLegsPiece().getBrandName() + ", Color: " + m.getLegsPiece().getColor() + "\n" +
			"Shoes: " + m.getShoes().getItemName() + ", Brand: " + m.getShoes().getBrandName() + ", Color: " + m.getShoes().getColor() + "\n" +
			"Accessory: " + m.getAcc().getItemName() + ", Brand: " + m.getAcc().getBrandName() + ", Color: " + m.getAcc().getColor() + "\n"
			
			);
		}
	}
	
	/**
	 * @author Emily Young
	 * @param agent-agent name
	 * @param name-model name
	 * @param phoneNum-model phone number
	 * @param payStubInfo-model pay info
	 * 
	 * This method adds a new model to a list of existing models
	 */
	public void createModel(int eid,String agent, String name, String jobTitle, String phoneNum, PayStubInfo payStubInfo) {
		model.add(new Model(eid,agent,name,jobTitle,phoneNum,payStubInfo));
	}

}
