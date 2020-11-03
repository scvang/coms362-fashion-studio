package com.fashion.pay;

public class Card {
    private String cardNum;
    private String endMonth;
    private String endYear;
    private String code;
    private String billingAddress;

    public Card(String cardNum, String endMonth, String endYear, String code, String billingAddress) {
        this.cardNum = cardNum;
        this.endMonth = endMonth;
        this.endYear = endYear;
        this.code = code;
        this.billingAddress = billingAddress;
    }

    public Card(){}

    public String getCardNum() {
        return cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    public String getEndMonth() {
        return endMonth;
    }

    public void setEndMonth(String endMonth) {
        this.endMonth = endMonth;
    }

    public String getEndYear() {
        return endYear;
    }

    public void setEndYear(String endYear) {
        this.endYear = endYear;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(String billingAddress) {
        this.billingAddress = billingAddress;
    }
}
