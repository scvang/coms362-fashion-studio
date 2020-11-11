package com.fashion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Scanner;
import com.fashion.employees.HumanResources;
import com.fashion.events.Dining;
import com.fashion.events.Event;
import com.fashion.events.Party;
import com.fashion.events.Showing;
import com.fashion.pay.PayStubInfo;

public interface Command {
	public String getDescription();
	public void execute();
}

/**
class ListChoices implements Command {

	public ListChoices() {

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
		}
		in.close();
	}

	@Override
	public String getDescription() {
		return "Display Initial Choices";
	}
}
**/

class ListEmployeeOptions implements Command {

	public ListEmployeeOptions() {

	}

	@Override
	public void execute() {

	}

	@Override
	public String getDescription() {
		return "List Employee Commands";
	}
}

class ViewEmployees implements Command {
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

	@Override
	public String getDescription() {
		return "View Current Employees";
	}
}

class PayEmployee implements Command {
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
		in2.close();
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

	@Override
	public String getDescription() {
		return "Pay Employee";
	}
}


class ListEventOptions implements Command {

	public ListEventOptions() {

	}

	@Override
	public void execute() {
		String choice = "";
		Scanner in = new Scanner(System.in);
		while (!choice.equals("q")) {
			System.out.println("Select an event ('q' to exit): \n" + "1) Showing \n" + "2) Dining \n" + "3) Party \n"
					+ "4) Create new Event \n" + "5) Display events \n" + "6) Go back");

			choice = in.next();
			if (choice.equals("q") || choice.equals("'q'"))
				break;
		}
		in.close();
	}

	@Override
	public String getDescription() {
		return "List Event Commands";
	}
}

class ShowingEventCommands implements Command {
	
	public ShowingEventCommands() {
		
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return "List SHowing Commands";
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
	}
	
}

class DiningEventCommands implements Command {
	
	public DiningEventCommands() {
		
	}

	@Override
	public void execute() {

	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}

}

class PartyEventCommands implements Command {
	
	public PartyEventCommands() {

	}

	@Override
	public void execute() {
		
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}

}

class CreateNewEvent implements Command {
	private Event E;

	public CreateNewEvent() {
		E = new Event();
	}

	@Override
	public void execute() {
		Scanner in = new Scanner(System.in);
		System.out.println("What type of event (showing, dining, party)? ");
		String type = in.nextLine();

		System.out.println("Event name?");
		String name = in.nextLine();

		System.out.println("What date (mm-dd-yy)? ");
		String date = in.nextLine();

		System.out.println("What time (hh:mm am/pm)? ");
		String time = in.nextLine();

		E.createEvent(type, name, date, time);
		in.close();
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return "Create New Event";
	}

}

class DisplayEvents implements Command {
	private Event E;

	public DisplayEvents() {
		E = new Event();
	}

	@Override
	public void execute() {
		E.displayEvents();
	}

	@Override
	public String getDescription() {
		return "Display Current Events";
	}

}