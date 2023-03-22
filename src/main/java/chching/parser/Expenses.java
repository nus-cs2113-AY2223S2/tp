package chching.parser;

import chching.ChChingException;
import chching.record.Expense;

import java.util.HashMap;

/**
 * Models a class to parse expenses.
 */

public class Expenses {

    /**
     * Parses an expense into the expenseList
     *
     * @param argumentsByField       Input from users
     */
    public static Expense parseExpense(HashMap<String, String> argumentsByField) throws ChChingException{
        Expense exp = null;
        try {
            String expenseCategory = argumentsByField.get("c");
            String expenseDescription = argumentsByField.get("de");
            String expenseDate = argumentsByField.get("da");
            float expenseValue = Float.parseFloat(argumentsByField.get("v"));
            assert expenseValue > 0 : "Expense value should be greater than zero";
            exp = new Expense(expenseCategory, expenseDescription, expenseDate, expenseValue);
        } catch (Exception e) {
            throw new ChChingException("Trouble adding expense value");
        }
        return exp;
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
