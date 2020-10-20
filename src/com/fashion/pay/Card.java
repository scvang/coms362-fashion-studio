package com.fashion.pay;

public class Card {
    private int cardNum;
    private int endMonth;
    private int endYear;
    private int code;

    public Card(int cardNum, int endMonth, int endYear, int code) {
        this.cardNum = cardNum;
        this.endMonth = endMonth;
        this.endYear = endYear;
        this.code = code;
    }

    public int getCardNum() {
        return cardNum;
    }

    public void setCardNum(int cardNum) {
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
}
