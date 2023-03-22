package chching.parser;

import chching.ChChingException;
import chching.record.Income;

import java.util.HashMap;

public class Incomes {

    /**
     * Parses an income into the incomeList
     *
     * @param argumentsByField       Input from users
     */
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


    /**
     * Gets the index of the entry
     *
     * @param argumentsByField       ArrayList of income.
     */

    public static int getIndex(HashMap<String, String> argumentsByField) throws ChChingException {
        int index = -1;
        try {
            String indexString = argumentsByField.get("in");
            index = Integer.parseInt(indexString);
        } catch (Exception e) {
            throw new ChChingException("Missing/invalid index");
        }
        return index;
    }
}
