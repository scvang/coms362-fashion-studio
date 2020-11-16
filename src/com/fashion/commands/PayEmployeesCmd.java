package com.fashion.commands;

import java.util.Scanner;

import com.fashion.Studio;
import com.fashion.pay.PayStubInfo;
import com.fashion.screens.EmployeeScreen;

public class PayEmployeesCmd implements Command {
	private Studio studio;
	public PayEmployeesCmd(Studio s) {
		this.studio = s;
	}

	@Override
	public String getDescription() {

		return "Pay Employee";
	}

	@Override
	public void execute() {
		Scanner in2 = new Scanner (System.in);
		System.out.println("Enter the employee id ('q' to exit): ");
		int eid = -1;
		while(eid == -1){
			String temp = in2.next();
			if(temp.equals("q")) {
				System.out.println();
				System.out.println();
				new EmployeeScreen(studio).execute();
			}

			try {
				eid = Integer.parseInt(temp);
			} catch (NumberFormatException e) {
				System.out.println("Employee id must be a number ('q' to exit): ");
			}
		}

		PayStubInfo payStubInfo = studio.getEmployee(eid).getPayStubInfo();

		System.out.println("Did this employee recieve a bonus? (y/n) ('q' to exit)");
		String yesno = in2.next();

		double bonus = -1;
		if(yesno.equals("y")) {
			System.out.println("How much did they recieve? ('q' to exit)");
			while(bonus == -1){
				String temp = in2.next();
				if(temp.equals("q")) {
					System.out.println();
					System.out.println();
					new EmployeeScreen(studio).execute();
				}

				try {
					bonus = Double.parseDouble(temp);
				} catch (NumberFormatException e) {
					System.out.println("Bonus must be a number ('q' to exit): ");
				}
			}
		}
		payStubInfo.setBonus(bonus);

		if(studio.payEmployee(eid, payStubInfo)){
			System.out.println(studio.getEmployee(eid).getName() + " was paid!");
		} else {
			System.out.println("Error paying employee, try again later");
		}
		System.out.println();
		System.out.println();
		
		new EmployeeScreen(studio).execute();
	}

}
