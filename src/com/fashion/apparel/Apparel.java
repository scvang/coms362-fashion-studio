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
	private int id;
	private String size;
	private int price;
	private String itemName;
	private String brandName;
	private String color;

	public Apparel(int id, String size, int price, String itemName, String brandName, String color) {
		this.id = id;
		this.size = size;
		this.price = price;
		this.itemName = itemName;
		this.brandName = brandName;
		this.color = color;
	}

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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}
}