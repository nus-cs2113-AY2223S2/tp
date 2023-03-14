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

    public static Expense parseUpdateExpense(HashMap<String, String> argumentsByField, ExpenseList expenseList) {
        Expense exp = null;
        try {
            int index = Integer.parseInt(argumentsByField.get("i"));
            exp = (Expense) expenseList.get(index - 1);
            if (argumentsByField.containsKey("c")) {
                String updateCategory = argumentsByField.get("c");
                exp.setCategory(updateCategory);
            }
            if (argumentsByField.containsKey("de")) {
                String updateDescription = argumentsByField.get("de");
                exp.setDescription(updateDescription);
            }
            if (argumentsByField.containsKey("da")) {
                String updateDate = argumentsByField.get("da");
                exp.setDate(updateDate);
            }
            if (argumentsByField.containsKey("v")) {
                float updateValue = Float.parseFloat(argumentsByField.get("v"));
                exp.setValue(updateValue);
            }
        } catch (Exception e) {
            System.out.println("Trouble editing expense");
        }
        return exp;
    }
}
