package command;

import data.Expense;

import java.math.BigDecimal;
import java.util.ArrayList;

import static common.MessageList.MESSAGE_DIVIDER;
import static common.MessageList.TAB;


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
     * @param strTop    The first result we need to show.
     * @param expense   The expense list.
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
     * @param actionDone       Message to tell user what has been done.
     * @param expense          Expense details.
     * @param numberOfExpenses Number of expenses in the expense list.
     */
    public CommandRes(String actionDone, Expense expense, String numberOfExpenses) {
        System.out.println(MESSAGE_DIVIDER + '\n' + actionDone + '\n' +
                TAB + expense.toString() + '\n' + numberOfExpenses + '\n' + MESSAGE_DIVIDER);
    }

    public CommandRes(BigDecimal total) {
        System.out.println(MESSAGE_DIVIDER + '\n' + "Your total expenses add up to:" + '\n' +
                TAB + "SGD" + total.toString() + '\n' + MESSAGE_DIVIDER);
    }

}
