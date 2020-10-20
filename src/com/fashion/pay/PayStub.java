package com.fashion.pay;

public class PayStub {
    int eid;
    String date;
    PayStubInfo p;

    public PayStub(int eid, String date, PayStubInfo p) {
        this.eid = eid;
        this.date = date;
        this.p = p;
    }

    public PayStub(String date, PayStubInfo p) {
        this.p = p;
    }

    public PayStub() {
    }

    public int getEid() {
        return eid;
    }

    public void setEid(int eid) {
        this.eid = eid;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public PayStubInfo getP() {
        return p;
    }

    public void setP(PayStubInfo p) {
        this.p = p;
    }
}
