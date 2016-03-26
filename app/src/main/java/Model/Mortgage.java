package Model;

/**
 * Created by angelagao on 3/24/16.
 */
public class Mortgage {
    public double purchasePrice;
    public double downPayment;
    public int term;
    public double interestRate;
    public double tax;
    public double insurance;
    public int firstPaymentMonth;
    public int firstPaymentYear;

    public double monthlyMort;
    public double totalMort;

    public double getMonthlyMortgate() {
        downPayment = downPayment / 100;
        double loanPrice = purchasePrice * (1 - downPayment);
        int termInMonth = term * 12;
        double monthlyRate = interestRate /100 / 12;
        double propertyTaxInMonth = tax / 12;
        double insuranceInMonth = insurance / 12;


        double mortResult = (loanPrice * monthlyRate) /
                (1-Math.pow(1+monthlyRate, -termInMonth));
        mortResult += propertyTaxInMonth;
        mortResult += insuranceInMonth;

        this.monthlyMort = mortResult;
        return mortResult;
    }

    public double getTotalMortgate() {
        this.totalMort = getMonthlyMortgate() * term * 12;
        return getMonthlyMortgate() * term * 12;
    }

    public int[] getPayoffDate() {
        int[] date = new int[2];
        date[0] = firstPaymentMonth - 1;
        date[1] = firstPaymentYear + term;
        if(date[0] == 0) {
            date[0] = 12;
            date[1] -= 1;
        }
        return date;
    }

    public double getMonthlyRecord() {
        return this.monthlyMort;
    }

    public double getTotalRecord() {
        return this.totalMort;
    }

    public String getPayoffDateRecord() {
        String month = String.valueOf(firstPaymentMonth);
        String year = String.valueOf(firstPaymentYear);
        return month + year;
    }
}
