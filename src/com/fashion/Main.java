package com.fashion;

import java.sql.*;
import com.fashion.apparel.Apparel;
import com.fashion.commands.*;
import com.fashion.employees.HumanResources;
import com.fashion.events.*;
import com.fashion.negotiations.ContractSession;
import com.fashion.pay.*;
import com.fashion.screens.*;
import com.fashion.shopping.ShoppingSession;
import java.util.ArrayList;
import javax.swing.*;
import java.util.Scanner;

/**
 * 
 * @author Sebastian Vang
 * @author Chad Morrow
 * @author Emily Young
 *
 */
public class Main extends JFrame {

	public static Studio studio;
	public static void main(String[] args) {
		
		// Create a studio.
		String company = "Fashion Inc";
		String address = "401 Somewhere Ave";
		String phone = "555-555-5555";
		double balance = 500000;

		studio = new Studio(company,address,phone, balance);
		
		// Execute the program.
		run();
	}
	
	/**
	 * Run the program.
	 */
	public static void run() {
		new MainScreen(studio).execute();
	}
}
