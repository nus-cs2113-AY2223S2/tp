package command;

import data.Expense;
import common.MessageList;

import java.util.ArrayList;
import java.util.stream.IntStream;

import static common.MessageList.*;

public class CommandRes {
    public String messageTop;
    public String messageBottom;
    public ArrayList<Expense> expense;

    public CommandRes(String strTop) {
        this.messageTop = strTop;
    }

    /**
     * Instantiates and references the expense list for the entry to be shown as the result.
     *
     * @param strTop The first result we need to show.
     * @param expense The expense list.
     * @param strBottom The last result we need to show.
     */
    public CommandRes(String strTop, ArrayList<Expense> expense, String strBottom) {
        this.messageTop = strTop;
        this.expense = expense;
        this.messageBottom = strBottom;
    }

    /**
     * Prints out messages with single expense detail.
     *
     * @param actionDone Message to tell user what has been done.
     * @param expense Expense details.
     * @param numberOfExpenses Number of expenses in the expense list.
     */
    public CommandRes(String actionDone, Expense expense, String numberOfExpenses) {
        System.out.println(MESSAGE_DIVIDER + '\n' + actionDone + '\n' +
                TAB + expense.toString() + '\n' + numberOfExpenses + '\n' + MESSAGE_DIVIDER);
    }

    /**
     * Prints the result for the user.
     */
    public String[] printTask() {
        if (expense == null) {
            return new String[]{};
        } else if (messageTop.equals(MessageList.MESSAGE_DIVIDER_LIST)) {
            return IntStream.range(0, expense.size()).mapToObj(i ->
                    (i + MessageList.OFFSET + "." + expense.get(i))).toArray(String[]::new);
        } else {
            return expense.stream().map(Expense::toString).toArray(String[]::new);
        }
    }
}
