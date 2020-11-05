package com.fashion;

import com.fashion.apparel.Apparel;

public class MakeUp {
	
	private String itemName;
	private String brandName;
	private String color;
	private double weight;
	private int quantity;
	
	public MakeUp(String itemName, String brandName, String color, double weight) {
		this.itemName = itemName;
		this.brandName = brandName;
		this.color = color;
		this.weight = weight;
		this.quantity = 1;
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
	
	public double getWeight() {
		return this.weight;
	}
	
	public int getQuantity() {
		return this.quantity;
	}
	
	public void setQuantity(int number) {
		this.quantity = number;
	}

	public boolean sameItem(Object o) {
		if(o == null || o.getClass() != this.getClass()) return false;
		MakeUp m = (MakeUp)o;
		return this.itemName.equals(m.itemName) && 
				this.brandName.equals(m.brandName) && 
				this.color.equals(m.color) &&
				this.weight == m.weight;
	}
}
