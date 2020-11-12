package com.fashion;

import com.fashion.employees.Employee;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;

public class MySQLController {
    private String url;
    private String username;
    private String password;

    /**
     * adds the connection information
     */
    public MySQLController() {
        this.url = "jdbc:mysql://127.0.0.1:3306/fashion_studio";
        this.username = "root";
        this.password = "";
    }

    /**
     * runs a push command (INSERT, UPDATE)
     * @param command is the SQL command we want to run
     */
    public void runPushCommand(String command) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(this.url, this.username, this.password);
            Statement mystatement = con.createStatement();
            mystatement.execute(command);
            con.close();
        } catch (Exception e){
            System.out.println("Unable to connect");
        }
    }

    /**
     * runs the pull command (SELECT)
     * @param command is the query we want to run
     * @return the ResultSet with our query results
     */
    public ResultSet runPullCommand(String command) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(this.url, this.username, this.password);
            Statement mystatement = con.createStatement();
            ResultSet response = mystatement.executeQuery(command);

            if(response.next()){
                return response;
            }
            con.close();
            return null;
        } catch (Exception e){
            return null;
        }
    }

    public boolean pushRefund(String preparedStatement, int refundShoppingSession, File image){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(this.url, this.username, this.password);
            PreparedStatement ps = con.prepareStatement(preparedStatement);
            ps.setInt(1, 1);
            ps.setBinaryStream(2, new FileInputStream(image), (int) image.length());
            ps.setInt(3, refundShoppingSession);
            ps.executeUpdate();
            con.close();

            System.out.println("\nYour return has been accepted :)\n");

            return true;
        } catch (Exception e){
            return false;
        }
    }

    public boolean pushNewEmployee(Employee newEmployee, String headshot){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(this.url, this.username, this.password);
            PreparedStatement ps = con.prepareStatement("INSERT INTO `employees` (`eid`,`firstName`,`lastName`," +
                    "`jobTitle`,`phoneNum`,`authorityLevel`,`username`,`password`,`salary`,`headshot`,`isActive`) " +
                    "VALUES (?,?,?,?,?,?,?,?,?,?,?)");
            ps.setInt(1, newEmployee.getEid());
            ps.setString(2, newEmployee.getName().split(" ")[0]);
            ps.setString(3, newEmployee.getName().split(" ")[1]);
            ps.setString(4, newEmployee.getJobTitle());
            ps.setString(5, "");
            ps.setInt(6, 0);
            ps.setString(7, newEmployee.getName().split(" ")[1] + newEmployee.getName().split(" ")[0].charAt(0));
            ps.setString(8, "password");
            ps.setInt(9, (int) newEmployee.getPayStubInfo().getSalary());
            ps.setBlob(10, new FileInputStream(headshot));
            ps.setInt(11, 1);
            ps.executeUpdate();
            con.close();

            return true;
        } catch (Exception e){
            System.out.println("Error adding the new employee");
            return false;
        }
    }

    /**
     * @param title is the title of the JFrame image
     * @param dbItemName is the name of the saved jpg
     * @param dbImage is the image pulled from the database
     */
    public void displayImageFromDatabase(String title, String dbItemName, Blob dbImage){
        /**
         * creates a JFrame for our apparel image
         */
        try {
            File file = new File(dbItemName.trim() + ".png");
            FileOutputStream fos = new FileOutputStream(file);
            byte b[];
            Blob blob = dbImage;
            b= blob.getBytes(1,(int) blob.length());
            fos.write(b);
            fos.close();

            JFrame editorFrame = new JFrame(title);
            editorFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

            BufferedImage image = null;
            image = ImageIO.read(new File(dbItemName.trim() + ".png"));

            ImageIcon imageIcon = new ImageIcon(image);
            JLabel jLabel = new JLabel();
            jLabel.setIcon(imageIcon);
            editorFrame.getContentPane().add(jLabel, BorderLayout.CENTER);

            editorFrame.pack();
            editorFrame.setLocationRelativeTo(null);
            editorFrame.setVisible(true);
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return the username to login to database
     */
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}