package com.fashion.negotiations;

import com.fashion.Business;
import com.fashion.MySQLController;
import com.fashion.employees.Employee;
import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.PdfWriter;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

public class ContractSession {
    private List<Business> businesses;
    private Contract contract;
    private MySQLController mySQLController = new MySQLController();

    public ContractSession(List<Business> businesses, Contract contract) {
        this.businesses = businesses;
        this.contract = contract;
    }

    /**
     * basic constructor to add the bare minimum elements
     */
    public ContractSession(){
        businesses = getManufacturers();
        contract = new Contract();
    }

    /**
     * @return the business that can manufacture our clothes
     */
    private List<Business> getManufacturers(){
        List<Business> businesses = new ArrayList<>();
        try {
            ResultSet rs = mySQLController.runPullCommand("SELECT * FROM `businesses` WHERE `busType` = 'Manufacturer'");

            if(rs != null){
                businesses.add(new Business(rs.getInt("bid"), rs.getString("name"),
                        rs.getString("address"), rs.getString("number"), rs.getString("busType")));

                while(rs.next()){
                    businesses.add(new Business(rs.getInt("bid"), rs.getString("name"),
                            rs.getString("address"), rs.getString("number"), rs.getString("busType")));
                }
            }
        } catch (SQLException throwables) {
            System.out.println("Error creating a shopping session");
        }
        return businesses;
    }

    /**
     * runs the negotiation process
     */
    public void negotiate(){
        Random rand = new Random();
        Employee negotiator = new Employee().getRandomSalesEmployee();

        /**
         * if there is no sales employee then do not proceed
         */
        if(negotiator == null){
            return;
        }

        boolean foundDeal = false;
        /**
         * loop through the manufacturing businesses to negotiate a deal
         */
        for(Business business : businesses){
            Boolean stillNegotiating = true;
            System.out.println(negotiator.getName() + ", you are on call with " + business.getName() + "\n");
            String response = "";

            Scanner in = new Scanner (System.in);
            while(contract.getFranchiseFee() == null && stillNegotiating){
                int tempFranchiseFee = rand.nextInt(30001) + 20000;
                System.out.println("They are offering a franchise fee of $" + tempFranchiseFee);
                System.out.println("Do you accept? (y/n)");
                response = in.nextLine().trim();

                if(response.equals("y")){
                    contract.setFranchiseFee(Integer.toString(tempFranchiseFee));
                } else {
                    if(rand.nextInt(11) > 9){
                        stillNegotiating = false;
                    }
                }
                System.out.println();
            }

            while(contract.getAnnualFee() == null && stillNegotiating){
                int tempAnnualFee = rand.nextInt(10) + 1;
                System.out.println("They are offering an annual fee rate of " + tempAnnualFee + "%");
                System.out.println("Do you accept? (y/n)");
                response = in.nextLine().trim();

                if(response.equals("y")){
                    contract.setAnnualFee(Integer.toString(tempAnnualFee));
                } else {
                    if(rand.nextInt(11) > 9){
                        stillNegotiating = false;
                    }
                }
                System.out.println();
            }

            while(contract.getPercentProfit() == null && stillNegotiating){
                int tempPercentProfit = rand.nextInt(10) + 80;
                System.out.println("They are offering to give you " + tempPercentProfit + "% of all profits");
                System.out.println("Do you accept? (y/n)");
                response = in.nextLine().trim();

                if(response.equals("y")){
                    contract.setPercentProfit(Integer.toString(tempPercentProfit));
                } else {
                    if(rand.nextInt(11) > 9){
                        stillNegotiating = false;
                    }
                }
                System.out.println();
            }

            while(contract.getYieldRate() == null && stillNegotiating){
                int tempYieldRate = rand.nextInt(16) + 85;
                System.out.println("They are promising to provide a yield rate of " + tempYieldRate + "%");
                System.out.println("Do you accept? (y/n)");
                response = in.nextLine().trim();

                if(response.equals("y")){
                    contract.setYieldRate(Integer.toString(tempYieldRate));
                } else {
                    if(rand.nextInt(11) > 9){
                        stillNegotiating = false;
                    }
                }
                System.out.println();
            }

            while(contract.getItemsPerMonth() == null && stillNegotiating){
                int tempItemsPerMonth = rand.nextInt(2000) + 3000;
                System.out.println("They are proposing that they can produce " + tempItemsPerMonth + " items/month");
                System.out.println("Do you accept? (y/n)");
                response = in.nextLine().trim();

                if(response.equals("y")){
                    contract.setItemsPerMonth(Integer.toString(tempItemsPerMonth));
                } else {
                    if(rand.nextInt(11) > 9){
                        stillNegotiating = false;
                    }
                }
                System.out.println();
            }

            if(stillNegotiating){
                System.out.println("Here's an overview of the contract, are you ready to sign? (y/n)");
                System.out.println(contract.toString());
                response = in.nextLine().trim();

                if(response.equals("y")){
                    contract.setEmployeeSignature(negotiator.getName());
                    contract.setBusinessSignature(business.getName());
                    finalizeContract(contract);
                    foundDeal = true;
                    break;
                } else if(response.equals("n")) {
                    System.out.println("They won't be happy about this");
                }
            } else {
                System.out.println(business.getName() + " has backed out of the negotiation. on to the next\n");
            }
        }

        if(!foundDeal){
            System.out.println("You were unable to negotiate a contract. The boss isn't going to be happy");
        }
    }

