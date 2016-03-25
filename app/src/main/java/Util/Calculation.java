package Util;

/**
 * Created by angelagao on 3/24/16.
 */
public class Calculation {
    public double calculation(double purchasePrice, double downPayment, int termInYear, double interestRate, double tax, double insurance) {

        downPayment = downPayment / 100;
        double loanPrice = purchasePrice * (1 - downPayment);
        int termInMonth = termInYear * 12;
        double monthlyRate = interestRate /100 / 12;
        double propertyTaxInMonth = tax / 12;
        double insuranceInMonth = insurance / 12;


        double mortResult = (loanPrice * monthlyRate) /
                (1-Math.pow(1+monthlyRate, -termInMonth));
        mortResult += propertyTaxInMonth;
        mortResult += insuranceInMonth;

        return mortResult;
    }
}
