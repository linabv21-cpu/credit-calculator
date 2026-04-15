package com.galya.credit.model;

public class Payment {
    private int month;
    private double payment;
    private double principal;
    private double interest;
    private double balance;

    public Payment(int month, double payment, double principal, double interest, double balance) {
        this.month = month;
        this.payment = payment;
        this.principal = principal;
        this.interest = interest;
        this.balance = balance;
    }

    public int getMonth() {
        return month;
    }

    public double getPayment() {
        return payment;
    }
    public double getPrincipal() {
        return principal;
    }

    public double getInterest() {
        return interest;
    }

    public double getBalance() {
        return balance;
    }

    @Override
    public String toString() {
        return String.format("Месяц %d: Платёж = %.2f (Долг: %.2f + Проценты: %.2f), Остаток: %.2f",
                month, payment, principal, interest, balance);
    }
}