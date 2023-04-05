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
            throw new ChChingException("Date must be valid with format: dd-MM-yyyy");
        }
        if (incomeDate.isAfter(LocalDate.now())) {
            throw new ChChingException("Date cannot be in the future");
        }
        return incomeDate;
    }

    /**
     * Parses an income into the incomeList
     *
     * @param argumentsByField       Input from users
     */
    public static Income parseIncome(HashMap<String, String> argumentsByField) throws ChChingException {
        Income inc = null;
        try {
            incomeValue = Float.parseFloat(argumentsByField.get(VALUE_FIELD));
        } catch (Exception e) {
            throw new ChChingException("Income value must be a valid float that is 2 d.p. or less");
        }
        try {
            if (incomeValue > 999999.99) {
                throw new ChChingException("Income value cannot be 1000000 or more");
            } else if (incomeValue <= 0) {
                throw new ChChingException("Income value must be greater than 0");
            } else{
                incomeDescription = argumentsByField.get("de");
                incomeDateString = argumentsByField.get("da");
                incomeDate = parseDate(incomeDateString);
                incomeValue = Float.parseFloat(argumentsByField.get("v"));
                if (incomeValue > 1000000) {
                    throw new ChChingException("Income value can at most be 1000000");
                }
                assert incomeValue > 0 : "incomeValue has to be more than 0";
                inc = new Income(incomeDescription, incomeDate, incomeValue);
            }
        } catch (Exception e) {
            throw new ChChingException("trouble adding income value");         
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
            throw new ChChingException("Missing/Invalid index");
        }
        return index;
    }
}
