package com.fashion;

public interface Command {
	public void execute();
}

class ListChoices implements Command {
	
	public ListChoices() {
		
	}
	
	@Override
	public void execute() {
		System.out.println(
		"Select an option ('q' to exit): \n" +
		"1) Employees \n" +
		"2) Inventory \n" +
		"3) Models \n" +
		"4) Events \n" +
		"5) Advertisements \n" +
		"6) Promotions \n" +
		"7) Shop \n" +
		"8) Negotiate Contract\n" +
		"9) Manage Businesses\n"
		);
	}
	
}

interface EmployeeCommands extends Command {

}
class ListEmployeeOptions implements EmployeeCommands {
	
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
	}
	
}
interface InventoryCommands extends Command {
	
}

interface ModelCommands extends Command {
	
}

interface EventCommands extends Command {
	
}

interface AdvertismentCommands extends Command {
	
}

interface PromotionCommands extends Command {
	
}

interface ShopCommands extends Command {
	
}

interface ContractCommands extends Command {
	
}

interface BusinessCommands extends Command {
	
}