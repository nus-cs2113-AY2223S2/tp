package chching.parser;

import chching.ChChingException;
import chching.record.Expense;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.HashMap;

public class Expenses {
    public static Expense parseExpense(HashMap<String, String> argumentsByField) throws ChChingException{
        Expense exp = null;
        try {
            String expenseCategory = argumentsByField.get("c");
            String expenseDescription = argumentsByField.get("de");
            LocalDate expenseDate = LocalDate.parse(argumentsByField.get("da"));
            float expenseValue = Float.parseFloat(argumentsByField.get("v"));
            assert expenseValue > 0 : "Expense value should be greater than zero";
            exp = new Expense(expenseCategory, expenseDescription, expenseDate, expenseValue);
        } catch (DateTimeParseException e) {
            throw new ChChingException("Date format should be: yyyy-mm-dd");
        }
        catch (Exception e) {
            throw new ChChingException("Trouble adding expense value");
        }
        return exp;
    }
    
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
