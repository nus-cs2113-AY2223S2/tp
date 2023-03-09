package seedu.BrokeMan.command;

import seedu.BrokeMan.expense.Expenses;

public class SortExpenseCommand extends Command {
    public static final String COMMAND_WORD = "sortExpense";

    public void execute() {
        Expenses.sortExpenses();
    }
}
