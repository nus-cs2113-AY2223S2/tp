package command;

import data.Expense;
import data.ExpenseList;

import java.util.ArrayList;

import static common.MessageList.MESSAGE_DIVIDER;
import static common.MessageList.MESSAGE_DIVIDER_LIST;

public class CommandList extends Command {
    public static final String COMMAND_NAME = "list";

    protected ArrayList<Expense> expenseList;

    public CommandList(ArrayList<Expense> expenseList) {
        super(COMMAND_NAME);
        this.expenseList = expenseList;
    }

    public boolean run() {
        try {
            if (expenseList.size() == 0) {
                System.out.println("Sorry, there are no expenses tracked currently.");
                System.out.println(MESSAGE_DIVIDER);
                return false;
            } else {
                System.out.println("Here are the tasks in your list:\n");
                System.out.println(MESSAGE_DIVIDER_LIST);
                for (int i = 0; i < expenseList.size(); i++) {
                    System.out.print((i + 1) + ".");
                    System.out.println(expenseList.get(i).toString());
                }
                System.out.println(ExpenseList.getAllMessage(expenseList));
                System.out.println(MESSAGE_DIVIDER);
            }
        } catch (NullPointerException e) {
            System.out.println("Sorry, there are no expenses tracked currently.");
        }
        return true;
    }

    /**
     * Execution of the list command
     * All the expenses are displayed in the order in which it was added.
     *
     * @return printing the list of expenses
     */
    @Override
    public CommandRes execute() {
        //return new CommandRes(MESSAGE_DIVIDER_LIST, expenseList, ExpenseList.getAllMessage(expenseList));
        return null;
    }
}
