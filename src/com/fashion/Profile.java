package com.fashion;

/**
 * 
 * @author Sebastian Vang
 * Profile is the information expert that knows about Picture.
 *
 */
public class Profile {
	private String path;
	private String desc;
	
	public Profile() {}
	
	public Profile(String path, String desc) {
		this.path = path;
		this.desc = desc;
	}
	
	public void displayInfo() {
		Picture.display(path,desc);
	}
	
	public void setDescription(String desc) {
		this.desc = desc;
	}
	
	public void setImage(String path) {
		this.path = path;
	}
}
