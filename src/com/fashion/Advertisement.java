package com.fashion;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 
 * @author Emily Young
 * 
 *   Advertisement is used to promote shows that are hosted by the Fashion Studio
 *
 */

public class Advertisement {

	/**
	 * Instance variables
	 */
	private int eid;
	private String eventName;
	private String loc;
	private String time;
	private String contactInfo;
	private File createdAd;

	/**
	 * Constructor for advertisement
	 */
	public Advertisement(int eid, String eventName, String loc, String time, String contactInfo) {
		this.eid = eid;
		this.eventName = eventName;
		this.loc = loc;
		this.time = time;
		this.contactInfo = contactInfo;
	}

	public void createAdPaper() {
		createdAd = new File("coms362-fashion-studio\\" + getFileName());
		FileWriter writer = null;
		try {
			writer = new FileWriter(createdAd.getName());
			writer.write("Come enjoy the lovely event: " + eventName + "\n" + "Location: " + loc + "\n" + "Time: "
					+ time + "\n" + "Please get in contact with our ticket office to reserve seats today!\n"
					+ "You can reach us at: " + contactInfo);
		} catch (IOException e) {
			System.out.println("An error occurred");
			e.printStackTrace();
		} finally {
			try {
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public String getFileName() {
		return eventName + ".txt";
	}

	public int getEventID() {
		return this.eid;
	}
	
	public String getLocation() {
		return this.loc;
	}
	
	public String getTime() {
		return this.time;
	}
	
	public String getContactInfo() {
		return this.contactInfo;
	}

	public File getFile() {
		return createdAd;
	}

	public String getEventName() {
		return this.eventName;
	}
}
