package data;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;

public class Currency {

    protected HashMap<String, BigDecimal> exchangeRate = null;

    /**
     * Gets the HashMap of exchange rates. Instantiates the HashMap if it has not been instantiated.
     * @return returns the HashMap exchangeRate with the input exchange rates. (Singleton Pattern)
     */

    public HashMap<String, BigDecimal> getExchangeRate() {
        if(exchangeRate == null) {
            exchangeRate = new HashMap<>();
            exchangeRate.put("SGD", new BigDecimal(1.0));
            exchangeRate.put("USD", new BigDecimal(0.75));
        }
        return exchangeRate;
    }

    /**
     * Converts the currency to SGD if the input currency is not found in the HashMap. Else it returns the input
     * currency.
     * @param currency the input currency specified by the user.
     * @return returns SGD as the default currency if input currency is missing or not found in HashMap, else return
     * the input currency.
     */
    public String convertCurrency(String currency) {
        // Default currency is SGD
        exchangeRate = getExchangeRate();
        if (currency == null || !exchangeRate.containsKey(currency)) {
            return "SGD";
        }
        return currency;
    }

    /**
     * Checks if the input currency is found in the HashMap of exchange rates.
     * @param currency the input currency specified by the user.
     * @return returns true if the input currency is found in the HashMap, false otherwise.
     */
    public boolean checkCurrency(String currency) {
        if(exchangeRate.containsKey(currency)) {
            return true;
        }
        return false;
    }

    /**
     * Updates the input currency with a new exchange rate or adds it into the HashMap if it is not found
     * @param currency the input currency specified by the user.
     * @param rate the new exchange rate of the currency with respect to SGD.
     */
    public void updateCurrency(String currency, BigDecimal rate) {
        exchangeRate.put(currency, rate);
    }

    /**
     * Standardize the format of double when we add it to expenseList
     */
    public BigDecimal roundInput(String expenseAmountInput) {
        BigDecimal roundedExpense = new BigDecimal(expenseAmountInput);
        roundedExpense = roundedExpense.setScale(2, RoundingMode.HALF_UP);
        return roundedExpense;
    }
}
