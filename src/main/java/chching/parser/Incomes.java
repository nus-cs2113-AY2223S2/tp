package chching.parser;

import chching.ChChingException;
import chching.record.Income;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.HashMap;

public class Incomes {
    
    public static final String DESCRIPTION_FIELD = "de";
    public static final String DATE_FIELD = "da";
    public static final String VALUE_FIELD = "v";
    public static final String INDEX_FIELD = "in";
    
    public static LocalDate parseDate(String incomeDateString) throws ChChingException {
        LocalDate incomeDate;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-uuuu")
                .withResolverStyle(ResolverStyle.STRICT);
        try {
            incomeDate = LocalDate.parse(incomeDateString, formatter);
        } catch (DateTimeParseException e) {
            throw new ChChingException("Date must be valid and have format: \"DD-MM-YYYY\"");
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
        // check if all the fields are present
        boolean isDescriptionPresent = argumentsByField.containsKey(DESCRIPTION_FIELD);
        boolean isDatePresent = argumentsByField.containsKey(DATE_FIELD);
        boolean isValuePresent = argumentsByField.containsKey(VALUE_FIELD);
        boolean isAllPresent = isDescriptionPresent && isDatePresent && isValuePresent;
        if (!isAllPresent) {
            throw new ChChingException("Missing fields detected");
        }
        
        Income inc = null;
        String incomeDescription = argumentsByField.get(DESCRIPTION_FIELD);
        String incomeDateString = argumentsByField.get(DATE_FIELD);
        LocalDate incomeDate = parseDate(incomeDateString);

        double incomeValue;
        try {
            incomeValue = Float.parseFloat(argumentsByField.get(VALUE_FIELD));
            incomeValue = Math.round(incomeValue * 100.0) / 100.0;
        } catch (Exception e) {
            throw new ChChingException("Income value must be a valid double that is 2 d.p. or less");
        }
        if (incomeValue > 999999.99) {
            throw new ChChingException("Income value must be less than 1000000");
        } else if (incomeValue <= 0) {
            throw new ChChingException("Income value must be greater than 0");
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
            String indexString = argumentsByField.get(INDEX_FIELD);
            index = Integer.parseInt(indexString);
        } catch (Exception e) {
            throw new ChChingException("Index field not found");
        }
        return index;
    }
}
