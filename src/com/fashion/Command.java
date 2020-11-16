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
import com.fashion.negotiations.ContractSession;
import com.fashion.pay.Card;
import com.fashion.pay.PayStubInfo;
import com.fashion.shopping.ShoppingSession;

public interface Command {
	public String getDescription();

	public void execute();
}

class ListEmployeeOptions implements Command {
	static EmployeeSession employeeSession1 = new EmployeeSession();
	static 	HumanResources HR = new HumanResources();
	static 	Management M = new Management();

	public ListEmployeeOptions() {

	}

	@Override
	public void execute() {

	}

	@Override
	public String getDescription() {
		return "List Employee Commands";
	}
	
	public static EmployeeSession getSes() {
		return employeeSession1;
	}
	
	public static HumanResources getHR() {
		return HR;
	}
	
	public static Management getManage() {
		return M;
	}
}

class ViewEmployees implements Command {

	@Override
	public void execute() {
		ListEmployeeOptions.getSes().viewEmployees();
		ListEmployeeOptions.getSes().displayHeadshot();
	}

	@Override
	public String getDescription() {
		return "View Current Employees";
	}
}

class PayEmployee implements Command {

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
		PayStubInfo payStubInfo = ListEmployeeOptions.getHR().getEmployee(eid).getPayStubInfo();

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

		if (ListEmployeeOptions.getHR().payEmployee(eid, payStubInfo)) {
			System.out.println(ListEmployeeOptions.getHR().getEmployee(eid).getName() + " was paid!");
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

	@Override
	public String getDescription() {
		return "Hire an employee";
	}

	@Override
	public void execute() {
		if(ListEmployeeOptions.getSes().getAccessRights(ListEmployeeOptions.getManage().getUsername(), ListEmployeeOptions.getManage().getPassword())){
			ListEmployeeOptions.getSes().hireEmployee();
		}
	}
}

class FireEmployee implements Command {

	@Override
	public String getDescription() {
		return "Fire an employee";
	}

	@Override
	public void execute() {
		if(ListEmployeeOptions.getSes().getAccessRights(ListEmployeeOptions.getManage().getUsername(), ListEmployeeOptions.getManage().getPassword())){
			ListEmployeeOptions.getSes().fireEmployee();
		}
	}
	
}

class ListEventOptions implements Command {
	static Event E = new Event();
	static String eventName = new String();
	
	public ListEventOptions() {

	}

	@Override
	public void execute() {

	}

	@Override
	public String getDescription() {
		return "List Event Commands";
	}
	
	public static Event getEvent() {
		return E;
	}
	
	public static String getName() {
		return eventName;
	}
	
	public static String setName(String name) {
		return eventName = name;
	}
}

class ShowingEventCommands implements Command {

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
		ListEventOptions.setName(in3.next());
		int count = 1;
		ArrayList<Showing> showingList = new ArrayList<>();
		for (int i = 0; i < ListEventOptions.getEvent().getEventList().size(); ++i) {
			if (ListEventOptions.getEvent().getEventList().get(i) instanceof Showing) {
				System.out.println(count + ") " + ListEventOptions.getEvent().getEventList().get(i).getEvent());
				++count;
				showingList.add((Showing) ListEventOptions.getEvent().getEventList().get(i));
			}
		}
		if (showingList.isEmpty()) {
			System.out.println("There are no showings!");
		}
		int i = in3.nextInt();
		ListEventOptions.setName(showingList.get(i - 1).getEvent());

		in3.close();
	}
}

class DisplaySeat implements Command {

	@Override
	public String getDescription() {
		return "Display available seats";
	}

	@Override
	public void execute() {
		ListEventOptions.getEvent().displaySeats(ListEventOptions.getEvent().getEvent(ListEventOptions.getName()));

	}

}

class ReserveSeat implements Command {

	@Override
	public String getDescription() {
		return "Reserve a seat";
	}

