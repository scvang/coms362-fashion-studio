package com.fashion;

public class MakeUp {
	
	private String itemName;
	private String brandName;
	private String color;
	private double weight;
	private int quantity;
	
	public MakeUp(String itemName, String brandName, String color, double weight, int quantity) {
		this.itemName = itemName;
		this.brandName = brandName;
		this.color = color;
		this.weight = weight;
		this.quantity = quantity;
	}
	
	public String getItemName() {
		return this.itemName;
	}
	
	public String getBrandName() {
		return this.getBrandName();
	}
	
	public String getColor() {
		return this.getColor();
	}
	
	public double weight() {
		return this.weight;
	}
	
	public int quantity() {
		return this.quantity;
	}
	
	public void setQuantity(int number) {
		this.quantity = number;
	}
}
