package com.fashion.commands;

import java.util.ArrayList;
import java.util.Scanner;

import com.fashion.Main;
import com.fashion.Studio;
import com.fashion.apparel.Apparel;
import com.fashion.screens.InventoryScreen;

public class SearchClothingCmd implements Command {
	private Studio studio;
	public SearchClothingCmd(Studio s) {
		this.studio = s;
	}

	@Override
	public String getDescription() {
		return "Search Clothing";
	}

	@Override
	public void execute() {
		Scanner in = new Scanner(System.in);
		System.out.println("Enter item name:");
		String n = in.nextLine();
		
		System.out.println("Enter brand name:");
		String b = in.nextLine();
		
		System.out.println("Enter color:");
		String c = in.nextLine();
		
		System.out.println("Enter size");
		String s = in.nextLine();
		
		System.out.println("Enter the price:");
		double p = in.nextInt(); in.nextLine();
		
		ArrayList<Apparel> list = studio.getInventory().search(new Apparel(0,n,b,c,s,p,0));
		
		if(list == null) {
			System.out.println("Search results:");
			System.out.println("No items were found.");
			new InventoryScreen(studio).execute();;
		}
		System.out.println("Search results:");
		
		for(Apparel apparel : list) {
		System.out.println(
				"Item name: " + apparel.getItemName() + "\n" +
				"Brand name: " + apparel.getBrandName() + "\n" +
				"Color: " + apparel.getColor() + "\n" +
				"Size: " + apparel.getSize() + "\n" +
				"Price: " + apparel.getPrice() + "\n" +
				"Quantity: " + apparel.getQuantity() + "\n"
				);
		}
		
		new InventoryScreen(studio).execute();
	}

}
