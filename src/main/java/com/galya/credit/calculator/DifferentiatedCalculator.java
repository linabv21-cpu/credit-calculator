package com.galya.credit.calculator;

import com.galya.credit.model.Payment;
import com.galya.credit.util.InputValidator;
import java.util.ArrayList;
import java.util.List;

public class DifferentiatedCalculator implements ICalculator {
    private double totalAmount;
    private double overpayment;
    private double downPayment;

    @Override
    public List<Payment> calculate(double amount, double rate, int years) {
        return calculate(amount, rate, years, 0);  // Взнос = 0
    }

    public List<Payment> calculate(double amount, double rate, int years, double downPayment) {
        InputValidator.validateDownPayment(downPayment, amount);

        this.downPayment = downPayment;
        double loanAmount = amount - downPayment;

        List<Payment> schedule = new ArrayList<>();

        int months = years * 12;
        double monthlyRate = rate / 12 / 100;

        double basePayment = loanAmount / months;

        double balance = loanAmount;
        double totalMonthlyPayment = 0;

        for (int month = 1; month <= months; month++) {
            double interestPayment = balance * monthlyRate;

            double principalPayment = basePayment;

            double monthlyPayment = principalPayment + interestPayment;
            totalMonthlyPayment += monthlyPayment;

            balance -= principalPayment;

            if (month == months) {
                balance = 0;
            }

            Payment payment = new Payment(
                    month,
                    monthlyPayment,
                    principalPayment,
                    interestPayment,
                    balance
            );

            schedule.add(payment);
        }

        totalAmount = totalMonthlyPayment;
        overpayment = totalAmount - loanAmount;

        return schedule;
    }

    @Override
    public double getMonthlyPayment() {
        return 0;
    }

    @Override
    public double getTotalAmount() {
        return totalAmount;
    }

    @Override
    public double getOverpayment() {
        return overpayment;
    }
    public double getDownPayment() {
        return downPayment;
    }
}