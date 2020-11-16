package com.fashion.commands;

import java.io.File;
import java.util.Scanner;

import javax.swing.JFileChooser;

import com.fashion.Studio;
import com.fashion.screens.ShoppingScreen;
import com.fashion.shopping.ShoppingSession;

public class ProcessRefundCmd implements Command {
	private Studio studio;
	ShoppingSession shoppingSession;
	public ProcessRefundCmd(Studio s, ShoppingSession ss) {
		this.studio = s;
		this.shoppingSession = ss;
	}

	@Override
	public String getDescription() {

		return "Process Refund";
	}

	@Override
	public void execute() {
		if(shoppingSession.displayRefundOrders()){
			Scanner in5 = new Scanner (System.in);

			System.out.println("What would you like to return? (enter id number): ");
			String responseRefund = in5.nextLine().trim();
			int refundShoppingSession = Integer.parseInt(responseRefund);
			shoppingSession.getRefundCart().addItem(refundShoppingSession);
			System.out.println();

			System.out.println("Please select an image for verification: ");
			JFileChooser jfc = new JFileChooser();
			jfc.showDialog(null,"Please Select the File");
			jfc.setVisible(true);
			File image = jfc.getSelectedFile();
			System.out.println(image.getName());

			shoppingSession.pushRefund(image, refundShoppingSession);
		} else {
			System.out.println("No eligible items to return :(\n");
		}
		new ShoppingScreen(studio).execute();
	}

}
