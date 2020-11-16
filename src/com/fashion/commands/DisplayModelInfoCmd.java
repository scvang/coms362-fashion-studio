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
					"Salary: " + studio.getModel(name).getPayStubInfo().getSalary() + "<br/>" +
					"Head: " + studio.getModel(name).getHeadPiece().getItemName() + ", Brand: " + studio.getModel(name).getHeadPiece().getBrandName() + ", Color: " + studio.getModel(name).getHeadPiece().getColor() + "<br/>" +
					"Top: " + studio.getModel(name).getTopPiece().getItemName() + ", Brand: " + studio.getModel(name).getTopPiece().getBrandName() + ", Color: " + studio.getModel(name).getTopPiece().getColor() + "<br/>" +
					"Bottoms: " + studio.getModel(name).getBotPiece().getItemName() + ", Brand: " + studio.getModel(name).getBotPiece().getBrandName() + ", Color: " + studio.getModel(name).getBotPiece().getColor() + "<br/>" +
					"Leggings: " + studio.getModel(name).getLegsPiece().getItemName() + ", Brand: " + studio.getModel(name).getLegsPiece().getBrandName() + ", Color: " + studio.getModel(name).getLegsPiece().getColor() + "<br/>" +
					"Shoes: " + studio.getModel(name).getShoes().getItemName() + ", Brand: " + studio.getModel(name).getShoes().getBrandName() + ", Color: " + studio.getModel(name).getShoes().getColor() + "<br/>" +
					"Accessory: " + studio.getModel(name).getAcc().getItemName() + ", Brand: " + studio.getModel(name).getAcc().getBrandName() + ", Color: " + studio.getModel(name).getAcc().getColor() + "<br/>";
			studio.getModel(name).setDescription(description);
			studio.getModel(name).displayInfo();
		}
		else {
			System.out.println("Model wasn't found.");
		}
		new ModelScreen(studio).execute();
	}

}
