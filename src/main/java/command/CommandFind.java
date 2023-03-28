package command;

import data.Expense;

import java.util.ArrayList;

import static common.MessageList.MESSAGE_DIVIDER;
import static common.MessageList.MESSAGE_DIVIDER_FIND;

public class CommandFind extends Command {
    public static final String COMMAND_NAME = "find";

    protected ArrayList<Expense> expenseList;
    private String toFind;
    // isZero = true means there are no expenses with such input
    private boolean isZero = false;


    public CommandFind(ArrayList<Expense> expenseList, String toFind) {
        super(COMMAND_NAME);
        this.expenseList = expenseList;
        this.toFind = toFind;

    }

    /**
     * This method will return all the expenses with the string user input in
     * If the input string is not a complete word, the method will also return the associated sense
     *
     * @return printing the expenses with input string
     *
     */
    @Override
    public CommandRes execute() {
        if (expenseList.size() == 0) {
            System.out.println("Sorry, there are no expenses tracked currently.");
            System.out.println(MESSAGE_DIVIDER);
        }
        isZero = checkWithInput(toFind);

        if (isZero) {
            System.out.println("Sorry, none of your previous expenses have such input.");
            System.out.println(MESSAGE_DIVIDER);
        } else {
            System.out.println(MESSAGE_DIVIDER_FIND);
            displayAllRelevantExpenses(toFind);
            System.out.println(MESSAGE_DIVIDER);
        }
        return null;
    }

    private boolean checkWithInput(String toFind) {
        for (Expense e: expenseList) {
            if (e.getDescription().contains(toFind) ||
                e.getCurrencyType().contains(toFind) ||
                e.getExpenseAmount().toString().contains(toFind) ||
                e.getExpenseTime().contains(toFind)) {
                return false;
            }
        }
        return true;
    }

    private void displayAllRelevantExpenses(String toFind) {
        int index = 1;
        for (Expense e: expenseList) {
            if (e.getDescription().contains(toFind) ||
                    e.getCurrencyType().contains(toFind) ||
                    e.getExpenseAmount().toString().contains(toFind) ||
                    e.getExpenseTime().contains(toFind)) {
                System.out.print((index) + ".");
                System.out.println(e.toString());
                index++;
            }
        }
    }
}
