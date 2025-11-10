package me.project;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        double principle = inputs("Principle(Rs.10K-Rs.1M) : ",10000,1000000);
        double annualInterest = inputs("Annual Interrest Rate : ",1,30);
        short years = (short) inputs("Period - ",1,30);
        double mothlyPayment = monthlyPayment(annualInterest,principle,years);

        paymentShedul(principle,years,annualInterest);


    }

    public static double inputs(String message,double minValue, double maxValue){
        Scanner scanner = new Scanner(System.in);
        double value = 0;
        while (true){
            System.out.print(message);
            value = Double.parseDouble(scanner.nextLine());

            if(value>=minValue && value<=maxValue){
                break;
            }
            else{
                System.out.println("Enter Value Between "+ "Rs."+(int)minValue+" - "+"Rs."+(int)maxValue);
            }
        }
        return value;

    }

    public static double monthlyPayment(double annualInterest, double principle, int years){
        double monthlyPayment = 0;
        double monthlyInterest = annualInterest/100/12;
        int installments = years*12;

        monthlyPayment = principle*(monthlyInterest*Math.pow((1+monthlyInterest), installments))
                /(Math.pow((1+monthlyInterest),installments)-1);

        NumberFormat currency = NumberFormat.getCurrencyInstance(new Locale("eng", "LK"));
        String formatMonthlyPayment = currency.format(monthlyPayment);

        System.out.println("Mothly Payment : "+formatMonthlyPayment);

        return monthlyPayment;

    }

    public static void paymentShedul(double princeple, int years, double annualInterest ){
        int installments = years*12;
        double monthlyInterest = annualInterest/100/12;
        double balance = 0;
        System.out.println();
        System.out.println("                Payment Schedule    ");
        System.out.println("-----------------------------------------------");
        for (int completePayments = 1;installments>=completePayments;completePayments++){
            balance = princeple*(Math.pow((1+monthlyInterest),installments)-Math.pow((1+monthlyInterest),completePayments))/
                    (Math.pow((1+monthlyInterest),installments)-1);
            NumberFormat currency = NumberFormat.getCurrencyInstance(new Locale("eng", "LK"));
            String formatBalance = currency.format(balance);
            System.out.println("After Complete "+completePayments+" Payment Balance - "+formatBalance);
        }
    }
}
