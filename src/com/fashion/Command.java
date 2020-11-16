package com.fashion;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFileChooser;

import com.fashion.apparel.Apparel;
import com.fashion.employees.EmployeeSession;
import com.fashion.employees.HumanResources;
import com.fashion.events.Dining;
import com.fashion.events.Event;
import com.fashion.events.Party;
import com.fashion.events.Showing;
import com.fashion.pay.PayStubInfo;
import com.fashion.shopping.ShoppingSession;

public interface Command {
	public String getDescription();

	public void execute();
}

/**
 * class ListChoices implements Command {
 * 
 * public ListChoices() {
 * 
 * }
 * 
 * @Override public void execute() { Scanner in = new Scanner(System.in); String
 *           choice = ""; while (!choice.equals("q")) {
 *           System.out.println("Select an option ('q' to exit): \n" + "1)
 *           Employees \n" + "2) Inventory \n" + "3) Models \n" + "4) Events \n"
 *           + "5) Advertisements \n" + "6) Promotions \n" + "7) Shop \n" + "8)
 *           Negotiate Contract\n" + "9) Manage Businesses\n");
 * 
 *           choice = in.next(); } in.close(); }
 * 
 * @Override public String getDescription() { return "Display Initial Choices";
 *           } }
 **/

class ListEmployeeOptions implements Command {
	EmployeeSession employeeSession1 = new EmployeeSession();

	public ListEmployeeOptions() {

	}

	@Override
	public void execute() {

	}

	@Override
	public String getDescription() {
		return "List Employee Commands";
	}
	
	public EmployeeSession getSes() {
		return employeeSession1;
	}
}

class ViewEmployees implements Command {
	ListEmployeeOptions LEO = new ListEmployeeOptions();

	@Override
	public void execute() {
		LEO.getSes().viewEmployees();
		LEO.getSes().displayHeadshot();
	}

	@Override
	public String getDescription() {
		return "View Current Employees";
	}
}

class PayEmployee implements Command {
	HumanResources HR = new HumanResources();

	/**
	 * @author Chad Morrow
	 */
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

class Management implements Command {
	String username;
	String password;
	
	public Management() {
		
	}

	@Override
	public String getDescription() {
		return "Go to management screen";
	}

	@Override
	public void execute() {
		Scanner in3 = new Scanner (System.in);

		System.out.println("Username: ");
		username = in3.next();
		System.out.println("Password: ");
		password = in3.next();
		
		in3.close();
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}
	
}

class HireEmployee implements Command {
	Management M = new Management();
	ListEmployeeOptions LEO = new ListEmployeeOptions();

	@Override
	public String getDescription() {
		return "Hire an employee";
	}

	@Override
	public void execute() {
		if(LEO.getSes().getAccessRights(M.getUsername(), M.getPassword())){
			LEO.getSes().hireEmployee();
		}
	}
	
}

class FireEmployee implements Command {
	Management M = new Management();
	ListEmployeeOptions LEO = new ListEmployeeOptions();

	@Override
	public String getDescription() {
		return "Fire an employee";
	}

	@Override
	public void execute() {
		if(LEO.getSes().getAccessRights(M.getUsername(), M.getPassword())){
			LEO.getSes().fireEmployee();
		}
	}
	
}

class ListEventOptions implements Command {
	Event E;
	
	public ListEventOptions() {
		E = new Event();
	}

	@Override
	public void execute() {

	}

	@Override
	public String getDescription() {
		return "List Event Commands";
	}
	
	public Event getEvent() {
		return E;
	}
}

class ShowingEventCommands implements Command {
	ListEventOptions LEO = new ListEventOptions();
	String eventName;

	public ShowingEventCommands() {

	}

	@Override
	public String getDescription() {
		return "List Showing Commands";
	}

	@Override
	public void execute() {
		System.out.println("Choose a showing event:");
		Scanner in3 = new Scanner(System.in);
		eventName = in3.next();
		int count = 1;
		ArrayList<Showing> showingList = new ArrayList<>();
		for (int i = 0; i < LEO.getEvent().getEventList().size(); ++i) {
			if (LEO.getEvent().getEventList().get(i) instanceof Showing) {
				System.out.println(count + ") " + LEO.getEvent().getEventList().get(i).getEvent());
				++count;
				showingList.add((Showing) LEO.getEvent().getEventList().get(i));
			}
		}
		if (showingList.isEmpty()) {
			System.out.println("There are no showings!");
		}
		int i = in3.nextInt();
		eventName = showingList.get(i - 1).getEvent();

		in3.close();
	}
	
