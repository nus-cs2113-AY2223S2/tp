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
    
    public static final String CATEGORY_FIELD = "c";
    public static final String DESCRIPTION_FIELD = "de";
    public static final String DATE_FIELD = "da";
    public static final String VALUE_FIELD = "v";
    public static final String INDEX_FIELD = "in";
    
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
            throw new ChChingException("Date must be valid and have format: \"DD-MM-YYYY\"");
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
        // check if all the fields are present
        boolean isCategoryPresent = argumentsByField.containsKey(CATEGORY_FIELD);
        boolean isDescriptionPresent = argumentsByField.containsKey(DESCRIPTION_FIELD);
        boolean isDatePresent = argumentsByField.containsKey(DATE_FIELD);
        boolean isValuePresent = argumentsByField.containsKey(VALUE_FIELD);
        boolean isAllPresent = isCategoryPresent && isDescriptionPresent && isDatePresent && isValuePresent;
        if (!isAllPresent) {
            throw new ChChingException("Missing fields detected");
        }
        Expense exp = null;

        String expenseCategory = argumentsByField.get(CATEGORY_FIELD);
        String expenseDescription = argumentsByField.get(DESCRIPTION_FIELD);
        String expenseDateString = argumentsByField.get(DATE_FIELD);
        String expenseValueString = argumentsByField.get(VALUE_FIELD);
        double expenseValue;
        LocalDate expenseDate = parseDate(expenseDateString);

        if(!DecimalsChecker.isTwoDecimals(expenseValueString)) {
            throw new ChChingException("Expense value must be a valid positive double that is 2 d.p. or less");
        }
        try {
            expenseValue = Double.parseDouble(expenseValueString);
        } catch (Exception e) {
            throw new ChChingException("Expense value must be a valid double that is 2 d.p. or less");
        }
        if (expenseValue > 999999.99) {
            throw new ChChingException("Expense value must be less than 1000000");
        } else if (expenseValue <= 0) {
            throw new ChChingException("Expense value must be greater than 0");
        }
        assert expenseValue >= 0.01 : "expenseValue has to be more than or equals 0.01";
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
            String indexString = argumentsByField.get(INDEX_FIELD);
            index = Integer.parseInt(indexString);
        } catch (Exception e) {
            throw new ChChingException("Index field not found");
        }
        return index;
    }
}
