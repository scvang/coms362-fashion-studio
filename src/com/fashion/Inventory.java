package com.fashion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.fashion.apparel.Apparel;

/**
 * Inventory is the information expert that knows about Apparel, Food and Makeup.
 * @author Sebastian Vang
 *
 */
public class Inventory {
	private String name;
	private ArrayList<Apparel> clothingStock;
	private ArrayList<Food> foodStock;
	private ArrayList<MakeUp> makeupStock;
	
	public Inventory() {
		this.name = "default";
		clothingStock = new ArrayList<>();
		foodStock = new ArrayList<>();
		makeupStock = new ArrayList<>();
	}
	
	public String getInventoryName() {
		return this.name;
	}
	public void changeInventoryName(String name) {
		this.name = name;
	}
	
	public void storeClothing(Apparel a) {
		int count = 0;
		for(int i = 0; i < clothingStock.size(); ++i) {
			if(a.sameItem(clothingStock.get(i))) {
				count = clothingStock.get(i).getQuantity();
				++count;
				clothingStock.get(i).setQuantity(count);
				return;
			}
		}
		clothingStock.add(a);
	}
	
	public void storeFood(Food f) {
		foodStock.add(f);
	}
	public void storeMakeup(MakeUp m) {
		makeupStock.add(m);
	}
	
	public void displayClothingInventory() {
		for(Apparel a : clothingStock) {
			System.out.println("Item name: " + a.getItemName() + ", " + "Brand name: " + a.getBrandName() + ", " + a.getColor() + ", " + "Quantity: " + a.getQuantity());
		}
	}
}
