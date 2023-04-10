package command;

import data.Expense;
import data.ExpenseList;

import java.util.ArrayList;

import static common.MessageList.OFFSET;
import static common.MessageList.SUCCESSFUL_DELETE;


public class CommandDelete extends Command {

    private static final String MESSAGE_INVALID_INDEX_ERROR = "Invalid expense index. Please try again.";
    private static final String COMMAND_NAME = "delete";
    protected ArrayList<Expense> expenseList;
    protected Integer index;


    public CommandDelete(ArrayList<Expense> expenseList, Integer index) {
        super(COMMAND_NAME);
        this.expenseList = expenseList;
        this.index = index;
    }

    /**
     * Executes delete command and prints out result of execution
     *
     * @return message to notify user that the expense has been deleted
     */
    @Override
    public CommandRes execute() {
        try {
            Expense deletedExpense = expenseList.get(index - OFFSET);
            // change to 0-based indexing
            expenseList.remove(index - OFFSET);
            return new CommandRes(SUCCESSFUL_DELETE, deletedExpense,
                    ExpenseList.getAllMessage(expenseList));
        } catch (IndexOutOfBoundsException e) {
            System.out.println(MESSAGE_INVALID_INDEX_ERROR);
        }
        return null;
    }

}
