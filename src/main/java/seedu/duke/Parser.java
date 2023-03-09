package seedu.duke;
import seedu.duke.data.expense.Expense;
import seedu.duke.data.expense.ExpenseList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Parser {
    public static void parseCommand(String line, ExpenseList expenseList) {
        List<String> lineParts = splitLine(line);
        String command = lineParts.get(0);
        List<String> arguments = lineParts.subList(1, lineParts.size());
        switch(command) {
        case "add expense":
            addExpenses(arguments, expenseList);
            break;
        case "list":
            ExpenseList.printExpenseList();
            break;
        default:
            System.out.println("Command not recognized");
        }
    }
    
    public static ArrayList<String> splitLine(String line) {
        ArrayList<String> lineParts = new ArrayList<String>();
        lineParts.addAll(Arrays.asList(line.split(" /")));
        return lineParts;
    }
    
    private static void addExpenses(List<String> arguments, ExpenseList expenseList) {
        try {
            String expenseDescription = arguments.get(0).substring(2).trim();
            String expenseDate = arguments.get(1).substring(2).trim();
            float expenseValue = Float.parseFloat(arguments.get(2).substring(1).trim());
            Expense exp = new Expense(expenseDescription, expenseDate, expenseValue);
            ExpenseList.addExpense(exp);
            System.out.println("Expense added");
        } catch (Exception e) {
            System.out.println("Trouble adding expenses");
        }
    }
}
