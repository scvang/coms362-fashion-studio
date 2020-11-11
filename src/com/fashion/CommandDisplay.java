package com.fashion;

import java.util.ArrayList;
import java.util.Scanner;

public class CommandDisplay {

	private ArrayList<Command> commandList = new ArrayList<>();
	private ArrayList<Command> subEmpCommands = new ArrayList<>();
			
	public CommandDisplay() {
	}
	
	public void addCommand(Command c) {
		commandList.add(c);
	}
	public void addSubEmp(Command c) {
		subEmpCommands.add(c);
	}
	
	public void displaycommands() {
		for (int i = 0; i < commandList.size(); i++) {
			System.out.println(i+1+": "+ commandList.get(i).getDescription());
		}
		Scanner in = new Scanner(System.in);
		int next = in.nextInt();
		commandList.get(next-1).execute();
		if(commandList.get(next-1).getDescription().equals("List Employee Commands")){
			for (int i = 0; i < subEmpCommands.size(); i++) {
				System.out.println(i+1+": "+ subEmpCommands.get(i).getDescription());
			}
			int empNext = in.nextInt();
			subEmpCommands.get(empNext).execute();
		}
		in.close();
	}
	
	
	
	
	
}
