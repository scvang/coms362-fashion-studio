package com.fashion;

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