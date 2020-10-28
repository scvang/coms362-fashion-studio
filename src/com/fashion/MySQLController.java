package com.fashion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class MySQLController {
    private String url;
    private String username;
    private String password;

    public MySQLController() {
        this.url = "localhost";
        this.username = "root";
        this.password = "";
    }

    public ResultSet runPushCommand(String command) {
        try {
//            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(this.url, this.username, this.password);
            Statement mystatement = con.createStatement();
//            ResultSet codespeedy = mystatement.executeQuery("select * from data where username=\"ironman\"");
            ResultSet response = mystatement.executeQuery(command);

            if(response.next()){
                return response;
            }

            return null;
        } catch (Exception e){
            return null;
        }
    }

    public ResultSet runPullCommand(String command) {
        try {
//            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(this.url, this.username, this.password);
            Statement mystatement = con.createStatement();
//            ResultSet codespeedy = mystatement.executeQuery("select * from data where username=\"ironman\"");
            ResultSet response = mystatement.executeQuery(command);

            if(response.next()){
                return response;
            }

            return null;
        } catch (Exception e){
            return null;
        }
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

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
