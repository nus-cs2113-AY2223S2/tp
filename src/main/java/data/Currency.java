package data;

public enum Currency {
    // This class initialized the currencies in the Account;
    // It will be replaced in later version by currency API;
    // Currently, we have USD;
    USD, SGD;

    public static Currency checkCurrency(String currency) {
        // Default currency is SGD
        if (currency == null) {
            return SGD;
        }
        if (currency.equals("USD")) {
            return USD;
        } else {
            // Will change to other currency later on, currently just assume SGD
            return SGD;
        }
    }

    /**
     * Given the type of currency, return a string of it.
     */
    public static String returnCurrency(Currency currency) {
        if (currency.equals(SGD)) {
            return "SGD";
        } else if (currency.equals(USD)) {
            return "USD";
        } else {
            return "SGD"; // By default
        }
    }

    public static double getExchangeRate(Currency from, Currency to) {
        // Could return the currency exchange rate for other methods' usage
        return 1;
    }
}
