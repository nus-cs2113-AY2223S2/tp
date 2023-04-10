package chching.currency;

import java.util.HashMap;

/**
 * Models a class that selects the relevant currency. Inherited from Command
 * class.
 */
public class Selector {
    protected HashMap<String, Boolean> selectedCurrencies;

    public Selector() {
        selectedCurrencies = new HashMap<>();
        selectedCurrencies.put("HKD", false);
        selectedCurrencies.put("PHP", false);
        selectedCurrencies.put("IDR", false);
        selectedCurrencies.put("MYR", false);
        selectedCurrencies.put("VND", false);
    }

    public void setCurrency(String currency) {
        selectedCurrencies.put(currency, true);
    }

    public void unsetCurrency(String currency) {
        selectedCurrencies.put(currency, false);
    }

    public void printSelector(Converter converter) {
        for (String currency : selectedCurrencies.keySet()) {
            String marked = selectedCurrencies.get(currency) ? "[X] " : "[ ] ";
            System.out.println(marked + currency + " " + converter.convert(currency, 1));
        }
    }

    public boolean containsCurrency(String currency) {
        return selectedCurrencies.containsKey(currency);
    }

    public boolean isSelected(String currency) {
        return selectedCurrencies.get(currency);
    }

    public int getSize() {
        return selectedCurrencies.size();
    }
}
