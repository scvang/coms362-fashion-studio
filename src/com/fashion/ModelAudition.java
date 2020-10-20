package com.fashion;

import com.fashion.employees.Employee;

import java.io.File;

/**
 * 
 * @author Emily Young
 * 
 *         ModelAudition displays the model audition/hiring process
 *
 */

public class ModelAudition {

	/**
	 * Instance Variables
	 */
	private String name;
	private String phoneNum;
	private int audNum;
	private File resume = new File("resume.txt");
	private boolean hireable;
	private Employee newMod;
	private File headshot;

	/**
	 * Constructor for ModelAudition
	 */
	public ModelAudition(String name, String phoneNum, int audNum) {
		this.name = name;
		this.phoneNum = phoneNum;
		this.audNum = audNum;
		this.headshot = new File("headshot.jpg");
	}
	
	/**
	 * 
	 * @return name
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * 
	 * @return phone number
	 */
	public String getPhoneNum() {
		return this.phoneNum;
	}

	/**
	 * 
	 * @return resume
	 */
	public File getResume() {
		return this.resume;
	}

	/**
	 * 
	 * @return headshot
	 */
	public File getHeadshot() {
		return this.headshot;
	}
	
	/**
	 * 
	 * @return audition number
	 */
	public int getAudNum() {
		return this.audNum;
	}

	/**
	 * 
	 * @param audNum-audition number
	 * @return hireable
	 * This method checks the validitiy of the model's items and decides if they are hireable
	 */
	public boolean checkItems(int audNum) {
		if (audNum == 0 || !resume.exists() || !headshot.exists()) {
			hireable = false;
		} else {
			hireable = true;
		}
		return hireable;
	}
	
	/**
	 * 
	 * @param hire-hireable
	 * @return model as employee instance
	 * This method records the new model as an employee of the fashion studio
	 */
	public Employee hireModel(boolean hire) {
		if(hire) {
			newMod.setJobTitle("Model");
			newMod.setSalary(29000.0);
			newMod.setPhoneNum(phoneNum);
		} else {
			System.out.println("We are not going to hire you at this time.");
		}
		return newMod;
	}
}
