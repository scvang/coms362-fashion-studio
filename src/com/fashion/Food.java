package com.fashion;

public class Food
{
	private String itemName;
	private int quantity;
	
	public Food(String name, int quantity)
	{
		this.itemName = name;
		this.quantity = quantity;
	}
	
	public String getItemName() {
		return this.itemName;
	}
	
	public int getQuantity() {
		return this.quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
