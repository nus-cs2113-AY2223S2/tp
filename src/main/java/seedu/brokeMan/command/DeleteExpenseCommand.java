package seedu.brokeMan.command;

import seedu.brokeMan.expense.Expenses;

public class DeleteExpenseCommand extends Command {
    public static final String COMMAND_WORD = "deleteExpense";
    int index;
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": deletes an expense from the list\n" +
            "|  Parameter: i/ <index>\n" +
            "|  Example: " + COMMAND_WORD + " i/ 1";
    public DeleteExpenseCommand(int index) {
        this.index = index;
    }

    public void execute() {
        Expenses.deleteExpense(index);
    }
}
