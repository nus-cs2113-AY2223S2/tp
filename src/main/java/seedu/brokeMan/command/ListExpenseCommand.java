package seedu.brokeMan.command;

import seedu.brokeMan.expense.Expenses;

public class ListExpenseCommand extends Command {
    public static final String COMMAND_WORD = "listExpense";

    public void execute() {
        Expenses.listExpense();
    }
}
