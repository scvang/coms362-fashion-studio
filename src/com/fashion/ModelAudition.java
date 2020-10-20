package com.fashion;

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
	
	public String getName() {
		return this.name;
	}
	
	public String getPhoneNum() {
		return this.phoneNum;
	}

	public File getResume() {
		return this.resume;
	}

	public File getHeadshot() {
		return this.headshot;
	}
	
	public int getAudNum() {
		return this.audNum;
	}

	public boolean checkItems(int audNum) {
		if (audNum == 0 || !resume.exists() || !headshot.exists()) {
			hireable = false;
		} else {
			hireable = true;
		}
		return hireable;
	}
	
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
