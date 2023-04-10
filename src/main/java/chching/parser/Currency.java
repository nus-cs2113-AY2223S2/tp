package chching.parser;

import java.util.HashMap;
import chching.ChChingException;

/**
 * Models a class to parse currency.
 */
public class Currency {
    public static String getCurrency(HashMap<String, String> argumentsByField) throws ChChingException {
        String currency = null;
        try {
            currency = argumentsByField.get("cr");
        } catch (Exception e) {
            throw new ChChingException("Missing/invalid currency");
        }
        return currency;
    }

}