	public Event getEvent() {
		return LEO.getEvent();
	}

	public String getName() {
		return eventName;
	}

}

class DisplaySeat implements Command {
	Event E = new Event();
	ShowingEventCommands SEC = new ShowingEventCommands();

	@Override
	public String getDescription() {
		return "Display available seats";
	}

	@Override
	public void execute() {
		SEC.getEvent().displaySeats(SEC.getEvent().getEvent(SEC.getName()));

	}

}

class ReserveSeat implements Command {
	Event E = new Event();
	ShowingEventCommands SEC = new ShowingEventCommands();

	@Override
	public String getDescription() {
		return "Reserve a seat";
	}

	@Override
	public void execute() {
		if (E.isShowingFull(E.getEvent(SEC.getName()))) {
			System.out.println("No available seats.");
		}
		Scanner in = new Scanner(System.in);
		System.out.println("Enter your customer name: ");
		String customerName = in.nextLine();
		System.out.println("Enter your desired seat (A1~I9): ");
		String seat = in.nextLine();
		System.out.println("Enter your desired date (mm-dd-yy): ");
		String date = in.nextLine();
		System.out.println("Enter your desired time (hh:mm am/pm): ");
		String time = in.nextLine();

		in.close();

		if (E.reserveSeat(E.getEvent(SEC.getName()), seat, customerName, date, time)) {
			E.chargeCard(E.getEvent(SEC.getName()), customerName);

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
	}
}

class CheckSeat implements Command {
	Event E = new Event();
	ShowingEventCommands SEC = new ShowingEventCommands();

	@Override
	public String getDescription() {
		return "Check a seat";
	}

	@Override
	public void execute() {
		System.out.println("Enter your customer name:");
		Scanner in = new Scanner(System.in);
		String name = in.nextLine();
		if (E.hasSeatReservation(name, E.getEvent(SEC.getName()))) {
			System.out.println("Name: " + E.getSeat(name, E.getEvent(SEC.getName())).getCustomerName() + "\n" + "Date: "
					+ E.getSeat(name, E.getEvent(SEC.getName())).getDate() + "\n" + "Time: "
					+ E.getSeat(name, E.getEvent(SEC.getName())).getTime() + "\n" + "Seat: "
					+ E.getSeat(name, E.getEvent(SEC.getName())).getSeatNum() + "\n");
		} else {
			System.out.println("No reservation found for " + name);
		}
		in.close();
	}

}

class Refund implements Command {
	Event E = new Event();
	ShowingEventCommands SEC = new ShowingEventCommands();

	@Override
	public String getDescription() {
		return "Provide a refund";
	}

	@Override
	public void execute() {
		System.out.println("Enter the customer name:");
		Scanner in = new Scanner(System.in);
		String name = in.nextLine();

		if (E.hasSeatReservation(name, E.getEvent(SEC.getName()))) {
			System.out.println("Reservation found.");
			E.removeSeatReservation(name, E.getEvent(SEC.getName()));
			System.out.println("Reservation removed.");
		} else {
			System.out.println("Could not find reservaton.");
		}
		in.close();
	}

}

class DiningEventCommands implements Command {
	Event E = new Event();
	String eventName;

	public DiningEventCommands() {

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
		}
		Scanner in = new Scanner(System.in);
		int i = in.nextInt();
		eventName = list.get(i - 1).getEvent();
		in.close();
	}

	@Override
	public String getDescription() {
		return "List Dining Event Commands";
	}

	public String getName() {
		return eventName;
	}
	
	public Event getEvent() {
		return E;
	}

}

class DisplayTable implements Command {
	DiningEventCommands DEC = new DiningEventCommands();

	@Override
	public String getDescription() {
		return "Display Available Table";
	}

	@Override
	public void execute() {
		DEC.getEvent().displayTables(DEC.getEvent().getEvent(DEC.getName()));
	}

}

class ReserveTable implements Command {
	Event E = new Event();
	DiningEventCommands DEC = new DiningEventCommands();

	@Override
	public String getDescription() {
		return "Reserve a table";
	}

