    package com.galya.credit.calculator;
import com.galya.credit.model.Payment;
import java.util.ArrayList;
import java.util.List;
import com.galya.credit.util.InputValidator;


public class AnnuityCalculator implements ICalculator {


    private double monthlyPayment;    // Ежемесячный платёж
    private double totalAmount;       // Общая сумма выплат
    private double overpayment; // Переплата (проценты)
    private double downPayment;


    @Override
    public List<Payment> calculate(double amount, double rate, int years) {
        // Создаём список, куда будем добавлять платежи
        List<Payment> schedule = new ArrayList<>();


        int months = years * 12;// Общее количество месяцев
        double monthlyRate = rate / 12 / 100;
        //Годовую ставку делим на 12 месяцев
        // и делим на 100 чтоб избавиться от %

        //    A = S × (i × (1+i)n) / ((1+i)n - 1)

        double coefficient = (monthlyRate * Math.pow(1 + monthlyRate, months)) /
                (Math.pow(1 + monthlyRate, months) - 1);


        monthlyPayment = amount * coefficient;
        // Ежемесячный платёж

        totalAmount = monthlyPayment * months;
        // Общая сумма

        overpayment = totalAmount - amount;
        // Переплата

        double balance = amount;
        // Переменная для отслеживания остатка долга

        //каждый месяц
        for (int month = 1; month <= months; month++) {
            // Проценты за этот месяц (от остатка долга)
            double interestPayment = balance * monthlyRate;
            // Погашение основного долга (платёж минус проценты)
            double principalPayment = monthlyPayment - interestPayment;

            balance -= principalPayment;
            // Новый остаток долга он уменьшается

            if (month == months) {
                balance = 0;
                //Если это последний месяц нам нужен 0
            }


            Payment payment = new Payment(
                    month,                // номер месяца
                    monthlyPayment,       // общая сумма платежа
                    principalPayment,     // погашение долга
                    interestPayment,      // проценты
                    balance);               // остаток


            schedule.add(payment);//+платёж
        }


        return schedule;
    }


    @Override
    public double getMonthlyPayment() {
        return monthlyPayment;
    }


    @Override
    public double getTotalAmount() {
        return totalAmount;
    }


    @Override
    public double getOverpayment() {
        return overpayment;
    }
    public List<Payment> calculate(double amount, double rate, int years, double downPayment) {
        InputValidator.validateDownPayment(downPayment, amount);

        this.downPayment = downPayment;
        double loanAmount = amount - downPayment;

        List<Payment> schedule = new ArrayList<>();
        int months = years * 12;
        double monthlyRate = rate / 12 / 100;

        double coefficient = (monthlyRate * Math.pow(1 + monthlyRate, months)) /
                (Math.pow(1 + monthlyRate, months) - 1);

        monthlyPayment = loanAmount * coefficient;
        totalAmount = monthlyPayment * months;
        overpayment = totalAmount - loanAmount;// Переплата от суммы кредита
        double balance = loanAmount;

        for (int month = 1; month <= months; month++) {
            double interestPayment = balance * monthlyRate;
            double principalPayment = monthlyPayment - interestPayment;
            balance -= principalPayment;

            if (month == months) {
                balance = 0;
            }

            Payment payment = new Payment(
                    month,
                    monthlyPayment,
                    principalPayment,
                    interestPayment,
                    balance);

            schedule.add(payment);
        }

        return schedule;
    }
    public double getDownPayment() {
        return downPayment;
    }
}