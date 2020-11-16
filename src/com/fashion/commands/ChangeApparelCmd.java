package com.fashion.commands;

import java.util.Scanner;

import com.fashion.Main;
import com.fashion.Studio;

public class ChangeApparelCmd implements Command {
	private Studio studio;
	public ChangeApparelCmd(Studio s) {
		this.studio = s;
	}

	@Override
	public String getDescription() {

		return "Change Apparel";
	}

	@Override
	public void execute() {
		Scanner in = new Scanner(System.in);
		System.out.println("Which model do you want to change?");
		String name = in.nextLine();
		if(!studio.doesModelExist(name)) {
			System.out.println("Model was not found, try again.");
			Main.ModelScreen();
		}
		
		CommandDisplay cmd = new CommandDisplay();
		cmd.addCommand(new ChangeHeadCmd(studio,name));
		cmd.addCommand(new ChangeTopCmd(studio,name));
		cmd.addCommand(new ChangeBotCmd(studio,name));
		cmd.addCommand(new ChangeLegsCmd(studio,name));
		cmd.addCommand(new ChangeShoesCmd(studio,name));
		cmd.addCommand(new ChangeAccCmd(studio,name));
		cmd.addCommand(new ModelScreenCmd());
		cmd.displayCommands();
	}

}
