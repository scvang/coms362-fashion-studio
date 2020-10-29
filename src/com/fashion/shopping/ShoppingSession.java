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

    /**
     * could be used in the future for saving a shoppingsession state
     * @param sid
     * @param cart
     * @param card
     * @param shippingAddress
     * @param billingAddress
     */
    public ShoppingSession(int sid, Cart cart, Card card, String shippingAddress, String billingAddress) {
        this.sid = sid;
        this.cart = cart;
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
        this.card = new Card();
    }

    /**
     * basic constructor that takes in no values, but instantiates it with the next option or base option
     */
    public ShoppingSession() {
        this.sid = initSessionId();
        this.cart = new Cart();
        this.card = new Card();
    }

    /**
     * @return the next available session id value
     */
    private int initSessionId(){
        int sid = -1;
        try {
            ResultSet rs = mySQLController.runPullCommand("SELECT * FROM `shoppingsessions` ORDER BY `shoppingsessions`.`sid` DESC");

            if(rs != null){
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

    /**
     * Displays all the apparel from the backend
     */
    public void displayApparel(){
        try {
            ResultSet rs = mySQLController.runPullCommand("SELECT * FROM `inventory`");

            while(rs.next()){
                System.out.println("$" + rs.getInt("price") + " " + rs.getString("brandName") + " "
                        + rs.getString("itemName") + "\nDescription: " + rs.getString("color") + " " +
                        rs.getString("category") + "\nS: " + rs.getInt("quantitySmall") + " M: "
                        + rs.getInt("quantityMedium") + " L: " + rs.getInt("quantityLarge"));
                System.out.println();
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
                File file = new File(rs.getString("itemName") + ".png");
                FileOutputStream fos = new FileOutputStream(file);
                byte b[];
                Blob blob;

                blob = rs.getBlob("image");
                b=blob.getBytes(1,(int)blob.length());
                fos.write(b);
                fos.close();

                /**
                 * creates a JFrame for our apparel image
                 */
                JFrame editorFrame = new JFrame("Apparel Image");
                editorFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

                BufferedImage image = ImageIO.read(new File(rs.getString("itemName") + ".png"));
                ImageIcon imageIcon = new ImageIcon(image);
                JLabel jLabel = new JLabel();
                jLabel.setIcon(imageIcon);
                editorFrame.getContentPane().add(jLabel, BorderLayout.CENTER);

                editorFrame.pack();
                editorFrame.setLocationRelativeTo(null);
                editorFrame.setVisible(true);
            } else {
                System.out.println("Unable to find your product");
            }
        } catch (SQLException | IOException throwables) {
            System.out.println("Error displaying apparel on our side");
        }
    }

    /**
     * adds the completed shopping session to the database as a record
     */
    public void addShoppingSession(){
        StringBuilder items = new StringBuilder();
        for(Apparel item : cart.getItems()){
            items.append(item.getItemName()).append("\n");
        }

        try {
            mySQLController.runPushCommand("INSERT INTO SHOPPINGSESSIONS (SID,ITEMS,SUBTOTAL,TAXRATE,CARDNUM," +
                    "CARDMONTH,CARDYEAR,CARDCODE,SHIPPINGADDRESS,BILLINGADDRESS) VALUES ('" + sid + "','" + items.toString() +
                    "','" + (cart.getSubtotal() + (cart.getSubtotal() * (cart.getTaxRate() / 100.0))) + "','" + cart.getTaxRate() + "','" + card.getCardNum() + "','" + card.getEndMonth() + "','"
                    + card.getEndYear() + "','" + card.getCode() + "','" + shippingAddress + "','" + billingAddress + "')");

        } catch (Exception e) {
            System.out.println("ERROR: Unable to finalize purchase");
        }
    }

    /**
     * updates the inventory on the backend to reflect the change in purchased items
     */
    public void updateInventory(){
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
}
