package command;

import data.Expense;
import data.ExpenseList;

import java.util.ArrayList;
import static common.MessageList.MESSAGE_DIVIDER;

import static common.MessageList.MESSAGE_DIVIDER_LIST;

public class CommandList extends Command {
    public static final String COMMAND_NAME = "list";
    protected ArrayList<Expense> expenseList;

    /**
     * List all the expenses in the expenseList, else will print No expenses to the user currently.
     *
     * @param expenseList The expense list to add the entry to.
     */
    public CommandList(ArrayList<Expense> expenseList) {
        super(COMMAND_NAME);
        this.expenseList = expenseList;
    }

    public boolean run() {
        if (expenseList.size() == 0) {
            System.out.println("Sorry, there are no expenses tracked currently.");
            return false;
        } else {
            System.out.println(MESSAGE_DIVIDER_LIST);
            for (int i = 0; i < expenseList.size(); i++) {
                System.out.print((i + 1) + ".");
                expenseList.get(i).toString();
            }
            System.out.println("\n" + ExpenseList.getAllMessage());
            System.out.println(MESSAGE_DIVIDER);
        }

        return true;
    }


    @Override
    public CommandRes execute() {
        return new CommandRes(MESSAGE_DIVIDER_LIST, ExpenseList.expenseList, ExpenseList.getAllMessage());
    }
}
