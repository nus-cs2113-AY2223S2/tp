package seedu.BrokeMan.command;

import seedu.BrokeMan.expense.Expense;
import seedu.BrokeMan.expense.Expenses;

public class AddExpenseCommand extends Command {
    public static final String COMMAND_WORD = "addExpense";
    private final Expense expense;
    private final String ADD_SUCCESSFUL_MESSAGE = "You have successfully added: ";

    public AddExpenseCommand(double cost, String info, String time) {
        this.expense = new Expense(cost, info, time);
    }

    public void execute() {
        Expenses.addExpense(expense);
    }
}
