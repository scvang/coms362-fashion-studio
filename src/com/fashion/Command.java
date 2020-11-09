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
	public void execute();
}

class ListChoices implements Command {
	ListEmployeeOptions LEO;
	ListEventOptions LEvO;

	public ListChoices() {
		LEO = new ListEmployeeOptions();
		LEvO = new ListEventOptions();
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
			} else if (choice.equals("4")) {
				LEvO.execute();
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
}

interface InventoryCommands extends Command {

}

interface ModelCommands extends Command {

}

interface EventCommands extends Command {

}

class ListEventOptions implements EventCommands {
	private ShowingEventCommands SEO;
	private DiningEventCommands DEO;
	private PartyEventCommands PEO;
	private CreateNewEvent CNE;
	private DisplayEvents DE;

	public ListEventOptions() {
		SEO = new ShowingEventCommands();
		DEO = new DiningEventCommands();
		PEO = new PartyEventCommands();
		CNE = new CreateNewEvent();
		DE = new DisplayEvents();
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
			else
				in.nextLine(); // Clear the buffer.
			if (choice.equals("1")) {
				SEO.execute();
			} else if (choice.equals("2")) {
				DEO.execute();
			} else if (choice.equals("3")) {
				PEO.execute();
			} else if (choice.equals("4")) {
				CNE.execute();
			} else if (choice.equals("4")) {
				DE.execute();
			}
		}
		in.close();
	}
}

class ShowingEventCommands implements EventCommands {
	private ShowingSubCommands SSC;
	private Event E;
	private ListEventOptions LEO;

	public ShowingEventCommands() {
		E = new Event();

	}

	@Override
	public void execute() {
		System.out.println("Choose a showing event:");

		int count = 1;
		ArrayList<Showing> showingList = new ArrayList<>();
		for (int i = 0; i < E.getEventList().size(); ++i) {
			if (E.getEventList().get(i) instanceof Showing) {
				System.out.println(count + ") " + E.getEventList().get(i).getEvent());
				++count;
				showingList.add((Showing) E.getEventList().get(i));
			}
		}
		if (showingList.isEmpty()) {
			System.out.println("There are no showings!");
			//LEO.execute();
		}
		Scanner in3 = new Scanner(System.in);
		int i = in3.nextInt();

		String eventName = showingList.get(i - 1).getEvent();
		in3.close();
		SSC = new ShowingSubCommands(eventName);
		SSC.execute();
	}

}

class ShowingSubCommands implements EventCommands {
	private Event E;
	private String eventName = "";

	public ShowingSubCommands(String eventName) {
		this.eventName = eventName;
		E = new Event();
	}

