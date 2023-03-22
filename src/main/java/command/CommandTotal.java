package command;

import data.Expense;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import static common.MessageList.MESSAGE_DIVIDER;

public class CommandTotal extends Command{
    public static final String COMMAND_NAME = "total";
    protected ArrayList<Expense> expenseList;
    protected BigDecimal total = new BigDecimal(0);
    public CommandTotal(ArrayList<Expense> expenseList) {
        super(COMMAND_NAME);
        this.expenseList = expenseList;
    }

    /**
     * Gets the total expenses converted to SGD.
     * @return total expenses in SGD.
     */
    public BigDecimal getTotal() {
        return total.setScale(2, RoundingMode.HALF_UP);
    }
    @Override
    /**
     * Calculates and prints out the total expenses in the expense list in SGD.
     */
    public CommandRes execute() {
        total = new BigDecimal(0);
        for(Expense i : expenseList) {
            total = total.add(i.getExpenseAmount().multiply(i.getRate()));
        }
        return new CommandRes(total.setScale(2, RoundingMode.HALF_UP));
    }
}