	@Override
	public void execute() {
		if (E.isDiningFull(E.getEvent(DEC.getName()))) {
			System.out.println("There are no available tables.");
		}
		Scanner in = new Scanner(System.in);
		System.out.println("Enter your customer name: ");
		String customerName = in.nextLine();
		System.out.println("Enter your desired table (1~20): ");
		String table = in.nextLine();
		System.out.println("Enter your desired date (mm-dd-yy): ");
		String date = in.nextLine();
		System.out.println("Enter your desired time (hh:mm am/pm): ");
		String time = in.nextLine();

		if (E.reserveTable(E.getEvent(DEC.getName()), table, customerName, date, time)) {
			E.chargeCard(E.getEvent(DEC.getName()), customerName);
		} else {
			System.out.println("Table reservation failed.");
		}
		in.close();
	}

}

class CheckTable implements Command {
	Event E = new Event();
	DiningEventCommands DEC = new DiningEventCommands();

	@Override
	public String getDescription() {
		return "Check a table";
	}

	@Override
	public void execute() {
		System.out.println("Enter your customer name:");
		Scanner in = new Scanner(System.in);
		String name = in.nextLine();
		if (E.hasTableReservation(name, E.getEvent(DEC.getName()))) {
			System.out.println("Name: " + E.getTable(name, E.getEvent(DEC.getName())).getCustomerName() + "\n"
					+ "Date: " + E.getTable(name, E.getEvent(DEC.getName())).getDate() + "\n" + "Time: "
					+ E.getTable(name, E.getEvent(DEC.getName())).getTime() + "\n" + "Table: "
					+ E.getTable(name, E.getEvent(DEC.getName())).getTableNum() + "\n");
		} else {
			System.out.println("No reservation found for " + name);
		}
		in.close();
	}
}

class RefundTable implements Command {
	Event E = new Event();
	DiningEventCommands DEC = new DiningEventCommands();

	@Override
	public String getDescription() {
		return "Refund Table";
	}

	@Override
	public void execute() {
		System.out.println("Enter the customer name:");
		Scanner in = new Scanner(System.in);
		String name = in.nextLine();
		if (E.hasTableReservation(name, E.getEvent(DEC.getName()))) {
			System.out.println("Reservation found.");
			E.removeTableReservation(name, E.getEvent(DEC.getName()));
			System.out.println("Reservation removed.");
		} else {
			System.out.println("Could not find reservaton.");
		}
		in.close();
	}

}

class PartyEventCommands implements Command {
	Event E = new Event();
	String eventName;

	public PartyEventCommands() {

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
		}
		Scanner in3 = new Scanner(System.in);
		int i = in3.nextInt();
		eventName = list.get(i - 1).getEvent();
		in3.close();
	}

	@Override
	public String getDescription() {
		return "List Party Event Commands";
	}

	public String getName() {
		return eventName;
	}
}

class DisplayAttendees implements Command {
	Event E = new Event();
	PartyEventCommands PEC = new PartyEventCommands();

	@Override
	public String getDescription() {
		return "Display number of attendees";
	}

	@Override
	public void execute() {
		System.out.println("There are: " + E.getNumOfAttendees(E.getEvent(PEC.getName())) + " number of attendees.");
	}

}

class ReserveBadge implements Command {
	Event E = new Event();
	PartyEventCommands PEC = new PartyEventCommands();

	@Override
	public String getDescription() {
		return "Reserve a badge";
	}

	@Override
	public void execute() {
		if(E.isPartyFull(E.getEvent(PEC.getName()))) {
			System.out.println("The venue is full.");
		}
		Scanner in = new Scanner(System.in);
		System.out.println("Enter your customer name: ");
		String customerName = in.nextLine();
		System.out.println("Enter your desired date (mm-dd-yy): ");
		String date = in.nextLine();
		System.out.println("Enter your desired time (hh:mm am/pm): ");
		String time = in.nextLine();
		
		E.reserveBadge(E.getEvent(PEC.getName()),customerName,date,time);
		in.close();
	}
}

class CheckBadge implements Command {
	Event E = new Event();
	PartyEventCommands PEC = new PartyEventCommands();

	@Override
	public String getDescription() {
		return "Check a badge";
	}

