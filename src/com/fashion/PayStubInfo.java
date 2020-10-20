package com.fashion;

public class PayStubInfo {
    private double salary;
    private double bonus;
    private int bankAccountNum;
    private int bankRoutingNum;

    public PayStubInfo(double salary, double bonus, int bankAccountNum, int bankRoutingNum) {
        this.salary = salary;
        this.bonus = bonus;
        this.bankAccountNum = bankAccountNum;
        this.bankRoutingNum = bankRoutingNum;
    }

    public PayStubInfo(double salary, int bankAccountNum, int bankRoutingNum) {
        this.salary = salary;
        this.bankAccountNum = bankAccountNum;
        this.bankRoutingNum = bankRoutingNum;
    }

    public PayStubInfo(double salary, double bonus) {
        this.salary = salary;
        this.bonus = bonus;
    }

    public PayStubInfo(double salary) {
        this.salary = salary;
    }

    public PayStubInfo() {}

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public double getBonus() {
        return bonus;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

    public int getBankAccountNum() {
        return bankAccountNum;
    }

    public void setBankAccountNum(int bankAccountNum) {
        this.bankAccountNum = bankAccountNum;
    }

    public int getBankRoutingNum() {
        return bankRoutingNum;
    }

    public void setBankRoutingNum(int bankRoutingNum) {
        this.bankRoutingNum = bankRoutingNum;
    }

    public double getPay() { return salary + bonus; }

    @Override
    public String toString() {
        return "PayStub: " +
                "salary=" + salary +
                ", bonus=" + bonus +
                ", bankAccountNum=" + bankAccountNum +
                ", bankRoutingNum=" + bankRoutingNum;
    }
}