	@Override
	public void execute() {
		String choice = "";
		Scanner in = new Scanner(System.in);
		while (!choice.equals("q")) {
			System.out.println("Select a choice ('q' to exit): \n" + "1) Display available seats \n"
					+ "2) Reserve a seat \n" + "3) Check a seat \n" + "4) Refund \n" + "5) Go back \n");

			choice = in.next();
			if (choice.equals("q") || choice.equals("'q'"))
				break;
			else
				in.nextLine(); // Clear the buffer.

			String name = "";

			if (choice.equals("1")) {
				E.displaySeats(E.getEvent(eventName));
			} else if (choice.equals("2")) {
				if (E.isShowingFull(E.getEvent(eventName))) {
					System.out.println("No available seats.");
					break;
				}
				System.out.println("Enter your customer name: ");
				String customerName = in.nextLine();
				System.out.println("Enter your desired seat (A1~I9): ");
				String seat = in.nextLine();
				System.out.println("Enter your desired date (mm-dd-yy): ");
				String date = in.nextLine();
				System.out.println("Enter your desired time (hh:mm am/pm): ");
				String time = in.nextLine();

				if (E.reserveSeat(E.getEvent(eventName), seat, customerName, date, time)) {
					E.chargeCard(E.getEvent(eventName), customerName);

					// update the database
					// Establish a connection to the database test.
					try {
						// Step 1: "Load" the JDBC driver
						Class.forName("com.mysql.cj.jdbc.Driver");

						// Step 2: Establish the connection to the database
						String url = "jdbc:mysql://localhost/fashion_studio";
						Connection conn = DriverManager.getConnection(url, "root", "");
						// System.out.println("Connected.");

						// create a prepared statement from the connection
						PreparedStatement ps = conn
								.prepareStatement("INSERT INTO showing (name,date,time,seat)" + "VALUES (?,?,?,?)");

						ps.setString(1, customerName);
						ps.setString(2, date);
						ps.setString(3, time);
						ps.setString(4, seat);

						ps.execute();
						conn.close();
					} catch (Exception e) {
						System.err.println(e.getMessage());
					}
					System.out.println("Reservation was added into the database.");
				} else {
					System.out.println("Seat Reservation failed.");
				}
			} else if (choice.equals("3")) {
				System.out.println("Enter your customer name:");
				name = in.nextLine();
				if (E.hasSeatReservation(name, E.getEvent(eventName))) {
					System.out.println("Name: " + E.getSeat(name, E.getEvent(eventName)).getCustomerName() + "\n"
							+ "Date: " + E.getSeat(name, E.getEvent(eventName)).getDate() + "\n" + "Time: "
							+ E.getSeat(name, E.getEvent(eventName)).getTime() + "\n" + "Seat: "
							+ E.getSeat(name, E.getEvent(eventName)).getSeatNum() + "\n");
				} else {
					System.out.println("No reservation found for " + name);
				}
			} else if (choice.equals("4")) {
				System.out.println("Enter the customer name:");
				name = in.nextLine();
				if (E.hasSeatReservation(name, E.getEvent(eventName))) {
					System.out.println("Reservation found.");
					E.removeSeatReservation(name, E.getEvent(eventName));
					System.out.println("Reservation removed.");
				} else {
					System.out.println("Could not find reservaton.");
				}
			}
		}
		in.close();
	}
}

class DiningEventCommands implements EventCommands {
	private Event E;
	private DiningSubCommands DSC;
	private ListEventOptions LEO;
	
	public DiningEventCommands() {
		E = new Event();
	}

	@Override
	public void execute() {

		System.out.println("Choose a dining event:");

		int count = 1;
		ArrayList<Dining> list = new ArrayList<>();
		for (int i = 0; i < E.getEventList().size(); ++i) {
			if (E.getEventList().get(i) instanceof Dining) {
				System.out.println(count + ") " + E.getEventList().get(i).getEvent());
				++count;
				list.add((Dining) E.getEventList().get(i));
			}
		}
		if (list.isEmpty()) {
			System.out.println("There are no dinings!");
			//LEO.execute();
		}
		Scanner in = new Scanner(System.in);
		int i = in.nextInt();

		String eventName = list.get(i - 1).getEvent();
		DSC = new DiningSubCommands(eventName);
		DSC.execute();
		in.close();
	}

}

class DiningSubCommands implements EventCommands {
	private String eventName = "";
	private Event E;

	public DiningSubCommands(String eventName) {
		this.eventName = eventName;
		E = new Event();
	}

	@Override
	public void execute() {
		String choice = "";
		Scanner in = new Scanner(System.in);
		while (!choice.equals("q")) {
			System.out.println("Select a choice ('q' to exit): \n" + "1) Display available tables \n"
					+ "2) Reserve a table \n" + "3) Check a table \n" + "4) Refund \n" + "5) Go back \n");

			choice = in.next();
			if (choice.equals("q") || choice.equals("'q'"))
				break;
			else
				in.nextLine();

			String name = "";
			if (choice.equals("1")) {
				E.displayTables(E.getEvent(eventName));
			} else if (choice.equals("2")) {
				if (E.isDiningFull(E.getEvent(eventName))) {
					System.out.println("There are no available tables.");
					break;
				}
				System.out.println("Enter your customer name: ");
				String customerName = in.nextLine();
				System.out.println("Enter your desired table (1~20): ");
				String table = in.nextLine();
				System.out.println("Enter your desired date (mm-dd-yy): ");
				String date = in.nextLine();
				System.out.println("Enter your desired time (hh:mm am/pm): ");
				String time = in.nextLine();

				if (E.reserveTable(E.getEvent(eventName), table, customerName, date, time)) {
					E.chargeCard(E.getEvent(eventName), customerName);
				} else {
					System.out.println("Table reservation failed.");
				}
			} else if (choice.equals("3")) {
				System.out.println("Enter your customer name:");
				name = in.nextLine();
				if (E.hasTableReservation(name, E.getEvent(eventName))) {
					System.out.println("Name: " + E.getTable(name, E.getEvent(eventName)).getCustomerName() + "\n"
							+ "Date: " + E.getTable(name, E.getEvent(eventName)).getDate() + "\n" + "Time: "
							+ E.getTable(name, E.getEvent(eventName)).getTime() + "\n" + "Table: "
							+ E.getTable(name, E.getEvent(eventName)).getTableNum() + "\n");
				} else {
					System.out.println("No reservation found for " + name);
				}
			} else if (choice.equals("4")) {
				System.out.println("Enter the customer name:");
				name = in.nextLine();
				if (E.hasTableReservation(name, E.getEvent(eventName))) {
					System.out.println("Reservation found.");
					E.removeTableReservation(name, E.getEvent(eventName));
					System.out.println("Reservation removed.");
				} else {
					System.out.println("Could not find reservaton.");
				}
			}
		}
		in.close();
	}
}

