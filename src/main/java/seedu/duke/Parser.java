package seedu.duke;
import seedu.duke.data.expense.Expense;
import seedu.duke.data.expense.ExpenseList;

public class Parser {
    public static void parseCommand(String line) {
        String[] input = line.split(" ", 2);
        switch(input[0].trim()) {
        case "expense":
            addExpenses(input[1]);
            break;
        case "list":
            ExpenseList.printExpenseList();
            break;
        default:
            System.out.println("Command not recognized");
        }
    }
    private static void addExpenses(String input) {
        try {
            String expenseDescription = input.substring(input.indexOf("/de") + 3, input.indexOf("/da")).trim();
            String expenseDate = input.substring(input.indexOf("/da") + 3, input.indexOf("/va")).trim();
            float expenseValue = Float.parseFloat(input.substring(input.indexOf("/va") + 3).trim());
            Expense exp = new Expense(expenseDescription, expenseDate, expenseValue);
            ExpenseList.addExpense(exp);
            System.out.println("Expense added");
        } catch (Exception e) {
            System.out.println("Trouble adding expenses");
        }
    }
}

