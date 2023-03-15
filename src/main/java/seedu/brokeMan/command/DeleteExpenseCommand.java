package seedu.brokeMan.command;

import seedu.brokeMan.entry.Expenses;

public class DeleteExpenseCommand extends Command {
    public static final String COMMAND_WORD = "deleteExpense";
    int index;
    public DeleteExpenseCommand(int index) {
        this.index = index;
    }

    public void execute() {
        Expenses.deleteExpense(index);
    }
}
