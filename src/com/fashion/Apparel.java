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
	private int itemID;
	private int stock;
	
	/**
	 * 
	 * Constructor for the apparel.
	 * 
	 * @param name
	 * @param brand
	 * @param color
	 * @param id
	 * @param stock
	 */
	public Apparel(String name, String brand, String color, int id, int stock) {
		this.itemName = name;
		this.brandName = brand;
		this.color = color;
		this.itemID = id;
		this.stock = stock;
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
	
	public String getColor() {
		return this.color;
	}
	/**
	 * 
	 * @return item ID
	 */
	public int getItemID() {
		return this.itemID;
	}
	
	/**
	 * 
	 * @return number of items in stock
	 */
	public int getStock() {
		return this.stock;
	}
}