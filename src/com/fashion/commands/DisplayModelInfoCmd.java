package com.fashion.commands;

import java.util.Scanner;

import com.fashion.Main;
import com.fashion.Studio;
import com.fashion.screens.ModelScreen;

public class DisplayModelInfoCmd implements Command {
	private Studio studio;
	
	public DisplayModelInfoCmd(Studio s) {
		this.studio = s;
	}

	@Override
	public String getDescription() {

		return "Display Model Information";
	}

	@Override
	public void execute() {
		Scanner in = new Scanner(System.in);
		System.out.println("Enter the model's name:");
		String name = in.nextLine();
		
		if(studio.getModel(name) != null) {
			String description = "Model Name: " + studio.getModel(name).getName() + "<br/>" + 
					"Agent: " + studio.getModel(name).getAgent() + "<br/>" +
					"Phone: " + studio.getModel(name).getPhoneNum() + "<br/>" +
					"Salary: " + studio.getModel(name).getPayStubInfo().getSalary() + "<br/><br/>" +
					
					"Headwear: " + studio.getModel(name).getHeadPiece().getItemName() + "<br/>" +
					"Brand: " + studio.getModel(name).getHeadPiece().getBrandName() + "<br/>" +
					"Color: " + studio.getModel(name).getHeadPiece().getColor() + "<br/>" +
					"Size: " + studio.getModel(name).getHeadPiece().getSize() + "<br/><br/>" +
					
					"Top: " + studio.getModel(name).getTopPiece().getItemName() + "<br/>" +
					"Brand: " + studio.getModel(name).getTopPiece().getBrandName() + "<br/>" +
					"Color: " + studio.getModel(name).getTopPiece().getColor() + "<br/>" +
					"Size: " + studio.getModel(name).getTopPiece().getSize() + "<br/><br/>" +
					
					"Bottoms: " + studio.getModel(name).getBotPiece().getItemName() + "<br/>" +
					"Brand: " + studio.getModel(name).getBotPiece().getBrandName() + "<br/>" +
					"Color: " + studio.getModel(name).getBotPiece().getColor() + "<br/>" +
					"Size: " + studio.getModel(name).getBotPiece().getSize() + "<br/><br/>" +
					
					"Leggings: " + studio.getModel(name).getLegsPiece().getItemName() + "<br/>" +
					"Brand: " + studio.getModel(name).getLegsPiece().getBrandName() + "<br/>" +
					"Color: " + studio.getModel(name).getLegsPiece().getColor() + "<br/>" +
					"Size: " + studio.getModel(name).getLegsPiece().getSize() + "<br/><br/>" +
					
					"Shoes: " + studio.getModel(name).getShoes().getItemName() + "<br/>" +
					"Brand: " + studio.getModel(name).getShoes().getBrandName() + "<br/>" +
					"Color: " + studio.getModel(name).getShoes().getColor() + "<br/>" +
					"Size: " + studio.getModel(name).getShoes().getSize() + "<br/><br/>" +
					
					"Accessory: " + studio.getModel(name).getAcc().getItemName() + "<br/>" +
					"Brand: " + studio.getModel(name).getAcc().getBrandName() + "<br/>" +
					"Color: " + studio.getModel(name).getAcc().getColor() + "<br/>" +
					"Size: " + studio.getModel(name).getAcc().getSize() + "<br/><br/>";
			
			
			studio.getModel(name).setDescription(description);
			studio.getModel(name).displayInfo();
		}
		else {
			System.out.println("Model wasn't found.");
		}
		new ModelScreen(studio).execute();
	}

}
