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
	 * @param size describes the size of clothing being added
	 * @param itemName is the name of the product being added
	 */
	public Apparel(String size, String itemName) {
		this.size = size;
		this.itemName = itemName;
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
	public int getPrice() {
		return price;
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
		return size;
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