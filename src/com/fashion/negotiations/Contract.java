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

    /**
     * @param date is the date when the contract was signed
     * @param franchiseFee
     * @param annualFee
     * @param percentProfit
     * @param yieldRate
     * @param itemsPerMonth
     * @param employeeSignature
     * @param businessSignature
     */
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

    /**
     *
     */
    public Contract(){}

    /**
     * @return
     */
    public String getDate() {
        return date;
    }

    /**
     * @param date sets the date of our contract
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * @return the date of our contract
     */
    public String getFranchiseFee() {
        return franchiseFee;
    }

    /**
     * @param franchiseFee sets the franchise fee value
     */
    public void setFranchiseFee(String franchiseFee) {
        this.franchiseFee = franchiseFee;
    }

    /**
     * @return the franchise fee
     */
    public String getAnnualFee() {
        return annualFee;
    }

    /**
     * @param annualFee sets the annual fee paid by the manufacturer
     */
    public void setAnnualFee(String annualFee) {
        this.annualFee = annualFee;
    }

    /**
     * @return the value for percent profit we recieve from every sale
     */
    public String getPercentProfit() {
        return percentProfit;
    }

    /**
     * @param percentProfit set the value for percent profit we recieve from every sale
     */
    public void setPercentProfit(String percentProfit) {
        this.percentProfit = percentProfit;
    }

    /**
     * @return the yield rate the manufacturer guarantees us
     */
    public String getYieldRate() {
        return yieldRate;
    }

    /**
     * @param yieldRate set the yield rate the manufacturer guarantees us
     */
    public void setYieldRate(String yieldRate) {
        this.yieldRate = yieldRate;
    }

    /**
     * @return the items/month value the manufacturer guarantees us
     */
    public String getItemsPerMonth() {
        return itemsPerMonth;
    }

    /**
     * @param itemsPerMonth set the items/month value the manufacturer guarantees us
     */
    public void setItemsPerMonth(String itemsPerMonth) {
        this.itemsPerMonth = itemsPerMonth;
    }

    /**
     * @return the employee who signed the contract
     */
    public String getEmployeeSignature() {
        return employeeSignature;
    }

    /**
     * @param employeeSignature set the employee who signed the contract
     */
    public void setEmployeeSignature(String employeeSignature) {
        this.employeeSignature = employeeSignature;
    }

    /**
     * @return the business who signed the contract
     */
    public String getBusinessSignature() {
        return businessSignature;
    }

    /**
     * @param businessSignature set the business who signed the contract
     */
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

    /**
     * @return the formatted contract for old contracts
     * (only difference is contains finalization date and both signatures)
     */
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

