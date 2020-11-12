package com.fashion;

import java.util.ArrayList;
import java.util.Scanner;

public class CommandDisplay {

	private ArrayList<Command> commandList = new ArrayList<>();
	private ArrayList<Command> subEmpCommands = new ArrayList<>();
	private ArrayList<Command> manageCommands = new ArrayList<>();
	private ArrayList<Command> subEventCommands = new ArrayList<>();
	private ArrayList<Command> subShowEventCommands = new ArrayList<>();
	private ArrayList<Command> subDiningEventCommands = new ArrayList<>();
	private ArrayList<Command> subPartyEventCommands = new ArrayList<>();

	public CommandDisplay() {
	}

	public void addCommand(Command c) {
		commandList.add(c);
	}

	public void addSubEmp(Command c) {
		subEmpCommands.add(c);
	}
	
	public void addManageCommands(Command c) {
		manageCommands.add(c);
	}

	public void addSubEvent(Command c) {
		subEventCommands.add(c);
	}

	public void addSubShowEvent(Command c) {
		subShowEventCommands.add(c);
	}

	public void addSubDiningEvent(Command c) {
		subDiningEventCommands.add(c);
	}

	public void addSubPartyEvent(Command c) {
		subPartyEventCommands.add(c);
	}

	public void displaycommands() {
		for (int i = 0; i < commandList.size(); i++) {
			System.out.println(i + 1 + ": " + commandList.get(i).getDescription());
		}
		Scanner in = new Scanner(System.in);
		int next = in.nextInt();
		commandList.get(next - 1).execute();
		if (commandList.get(next - 1).getDescription().equals("List Employee Commands")) {
			for (int i = 0; i < subEmpCommands.size(); i++) {
				System.out.println(i + 1 + ": " + subEmpCommands.get(i).getDescription());
			}
			int empNext = in.nextInt();
			subEmpCommands.get(empNext - 1).execute();
			if (subEmpCommands.get(next - 1).getDescription().equals("Go to management screen")) {
				for (int i = 0; i < manageCommands.size(); i++) {
					System.out.println(i + 1 + ": " + manageCommands.get(i).getDescription());
				}
				int manNext = in.nextInt();
				manageCommands.get(manNext - 1).execute();
			} else if (commandList.get(next - 1).getDescription().equals("List Event Commands")) {
				for (int i = 0; i < subEventCommands.size(); i++) {
					System.out.println(i + 1 + ": " + subEventCommands.get(i).getDescription());
				}
				int eventNext = in.nextInt();
				subEventCommands.get(eventNext - 1).execute();
				if (subEventCommands.get(next - 1).getDescription().equals("List Showing Event Commands")) {
					for (int i = 0; i < subShowEventCommands.size(); i++) {
						System.out.println(i + 1 + ": " + subShowEventCommands.get(i).getDescription());
					}
					int showEventNext = in.nextInt();
					subShowEventCommands.get(showEventNext - 1).execute();
				} else if (subEventCommands.get(next - 1).getDescription().equals("List Dining Event Commands")) {
					for (int i = 0; i < subDiningEventCommands.size(); i++) {
						System.out.println(i + 1 + ": " + subDiningEventCommands.get(i).getDescription());
					}
					int diningEventNext = in.nextInt();
					subDiningEventCommands.get(diningEventNext - 1).execute();
				} else if (subEventCommands.get(next - 1).getDescription().equals("List Party Event Commands")) {
					for (int i = 0; i < subPartyEventCommands.size(); i++) {
						System.out.println(i + 1 + ": " + subPartyEventCommands.get(i).getDescription());
					}
					int partyEventNext = in.nextInt();
					subPartyEventCommands.get(partyEventNext - 1).execute();
				} else {
					for (int i = 0; i < commandList.size(); i++) {
						System.out.println(i + 1 + ": " + commandList.get(i).getDescription());
					}
				}
				in.close();
			}
		}

	}
}
