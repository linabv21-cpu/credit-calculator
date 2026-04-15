package com.galya.credit.calculator;

import com.galya.credit.model.Payment;
import java.util.List;

public interface ICalculator {
    List<Payment> calculate(double amount, double rate, int years);
    double getMonthlyPayment();//Ежемесячный платеж
    double getTotalAmount();//Общая сумма
    double getOverpayment();//Переплата
}