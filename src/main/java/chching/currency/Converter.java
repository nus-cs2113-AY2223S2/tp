package chching.currency;

import java.util.HashMap;

/**
 * Models a class that converts to relevant currency
 */
public class Converter {
    protected HashMap<String, Double> currencyMap;

    public Converter() {
        currencyMap = new HashMap<>();
        currencyMap.put("HKD", 5.87);
        currencyMap.put("PHP", 40.72);
        currencyMap.put("IDR", 11437.80);
        currencyMap.put("MYR", 3.34);
        currencyMap.put("VND", 17618.54);
    }

    public void setConversionRate(String currency, double rate) {
        currencyMap.put(currency, rate);
    }

    public double convert(String currency, double amount) {
        return amount * currencyMap.get(currency);
    }

    /**
     * Executes print converter
     *
     * @param value    income value
     * @param selector selector
     */
    public String printConverter(double value, Selector selector) {
        String result = "";
        for (String currency : currencyMap.keySet()) {
            if (selector.isSelected(currency)) {
                double convertedValue = convert(currency, value);
                result += " | " + currency + " " + String.format("%.2f", convertedValue);

            }
        }
        return result;
    }
    public void printConversionRate() {
        for (String currency : currencyMap.keySet()) {
            System.out.println(currency + " " + currencyMap.get(currency));
        }
    }

}
