package seedu.brokeMan.command;

import seedu.brokeMan.entry.Expense;
import seedu.brokeMan.entry.Expenses;

public class AddExpenseCommand extends Command {
    public static final String COMMAND_WORD = "addExpense";
    private final Expense expense;

    public AddExpenseCommand(double cost, String info, String time) {
        this.expense = new Expense(cost, info, time);
    }

    public void execute() {
        Expenses.addExpense(expense);
    }
}
