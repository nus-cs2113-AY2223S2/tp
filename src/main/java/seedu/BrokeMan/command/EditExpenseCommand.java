package seedu.BrokeMan.command;

import seedu.BrokeMan.expense.Expenses;

public class EditExpenseCommand extends Command {
    public static final String COMMAND_WORD = "editExpense";
    private int index;
    private String type;
    private double newCost;
    private String newEntry;

    private boolean isEditCost = false;

    public EditExpenseCommand(int index, String type, String newEntry) {
        this.index = index;
        this.type = type;
        this.newEntry = newEntry;
    }

    public EditExpenseCommand(int index, String type, double newCost) {
        this.index = index;
        this.type = type;
        this.newCost = newCost;
        isEditCost = true;
    }

    public void execute() {
        if (isEditCost) {
            Expenses.editExpenseCost(type, index, newCost);
        } else {
            Expenses.editExpense(type, index, newEntry);
        }
    }
}
