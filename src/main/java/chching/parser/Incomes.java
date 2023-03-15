package chching.parser;

import chching.ChChingException;
import chching.record.Income;

import java.util.HashMap;

public class Incomes {
    public static Income parseIncome(HashMap<String, String> argumentsByField) throws ChChingException {
        Income inc = null;
        try {
            String incomeDescription = argumentsByField.get("de");
            String incomeDate = argumentsByField.get("da");
            float incomeValue = Float.parseFloat(argumentsByField.get("v"));
            inc = new Income(incomeDescription, incomeDate, incomeValue);
            assert incomeValue > 0 : "incomeValue has to be more than 0";
            inc = new Income(incomeDescription, incomeDate, incomeValue);
        } catch (Exception e) {
            throw new ChChingException("Trouble adding income value");
        }
        return inc;
    }
    
    public static int getIndex(HashMap<String, String> argumentsByField) throws ChChingException {
        int index = -1;
        try {
            String indexString = argumentsByField.get("no");
            index = Integer.parseInt(indexString);
        } catch (NumberFormatException e) {
            throw new ChChingException("Index needs to be an integer");
        } catch (NullPointerException e) {
            throw new ChChingException("Index not found");
        }
        return index;
    }
}
