package com.fashion;

public class Business {
    private int bid;
    private String name;
    private String address;
    private String phoneNum;
    private String busType;

    public Business(int bid, String name, String address, String phoneNum, String busType) {
        this.bid = bid;
        this.name = name;
        this.address = address;
        this.phoneNum = phoneNum;
        this.busType = busType;
    }

    public Business(String name, String address, String phoneNum) {
        this.name = name;
        this.address = address;
        this.phoneNum = phoneNum;
    }

    public Business(String name, String phoneNum) {
        this.name = name;
        this.phoneNum = phoneNum;
    }

    public Business() { }

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getBusType() {
        return busType;
    }

    public void setBusType(String busType) {
        this.busType = busType;
    }

    @Override
    public String toString() {
        return "\nBusiness: " +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phoneNum='" + phoneNum + '\'';
    }
}
