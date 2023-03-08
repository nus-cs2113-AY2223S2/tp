package Command;

import MajorClasses.Expense;
import data.MessageList;

import java.util.ArrayList;
import java.util.stream.IntStream;

public class CommandRes {
    public String messageTop = "";
    public String messageBottom = "";
    public ArrayList<Expense> expense = null;

    public CommandRes(String strTop) {
        this.messageTop = strTop;
    }
    public CommandRes(String strTop, ArrayList expense, String strBottom) {
        this.messageTop = strTop;
        this.messageBottom = strBottom;
        this.expense = expense;
    }

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