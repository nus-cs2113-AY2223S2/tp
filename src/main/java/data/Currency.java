package data;

public enum Currency {
    // This class initialized the currencies in the Account;
    // It will be replaced in later version by currency API;
    // Currently, we have USD;
    USD, SGD;

    public static Currency checkCurrency(String currency) {
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

    public static double getExchangeRate(Currency from, Currency to) {
        // Could return the currency exchange rate for other methods' usage
        return 1;
    }
}
