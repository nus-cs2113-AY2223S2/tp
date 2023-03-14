package chching.parser;

import chching.record.Expense;
import chching.record.ExpenseList;

import java.util.HashMap;

public class Expenses {
    public static Expense parseExpense(HashMap<String, String> argumentsByField) {
        Expense exp = null;
        try {
            String expenseCategory = argumentsByField.get("c");
            String expenseDescription = argumentsByField.get("de");
            String expenseDate = argumentsByField.get("da");
            float expenseValue = Float.parseFloat(argumentsByField.get("v"));
            exp = new Expense(expenseCategory, expenseDescription, expenseDate, expenseValue);
        } catch (Exception e) {
            System.out.println("Trouble adding expenses");
        }
        return exp;
    }
}
