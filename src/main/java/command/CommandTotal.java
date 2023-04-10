package command;

import data.Expense;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

public class CommandTotal extends Command {
    public static final String COMMAND_NAME = "total";
    protected ArrayList<Expense> expenseList;
    protected BigDecimal total = new BigDecimal(0);

    public CommandTotal(ArrayList<Expense> expenseList) {
        super(COMMAND_NAME);
        this.expenseList = expenseList;
    }

    /**
     * Gets the total expenses converted to SGD.
     *
     * @return total expenses in SGD.
     */
    public BigDecimal getTotal() {
        return total.setScale(2, RoundingMode.HALF_UP);
    }

    /**
     * Calculates the total expenses in the expense list in SGD.
     */

    public BigDecimal calculateTotal() {
        total = BigDecimal.valueOf(0);
        for (Expense i : expenseList) {
            total = total.add(i.getExpenseAmount().multiply(i.getRate()));
        }
        return total;
    }

    /**
     * Prints out the total expenses in the expense list in SGD.
     */
    @Override
    public CommandRes execute() {
        return new CommandRes(calculateTotal().setScale(2, RoundingMode.HALF_UP));
    }
}
