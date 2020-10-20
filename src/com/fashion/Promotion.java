package com.fashion;

import java.util.Date;

public class Promotion {
    private int pid; //Promotion id
    private String businessName;
    private String promotionText;
    private int location; //number-based location for inside of studio
    private double dollarAmount;

    public Promotion(int pid, String businessName, String promotionText, int location, double dollarAmount) {
        this.pid = pid;
        this.businessName = businessName;
        this.promotionText = promotionText;
        this.location = location;
        this.dollarAmount = dollarAmount;
    }

    public Promotion(String businessName, String promotionText, double dollarAmount) {
        this.businessName = businessName;
        this.promotionText = promotionText;
        this.dollarAmount = dollarAmount;
    }

    public Promotion() {
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getPromotionText() {
        return promotionText;
    }

    public void setPromotionText(String promotionText) {
        this.promotionText = promotionText;
    }

    public int getLocation() {
        return location;
    }

    public void setLocation(int location) {
        this.location = location;
    }

    public double getDollarAmount() {
        return dollarAmount;
    }

    public void setDollarAmount(double dollarAmount) {
        this.dollarAmount = dollarAmount;
    }

    @Override
    public String toString() {
        return "\nPromotion: " +
                "pid=" + pid +
                ", businessName='" + businessName + '\'' +
                ", promotionText='" + promotionText + '\'' +
                ", location=" + location +
                ", dollarAmount=" + dollarAmount;
    }
}
