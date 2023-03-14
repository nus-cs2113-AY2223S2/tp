package command;

import data.Expense;
import data.ExpenseList;

import java.util.ArrayList;

import static common.MessageList.MESSAGE_DIVIDER_LIST;

public class CommandDelete extends Command {
    public static final String COMMAND_NAME = "delete";
    protected ArrayList<Expense> expenseList;
    protected Integer index;
    public static final String MESSAGE_INVALID_INDEX_ERROR = "Invalid expense index. Please try again.";

    public CommandDelete(ArrayList<Expense> expenseList, Integer index) {
        super(COMMAND_NAME);
        this.expenseList = expenseList;
        this.index = index;
    }

    /**
     * Executes delete command.
     *
     * @return message to notify user that the expense has been deleted
     */
    @Override
    public CommandRes execute() {
        try {
            expenseList.remove(index - 1); // change to 0-based indexing
            return new CommandRes(MESSAGE_DIVIDER_LIST, expenseList.get(index - 1).toString(),
                    ExpenseList.getAllMessage(expenseList));
        } catch (IndexOutOfBoundsException e) {
            System.out.println(MESSAGE_INVALID_INDEX_ERROR);
        }
        return null;
    }

}
