package seedu.expenditure;

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
    public static final String LIST_OF_RATES = "Currency rates per SGD:\n" +
            "AUS: " + AUS_CONVERSION + "\n" +
            "CAD: " + CAD_CONVERSION + "\n" +
            "CNY: " + CNY_CONVERSION + "\n" +
            "DKK: " + DKK_CONVERSION + "\n" +
            "EUR: " + EUR_CONVERSION + "\n" +
            "GBP: " + GBP_CONVERSION + "\n" +
            "ILS: " + ILS_CONVERSION + "\n" +
            "JPY: " + JPY_CONVERSION + "\n" +
            "KRW: " + KRW_CONVERSION + "\n" +
            "NOK: " + NOK_CONVERSION + "\n" +
            "NZD: " + NZD_CONVERSION + "\n" +
            "SEK: " + SEK_CONVERSION + "\n" +
            "TWD: " + TWD_CONVERSION + "\n" +
            "USD: " + USD_CONVERSION;

    /**
     * Converts SGD to specified currency and returns the value.
     * @param amount
     * @param currency should be checked for non-valid inputs when command that would call
     *        sgDConversion is instantiated.
     * @return
     */
    public static double sgDConversion (double amount, String currency) {
        switch (currency) {
        case "AUS":
            return amount * AUS_CONVERSION;
        case "CAD":
            return amount * CAD_CONVERSION;
        case "CNY":
            return amount * CNY_CONVERSION;
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
        default:
            return amount;
        }
    }

    //Returns true if string corresponds to currency type
    public static boolean isValidCurrency(String string) {
        if (string.matches("AUS|CAD|CNY|DKK|EUR|GBP|ILS|JPY|KRW|NOK|NZD|SEK|TWD|USD|SGD")) {
            return true;
        }
        return false;
    }

    //Returns the prepared string of different rates
    public static String getRates () {
        return LIST_OF_RATES;
    }
}
