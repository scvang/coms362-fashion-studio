package com.fashion.apparel;

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
	private int quantity;
	
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
		quantity = 1;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public int getQuantity() {
		return this.quantity;
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
	
	public boolean sameItem(Object o) {
		if(o == null || o.getClass() != this.getClass()) return false;
		Apparel a = (Apparel)o;
		return this.itemName.equals(a.itemName) && this.brandName.equals(a.brandName) && this.color.equals(a.color);
	}

}