package com.fashion.shopping;

import com.fashion.MySQLController;
import com.fashion.apparel.Apparel;

import java.sql.Blob;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RefundCart {
    int sid;
    String date;
    String items;
    int subtotal;
    Blob returnImage;
    private MySQLController mySQLController = new MySQLController();

    public RefundCart(int sid, String date, String items, int subtotal, Blob returnImage) {
        this.sid = sid;
        this.date = date;
        this.items = items;
        this.subtotal = subtotal;
        this.returnImage = returnImage;
    }

    public RefundCart(){ }

    public void addItem(int sid){
        try {
            ResultSet rs = mySQLController.runPullCommand("SELECT * FROM `shoppingsessions` WHERE `sid` = '" + sid + "'");

            if(rs != null){
                this.sid = sid;
                this.date = rs.getString("date");
                this.items = rs.getString("items");
                this.subtotal = rs.getInt("subtotal");
            } else {
                System.out.println("ERROR: issue finding some apparel");
                return;
            }
        } catch (Exception e) {
            System.out.println("ERROR: issue with inventory");
            return;
        }
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getItems() {
        return items;
    }

    public void setItems(String items) {
        this.items = items;
    }

    public int getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(int subtotal) {
        this.subtotal = subtotal;
    }

    public Blob getReturnImage() {
        return returnImage;
    }

    public void setReturnImage(Blob returnImage) {
        this.returnImage = returnImage;
    }
}
