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
	private ArrayList<Command> subShopCommands = new ArrayList<>();
	private ArrayList<Command> subInventCommands = new ArrayList<>();
	private ArrayList<Command> subModCommands = new ArrayList<>();
	private ArrayList<Command> subAdCommands = new ArrayList<>();
	private ArrayList<Command> subPromoCommands = new ArrayList<>();
	private ArrayList<Command> subContractCommands = new ArrayList<>();
	private ArrayList<Command> subBusCommands = new ArrayList<>();

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
	
	public void addSubShopCommands(Command c) {
		subShopCommands.add(c);
	}
	
	public void addSubInventCommands(Command c) {
		subInventCommands.add(c);
	}
	
	public void addSubModCommands(Command c) {
		subModCommands.add(c);
	}
	
	public void addSubAdCommands(Command c) {
		subAdCommands.add(c);
	}
	
	public void addSubPromoCommands(Command c) {
		subPromoCommands.add(c);
	}
	
	public void addSubContractCommands(Command c) {
		subContractCommands.add(c);
	}
	
	public void subBusinessCommands(Command c) {
		subBusCommands.add(c);
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
			} 
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
			} else if(commandList.get(next - 1).getDescription().equals("List Shopping Commands")) {
				for (int i = 0; i < subShopCommands.size(); i++) {
					System.out.println(i + 1 + ": " + subShopCommands.get(i).getDescription());
				}
				int shopNext = in.nextInt();
				subShopCommands.get(shopNext - 1).execute();
			} else if(commandList.get(next - 1).getDescription().equals("List Inventory Commands")) {
				for (int i = 0; i < subInventCommands.size(); i++) {
					System.out.println(i + 1 + ": " + subInventCommands.get(i).getDescription());
				}
				int inventNext = in.nextInt();
				subShopCommands.get(inventNext - 1).execute();
			}else if(commandList.get(next - 1).getDescription().equals("List Model Commands")) {
				for (int i = 0; i < subModCommands.size(); i++) {
					System.out.println(i + 1 + ": " + subModCommands.get(i).getDescription());
				}
				int modNext = in.nextInt();
				subModCommands.get(modNext - 1).execute();
			} else if(commandList.get(next - 1).getDescription().equals("List Advertisment Commands")) {
				for (int i = 0; i < subAdCommands.size(); i++) {
					System.out.println(i + 1 + ": " + subAdCommands.get(i).getDescription());
				}
				int adNext = in.nextInt();
				subAdCommands.get(adNext - 1).execute();
			} else if(commandList.get(next - 1).getDescription().equals("List Promotion Commands")) {
				for (int i = 0; i < subPromoCommands.size(); i++) {
					System.out.println(i + 1 + ": " + subPromoCommands.get(i).getDescription());
				}
				int promoNext = in.nextInt();
				subPromoCommands.get(promoNext - 1).execute();
			} else if(commandList.get(next - 1).getDescription().equals("List Contract Commands")) {
				for (int i = 0; i < subContractCommands.size(); i++) {
					System.out.println(i + 1 + ": " + subContractCommands.get(i).getDescription());
				}
				int conNext = in.nextInt();
				subContractCommands.get(conNext - 1).execute();
			} else if(commandList.get(next - 1).getDescription().equals("List Business Commands")) {
				for (int i = 0; i < subBusCommands.size(); i++) {
					System.out.println(i + 1 + ": " + subBusCommands.get(i).getDescription());
				}
				int busNext = in.nextInt();
				subBusCommands.get(busNext - 1).execute();
			}
		in.close();
		}
	}
