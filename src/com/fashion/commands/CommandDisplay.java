package com.fashion.commands;

import java.util.ArrayList;
import java.util.Scanner;

public class CommandDisplay {
	private ArrayList<Command> commandList = new ArrayList<>();
	
	public void addCommand(Command c) {
		commandList.add(c);
	}
	
	public void displayCommands() {
		for (int i = 0; i < commandList.size(); ++i) {
			System.out.println(i + 1 + ": " + commandList.get(i).getDescription());
		}
		
		Scanner in = new Scanner(System.in);
		int next = in.nextInt();
		commandList.get(next - 1).execute();
	}
}
