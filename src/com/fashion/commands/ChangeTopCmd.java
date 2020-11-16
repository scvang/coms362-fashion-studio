package com.fashion.commands;

import java.util.Scanner;

import com.fashion.Studio;
import com.fashion.apparel.Apparel;

public class ChangeTopCmd implements Command {
	private Studio studio;
	private String modelName;
	public ChangeTopCmd(Studio s, String n) {
		this.studio = s;
		this.modelName = n;
	}

	@Override
	public String getDescription() {

		return "Change Top";
	}

	@Override
	public void execute() {
		Apparel item = makeApparel();
		studio.changeTop(modelName,item);
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
		String size = in.nextLine();;
		
		Apparel item = new Apparel(0,name, brand, color,size,0,0);
		
		return item;
	}

}
