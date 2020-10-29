package com.fashion.negotiations;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Contract {
    private String date;
    private String franchiseFee;
    private String annualFee;
    private String percentProfit;
    private String yieldRate;
    private String itemsPerMonth;
    private String employeeSignature;
    private String businessSignature;

    public Contract(String date, String franchiseFee, String annualFee, String percentProfit,
                    String yieldRate, String itemsPerMonth, String employeeSignature, String businessSignature) {
        this.date = date;
        this.franchiseFee = franchiseFee;
        this.annualFee = annualFee;
        this.percentProfit = percentProfit;
        this.yieldRate = yieldRate;
        this.itemsPerMonth = itemsPerMonth;
        this.employeeSignature = employeeSignature;
        this.businessSignature = businessSignature;
    }

    public Contract(){}

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getFranchiseFee() {
        return franchiseFee;
    }

    public void setFranchiseFee(String franchiseFee) {
        this.franchiseFee = franchiseFee;
    }

    public String getAnnualFee() {
        return annualFee;
    }

    public void setAnnualFee(String annualFee) {
        this.annualFee = annualFee;
    }

    public String getPercentProfit() {
        return percentProfit;
    }

    public void setPercentProfit(String percentProfit) {
        this.percentProfit = percentProfit;
    }

    public String getYieldRate() {
        return yieldRate;
    }

    public void setYieldRate(String yieldRate) {
        this.yieldRate = yieldRate;
    }

    public String getItemsPerMonth() {
        return itemsPerMonth;
    }

    public void setItemsPerMonth(String itemsPerMonth) {
        this.itemsPerMonth = itemsPerMonth;
    }

    public String getEmployeeSignature() {
        return employeeSignature;
    }

    public void setEmployeeSignature(String employeeSignature) {
        this.employeeSignature = employeeSignature;
    }

    public String getBusinessSignature() {
        return businessSignature;
    }

    public void setBusinessSignature(String businessSignature) {
        this.businessSignature = businessSignature;
    }

    @Override
    public String toString() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();

        return "Current Date: " + formatter.format(date) +
                "\nFrachise Fee: $" + franchiseFee +
                "\nAnnual Fee: " + annualFee + "%" +
                "\nPercent Profit: " + percentProfit + "%" +
                "\nYield Rate: " + yieldRate + "%" +
                "\nProduction Rate: " + itemsPerMonth + " items/month";
    }

    public String toOldString() {
        return "Finalization Date: " + date +
                "\nFrachise Fee: $" + franchiseFee +
                "\nAnnual Fee: " + annualFee + "%" +
                "\nPercent Profit: " + percentProfit + "%" +
                "\nYield Rate: " + yieldRate + "%" +
                "\nProduction Rate: " + itemsPerMonth + " items/month" +
                "\nEmployee Signature: " + employeeSignature +
                "\nBusiness Signature: " + businessSignature;
    }
}

