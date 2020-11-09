package com.fashion;

import java.util.Scanner;

import com.fashion.employees.HumanResources;
import com.fashion.pay.PayStubInfo;

public interface Command {
	public void execute();
}

class ListChoices implements Command {
	ListEmployeeOptions LEO;

	public ListChoices() {
		LEO = new ListEmployeeOptions();
	}

	@Override
	public void execute() {
		Scanner in = new Scanner(System.in);
		String choice = "";
		while (!choice.equals("q")) {
			System.out.println("Select an option ('q' to exit): \n" + "1) Employees \n" + "2) Inventory \n"
					+ "3) Models \n" + "4) Events \n" + "5) Advertisements \n" + "6) Promotions \n" + "7) Shop \n"
					+ "8) Negotiate Contract\n" + "9) Manage Businesses\n");

			choice = in.next();
			if (choice.equals("1")) {
				LEO.execute();
				break;
			}
		}
		in.close();
	}
}

interface EmployeeCommands extends Command {

}

class ListEmployeeOptions implements EmployeeCommands {
	ViewEmployees VE;
	PayEmployee PE;

	public ListEmployeeOptions() {
		VE = new ViewEmployees();
		PE = new PayEmployee();
	}

	@Override
	public void execute() {
		Scanner in = new Scanner(System.in);
		String choice = "";
		while (!choice.contentEquals("q")) {
			System.out.println("Select an event ('q' to exit): \n" + "1) View Employees \n" + "2) Pay Employee \n"
					+ "3) Go back \n");
			choice = in.next();
			if (choice.equals("1")) {
				VE.execute();
			} else if (choice.equals("2")) {
				PE.execute();
			}
		}
		in.close();
	}
}

class ViewEmployees implements EmployeeCommands {
	HumanResources HR;

	public ViewEmployees() {
		HR = new HumanResources();
	}

	@Override
	public void execute() {
		HR.getEmployees();
		System.out.println();
		System.out.println();
	}
}

class PayEmployee implements EmployeeCommands {
	HumanResources HR;
/**
 * @author Chad Morrow
 */
	public PayEmployee() {
		HR = new HumanResources();
	}

	@Override
	public void execute() {
		Scanner in2 = new Scanner(System.in);
		System.out.println("Enter the employee id ('q' to exit): ");
		int eid = -1;
		while (eid == -1) {
			String temp = in2.next();
			try {
				eid = Integer.parseInt(temp);
			} catch (NumberFormatException e) {
				System.out.println("Employee id must be a number ('q' to exit): ");
			}
		}

		PayStubInfo payStubInfo = HR.getEmployee(eid).getPayStubInfo();

		System.out.println("Did this employee recieve a bonus? (y/n) ('q' to exit)");
		String yesno = in2.next();

		double bonus = -1;
		if (yesno.equals("y")) {
			System.out.println("How much did they recieve? ('q' to exit)");
			while (bonus == -1) {
				String temp = in2.next();
				try {
					bonus = Double.parseDouble(temp);
				} catch (NumberFormatException e) {
					System.out.println("Bonus must be a number ('q' to exit): ");
				}
			}
		}
		payStubInfo.setBonus(bonus);

		if (HR.payEmployee(eid, payStubInfo)) {
			System.out.println(HR.getEmployee(eid).getName() + " was paid!");
		} else {
			System.out.println("Error paying employee, try again later");
		}
		System.out.println();
		System.out.println();

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