	@Override
	public void execute() {
		if (ListEventOptions.getEvent().isShowingFull(ListEventOptions.getEvent().getEvent(ListEventOptions.getName()))) {
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

		if (ListEventOptions.getEvent().reserveSeat(ListEventOptions.getEvent().getEvent(ListEventOptions.getName()), seat, customerName, date, time)) {
			ListEventOptions.getEvent().chargeCard(ListEventOptions.getEvent().getEvent(ListEventOptions.getName()), customerName);

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

	@Override
	public String getDescription() {
		return "Check a seat";
	}

	@Override
	public void execute() {
		System.out.println("Enter your customer name:");
		Scanner in = new Scanner(System.in);
		String name = in.nextLine();
		if (ListEventOptions.getEvent().hasSeatReservation(name, ListEventOptions.getEvent().getEvent(ListEventOptions.getName()))) {
			System.out.println("Name: " + ListEventOptions.getEvent().getSeat(name, ListEventOptions.getEvent().getEvent(ListEventOptions.getName())).getCustomerName() + "\n" + "Date: "
					+ ListEventOptions.getEvent().getSeat(name, ListEventOptions.getEvent().getEvent(ListEventOptions.getName())).getDate() + "\n" + "Time: "
					+ ListEventOptions.getEvent().getSeat(name, ListEventOptions.getEvent().getEvent(ListEventOptions.getName())).getTime() + "\n" + "Seat: "
					+ ListEventOptions.getEvent().getSeat(name, ListEventOptions.getEvent().getEvent(ListEventOptions.getName())).getSeatNum() + "\n");
		} else {
			System.out.println("No reservation found for " + name);
		}
		in.close();
	}

}

class Refund implements Command {

	@Override
	public String getDescription() {
		return "Provide a refund";
	}

	@Override
	public void execute() {
		System.out.println("Enter the customer name:");
		Scanner in = new Scanner(System.in);
		String name = in.nextLine();

		if (ListEventOptions.getEvent().hasSeatReservation(name, ListEventOptions.getEvent().getEvent(ListEventOptions.getName()))) {
			System.out.println("Reservation found.");
			ListEventOptions.getEvent().removeSeatReservation(name, ListEventOptions.getEvent().getEvent(ListEventOptions.getName()));
			System.out.println("Reservation removed.");
		} else {
			System.out.println("Could not find reservaton.");
		}
		in.close();
	}

}

class DiningEventCommands implements Command {

	public DiningEventCommands() {

	}

	@Override
	public void execute() {
		System.out.println("Choose a dining event:");

		int count = 1;
		ArrayList<Dining> list = new ArrayList<>();
		for (int i = 0; i < ListEventOptions.getEvent().getEventList().size(); ++i) {
			if (ListEventOptions.getEvent().getEventList().get(i) instanceof Dining) {
				System.out.println(count + ") " + ListEventOptions.getEvent().getEventList().get(i).getEvent());
				++count;
				list.add((Dining) ListEventOptions.getEvent().getEventList().get(i));
			}
		}
		if (list.isEmpty()) {
			System.out.println("There are no dinings!");
		}
		Scanner in = new Scanner(System.in);
		int i = in.nextInt();
		ListEventOptions.setName(list.get(i - 1).getEvent());
		in.close();
	}

	@Override
	public String getDescription() {
		return "List Dining Event Commands";
	}
}

class DisplayTable implements Command {

	@Override
	public String getDescription() {
		return "Display Available Table";
	}

	@Override
	public void execute() {
		ListEventOptions.getEvent().displayTables(ListEventOptions.getEvent().getEvent(ListEventOptions.getName()));
	}

}

class ReserveTable implements Command {

	@Override
	public String getDescription() {
		return "Reserve a table";
	}

	@Override
	public void execute() {
		if (ListEventOptions.getEvent().isDiningFull(ListEventOptions.getEvent().getEvent(ListEventOptions.getName()))) {
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

		if (ListEventOptions.getEvent().reserveTable(ListEventOptions.getEvent().getEvent(ListEventOptions.getName()), table, customerName, date, time)) {
			ListEventOptions.getEvent().chargeCard(ListEventOptions.getEvent().getEvent(ListEventOptions.getName()), customerName);
		} else {
			System.out.println("Table reservation failed.");
		}
		in.close();
	}

}

class CheckTable implements Command {

	@Override
	public String getDescription() {
		return "Check a table";
	}

	@Override
	public void execute() {
		System.out.println("Enter your customer name:");
		Scanner in = new Scanner(System.in);
		String name = in.nextLine();
		if (ListEventOptions.getEvent().hasTableReservation(name, ListEventOptions.getEvent().getEvent(ListEventOptions.getName()))) {
			System.out.println("Name: " + ListEventOptions.getEvent().getTable(name, ListEventOptions.getEvent().getEvent(ListEventOptions.getName())).getCustomerName() + "\n"
					+ "Date: " + ListEventOptions.getEvent().getTable(name, ListEventOptions.getEvent().getEvent(ListEventOptions.getName())).getDate() + "\n" + "Time: "
					+ ListEventOptions.getEvent().getTable(name, ListEventOptions.getEvent().getEvent(ListEventOptions.getName())).getTime() + "\n" + "Table: "
					+ ListEventOptions.getEvent().getTable(name, ListEventOptions.getEvent().getEvent(ListEventOptions.getName())).getTableNum() + "\n");
		} else {
			System.out.println("No reservation found for " + name);
		}
		in.close();
	}
}

class RefundTable implements Command {

	@Override
	public String getDescription() {
		return "Refund Table";
	}

	@Override
	public void execute() {
		System.out.println("Enter the customer name:");
		Scanner in = new Scanner(System.in);
		String name = in.nextLine();
		if (ListEventOptions.getEvent().hasTableReservation(name, ListEventOptions.getEvent().getEvent(ListEventOptions.getName()))) {
			System.out.println("Reservation found.");
			ListEventOptions.getEvent().removeTableReservation(name, ListEventOptions.getEvent().getEvent(ListEventOptions.getName()));
			System.out.println("Reservation removed.");
		} else {
			System.out.println("Could not find reservaton.");
		}
		in.close();
	}

}

class PartyEventCommands implements Command {

	public PartyEventCommands() {

	}

	@Override
	public void execute() {
		System.out.println("Choose a party event:");

		int count = 1;
		ArrayList<Party> list = new ArrayList<>();
		for (int i = 0; i < ListEventOptions.getEvent().getEventList().size(); ++i) {
			if (ListEventOptions.getEvent().getEventList().get(i) instanceof Party) {
				System.out.println(count + ") " + ListEventOptions.getEvent().getEventList().get(i).getEvent());
				++count;
				list.add((Party) ListEventOptions.getEvent().getEventList().get(i));
			}
		}
		if (list.isEmpty()) {
			System.out.println("There are no parties!");
		}
		Scanner in3 = new Scanner(System.in);
		int i = in3.nextInt();
		ListEventOptions.setName(list.get(i - 1).getEvent());
		in3.close();
	}

	@Override
	public String getDescription() {
		return "List Party Event Commands";
	}
}

class DisplayAttendees implements Command {

	@Override
	public String getDescription() {
		return "Display number of attendees";
	}

	@Override
	public void execute() {
		System.out.println("There are: " + ListEventOptions.getEvent().getNumOfAttendees(ListEventOptions.getEvent().getEvent(ListEventOptions.getName())) + " number of attendees.");
	}

}

class ReserveBadge implements Command {

	@Override
	public String getDescription() {
		return "Reserve a badge";
	}

	@Override
	public void execute() {
		if(ListEventOptions.getEvent().isPartyFull(ListEventOptions.getEvent().getEvent(ListEventOptions.getName()))) {
			System.out.println("The venue is full.");
		}
		Scanner in = new Scanner(System.in);
		System.out.println("Enter your customer name: ");
		String customerName = in.nextLine();
		System.out.println("Enter your desired date (mm-dd-yy): ");
		String date = in.nextLine();
		System.out.println("Enter your desired time (hh:mm am/pm): ");
		String time = in.nextLine();
		
		ListEventOptions.getEvent().reserveBadge(ListEventOptions.getEvent().getEvent(ListEventOptions.getName()),customerName,date,time);
		in.close();
	}
}

class CheckBadge implements Command {

	@Override
	public String getDescription() {
		return "Check a badge";
	}

	@Override
	public void execute() {
		System.out.println("Enter your customer name:");
		Scanner in = new Scanner(System.in);
		String name = in.nextLine();
		if(ListEventOptions.getEvent().hasBadgeReservation(name,ListEventOptions.getEvent().getEvent(ListEventOptions.getName()))) {
			System.out.println(
					"Name: " + ListEventOptions.getEvent().getBadge(name,ListEventOptions.getEvent().getEvent(ListEventOptions.getName())).getName() + "\n" +
					"Date: " + ListEventOptions.getEvent().getBadge(name,ListEventOptions.getEvent().getEvent(ListEventOptions.getName())).getDate() + "\n" + 
					"Time: " + ListEventOptions.getEvent().getBadge(name,ListEventOptions.getEvent().getEvent(ListEventOptions.getName())).getTime() + "\n"
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

		ListEventOptions.getEvent().createEvent(type, name, date, time);
		in.close();
	}

	@Override
	public String getDescription() {
		return "Create New Event";
	}

}

class DisplayEvents implements Command {

	@Override
	public void execute() {
		ListEventOptions.getEvent().displayEvents();
	}

	@Override
	public String getDescription() {
		return "Display Current Events";
	}

}

class ListShoppingOptions implements Command {
	static ShoppingSession shoppingSession = new ShoppingSession();
	static String itemName = new String();
	
	public ListShoppingOptions() {

	}
	
	@Override
	public String getDescription() {
		return "List Shopping Commands";
	}

	@Override
	public void execute() {

	}
	
	public static void initalizeShop() {
		shoppingSession.initSessionId();
	}
	
	public static ShoppingSession getShop() {
		ListShoppingOptions.initalizeShop();
		return shoppingSession;
	}
	
	public static String getName() {
		return itemName;
	}
	
	public static String setName(String name) {
		return itemName = name;
	}
}

class ApparelInfo implements Command {
	
	@Override
	public String getDescription() {
		return "Display apparel information";
	}

	@Override
	public void execute() {
		System.out.println();
		ListShoppingOptions.getShop().displayApparel();
		System.out.println();
	}
	
}

class ShowApparel implements Command {

	@Override
	public String getDescription() {
		return "Show Apparel";
	}

	@Override
	public void execute() {
		System.out.println();
		Scanner in2 = new Scanner (System.in);
		System.out.println("What apparel item would you like to view? ('q' to exit): ");
		ListShoppingOptions.setName(in2.nextLine().trim());
		if(ListShoppingOptions.getName().equals("q")) {
			System.out.println();
		}
		ListShoppingOptions.getShop().apparelImage(ListShoppingOptions.getName());
		System.out.println();
		in2.close();
	}
}

class AddCart implements Command {

	@Override
	public String getDescription() {
		return "Add to cart";
	}

	@Override
	public void execute() {
		System.out.println();
		Scanner in3 = new Scanner (System.in);

		System.out.println("What item would you like to add to your cart? ('q' to exit): ");
		ListShoppingOptions.setName(in3.nextLine().trim());
		if(ListShoppingOptions.getName().equals("q")) {
			System.out.println();
		}

		System.out.println("What size? ('q' to exit): ");
		String size = in3.nextLine().trim();
		if(size.equals("q")) {
			System.out.println();
		}
		ListShoppingOptions.getShop().getCart().addItem(new Apparel(size, ListShoppingOptions.getName()));
		System.out.println();	
		in3.close();
	}
	
}

class GoCart implements Command {

	@Override
	public String getDescription() {
		return "Go to cart";
	}

	@Override
	public void execute() {
		System.out.println(ListShoppingOptions.getShop().getCart().toString());
		System.out.println();

		Scanner in4 = new Scanner (System.in);

		if(!ListShoppingOptions.getShop().getCart().getItems().isEmpty()) {
			System.out.println("Edit Cart? (y/n): ");

			String response = in4.nextLine().trim();
			if (response.equals("y")) {
				System.out.println("What item would you like to remove from your cart? ('q' to exit): ");
				ListShoppingOptions.setName(in4.nextLine().trim());
				if(ListShoppingOptions.getName().equals("q")) {
					System.out.println();
				}

				System.out.println("What size? ('q' to exit): ");
				String size = in4.nextLine().trim();
				if(size.equals("q")) {
					System.out.println();
				}
				ListShoppingOptions.getShop().getCart().removeItem(new Apparel(size, ListShoppingOptions.getName()));

				if(ListShoppingOptions.getShop().getCart().getItems().size() == 0)
					System.out.println();
			} else if (response.equals("n")) {
				System.out.println();
			}

			System.out.println("Checkout? (y/n): ");
			response = in4.nextLine().trim();
			if (response.equals("y")) {
				System.out.println("Card Number: ");
				response = in4.nextLine().trim();
				ListShoppingOptions.getShop().getCard().setCardNum(response);

				System.out.println("Card Exp Month: ");
				response = in4.nextLine().trim();
				ListShoppingOptions.getShop().getCard().setEndMonth(response);

				System.out.println("Card Exp Year: ");
				response = in4.nextLine().trim();
				ListShoppingOptions.getShop().getCard().setEndYear(response);

				System.out.println("Card Security Code: ");
				response = in4.nextLine().trim();
				ListShoppingOptions.getShop().getCard().setCode(response);

				System.out.println("Shipping Address: ");
				response = in4.nextLine().trim();
				ListShoppingOptions.getShop().setShippingAddress(response);

				System.out.println("Billing Address: ");
				response = in4.nextLine().trim();
				ListShoppingOptions.getShop().getCard().setBillingAddress(response);
				ListShoppingOptions.getShop().setBillingAddress(response);

				System.out.println("Purchase? (y/n): ");
				response = in4.nextLine().trim();
				if (response.equals("y")) {
					ListShoppingOptions.getShop().updateInventory();
					ListShoppingOptions.getShop().getCart().clearCart();
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
	
	@Override
	public String getDescription() {
		return "Process a refund";
	}

	@Override
	public void execute() {
		if(ListShoppingOptions.getShop().displayRefundOrders()){
			Scanner in5 = new Scanner (System.in);

			System.out.println("What would you like to return? (enter id number): ");
			String responseRefund = in5.nextLine().trim();
			int refundShoppingSession = Integer.parseInt(responseRefund);
			ListShoppingOptions.getShop().getRefundCart().addItem(refundShoppingSession);
			System.out.println();

			System.out.println("Please select an image for verification: ");
			JFileChooser jfc = new JFileChooser();
			jfc.showDialog(null,"Please Select the File");
			jfc.setVisible(true);
			File image = jfc.getSelectedFile();
			System.out.println(image.getName());

			ListShoppingOptions.getShop().pushRefund(image, refundShoppingSession);
			in5.close();
		} else {
			System.out.println("No eligible items to return :(\n");
		}
	}
	
}

class ViewRefund implements Command {

	@Override
	public String getDescription() {
		return "View last refund";
	}

	@Override
	public void execute() {
		ListShoppingOptions.getShop().viewLastRefund();
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
	
	}
}

class ListModels implements Command {

	@Override
	public String getDescription() {
		return "Check list of models";
	}

	@Override
	public void execute() {
		ListEmployeeOptions.getHR().displayModels();
	}
	
}

class ChangeApparel implements Command {

	@Override
	public String getDescription() {
		return "Change apparel";
	}

	@Override
	public void execute() {
		Scanner in = new Scanner(System.in);
		
		System.out.println("Which model do you want to change?");
		String name = in.next();
		if(!ListEmployeeOptions.getHR().doesModelExist(name)) {
			System.out.println("Model was not found, try again.");
		}
		//changeApparelScreen(name);
		in.close();
	}
	
}

class UpdateContact implements Command {

	@Override
	public String getDescription() {
		return "Update contact information";
	}

	@Override
	public void execute() {
		Scanner in = new Scanner(System.in);
		
		System.out.println("Enter model name:");
		String name = in.nextLine();
		
		if(!ListEmployeeOptions.getHR().doesModelExist(name)) {
			System.out.println("Model was not found, try again.");
		}
		
		System.out.println("Enter contact information:");
		String phoneNum = in.nextLine();
		
		ListEmployeeOptions.getHR().getModel(name).setPhoneNum(phoneNum);
		in.close();
	}	
}

class UpdatePay implements Command {

	@Override
	public String getDescription() {
		return "Update salary";
	}

	@Override
	public void execute() {
		Scanner in = new Scanner(System.in);
		
		System.out.println("Enter model name:");
		String name = in.nextLine();
		
		if(!ListEmployeeOptions.getHR().doesModelExist(name)) {
			System.out.println("Model was not found, try again.");
		}
		
		System.out.println("Enter salary:");
		double salary = in.nextDouble();
		PayStubInfo p = new PayStubInfo(salary,0,0,0);
		ListEmployeeOptions.getHR().getModel(name).setPayStubInfo(p);;
		in.close();
	}
	
}

class AddModel implements Command {

	@Override
	public String getDescription() {
		return "Add model";
	}

	@Override
	public void execute() {
		Scanner in = new Scanner(System.in);
		
		System.out.println("Enter EID: ");
		int eid = in.nextInt(); in.nextLine();
		System.out.println("Enter agent name:");
		String agent = in.nextLine();
		System.out.println("Enter model name:");
		String model = in.nextLine();
		System.out.println("Enter phone number:");
		String number = in.nextLine();
		System.out.println("Enter salary:");
		double salary = in.nextDouble();
		ListEmployeeOptions.getHR().createModel(eid,agent,model,"Fashion Model",number,new PayStubInfo(salary, 0, 0, 0)); // Probably need to change this.
		in.close();
	}
	
}

class ListPromotionOptions implements Command {

	@Override
	public String getDescription() {
		return "List Promotion Commands";
	}

	@Override
	public void execute() {
	
	}	
}

class UpEvents implements Command {

	@Override
	public String getDescription() {
		return "Display upcoming events";
	}

	@Override
	public void execute() {
		ListEventOptions.getEvent().displayEvents();
		System.out.println();
		System.out.println();	
	}	
}

class CheckAvail implements Command {

	@Override
	public String getDescription() {
		return "Check available promotions";
	}

	@Override
	public void execute() {
		Scanner in2 = new Scanner (System.in);
		System.out.println("Enter the event you'd like to view open promotion spots ('q' to exit): ");
		String eventName = in2.nextLine().trim();

		while(ListEventOptions.getEvent().getEvent(eventName) == null){
			System.out.println("Sorry! we could not find your event, please re-enter a new event ('q' to exit): ");
			eventName = in2.nextLine().trim();
		}

		if(ListEventOptions.getEvent().getEvent(eventName).isPromotionSpotOpen(1)){
			System.out.println("1:  Open");
		} else {
			System.out.println("1:  Taken");
		}

		if(ListEventOptions.getEvent().getEvent(eventName).isPromotionSpotOpen(2)){
			System.out.println("2:  Open");
		} else {
			System.out.println("2:  Taken");
		}

		if(ListEventOptions.getEvent().getEvent(eventName).isPromotionSpotOpen(3)){
			System.out.println("3:  Open");
		} else {
			System.out.println("3:  Taken");
		}

		if(ListEventOptions.getEvent().getEvent(eventName).isPromotionSpotOpen(4)){
			System.out.println("4:  Open");
		} else {
			System.out.println("4:  Taken");
		}

		if(ListEventOptions.getEvent().getEvent(eventName).isPromotionSpotOpen(5)){
			System.out.println("5:  Open");
		} else {
			System.out.println("5:  Taken");
		}

		if(ListEventOptions.getEvent().getEvent(eventName).isPromotionSpotOpen(6)){
			System.out.println("6:  Open");
		} else {
			System.out.println("6:  Taken");
		}

		if(ListEventOptions.getEvent().getEvent(eventName).isPromotionSpotOpen(7)){
			System.out.println("7:  Open");
		} else {
			System.out.println("7:  Taken");
		}

		if(ListEventOptions.getEvent().getEvent(eventName).isPromotionSpotOpen(8)){
			System.out.println("8:  Open");
		} else {
			System.out.println("8:  Taken");
		}

		if(ListEventOptions.getEvent().getEvent(eventName).isPromotionSpotOpen(9)){
			System.out.println("9:  Open");
		} else {
			System.out.println("9:  Taken");
		}

		if(ListEventOptions.getEvent().getEvent(eventName).isPromotionSpotOpen(10)){
			System.out.println("10: Open");
		} else {
			System.out.println("10: Taken");
		}

		in2.close();
		System.out.println();
		System.out.println();
	}
}

class ReservePromo implements Command {
	public String getDescription() {
		return "Reserve a promotion spot";
	}

	@Override
	public void execute() {
		Scanner in3 = new Scanner (System.in);
		System.out.println("Enter the event  ('q' to exit): ");
		String eventNameReserve = in3.nextLine();

		while(ListEventOptions.getEvent().getEvent(eventNameReserve) == null) {
			System.out.println("Sorry! we could not find your event, please re-enter a new event  ('q' to exit): ");
			eventNameReserve = in3.nextLine().trim();
		}

		System.out.println("Enter your business name ('q' to exit): ");
		String businessName = in3.nextLine();

		System.out.println("What would you like it to say? ('q' to exit): ");
		String text = in3.nextLine();

		System.out.println("Enter your desired promotion location ('q' to exit): ");
		int location = 0;
		while(location == 0){
			String temp = in3.next();

			try {
				location = Integer.parseInt(temp);
			} catch (NumberFormatException e) {
				System.out.println("Please enter a location (1-10). Open spots are viewable from " +
						"the promotion start screen ('q' to exit)");
			}
		}

		System.out.println("What is your offer ('q' to exit): ");
		double dollarAmount = 0.0;
		while(dollarAmount == 0.0){
			String temp = in3.next();

			try {
				dollarAmount = Double.parseDouble(temp);
			} catch (NumberFormatException e) {
				System.out.println("Please enter a valid dollar amount ('q' to exit).");
			}
		}

		System.out.println("What is your card number ('q' to exit): ");
		String cardNum = "";
		while(cardNum.isEmpty()) {
			String temp = in3.next();

			if (temp.length() < 16) {
				System.out.println("Please enter a valid card number ('q' to exit).");
			}
			cardNum = temp;
		}

		System.out.println("What is your card month ('q' to exit): ");
		String cardMonth = "";
		while(cardMonth.isEmpty()){
			String temp = in3.next();

			if(temp.length() < 2) {
				System.out.println("Please enter a valid card month ('q' to exit).");
			} else  {
				try {
					cardMonth = temp;
				} catch (NumberFormatException e) {
					System.out.println("Please enter a valid card month ('q' to exit).");
				}
			}
		}

		System.out.println("What is your card year ('q' to exit): ");
		String cardYear = "";
		while(cardYear.isEmpty()){
			String temp = in3.next();

			if(temp.length() < 2) {
				System.out.println("Please enter a valid card year ('q' to exit).");
			} else  {
				try {
					cardYear = temp;
				} catch (NumberFormatException e) {
					System.out.println("Please enter a valid card year ('q' to exit).");
				}
			}
		}

		System.out.println("What is your card code ('q' to exit): ");
		String cardCode = "";
		while(cardCode.isEmpty()){
			String temp = in3.next();

			if(temp.length() != 3) {
				System.out.println("Please enter a valid card code ('q' to exit).");
			} else  {
				try {
					cardCode = temp;
				} catch (NumberFormatException e) {
					System.out.println("Please enter a valid card code ('q' to exit).");
				}
			}
		}

		in3.close();
		if(ListEventOptions.getEvent().getEvent(eventNameReserve).addPromotion(businessName, text, location, dollarAmount, new Card(cardNum, cardMonth, cardYear, cardCode, null))) {
			System.out.println("Promotion added!");
		}

		System.out.println();
		System.out.println();
	}
}

class ListAdvertisementOptions implements Command {

	@Override
	public String getDescription() {
		return "List Advertisement Commands";
	}

	@Override
	public void execute() {

	}	
}

class ViewAds implements Command {

	@Override
	public String getDescription() {
		return "View Current Ads";
	}

	@Override
	public void execute() {
		ListInventoryOptions.getStud().getAd();
	}	
}

class CreateAd implements Command {

	@Override
	public String getDescription() {
		return "Create New Ad";
	}

	@Override
	public void execute() {
		Scanner in = new Scanner(System.in);
		
		System.out.println("What is the event ID?: ");
		int eid = in.nextInt();
		System.out.println("What type of advertisement do you need? (paper or video)");
		String adType = in.next();
		System.out.println("What is the name of the event?: ");
		String eventName = in.next() + in.next();
		System.out.println("Where is the location of the event?: ");
		String loc = in.next();
		System.out.println("What is the time of the event?: ");
		String time = in.next();
		System.out.println("What is the the number we should contact?: ");
		String contactInfo = in.next();
		//Advertisement advert = new Advertisement(eid, eventName, loc, time, contactInfo);
		if(adType.equals("paper")) {
			ListInventoryOptions.getStud().addAd(eid, eventName, loc, time, contactInfo);
			System.out.println("Advertisement Created!");
		} else if(adType.equals("video")) {
			//advert.createAdVideo();
			ListInventoryOptions.getStud().addAd(eid, eventName, loc, time, contactInfo);
			System.out.println("Advertisement Created!");
		} else {
			System.out.print("We don't currently support that type of advertisement at this time.");
		}
		in.close();
	}	
}

class ListContractOptions implements Command {
	static ContractSession contractSession = new ContractSession();
	
	@Override
	public String getDescription() {
		return "List Contract Commands";
	}

	@Override
	public void execute() {

	}
	
	public static ContractSession getConSes() {
		return contractSession;
	}
}

class BeginContract implements Command {

	@Override
	public String getDescription() {
		return "Begin contract negotiation";
	}

	@Override
	public void execute() {
		ListContractOptions.getConSes().negotiate();
	}	
}

class ViewOld implements Command {

	@Override
	public String getDescription() {
		return "View old contracts";
	}

	@Override
	public void execute() {
		ListContractOptions.getConSes().viewOldContracts();
	}	
}

class ViewCurrent implements Command {

	@Override
	public String getDescription() {
		return "View current contract";
	}

	@Override
	public void execute() {
		ListContractOptions.getConSes().viewCurrentContract();
	}	
}

class ListBusinessOptions implements Command {

	@Override
	public String getDescription() {
		return "List Business Commands";
	}

	@Override
	public void execute() {
		
	}
}

class ViewRecords implements Command {

	@Override
	public String getDescription() {
		return "View Business Records";
	}

	@Override
	public void execute() {
		HumanResources.getServices();
	}	
}

class HireBusiness implements Command {

	@Override
	public String getDescription() {
		return "Hire a Business";
	}

	@Override
	public void execute() {
		Scanner in = new Scanner(System.in);
		
		System.out.println("What is the service ID?");
		int sid = in.nextInt();
		in.nextLine();
		System.out.println("What is the name of the Business?");
		String name = in.nextLine();
		System.out.println("What is the address?");
		String loc = in.nextLine();
		System.out.println("What is the service requested?");
		String service = in.nextLine();
		System.out.println("Who do we contact?");
		String repName = in.nextLine();
		System.out.println("Please provice their phone number.");
		String contactInfo = in.nextLine();
		System.out.println("How much are they charging?");
		double salary = in.nextDouble();
		in.nextLine();
		HumanResources.hireBusiness(sid, name, loc, service, repName, contactInfo, salary);
		
		in.close();
	}
}

class ConfirmBusiness implements Command {

	@Override
	public String getDescription() {
		return "Confirm a Business";
	}

	@Override
	public void execute() {
		Scanner in = new Scanner(System.in);
		
		for (int i = 0; i < HumanResources.servicesUsed.size(); i++) {
			if (HumanResources.servicesUsed.get(i).hasBeenContacted() == false) {
				HumanResources.getServiceRequests();
				System.out.println("Would you like to contact them now? ('y' or 'n')\n");
				String yorn = in.next();
				in.nextLine();
				if (yorn.equals("y")) {
					HumanResources.servicesUsed.get(i).contactBusiness(HumanResources.servicesUsed.get(i));
					System.out.println("Service confirmed!\n");
				} else {
					System.out.println("Please be sure to contact them at a different date.\n");
				}
			} else {
				HumanResources.getServiceRequests();
			}
		}
		in.close();
	}
}