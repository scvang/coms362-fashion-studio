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
	private ArrayList<Apparel> clothingStock;
	private ArrayList<Food> foodStock;
	private ArrayList<MakeUp> makeupStock;
	
	public Inventory() {
		clothingStock = new ArrayList<>();
		foodStock = new ArrayList<>();
		makeupStock = new ArrayList<>();
	}
	
	public void storeClothing(Apparel a) {
		clothingStock.add(a);
	}
	
	public void storeFood(Food f) {
		int count = 0;
		for(int i = 0; i < foodStock.size(); ++i) {
			if(f.getItemName().equals((foodStock.get(i)).getItemName())) {
				count = foodStock.get(i).getQuantity();
				++count;
				foodStock.get(i).setQuantity(count);
				return;
			}
		}
		foodStock.add(f);
	}
	public void storeMakeup(MakeUp m) {
		int count = 0;
		for(int i = 0; i < foodStock.size(); ++i) {
			if(m.sameItem(makeupStock.get(i))) {
				count = makeupStock.get(i).getQuantity();
				++count;
				makeupStock.get(i).setQuantity(count);
				return;
			}
		}
		makeupStock.add(m);
	}
	
	public void displayClothingInventory() {
		for(Apparel a : clothingStock) {
			System.out.println(
					"Item name: " + a.getItemName() + "\n" +
					"Brand name: " + a.getBrandName() + "\n" +
					"Size: " + a.getSize() + "\n" +
					"Color: " + a.getColor() + "\n" +
					"Price: " + a.getPrice() + "\n" +
					"Quantity: " + a.getQuantity() + "\n"
					);
		}
	}
	
	public void displayFoodInventory() {
		for(Food f : foodStock) {
			System.out.println("Item name: " + f.getItemName() + " " + "Quantity: " + f.getQuantity());
		}
	}
	public void displayMakeupInventory() {
		for(MakeUp m : makeupStock) {
			System.out.println("Item name: " + m.getItemName() + ", Brand name:" + m.getBrandName() + ", Weight: " + m.getWeight() + ", Color: " + m.getColor() + ", Quantity: " + m.getQuantity());
		}
	}
	
	public Apparel search(Apparel a) {
		for(int i = 0; i < clothingStock.size(); ++i) {
			if(a.sameItem(clothingStock.get(i))) {
				return clothingStock.get(i);
			}
		}
		return null;
	}
}