class PartyEventCommands implements EventCommands {
	private Event E;
	private ListEventOptions LEO;
	private PartySubCommands PSC;

	public PartyEventCommands() {
		E = new Event();
	}

	@Override
	public void execute() {
		System.out.println("Choose a party event:");

		int count = 1;
		ArrayList<Party> list = new ArrayList<>();
		for (int i = 0; i < E.getEventList().size(); ++i) {
			if (E.getEventList().get(i) instanceof Party) {
				System.out.println(count + ") " + E.getEventList().get(i).getEvent());
				++count;
				list.add((Party) E.getEventList().get(i));
			}
		}
		if (list.isEmpty()) {
			System.out.println("There are no parties!");
			//LEO.execute();
		}
		Scanner in3 = new Scanner(System.in);
		int i = in3.nextInt();

		String eventName = list.get(i - 1).getEvent();
		PSC = new PartySubCommands(eventName);
		PSC.execute();
		in3.close();
	}

}

class PartySubCommands implements EventCommands {
	private String eventName = "";
	private Event E;

	public PartySubCommands(String eventName) {
		this.eventName = eventName;
		E = new Event();
	}

	@Override
	public void execute() {
		String choice = "";
		Scanner in = new Scanner(System.in);
		while (!choice.equals("q")) {
			System.out.println("Select a choice ('q' to exit): \n" + "1) Display number of attendees \n"
					+ "2) Reserve a badge \n" + "3) Check reservation \n" + "4) Refund \n" + "5) Go back \n");

			choice = in.next();
			if (choice.equals("q") || choice.equals("'q'"))
				break;
			else
				in.nextLine();

			String name = "";
			if (choice.equals("1")) {
				System.out
						.println("There are: " + E.getNumOfAttendees(E.getEvent(eventName)) + " number of attendees.");
			} else if (choice.equals("2")) {
				if (E.isPartyFull(E.getEvent(eventName))) {
					System.out.println("The venue is full.");
					break;
				}

				System.out.println("Enter your customer name: ");
				String customerName = in.nextLine();
				System.out.println("Enter your desired date (mm-dd-yy): ");
				String date = in.nextLine();
				System.out.println("Enter your desired time (hh:mm am/pm): ");
				String time = in.nextLine();

				E.reserveBadge(E.getEvent(eventName), customerName, date, time);
			} else if (choice.equals("3")) {
				System.out.println("Enter your customer name:");
				name = in.nextLine();
				if (E.hasBadgeReservation(name, E.getEvent(eventName))) {
					System.out.println("Name: " + E.getBadge(name, E.getEvent(eventName)).getName() + "\n" + "Date: "
							+ E.getBadge(name, E.getEvent(eventName)).getDate() + "\n" + "Time: "
							+ E.getBadge(name, E.getEvent(eventName)).getTime() + "\n");
				} else {
					System.out.println("No reservation found.");
				}
			} else if (choice.equals("4")) {

			}
		}
		in.close();
	}
}

class CreateNewEvent implements EventCommands {
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

}

class DisplayEvents implements EventCommands {
	private Event E;

	public DisplayEvents() {
		E = new Event();
	}

	@Override
	public void execute() {
		E.displayEvents();
	}

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