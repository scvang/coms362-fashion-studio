package com.fashion;

import javax.swing.JFrame;
import com.fashion.screens.MainScreen;

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
	public static void run() {
		new MainScreen(studio).execute();
	}
}
