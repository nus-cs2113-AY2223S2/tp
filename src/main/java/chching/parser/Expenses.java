package chching.parser;

import chching.ChChingException;
import chching.record.Expense;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

/**
 * Models a class to parse expenses.
 */

public class Expenses {

    public static LocalDate parseDate(String expenseDateString) throws ChChingException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate expenseDate = LocalDate.parse(expenseDateString, formatter);
            return expenseDate;
        } catch (DateTimeParseException e) {
            throw new ChChingException("Date format should be: dd-MM-yyyy");
        }
    }
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
            String expenseDateString = argumentsByField.get("da");
            LocalDate expenseDate = parseDate(expenseDateString);
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
