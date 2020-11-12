package com.fashion.shopping;

public class RefundSession {
    int sid;
    String date;
    String items;
    int subtotal;

    public RefundSession(int sid, String date, String items, int subtotal) {
        this.sid = sid;
        this.date = date;
        this.items = items;
        this.subtotal = subtotal;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getItems() {
        return items;
    }

    public void setItems(String items) {
        this.items = items;
    }

    public int getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(int subtotal) {
        this.subtotal = subtotal;
    }
}
