package com.fashion.commands;

import java.util.Scanner;

import com.fashion.Main;
import com.fashion.Studio;
import com.fashion.apparel.Apparel;
import com.fashion.screens.ModelScreen;

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
		new ModelScreen(studio).execute();
	}
	
	private static Apparel makeApparel() {

		Scanner in = new Scanner(System.in);
		
		System.out.println("Enter the item name:");
		String name = in.nextLine();
		
		System.out.println("Enter the brand name:");
		String brand = in.nextLine();
		
		System.out.println("Enter the color:");
		String color = in.nextLine();
		
		System.out.println("Enter the size:");
		String size = in.nextLine();
		
		Apparel item = new Apparel(0,name, brand, color,size,0,0);
		
		return item;
	}

}
