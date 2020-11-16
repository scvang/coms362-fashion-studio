package com.fashion.commands;

import java.util.Scanner;

import com.fashion.Main;
import com.fashion.Studio;
import com.fashion.apparel.Apparel;

public class ChangeLegsCmd implements Command {
	private Studio studio;
	private String modelName;
	public ChangeLegsCmd(Studio s, String n) {
		this.studio = s;
		this.modelName = n;
	}

	@Override
	public String getDescription() {

		return "Change Leggings";
	}

	@Override
	public void execute() {
		Apparel item = makeApparel();
		studio.changeLegs(modelName,item);
		Main.ModelScreen();
	}
	
	private static Apparel makeApparel() {
		System.out.println("Enter the item id:");
		Scanner in = new Scanner(System.in);
		int id = in.nextInt(); in.nextLine();
		
		System.out.println("Enter the item name:");
		String name = in.nextLine();
		
		System.out.println("Enter the brand name:");
		String brand = in.nextLine();
		
		System.out.println("Enter the color:");
		String color = in.nextLine();
		
		System.out.println("Enter the size:");
		String size = in.nextLine();
		
		System.out.println("Enter the price:");
		double price = in.nextDouble(); in.nextLine();
		
		System.out.println("Enter the quantity:");
		int quantity = in.nextInt(); in.nextLine();
		
		Apparel item = new Apparel(id,name, brand, color,size,price,quantity);
		
		return item;
	}

}
