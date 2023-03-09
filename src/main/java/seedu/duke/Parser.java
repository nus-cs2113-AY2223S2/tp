package seedu.duke;
import seedu.duke.data.expense.Expense;
import seedu.duke.data.expense.ExpenseList;

public class Parser {
    public static void parseCommand(String line) {
        String[] input = line.split(" ", 2);
        switch(input[0]) {
        case "expense":
            try {
                addExpenses(input[1]);
            } catch (Exception e) {
                System.out.println("FORMAT: expense /de DESCRIPTION /da DATE /va VALUE");
            }
            break;
        case "list":
            try {
                ExpenseList.printExpenseList();
            } catch (Exception e) {
                System.out.println("Can't print expense list");
            }
            break;
        default:
            System.out.println("Command not recognized");
        }
    }
    private static void addExpenses(String input) {
        String expenseDescription = input.substring(input.indexOf("/de") + 3, input.indexOf("/da")).trim();
        String expenseDate = input.substring(input.indexOf("/da") + 3, input.indexOf("/va")).trim();
        String expenseValue = input.substring(input.indexOf("/va") + 3).trim();
        Expense expense = new Expense(expenseDescription, expenseDate, Float.parseFloat(expenseValue));
        ExpenseList.addExpense(expense);
        System.out.println("Expense added");
    }
}
