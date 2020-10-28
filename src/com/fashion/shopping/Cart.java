package com.fashion.shopping;

import com.fashion.MySQLController;
import com.fashion.apparel.Apparel;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class Cart {
    private List<Apparel> items = new ArrayList<>();
    private double subtotal;
    private int taxRate;
    private MySQLController mySQLController = new MySQLController();

    public Cart(List<Apparel> items, double subtotal, int taxRate) {
        this.items = items;
        this.subtotal = subtotal;
        this.taxRate = taxRate;
    }

    public Cart(List<Apparel> items, int taxRate) {
        this.items = items;
        this.taxRate = taxRate;
    }

    public Cart(List<Apparel> items) {
        this.items = items;
    }

    public Cart() {
    }

    public List<Apparel> getItems() {
        return items;
    }

    public boolean addItem(Apparel item) {
        //TODO test
        try {
            ResultSet rs = mySQLController.runPullCommand("SELECT * FROM `inventory` WHERE `id` = " + item.getId());

            if(rs.getInt("quantitySmall") == 0
                    && rs.getInt("quantityMedium") == 0
                    && rs.getInt("quantityLarge") == 0) {
                System.out.println("Item is sold out :(");
                return false;
            }

            switch(item.getSize()){
                case "small":
                    if(rs.getInt("quantitySmall") > 0){
                        items.add(item);
                        System.out.println("Item added!");
                        return true;
                    } else {
                        System.out.println("Small is sold out :(");
                        return false;
                    }
                case "medium":
                    if(rs.getInt("quantityMedium") > 0){
                        items.add(item);
                        System.out.println("Item added!");
                        return true;
                    } else {
                        System.out.println("Medium is sold out :(");
                        return false;
                    }
                case "large":
                    if(rs.getInt("quantityLarge") > 0){
                        items.add(item);
                        System.out.println("Item added!");
                        return true;
                    } else {
                        System.out.println("Large is sold out :(");
                        return false;
                    }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return true;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public int getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(int taxRate) {
        this.taxRate = taxRate;
    }
}