    /**
     * adds the new contract to our database
     * @param contract holds all the info for our manufacturing contract
     */
    private void finalizeContract(Contract contract){
        updateCurrentContract();

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();

        try {
            mySQLController.runPushCommand("INSERT INTO `contracts`(`signDate`, `franchiseFee`, `annualFee`, `percentOfProfit`, " +
                    "`yieldRate`, `itemsPerMonth`, `employeeSignature`, `businessSignature`, `activeContract`) VALUES ('" + formatter.format(date) +
                    "','" + contract.getFranchiseFee() + "','" + contract.getAnnualFee() + "','" + contract.getPercentProfit()
                    + "','" + contract.getYieldRate() + "','" + contract.getItemsPerMonth() + "','" +
                    contract.getEmployeeSignature() + "','" + contract.getBusinessSignature() + "','" + 1 + "')");

            System.out.println("Congrats on the new contract!\n");
        } catch (Exception e) {
            System.out.println("ERROR: unable to create the contract");
        }
    }

    /**
     * changes the old current contract to invactive
     */
    private void updateCurrentContract(){
        try {
            mySQLController.runPushCommand("UPDATE `contracts` SET `activeContract`=0 WHERE `activeContract`=1");
        } catch (Exception e) {
            System.out.println("ERROR: unable to dismantle the old contract");
        }
    }

    /**
     * grabs all the old contracts
     * outputs all the old contracts in the terminal
     */
    public void viewOldContracts(){
        try {
            ResultSet rs = mySQLController.runPullCommand("SELECT * FROM `contracts` WHERE `activeContract`=0");

            if(rs != null) {
                System.out.println(new Contract(rs.getString("signDate"), rs.getString("franchiseFee"),
                        rs.getString("annualFee"), rs.getString("percentOfProfit"), rs.getString("yieldRate"),
                        rs.getString("itemsPerMonth"), rs.getString("employeeSignature"),
                        rs.getString("businessSignature")).toOldString() + "\n");

                while(rs.next()){
                    System.out.println(new Contract(rs.getString("signDate"), rs.getString("franchiseFee"),
                            rs.getString("annualFee"), rs.getString("percentOfProfit"), rs.getString("yieldRate"),
                            rs.getString("itemsPerMonth"), rs.getString("employeeSignature"),
                            rs.getString("businessSignature")).toOldString() + "\n");
                }
            }
        } catch (SQLException throwables) {
            System.out.println("Error creating the old contracts");
        }
    }

    /**
     * grabs the current contract
     * creates a pdf and adds the contract to it
     * opens the pdf
     */
    public void viewCurrentContract(){
        Contract currentContract = null;
        try {
            ResultSet rs = mySQLController.runPullCommand("SELECT * FROM `contracts` WHERE `activeContract`=1");

            if(rs != null) {
                currentContract = new Contract(rs.getString("signDate"), rs.getString("franchiseFee"),
                        rs.getString("annualFee"), rs.getString("percentOfProfit"), rs.getString("yieldRate"),
                        rs.getString("itemsPerMonth"), rs.getString("employeeSignature"),
                        rs.getString("businessSignature"));
            } else {
                System.out.println("ERROR: unable to find the current contract");
                return;
            }
        } catch (SQLException throwables) {
            System.out.println("Error creating the new contract");
            return;
        }

        Document document = new Document();

        try {
            PdfWriter.getInstance(document, new FileOutputStream("current_contract.pdf"));
        } catch (DocumentException | FileNotFoundException e) {
            e.printStackTrace();
        }

        document.open();
        Paragraph p = new Paragraph();
        p.add("On " + currentContract.getDate() + " " + currentContract.getEmployeeSignature() + " and "
                + currentContract.getBusinessSignature() + " agreed to a contract to manufacture our clothes.");
        p.add("\nThe deal consisted of:");
        p.add("\nFranchise Fee: $" + currentContract.getFranchiseFee());
        p.add("\nAnnual Fee: " + currentContract.getAnnualFee() + "%");
        p.add("\nPercent of Profit: " + currentContract.getPercentProfit() + "%");
        p.add("\nYield Rate: " + currentContract.getYieldRate() + "%");
        p.add("\nItems Per Month: " + currentContract.getItemsPerMonth() + " items/month");

        try {
            document.add(p);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        document.close();

        if (Desktop.isDesktopSupported()) {
            try {
                File myFile = new File("current_contract.pdf");
                Desktop.getDesktop().open(myFile);
            } catch (IOException ex) {
                // no application registered for PDFs
            }
        }
    }

    public List<Business> getBusinesses() {
        return businesses;
    }

    public void setBusinesses(List<Business> businesses) {
        this.businesses = businesses;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }
}
