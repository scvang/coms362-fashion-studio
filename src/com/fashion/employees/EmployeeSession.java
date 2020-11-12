package com.fashion.employees;

import com.fashion.MySQLController;
import com.fashion.pay.PayStubInfo;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import java.util.Scanner;

public class EmployeeSession {
    Employee manager;
    private MySQLController mySQLController = new MySQLController();

    private String[] firstNames = {"Bob", "Joe", "Sally", "Hailey", "Molly", "Johan", "Ricky"};
    private String[] lastNames = {"Martinez", "Smith", "Mama", "Rodriguez", "Merchant"};
    private String[] jobTitles = {"Accountant", "Janitor", "Human Resource General Worker", "Photographer",
                                    "Model", "Designer"};

    public EmployeeSession() {
    }

    /**
     * @param username of login
     * @param password of login
     * @return true if the user has access to the manager screen, otherwise false
     */
    public boolean getAccessRights(String username, String password){
        try {
            ResultSet rs = mySQLController.runPullCommand("SELECT * FROM `employees` WHERE `username` = '" + username + "'");

            if(rs != null) {
                if(rs.getString("password").equals(password) && rs.getInt("authorityLevel") == 5){
                    manager = new Employee(rs.getInt("eid"), rs.getString("firstName") + " " + rs.getString("lastName"),
                            rs.getString("jobTitle"), new PayStubInfo(rs.getInt("salary"), 0));
                    System.out.println("Welcome " + rs.getString("firstName") + "!\n");
                    return true;
                } else {
                    System.out.println("Sorry " + rs.getString("firstName") + " you do not have access.\n");
                }
            } else {
                System.out.println("Wrong password or username :(\n");
            }
            return false;
        } catch (SQLException throwables) {
            System.out.println("Error displaying apparel on our side");
            return false;
        }
    }

    /**
     * goes through the process of hiring a random employee
     */
    public void hireEmployee(){
        Scanner in = new Scanner(System.in);
        Random rand = new Random();
        Employee newEmployee = new Employee();
        newEmployee.setEid(initEmployeeId());

        if(newEmployee.getEid() != -1){
            newEmployee.setName(firstNames[rand.nextInt(firstNames.length)] + " " + lastNames[rand.nextInt(lastNames.length)]);
            String headshotImage = "headshot" + (rand.nextInt(4) + 1) + ".jpg";
            newEmployee.setJobTitle(jobTitles[rand.nextInt(jobTitles.length)]);
            newEmployee.getPayStubInfo().setSalary(75000);

            System.out.println("You are interviewing " + newEmployee.getName() + ".\n");
            try {
                JFrame editorFrame = new JFrame("Image Demo");
                editorFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

                BufferedImage image = ImageIO.read(new File(headshotImage));
                ImageIcon imageIcon = new ImageIcon(image);
                JLabel jLabel = new JLabel();
                jLabel.setIcon(imageIcon);
                editorFrame.getContentPane().add(jLabel, BorderLayout.CENTER);

                editorFrame.pack();
                editorFrame.setLocationRelativeTo(null);
                editorFrame.setVisible(true);
            } catch (IOException e) {
                String workingDir = System.getProperty("user.dir");
                System.out.println("Current working directory : " + workingDir);
                e.printStackTrace();
            }
            System.out.println("You are offering them $75000 to be a " + newEmployee.getJobTitle());
            System.out.println("Would you like to hire them? (y/n)");
            switch(in.next()){
                case "y":
                    if(mySQLController.pushNewEmployee(newEmployee, headshotImage)){
                        System.out.println(newEmployee.getName() + " has been hired!");
                    }
                    break;
                case "n":
                    break;
            }
        }
    }

    /**
     * @return the new employee id based upon the eid's in the dB
     */
    private int initEmployeeId(){
        try {
            ResultSet rs = mySQLController.runPullCommand("SELECT * FROM `employees` ORDER BY `employees`.`eid` DESC");

            if(rs != null){
                return rs.getInt("eid") + 1;
            } else {
                System.out.println("Error setting up the new employee");
            }
        } catch (SQLException throwables) {
            System.out.println("Error setting up the new employee");
        }
        return -1;
    }

    /**
     * displays the important information of the all the active employees
     */
    public void viewEmployees(){
        Scanner in = new Scanner(System.in);
        try {
            ResultSet rs = mySQLController.runPullCommand("SELECT * FROM `employees` WHERE `isActive`=1");

            if(rs != null) {
                System.out.println(rs.getInt("eid") + ": " + rs.getString("firstName") + " " + rs.getString("lastName"));
                System.out.println("Job Title: " + ": " + rs.getString("jobTitle"));
                System.out.println("Salary: " + ": " + rs.getString("salary") + "\n");
                while (rs.next()) {
                    System.out.println(rs.getInt("eid") + ": " + rs.getString("firstName") + " " + rs.getString("lastName"));
                    System.out.println("Job Title: " + ": " + rs.getString("jobTitle"));
                    System.out.println("Salary: " + ": " + rs.getString("salary") + "\n");
                }
            }
        } catch (SQLException throwables) {
            System.out.println("Error displaying employees on our side");
        }
    }

    /**
     * displays the employee's headshot from dB
     */
    public void displayHeadshot(){
        Scanner in = new Scanner(System.in);
        try {
            System.out.println("Would you like to view a specific employees headshot? (y/n)");
            if(in.next().equals("y")){
                System.out.println("Which one?");
                ResultSet rs = mySQLController.runPullCommand("SELECT * FROM `employees` WHERE `eid` = '" + Integer.parseInt(in.next()) + "'");

                if(rs != null) {
                    mySQLController.displayImageFromDatabase("Headshot", rs.getString("firstName"), rs.getBlob("headshot"));
                }
            }
            System.out.println();
        } catch (SQLException throwables) {
            System.out.println("Error displaying employees on our side");
        }
    }


    /**
     * runs through the sequence of firing one employee from the company
     */
    public void fireEmployee(){
        Scanner in = new Scanner(System.in);
        viewEmployees();

        System.out.println("Who's getting fired?");
        mySQLController.runPushCommand("UPDATE `employees` SET `isActive`=0 WHERE `eid`= '" + Integer.parseInt(in.next()) + "'");
        System.out.println("They have been fired :(\n");
    }
}
