 package com.galya.credit.util;

public class InputValidator {

    public static void validateAmount(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Сумма кредита больше 0; Введено: " + amount);
        }
    }

    public static void validateRate(double rate) {
        if (rate <= 0 || rate > 100) { //ну больше же 100 невозможно вроде?
            throw new IllegalArgumentException("Ставка  от 0 до 100%; Введено: " + rate);
        }
    }

    public static void validateYears(int years) {
        //больше 0 лет
        if (years <= 0 || years > 30) {
            throw new IllegalArgumentException("Срок должен быть от 1 до 30 лет;Введено: " + years);
        }
    }

    public static void validateDownPayment(double downPayment, double amount) {
        //не отрицательный
        if (downPayment < 0) {
            throw new IllegalArgumentException("Первоначальный взнос не может быть отрицательным;Введено: " + downPayment);
        }

        if (downPayment >= amount) {
            throw new IllegalArgumentException("Первоначальный взнос не может быть больше суммы кредита! " +
                            "Взнос: " + downPayment + ", Сумма: " + amount);
        }
    }

    public static void validateCreditType(String type) {

        if (type == null || (!type.equalsIgnoreCase("annuity") &&
                !type.equalsIgnoreCase("differentiated"))) {
            throw new IllegalArgumentException("Тип кредита должен быть 'annuity' или 'differentiated'! Введено: " + type);
        }
    }

    public static void validateAll(double amount, double rate, int years) {
        validateAmount(amount);
        validateRate(rate);
        validateYears(years);
    }
}