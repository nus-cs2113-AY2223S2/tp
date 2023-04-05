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
        String toFindLower = toFind.toLowerCase();

        for (Expense e: expenseList) {
            if (e.getDescription().toLowerCase().contains(toFindLower) ||
                e.getCurrencyType().toLowerCase().contains(toFindLower) ||
                e.getExpenseAmount().toString().toLowerCase().contains(toFindLower) ||
                e.getExpenseTime().toLowerCase().contains(toFindLower)) {
                return false;
            }
        }
        return true;
    }

    private void displayAllRelevantExpenses(String toFind) {
        int index = 1;
        String toFindLower = toFind.toLowerCase();
        for (Expense e: expenseList) {
            if (e.getDescription().toLowerCase().contains(toFindLower) ||
                    e.getCurrencyType().toLowerCase().contains(toFindLower) ||
                    e.getExpenseAmount().toString().toLowerCase().contains(toFindLower) ||
                    e.getExpenseTime().toLowerCase().contains(toFindLower)) {
                System.out.print((index) + ".");
                System.out.println(e.toString());
                index++;
            }
        }
    }
}
