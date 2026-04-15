package com.galya.credit;
import com.galya.credit.util.InputValidator;
import com.galya.credit.calculator.AnnuityCalculator;
import com.galya.credit.calculator.DifferentiatedCalculator;
import com.galya.credit.model.Payment;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Кредитный калькулятор");
        System.out.println("=======================");

        AnnuityCalculator calculator = new AnnuityCalculator();

        double amount = 100000;
        double rate = 10;
        int years = 2;

        System.out.println("Параметры кредита:");
        System.out.println("  Сумма: " + amount + " руб.");
        System.out.println("  Ставка: " + rate + "% годовых");
        System.out.println("  Срок: " + years + " года");


        System.out.println("Ваш график платежей: ");
        List<Payment> schedule = calculator.calculate(amount, rate, years);


        System.out.println("График платежей - первые 3 месяца :");

        for (int i = 0; i < Math.min(3, schedule.size()); i++) {
            Payment payment = schedule.get(i);
            System.out.println(payment);
        }

        if (schedule.size() > 3) {
            System.out.println("Крайний месяц - ");
            Payment lastPayment = schedule.get(schedule.size() - 1);
            System.out.println(lastPayment);
            //если будет больше 3 покажется ещё последний
        }

        System.out.println(" Итог:");
        System.out.println("======================================");
        System.out.println("Ежемесячный платёж :" +
                String.format("%.2f", calculator.getMonthlyPayment()) + " руб.");
        System.out.println("  Общая сумма: " +
                String.format("%.2f", calculator.getTotalAmount()) + " руб.");
        System.out.println("  Переплата: " +
                String.format("%.2f", calculator.getOverpayment()) + " руб.");

        System.out.println("Валидация:");
        System.out.println("==========================");

        try {
            InputValidator.validateAmount(-100000);
            System.out.println("Ошибка");
        } catch (IllegalArgumentException e) {
            System.out.println("Пройдено :" + e.getMessage());
        }
        try {
            InputValidator.validateRate(-5);
            System.out.println("Ошибка");
        } catch (IllegalArgumentException e) {
            System.out.println("Пройдено : " + e.getMessage());
        }
        try {
            InputValidator.validateYears(0);
            System.out.println("Ошибка");
        } catch (IllegalArgumentException e) {
            System.out.println("Пройдено: " + e.getMessage());
        }
        try {
            InputValidator.validateAll(100000, 10, 2);
            System.out.println("Пройдено");
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка" + e.getMessage());
        }

        try {
            InputValidator.validateRate(150);
            System.out.println("Ошибка");
        } catch (IllegalArgumentException e) {
            System.out.println("Пройдено : " + e.getMessage());
        }

        try {
            InputValidator.validateYears(50);
            System.out.println("Ошибка");
        } catch (IllegalArgumentException e) {
            System.out.println("Пройдено : " + e.getMessage());
        }
        System.out.println("С первоначальным взносом:");
        System.out.println("========================================");

        AnnuityCalculator calcWithDownPayment = new AnnuityCalculator();
        double totalAmount = 100000;
        double downPayment = 20000;
        double rate2 = 10;
        int years2 = 2;

        System.out.println("Параметры:");
        System.out.println("Общая сумма: " + totalAmount + " руб.");
        System.out.println("Первоначальный взнос: " + downPayment + " руб.");
        System.out.println("Сумма кредита: " + (totalAmount - downPayment) + " руб.");
        System.out.println("Ставка : " + rate2 + "% годовых");
        System.out.println("Срок: " + years2 + " года");

        List<Payment> schedule2 = calcWithDownPayment.calculate(
                totalAmount, rate2, years2, downPayment
        );

        System.out.println("Первые 3 платежа:");
        System.out.println("========================================");
        for (int i = 0; i < Math.min(3, schedule2.size()); i++) {
            System.out.println(schedule2.get(i));
        }

        System.out.println("Итоги :");
        System.out.println("========================================");
        System.out.printf("Ежемесячный платёж: %.2f руб.%n",
                calcWithDownPayment.getMonthlyPayment());
        System.out.printf("Общая сумма выплат: %.2f руб.%n",
                calcWithDownPayment.getTotalAmount());
        System.out.printf("Переплата: %.2f руб.%n",
                calcWithDownPayment.getOverpayment());

        System.out.println("Сравним с кредитом без взноса:");
        System.out.println("========================================");
        System.out.printf("Без взноса: платёж %.2f руб., переплата %.2f руб.%n",
                calculator.getMonthlyPayment(), calculator.getOverpayment());
        System.out.printf("Со взносом: платёж %.2f руб., переплата %.2f руб.%n",
                calcWithDownPayment.getMonthlyPayment(), calcWithDownPayment.getOverpayment());
        System.out.printf("Экономия: %.2f руб.%n",
                calculator.getOverpayment() - calcWithDownPayment.getOverpayment());

        System.out.println("Дифференцированный калькулятор:");
        System.out.println("==================================");

        DifferentiatedCalculator diffCalculator = new DifferentiatedCalculator();
        double amount3 = 100000;
        double rate3 = 10;
        int years3 = 2;
        System.out.println("Параметры:");
        System.out.println("Сумма: " + amount3 + " руб.");
        System.out.println("Ставка: " + rate3 + "% годовых");
        System.out.println("Срок: " + years3 + " года");

        List<Payment> schedule3 = diffCalculator.calculate(amount3, rate3, years3);

        System.out.println("Первые 3 платежа:");
        System.out.println("==================================");
        for (int i = 0; i < Math.min(3, schedule3.size()); i++) {
            System.out.println(schedule3.get(i));
        }

        if (schedule3.size() > 3) {
            Payment lastPayment = schedule3.get(schedule3.size() - 1);
            System.out.println(lastPayment);
        }
        System.out.println("Итоги:");
        System.out.println("==================================");
        System.out.printf("Общая сумма: %.2f руб.%n", diffCalculator.getTotalAmount());
        System.out.printf("Переплата: %.2f руб.%n", diffCalculator.getOverpayment());

// Сравниваем с аннуитетом
        System.out.println("Сравнение платежей:");
        System.out.println("==================================");
        System.out.printf("Аннуитетный:переплата %.2f руб.%n",
                calculator.getOverpayment());
        System.out.printf("Дифференцированный: переплата %.2f руб.%n",
                diffCalculator.getOverpayment());

        double savings = calculator.getOverpayment() - diffCalculator.getOverpayment();
        if (savings > 0) {
            System.out.printf("Дифференцированный выгоднее на %.2f руб.%n", savings);
        } else {
            System.out.printf("Аннуитетный выгоднее на %.2f руб.%n", -savings);
        }


    }
}


