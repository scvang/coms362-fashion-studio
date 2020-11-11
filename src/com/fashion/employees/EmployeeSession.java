package com.fashion.employees;

import com.fashion.MySQLController;
import com.fashion.pay.PayStubInfo;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeSession {
    Employee manager;
    Employee editEmployee;
    private MySQLController mySQLController = new MySQLController();

    public EmployeeSession() {
    }

    //TODO
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

    //TODO
    public void hireEmployee(){

    }

    //TODO
    public void fireEmployee(){

    }
}
