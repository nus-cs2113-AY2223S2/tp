package seedu.expenditure;

import seedu.exceptions.WrongInputException;

public abstract class CurrencyValue {
    private static final double AUS_CONVERSION = 1.11;
    private static final double CAD_CONVERSION = 1.01;
    private static final double CNY_CONVERSION = 5.07;
    private static final double DKK_CONVERSION = 5.15;
    private static final double EUR_CONVERSION = 0.69;
    private static final double GBP_CONVERSION = 0.61;
    private static final double ILS_CONVERSION = 2.70;
    private static final double JPY_CONVERSION = 99.96;
    private static final double KRW_CONVERSION = 989.05;
    private static final double NOK_CONVERSION = 7.78;
    private static final double NZD_CONVERSION = 1.20;
    private static final double SEK_CONVERSION = 7.80;
    private static final double TWD_CONVERSION = 22.98;
    private static final double USD_CONVERSION = 0.75;

    public static double sgDConversion(double amount, String currencyType) throws WrongInputException {
        switch (currencyType) {
            case "AUS":
                return amount * AUS_CONVERSION;
            case "CAD":
                return amount * CAD_CONVERSION;
            case "DKK":
                return amount * DKK_CONVERSION;
            case "EUR":
                return amount * EUR_CONVERSION;
            case "GBP":
                return amount * GBP_CONVERSION;
            case "ILS":
                return amount * ILS_CONVERSION;
            case "JPY":
                return amount * JPY_CONVERSION;
            case "KRW":
                return amount * KRW_CONVERSION;
            case "NOK":
                return amount * NOK_CONVERSION;
            case "NZD":
                return amount * NZD_CONVERSION;
            case "SEK":
                return amount * SEK_CONVERSION;
            case "TWD":
                return amount * TWD_CONVERSION;
            case "USD":
                return amount * USD_CONVERSION;
            case "CNY":
                return amount * CNY_CONVERSION;
            case "SGD":
                return amount;
            default:
                throw new WrongInputException();
        }
    }
}
