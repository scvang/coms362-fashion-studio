package com.fashion;

/**
 * @author Sebastian Vang
 * 
 * Apparel is worn by the models and sold in the studio.
 *
 */
public class Apparel {
	
	/**
	 * Instance variables.
	 */
	private String itemName;
	private String brandName;
	private String color;
	
	/**
	 * 
	 * Constructor for the apparel.
	 * 
	 * @param name
	 * @param brand
	 * @param color
	 */
	public Apparel(String name, String brand, String color) {
		this.itemName = name;
		this.brandName = brand;
		this.color = color;
	}
	
	/**
	 * 
	 * @return item name
	 */
	public String getItemName() {
		return this.itemName;
	}
	
	/**
	 * 
	 * @return brand name
	 */
	public String getBrandName() {
		return this.brandName;
	}
	
	/**
	 * 
	 * @return item color
	 */
	public String getColor() {
		return this.color;
	}

}