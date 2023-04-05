package chching.parser;

import chching.ChChingException;
import chching.record.Expense;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.HashMap;

/**
 * Models a class to parse expenses.
 */

public class Expenses {

    /**
     * Parses a date
     *
     * @param expenseDateString Input from users
     */
    public static LocalDate parseDate(String expenseDateString) throws ChChingException {
        LocalDate expenseDate;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-uuuu")
                .withResolverStyle(ResolverStyle.STRICT);
        try {
            expenseDate = LocalDate.parse(expenseDateString, formatter);
        } catch (DateTimeParseException e) {
            throw new ChChingException("Date must be valid with format: \"DD-MM-YYYY\"");
        }
        if (expenseDate.isAfter(LocalDate.now())) {
            throw new ChChingException("Date cannot be in the future");
        }
        return expenseDate;
    }

    /**
     * Parses an expense into the expenseList
     *
     * @param argumentsByField Input from users
     */
    public static Expense parseExpense(HashMap<String, String> argumentsByField) throws ChChingException {
        Expense exp = null;
        String expenseCategory = argumentsByField.get("c");
        String expenseDescription = argumentsByField.get("de");
        String expenseDateString = argumentsByField.get("da");
        LocalDate expenseDate = parseDate(expenseDateString);

        double expenseValue;
        try {
            expenseValue = Float.parseFloat(argumentsByField.get("v"));
            expenseValue = Math.round(expenseValue * 100.0) / 100.0;
        } catch (Exception e) {
            throw new ChChingException("Expense value must be a valid float that is 2 d.p. or less");
        }
        if (expenseValue > 1000000) {
            throw new ChChingException("Expense value can at most be 1000000");
        } else if (expenseValue <= 0) {
            throw new ChChingException("Expense value must be greater than 0");
        }
        assert expenseValue > 0 : "expenseValue has to be more than 0";
        exp = new Expense(expenseCategory, expenseDescription, expenseDate, expenseValue);
        return exp;
    }

    /**
     * Gets the index of the entry
     *
     * @param argumentsByField ArrayList of income.
     */
    public static int getIndex(HashMap<String, String> argumentsByField) throws ChChingException {
        int index = -1;
        try {
            String indexString = argumentsByField.get("in");
            index = Integer.parseInt(indexString);
        } catch (Exception e) {
            throw new ChChingException("Index must contain a valid integer only");
        }
        return index;
    }
}
