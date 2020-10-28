package com.fashion.pay;

public class Card {
    private String cardNum;
    private int endMonth;
    private int endYear;
    private int code;
    private String billingAddress;

    public Card(String cardNum, int endMonth, int endYear, int code, String billingAddress) {
        this.cardNum = cardNum;
        this.endMonth = endMonth;
        this.endYear = endYear;
        this.code = code;
        this.billingAddress = billingAddress;
    }

    public String getCardNum() {
        return cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    public int getEndMonth() {
        return endMonth;
    }

    public void setEndMonth(int endMonth) {
        this.endMonth = endMonth;
    }

    public int getEndYear() {
        return endYear;
    }

    public void setEndYear(int endYear) {
        this.endYear = endYear;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(String billingAddress) {
        this.billingAddress = billingAddress;
    }
}