	@Override
	public void execute() {
		System.out.println("Enter your customer name:");
		Scanner in = new Scanner(System.in);
		String name = in.nextLine();
		if(E.hasBadgeReservation(name,E.getEvent(PEC.getName()))) {
			System.out.println(
					"Name: " + E.getBadge(name,E.getEvent(PEC.getName())).getName() + "\n" +
					"Date: " + E.getBadge(name,E.getEvent(PEC.getName())).getDate() + "\n" + 
					"Time: " + E.getBadge(name,E.getEvent(PEC.getName())).getTime() + "\n"
					);
		}
		else {
			System.out.println("No reservation found.");
		}
		in.close();
	}

}

class RefundBadge implements Command {

	@Override
	public String getDescription() {
		return "Refund a badge";
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
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

class ListShoppingOptions implements Command {
	ShoppingSession shoppingSession = new ShoppingSession();
	
	public ListShoppingOptions() {

	}
	
	@Override
	public String getDescription() {
		return "List Shopping Commands";
	}

	@Override
	public void execute() {
		shoppingSession.initSessionId();
	}
	
	public ShoppingSession getShop() {
		return shoppingSession;
	}
	
}

class ApparelInfo implements Command {
	ListShoppingOptions LSO = new ListShoppingOptions();
	
	@Override
	public String getDescription() {
		return "Display apparel information";
	}

	@Override
	public void execute() {
		System.out.println();
		LSO.getShop().displayApparel();
		System.out.println();
	}
	
}

class ShowApparel implements Command {
	ListShoppingOptions LSO = new ListShoppingOptions();
	String itemName;
	
	public ShowApparel() {
		
	}

	@Override
	public String getDescription() {
		return "Show Apparel";
	}

	@Override
	public void execute() {
		System.out.println();
		Scanner in2 = new Scanner (System.in);
		System.out.println("What apparel item would you like to view? ('q' to exit): ");
		itemName = in2.nextLine().trim();
		if(itemName.equals("q")) {
			System.out.println();
		}
		LSO.getShop().apparelImage(itemName);
		System.out.println();
		in2.close();
	}
	
	public String getItemName() {
		return itemName;
	}
	
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
}

class AddCart implements Command {
	ListShoppingOptions LSO = new ListShoppingOptions();
	ShowApparel SA = new ShowApparel();

	@Override
	public String getDescription() {
		return "Add to cart";
	}

	@Override
	public void execute() {
		System.out.println();
		Scanner in3 = new Scanner (System.in);

		System.out.println("What item would you like to add to your cart? ('q' to exit): ");
		SA.setItemName(in3.nextLine().trim());
		if(SA.getItemName().equals("q")) {
			System.out.println();
		}

		System.out.println("What size? ('q' to exit): ");
		String size = in3.nextLine().trim();
		if(size.equals("q")) {
			System.out.println();
		}
		LSO.getShop().getCart().addItem(new Apparel(size, SA.getItemName()));
		System.out.println();	
		in3.close();
	}
	
}

class GoCart implements Command {
	ListShoppingOptions LSO = new ListShoppingOptions();
	ShowApparel SA = new ShowApparel();

	@Override
	public String getDescription() {
		return "Go to cart";
	}

	@Override
	public void execute() {
		System.out.println(LSO.getShop().getCart().toString());
		System.out.println();

		Scanner in4 = new Scanner (System.in);

		if(!LSO.getShop().getCart().getItems().isEmpty()) {
			System.out.println("Edit Cart? (y/n): ");

			String response = in4.nextLine().trim();
			if (response.equals("y")) {
				System.out.println("What item would you like to remove from your cart? ('q' to exit): ");
				SA.setItemName(in4.nextLine().trim());
				if(SA.getItemName().equals("q")) {
					System.out.println();
				}

				System.out.println("What size? ('q' to exit): ");
				String size = in4.nextLine().trim();
				if(size.equals("q")) {
					System.out.println();
				}
				LSO.getShop().getCart().removeItem(new Apparel(size, SA.getItemName()));

				if(LSO.getShop().getCart().getItems().size() == 0)
					System.out.println();
			} else if (response.equals("n")) {
				System.out.println();
			}

			System.out.println("Checkout? (y/n): ");
			response = in4.nextLine().trim();
			if (response.equals("y")) {
				System.out.println("Card Number: ");
				response = in4.nextLine().trim();
				LSO.getShop().getCard().setCardNum(response);

				System.out.println("Card Exp Month: ");
				response = in4.nextLine().trim();
				LSO.getShop().getCard().setEndMonth(response);

				System.out.println("Card Exp Year: ");
				response = in4.nextLine().trim();
				LSO.getShop().getCard().setEndYear(response);

				System.out.println("Card Security Code: ");
				response = in4.nextLine().trim();
				LSO.getShop().getCard().setCode(response);

				System.out.println("Shipping Address: ");
				response = in4.nextLine().trim();
				LSO.getShop().setShippingAddress(response);

				System.out.println("Billing Address: ");
				response = in4.nextLine().trim();
				LSO.getShop().getCard().setBillingAddress(response);
				LSO.getShop().setBillingAddress(response);

				System.out.println("Purchase? (y/n): ");
				response = in4.nextLine().trim();
				if (response.equals("y")) {
					LSO.getShop().updateInventory();
					LSO.getShop().getCart().clearCart();
					System.out.println();
				} else if (response.equals("n")) {
					System.out.println();
				}
			} else if (response.equals("n")) {
				System.out.println();
			}
			in4.close();
		}
	}
	
}

class ProcessRefund implements Command {
	ListShoppingOptions LSO = new ListShoppingOptions();
	
	@Override
	public String getDescription() {
		return "Process a refund";
	}

	@Override
	public void execute() {
		if(LSO.getShop().displayRefundOrders()){
			Scanner in5 = new Scanner (System.in);

			System.out.println("What would you like to return? (enter id number): ");
			String responseRefund = in5.nextLine().trim();
			int refundShoppingSession = Integer.parseInt(responseRefund);
			LSO.getShop().getRefundCart().addItem(refundShoppingSession);
			System.out.println();

			System.out.println("Please select an image for verification: ");
			JFileChooser jfc = new JFileChooser();
			jfc.showDialog(null,"Please Select the File");
			jfc.setVisible(true);
			File image = jfc.getSelectedFile();
			System.out.println(image.getName());

			LSO.getShop().pushRefund(image, refundShoppingSession);
			in5.close();
		} else {
			System.out.println("No eligible items to return :(\n");
		}
	}
	
}

class ViewRefund implements Command {
	ListShoppingOptions LSO = new ListShoppingOptions();

	@Override
	public String getDescription() {
		return "View last refund";
	}

	@Override
	public void execute() {
		LSO.getShop().viewLastRefund();
		System.out.println();
	}
	
}

class ListInventoryOptions implements Command {
	static Studio studio = new Studio();
	
	@Override
	public String getDescription() {
		return "List Inventory Commands";
	}
	
	public static Studio getStud() {
		return studio;
	}

	@Override
	public void execute() {
		int id = 0;
		String size = "";
		int price = 0;
		String itemName = "";
		String brandName = "";
		String color = ""; 
		int quantity = 0;
		
		// This retrieves a clothing list from the database.
		// Establish a connection to the database to query data.
		try{
	      // Step 1: "Load" the JDBC driver
			Class.forName("com.mysql.cj.jdbc.Driver");

	      // Step 2: Establish the connection to the database 
	      String url = "jdbc:mysql://localhost/fashion_studio"; 
	      Connection conn = DriverManager.getConnection(url,"root","");
	      //System.out.println("Connected.");
	      
	      // create a Statement from the connection
	      Statement st = conn.createStatement();
	      
	      // query the data
	      ResultSet rs = st.executeQuery("SELECT * FROM inventory2");
	      
	      studio.resetInventory();
	      while(rs.next()) {
	    	  id = rs.getInt("id");
	    	  size = rs.getString("size");
	    	  price = rs.getInt("price");
	    	  itemName = rs.getString("itemName");
	    	  brandName = rs.getString("brandName");
	    	  color = rs.getString("color");
	    	  quantity = rs.getInt("quantity");
	    	  
	    	  studio.storeClothingItem(new Apparel(id,size,price,itemName,brandName,color,quantity));
	      }
	      // close the connection.
	      st.close();
	    } catch (Exception e){
		      System.err.println(e.getMessage()); 
		    }
	}
}

class ViewClothes implements Command {

	@Override
	public String getDescription() {
		return "View clothing listing";
	}

	@Override
	public void execute() {
		ListInventoryOptions.getStud().displayClothingInventory();
	}
	
}

class ViewMakeup implements Command {

	@Override
	public String getDescription() {
		return "View makeup listing";
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
	}
	
}

class ViewFood implements Command {

	@Override
	public String getDescription() {
		return "View food listing";
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
	}
	
}

class StoreCLothes implements Command {

	@Override
	public String getDescription() {
		return "Store clothing item";
	}

	@Override
	public void execute() {
		Scanner in = new Scanner(System.in);
		int id = 0;
		String size = "";
		int price = 0;
		String itemName = "";
		String brandName = "";
		String color = ""; 
		int quantity = 0;
		
		System.out.println("Enter the id:");
		id = in.nextInt(); in.nextLine();
		System.out.println("Enter the size:");
		size = in.nextLine();
		System.out.println("Enter the price:");
		price = in.nextInt(); in.nextLine();
		System.out.println("Enter the item name:");
		itemName = in.nextLine();
		System.out.println("Enter the brand name:");
		brandName = in.nextLine();
		System.out.println("Enter the color:");
		color = in.nextLine();
		System.out.println("Enter the quantity:");
		quantity = in.nextInt(); in.nextLine();
		
		ListInventoryOptions.getStud().resetInventory();
		//studio.storeClothingItem(new Apparel(itemName,brandName,color));
		
		 // Establish a connection to the database test.
		try{
	      // Step 1: "Load" the JDBC driver
			Class.forName("com.mysql.cj.jdbc.Driver");

	      // Step 2: Establish the connection to the database 
	      String url = "jdbc:mysql://localhost/fashion_studio"; 
	      Connection conn = DriverManager.getConnection(url,"root","");
	      //System.out.println("Connected.");
	      
	      // create a prepared statement from the connection
	      PreparedStatement ps = conn.prepareStatement("INSERT INTO inventory2 (id,size,price,itemName,brandName,color,quantity) " + "VALUES (?,?,?,?,?,?,?)");
	      
	      ps.setInt(1,id);
	      ps.setString(2, size);
	      ps.setInt(3, price);
	      ps.setString(4,itemName);
	      ps.setString(5,brandName);
	      ps.setString(6, color);
	      ps.setInt(7, quantity);
	      
	      ps.execute();
	      conn.close();
	    }
	    catch (Exception e){
	      System.err.println(e.getMessage()); 
	    }
		System.out.println("Item was added into the database.");
		in.close();
	}
	
}

class StoreMakeup implements Command {

	@Override
	public String getDescription() {
		return "Store makeup item";
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
	}
	
}

class StoreFood implements Command {

	@Override
	public String getDescription() {
		return "Store food item";
	}
	

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
	}
	
}

class SearchItem implements Command {

