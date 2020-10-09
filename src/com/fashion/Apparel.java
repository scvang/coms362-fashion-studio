package com.fashion;

/**
 * @author Sebastian Vang
 * 
 * Apparel is worn by the models and sold in the studio.
 *
 */
public class Apparel {
	
	private String itemName;
	private String brandName;
	private int itemID;
	private int stock;
	
	/**
	 * 
	 * Constructor for the apparel.
	 * 
	 * @param name
	 * @param brand
	 * @param id
	 * @param stock
	 */
	public Apparel(String name, String brand, int id, int stock) {
		this.itemName = name;
		this.brandName = brand;
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