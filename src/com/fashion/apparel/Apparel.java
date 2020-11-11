package com.fashion.apparel;

import com.fashion.Item;

/**
 * @author Sebastian Vang
 * 
 * Apparel is worn by the models and sold in the studio.
 *
 */
public class Apparel implements Item{
	
	/**
	 * Instance variables.
	 */
	private int id;
	private String itemName;
	private String brandName;
	private String color;
	private String size;
	private double price;
	private int quantity;
	
	/**
	 * Empty constructor.
	 */
	public Apparel() {
		this.id = 0;
		this.itemName = "";
		this.brandName = "";
		this.color = "";
		this.size = "";
		this.price = 0;
		this.quantity = 0;
	}
	
	/**
	 * @author Sebastian Vang
	 * Constructor for the apparel.
	 * @param id
	 * @param name
	 * @param brand
	 * @param color
	 * @param size
	 * @param price
	 * @param quantity
	 */
	public Apparel(int id, String name, String brand, String color, String size, double price, int quantity) {
		this.id = id;
		this.size = size;
		this.price = price;
		this.itemName = name;
		this.brandName = brand;
		this.color = color;
		this.quantity = quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public int getQuantity() {
		return this.quantity;
	}

	/**
	 * @return the id associated with the product in out dB
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id is the id associated with the product in out dB
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the price of our product
	 */
	public double getPrice() {
		return this.price;
	}

	/**
	 * @param price is the price of our product
	 */
	public void setPrice(int price) {
		this.price = price;
	}

	/**
	 * @return the name of the product
	 */
	public String getItemName() {
		return itemName;
	}

	/**
	 * @param itemName is the name of product
	 */
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	/**
	 * @return the brand name of our product
	 */
	public String getBrandName() {
		return brandName;
	}

	/**
	 * @param brandName is the brand name of our product
	 */
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	/**
	 * @return the color of the apparel item
	 */
	public String getColor() {
		return color;
	}
	
	public boolean sameItem(Object o) {
		if(o == null || o.getClass() != this.getClass()) return false;
		Apparel a = (Apparel)o;
		return 	this.itemName.equals(a.itemName) && 
				this.brandName.equals(a.brandName) && 
				this.color.equals(a.color) &&
				this.size.equals(a.size) &&
				this.price == a.price;
	}

	/**
	 * @param color of the apparel item
	 */
	public void setColor(String color) {
		this.color = color;
	}

	/**
	 * @return the size of the apparel item
	 */
	public String getSize() {
		return this.size;
	}

	/**
	 * @param size sets the size of the apparel item
	 */
	public void setSize(String size) {
		this.size = size;
	}

	@Override
	public String toString() {
		return "$" + price + " " + brandName + " "
				+ itemName + "\n" + size + "\n";
	}
}