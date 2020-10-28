package com.fashion.shopping;

import com.fashion.MySQLController;
import com.fashion.apparel.Apparel;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class Cart {
    private List<Apparel> items;
    private int subtotal;
    private int taxRate;
    private MySQLController mySQLController = new MySQLController();

    public Cart(List<Apparel> items, int subtotal, int taxRate) {
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
        taxRate = 2;
    }

    public Cart() {
        taxRate = 2;
        items = new ArrayList<>();
    }

    public List<Apparel> getItems() {
        return items;
    }

    public void addItem(Apparel item) {
        //TODO test

        try {
            ResultSet rs = mySQLController.runPullCommand("SELECT * FROM `inventory` WHERE `itemName` = '" + item.getItemName() + "'");

            if(rs != null){
                item.setId(rs.getInt("id"));
                item.setPrice(rs.getInt("price"));
                item.setBrandName(rs.getString("brandName"));
                item.setColor(rs.getString("color"));

                if(rs.getInt("quantitySmall") == 0
                        && rs.getInt("quantityMedium") == 0
                        && rs.getInt("quantityLarge") == 0) {
                    System.out.println("Item is sold out :(");
                }

                switch(item.getSize()){
                    case "S":
                        if(rs.getInt("quantitySmall") > 0){
                            items.add(item);
                            subtotal += item.getPrice();
                            System.out.println("Item added!");
                        } else {
                            System.out.println("Small is sold out :(");
                        }
                        break;
                    case "M":
                        if(rs.getInt("quantityMedium") > 0){
                            items.add(item);
                            subtotal += item.getPrice();
                            System.out.println("Item added!");
                        } else {
                            System.out.println("Medium is sold out :(");
                        }
                        break;
                    case "L":
                        if(rs.getInt("quantityLarge") > 0){
                            items.add(item);
                            subtotal += item.getPrice();
                            System.out.println("Item added!");
                        } else {
                            System.out.println("Large is sold out :(");
                        }
                        break;
                }

            } else {
                System.out.println("Unable to find your product");
            }

        } catch (SQLException throwables) {
            System.out.println("Error displaying apparel on our side");
        }
    }

    public void clearCart(){
        items.clear();
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(int subtotal) {
        this.subtotal = subtotal;
    }

    public int getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(int taxRate) {
        this.taxRate = taxRate;
    }

    @Override
    public String toString() {
        StringBuilder toString = new StringBuilder();
        if(!items.isEmpty()){
            toString.append("Cart:\n");
            for(Apparel item : items) {
                toString.append(item.toString());
            }
            toString.append("\n\n");
            toString.append("Tax Rate: ").append(taxRate).append("%");
            toString.append("\nSubtotal: ").append("$").append(Math.round(subtotal + (subtotal * (taxRate / 100.0))));
        } else {
            toString.append("Empty");
        }

        return toString.toString();
    }
}
