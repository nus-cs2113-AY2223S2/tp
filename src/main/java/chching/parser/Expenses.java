package chching.parser;

import chching.record.Expense;

import java.util.HashMap;

public class Expenses {
    public static Expense parseExpense(HashMap<String, String> argumentsByField) {
        Expense exp = null;
        try {
            String expenseCategory = argumentsByField.get("c");
            String expenseDescription = argumentsByField.get("de");
            String expenseDate = argumentsByField.get("da");
            float expenseValue = Float.parseFloat(argumentsByField.get("v"));
            assert expenseValue > 0 : "Expense value should be greater than zero";
            exp = new Expense(expenseCategory, expenseDescription, expenseDate, expenseValue);
        } catch (Exception e) {
            System.out.println("    Trouble adding expense value");
        }
        return exp;
    }
}
