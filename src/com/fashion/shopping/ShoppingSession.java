package com.fashion.shopping;

import com.fashion.MySQLController;
import com.fashion.pay.Card;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ShoppingSession {
    private int sid;
    private Cart cart;
    private Card card;
    private String shippingAddress;
    private String billingAddress;
    private MySQLController mySQLController = new MySQLController();

    public ShoppingSession(int sid, Cart cart, Card card, String shippingAddress, String billingAddress) {
        this.sid = sid;
        this.cart = cart;
        this.card = card;
        this.shippingAddress = shippingAddress;
        this.billingAddress = billingAddress;
    }

    public ShoppingSession(int sid) {
        this.sid = sid;
        this.cart = new Cart();
    }

    public ShoppingSession() {
        this.sid = initSessionId();
        this.cart = new Cart();
    }

    private int initSessionId(){
        int sid = -1;
        //TODO test
        try {
            //SELECT * FROM `inventory` ORDER BY `inventory`.`id` DESC
            ResultSet rs = mySQLController.runPullCommand("SELECT * FROM `inventory` ORDER BY 'inventory'.'id' DESC");

            if(rs.next()){
                sid = rs.getInt("sid") + 1;
            } else {
                System.out.println("Unable to create a shopping session");
                return -1;
            }

        } catch (SQLException throwables) {
            System.out.println("Error creating a shopping session");
        }

        return sid;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public String getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(String billingAddress) {
        this.billingAddress = billingAddress;
    }
}
