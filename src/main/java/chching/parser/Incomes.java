package chching.parser;

import chching.ChChingException;
import chching.record.Income;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.HashMap;

public class Incomes {
    public static LocalDate parseDate(String incomeDateString) throws ChChingException {
        LocalDate incomeDate;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-uuuu")
                .withResolverStyle(ResolverStyle.STRICT);
        try {
            incomeDate = LocalDate.parse(incomeDateString, formatter);
        } catch (DateTimeParseException e) {
            throw new ChChingException("Date must be valid with format: \"DD-MM-YYYY\"");
        }
        if (incomeDate.isAfter(LocalDate.now())) {
            throw new ChChingException("Date cannot be in the future");
        }
        return incomeDate;
    }

    /**
     * Parses an income into the incomeList
     *
     * @param argumentsByField Input from users
     */
    public static Income parseIncome(HashMap<String, String> argumentsByField) throws ChChingException {
        Income inc = null;
        String incomeDescription = argumentsByField.get("de");
        String incomeDateString = argumentsByField.get("da");
        LocalDate incomeDate = parseDate(incomeDateString);

        float incomeValue;
        try {
            incomeValue = Float.parseFloat(argumentsByField.get("v"));
        } catch (Exception e) {
            throw new ChChingException("Income value must be a valid float that is 2 d.p. or less");
        }
        if (incomeValue > 1000000) {
            throw new ChChingException("Income value can at most be 1000000");
        } else if (incomeValue < 0.01) {
            throw new ChChingException("Income value must be greater than or equals 0.01");
        }
        assert incomeValue >= 0.01 : "incomeValue has to be more than or equals to 0.01";
        inc = new Income(incomeDescription, incomeDate, incomeValue);
        return inc;
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
