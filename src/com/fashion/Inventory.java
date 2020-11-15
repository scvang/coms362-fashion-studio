package com.fashion;

import java.sql.ResultSet;
import java.sql.SQLException;
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
	
	public Inventory() {
		clothingStock = new ArrayList<>();
	}
	
	public void storeClothing(Apparel a) {
		clothingStock.add(a);
	}
	
	public void displayClothingInventory() {
		for(Apparel a : clothingStock) {
			System.out.println(
					"Item name: " + a.getItemName() + "\n" +
					"Brand name: " + a.getBrandName() + "\n" +
					"Color: " + a.getColor() + "\n" +
					"Size: " + a.getSize() + "\n" +
					"Price: " + a.getPrice() + "\n" +
					"Quantity: " + a.getQuantity() + "\n"
					);
		}
	}
	
	public ArrayList<Apparel> search(Apparel apparel) {
		
		ArrayList<Apparel> list = new ArrayList<>();
		
		// Iterate through clothing stock list.
		for(int i = 0; i < clothingStock.size(); ++i) {
			if(apparel.getItemName().equals(clothingStock.get(i).getItemName())) {
				if(!list.contains(clothingStock.get(i))) list.add(clothingStock.get(i));
			}
			if(apparel.getBrandName().equals(clothingStock.get(i).getBrandName())) {
				if(!list.contains(clothingStock.get(i))) list.add(clothingStock.get(i));
			}
			if(apparel.getColor().equals(clothingStock.get(i).getColor())) {
				if(!list.contains(clothingStock.get(i))) list.add(clothingStock.get(i));
			}
			if(apparel.getSize().equals(clothingStock.get(i).getSize())) {
				if(!list.contains(clothingStock.get(i))) list.add(clothingStock.get(i));
			}
			if(apparel.getPrice() == (clothingStock.get(i).getPrice())) {
				if(!list.contains(clothingStock.get(i))) list.add(clothingStock.get(i));
			}
			
		}
		return list;
	}
}