	@Override
	public String getDescription() {
		return "Search clothing item";
	}

	@Override
	public void execute() {
		Scanner in = new Scanner(System.in);
		System.out.println("Enter the price:");
		int p = in.nextInt(); in.nextLine();
		
		System.out.println("Enter size");
		String s = in.nextLine();
		
		System.out.println("Enter item name:");
		String n = in.nextLine();
		
		System.out.println("Enter brand name:");
		String b = in.nextLine();
		
		System.out.println("Enter color:");
		String c = in.nextLine();
		
		Apparel apparel = ListInventoryOptions.getStud().getInventory().search(new Apparel(0,s,p,n,b,c,0));
		
		if(apparel == null) {
			System.out.println("Search results:");
			System.out.println("Item was not found.");
		}
		System.out.println("Search results:");
		System.out.println(
				"Item name: " + apparel.getItemName() + "\n" +
				"Brand name: " + apparel.getBrandName() + "\n" +
				"Size: " + apparel.getSize() + "\n" +
				"Color: " + apparel.getColor() + "\n" +
				"Price: " + apparel.getPrice() + "\n" +
				"Quantity: " + apparel.getQuantity() + "\n"
				);
		in.close();
	}
	
}
class ListModelOptions implements Command {

	@Override
	public String getDescription() {
		return "List Model Commands";
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
	}
	
}

class ListPromotionOptions implements Command {

	@Override
	public String getDescription() {
		return "List Promotion Commands";
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
	}
	
}

class ListAdvertisementOptions implements Command {

	@Override
	public String getDescription() {
		return "List Advertisement Commands";
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
	}
	
}

class ListContractOptions implements Command {

	@Override
	public String getDescription() {
		return "List Contract Commands";
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
	}
	
}

class ListBusinessOptions implements Command {

	@Override
	public String getDescription() {
		return "List Business Commands";
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
	}
	
}