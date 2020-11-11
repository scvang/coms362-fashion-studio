package com.fashion.shopping;

import com.fashion.MySQLController;
import com.fashion.apparel.Apparel;
import com.fashion.pay.Card;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ShoppingSession {
    private int sid;
    private Cart cart;
    private RefundCart refundCart;
    private Card card;
    private String shippingAddress;
    private String billingAddress;
    private MySQLController mySQLController = new MySQLController();

    public ShoppingSession(int sid, Cart cart, RefundCart refundCart, Card card, String shippingAddress, String billingAddress) {
        this.sid = sid;
        this.cart = cart;
        this.refundCart = refundCart;
        this.card = card;
        this.shippingAddress = shippingAddress;
        this.billingAddress = billingAddress;
    }

    /**
     * could be used in the future for saving a shoppingsession state
     * @param sid
     */
    public ShoppingSession(int sid) {
        this.sid = sid;
        this.cart = new Cart();
        this.refundCart = new RefundCart();
        this.card = new Card();
    }

    /**
     * basic constructor that takes in no values, but instantiates it with the next option or base option
     */
    public ShoppingSession() {
        this.cart = new Cart();
        this.refundCart = new RefundCart();
        this.card = new Card();
    }

    public void initSessionId(){
        try {
            ResultSet rs = mySQLController.runPullCommand("SELECT * FROM `shoppingsessions` ORDER BY `shoppingsessions`.`sid` DESC");

            if(rs != null){
                this.sid = rs.getInt("sid") + 1;
            } else {
                System.out.println("Unable to create a shopping session");
            }

        } catch (SQLException throwables) {
            System.out.println("Error creating a shopping session");
        }
    }

    /**
     * Displays all the apparel from the backend
     */
    public void displayApparel(){
        try {
            ResultSet rs = mySQLController.runPullCommand("SELECT * FROM `inventory`");

            if(rs != null) {
                System.out.println("$" + rs.getInt("price") + " " + rs.getString("brandName") + " "
                        + rs.getString("itemName") + "\nDescription: " + rs.getString("color") + " " +
                        rs.getString("category") + "\nS: " + rs.getInt("quantitySmall") + " M: "
                        + rs.getInt("quantityMedium") + " L: " + rs.getInt("quantityLarge"));

                while (rs.next()) {
                    System.out.println("$" + rs.getInt("price") + " " + rs.getString("brandName") + " "
                            + rs.getString("itemName") + "\nDescription: " + rs.getString("color") + " " +
                            rs.getString("category") + "\nS: " + rs.getInt("quantitySmall") + " M: "
                            + rs.getInt("quantityMedium") + " L: " + rs.getInt("quantityLarge"));
                }
            }

        } catch (SQLException throwables) {
            System.out.println("Error displaying apparel on our side");
        }
    }


    /**
     * pulls the apparel image from the database and displays it to the user
     * @param itemName tells us which item to display
     */
    public void apparelImage(String itemName){
        try {
            ResultSet rs = mySQLController.runPullCommand("SELECT * FROM `inventory` WHERE `itemName` = '" + itemName + "'");

            if(rs != null){
                mySQLController.displayImageFromDatabase("Apparel Image", rs.getString("itemName"), rs.getBlob("image"));
            } else {
                System.out.println("Unable to find your product");
            }
        } catch (SQLException throwables) {
            System.out.println("Error displaying apparel on our side");
        }
    }

    /**
     * adds the completed shopping session to the database as a record
     */
    public void addShoppingSession(){
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();

        StringBuilder items = new StringBuilder();
        for(Apparel item : cart.getItems()){
            items.append(item.getItemName()).append("\n");
        }

        try {
            mySQLController.runPushCommand("INSERT INTO SHOPPINGSESSIONS (SID,DATE,ITEMS,SUBTOTAL,TAXRATE,CARDNUM," +
                    "CARDMONTH,CARDYEAR,CARDCODE,SHIPPINGADDRESS,BILLINGADDRESS,ISREFUNDED) VALUES ('" + sid + "','" + formatter.format(date)
                    + "','"+ items.toString() + "','" + (cart.getSubtotal() + (cart.getSubtotal() * (cart.getTaxRate() / 100.0))) +
                    "','" + cart.getTaxRate() + "','" + card.getCardNum() + "','" + card.getEndMonth() + "','"
                    + card.getEndYear() + "','" + card.getCode() + "','" + shippingAddress + "','" + billingAddress + "','" + 0 + "')");
            System.out.println("Purchased!");
            System.out.println();
        } catch (Exception e) {
            System.out.println("ERROR: Unable to finalize purchase");
        }
    }

    /**
     * updates the inventory on the backend to reflect the change in purchased items
     */
    public void updateInventoryBought(){
        for(Apparel item : cart.getItems()){
            int numLeft = 0;
            try {
                ResultSet rs = mySQLController.runPullCommand("SELECT * FROM `inventory` WHERE `id` = '" + item.getId() + "'");

                if(rs != null){
                    switch(item.getSize()){
                        case "S":
                            numLeft = rs.getInt("quantitySmall") - 1;
                            break;
                        case "M":
                            numLeft = rs.getInt("quantityMedium") - 1;
                            break;
                        case "L":
                            numLeft = rs.getInt("quantityLarge") - 1;
                            break;
                    }
                } else {
                    System.out.println("ERROR: issue finding some apparel");
                    return;
                }
            } catch (Exception e) {
                System.out.println("ERROR: issue with inventory");
                return;
            }

            try {
                switch(item.getSize()){
                    case "S":
                        mySQLController.runPushCommand("UPDATE `inventory` SET `quantitySmall` = '" + numLeft + "' WHERE `id` = '" + item.getId() + "'");
                        break;
                    case "M":
                        mySQLController.runPushCommand("UPDATE `inventory` SET `quantityMedium` = '" + numLeft + "' WHERE `id` = '" + item.getId() + "'");
                        break;
                    case "L":
                        mySQLController.runPushCommand("UPDATE `inventory` SET `quantityLarge` = '" + numLeft + "' WHERE `id` = '" + item.getId() + "'");
                        break;
                }
            } catch (Exception e) {
                System.out.println("ERROR: issue with inventory");
                return;
            }
        }
        addShoppingSession();
        cart.clearCart();
    }

    /**
     * Displays orders at most 30 days back
     */
    public boolean displayRefundOrders(){
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        String currentDate = formatter.format(date);
        String[] currentDateSplit = currentDate.split("/");
        int currentDay = Integer.parseInt(currentDateSplit[0]);
        int currentMonth = Integer.parseInt(currentDateSplit[1]);
        boolean haveRefundItems = false;

        try {
            ResultSet rs = mySQLController.runPullCommand("SELECT * FROM `shoppingsessions` WHERE `isRefunded` = 0");

            if(rs != null) {
                if(!rs.getString("date").isEmpty()){
                    haveRefundItems = true;
                    String payDate = rs.getString("date");
                    String[] payDateSplit = payDate.split("/");

                    int payDay = Integer.parseInt(payDateSplit[0]);
                    int payMonth = Integer.parseInt(payDateSplit[1]);

                    if(isRefundable(currentDay, currentMonth, payDay, payMonth)){
                        System.out.println(rs.getInt("sid") + ": " + payDate + " $" + rs.getInt("subtotal") + " " + rs.getString("items").trim());
                    }
                }

                while (rs.next()) {
                    if(!rs.getString("date").isEmpty()){
                        haveRefundItems = true;
                        String payDate = rs.getString("date");
                        String[] payDateSplit = payDate.split("/");

                        int payDay = Integer.parseInt(payDateSplit[0]);
                        int payMonth = Integer.parseInt(payDateSplit[1]);

                        if(isRefundable(currentDay, currentMonth, payDay, payMonth)){
                            System.out.println(rs.getInt("sid") + ": " + payDate + " $" + rs.getInt("subtotal") + " " + rs.getString("items").trim());
                        }
                    }
                }
                System.out.println();
                return haveRefundItems;
            } else {
                return haveRefundItems;
            }
        } catch (SQLException throwables) {
            System.out.println("Error displaying apparel on our side");
            return false;
        }
    }

    /**
     * @param currentDay is the day the user is trying to process the return
     * @param currentMonth is the month the user is trying to process the return
     * @param payDay is the day when the user bought the item
     * @param payMonth is the the month when the user bought the item
     * @return
     */
    private boolean isRefundable(int currentDay, int currentMonth, int payDay, int payMonth){
        //if the current month is the same as our pay day or the month after then proceed
        if(currentMonth == payMonth) {
            return true;
        } else if(payMonth == (currentMonth - 1)){
            int remainder = 30 - currentDay;
            return (30 - remainder) <= payDay;
        }
        return false;
    }

    /**
     * @param image is the returned item image
     * @param refundShoppingSession is the shoppingsession when the user purchased the item
     */
    public void pushRefund(File image, int refundShoppingSession){
        mySQLController.pushRefund("UPDATE `shoppingsessions` SET `isRefunded` = ?, `returnImage` = ? WHERE `sid` = ?", refundShoppingSession, image);
    }

    /**
     * Displays the relevant information for the last refund
     */
    public void viewLastRefund(){
        try {
            ResultSet rs = mySQLController.runPullCommand("SELECT * FROM `shoppingsessions` WHERE `isRefunded` = 1 ORDER BY `shoppingsessions`.`sid` DESC");

            if(rs != null) {
                System.out.println("Processed on: " + rs.getString("date"));
                System.out.print("Items: " + rs.getString("items"));
                System.out.println("Subtotal: $" + rs.getInt("subtotal"));
                mySQLController.displayImageFromDatabase("Return Item", rs.getString("items"), rs.getBlob("returnImage"));
            }
        } catch (SQLException throwables) {
            System.out.println("Error displaying apparel on our side");
        }
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    /**
     * @return the cart associated with the shoppingsession
     */
    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    /**
     * @return the card associated with the shoppingsession
     */
    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    /**
     * @param shippingAddress is the shipping address for our shoppingsession
     */
    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public String getBillingAddress() {
        return billingAddress;
    }

    /**
     * @param billingAddress is the billing address for our shoppingsession
     */
    public void setBillingAddress(String billingAddress) {
        this.billingAddress = billingAddress;
    }

    public RefundCart getRefundCart() {
        return refundCart;
    }

    public void setRefundCart(RefundCart refundCart) {
        this.refundCart = refundCart;
    }
}
