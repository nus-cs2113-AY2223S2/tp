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
    private static final String VALID_CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!@#$%^&*()_+-=,./<>?;':\"[]{}\\|`~ ";

    /**
     * Parses a date
     *
     * @param expenseDateString     Input from users
     */
    public static LocalDate parseDate(String expenseDateString) throws ChChingException {
        LocalDate expenseDate;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-uuuu")
                .withResolverStyle(ResolverStyle.STRICT);
        try {
            expenseDate = LocalDate.parse(expenseDateString, formatter);
        } catch (DateTimeParseException e) {
            throw new ChChingException("Date must be valid with format: dd-MM-yyyy");
        }
        if (expenseDate.isAfter(LocalDate.now())) {
            throw new ChChingException("Date cannot be in the future");
        }
        return expenseDate;
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
            String invalidCharacters = expenseDescription.replaceAll(VALID_CHARACTERS, "");
            if (!invalidCharacters.isEmpty()) {
                throw new ChChingException("Description contains invalid characters");
            }
            String expenseDateString = argumentsByField.get("da");
            LocalDate expenseDate = parseDate(expenseDateString);
            float expenseValue = Float.parseFloat(argumentsByField.get("v"));
            if(expenseValue > 1000000){
                throw new ChChingException("Expense value can at most be 1000000");
            }
            assert expenseValue > 0: "Expense value should be greater than zero";
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
            throw new ChChingException("Missing/Invalid index");
        }
        return index;
    }
}
