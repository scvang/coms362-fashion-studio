package com.fashion.pay;

public class Card {
    private String cardNum;
    private int endMonth;
    private int endYear;
    private String code;

    public Card(String cardNum, int endMonth, int endYear, String code) {
        this.cardNum = cardNum;
        this.endMonth = endMonth;
        this.endYear = endYear;
        this.code = code;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
