package seedu.brokeMan.command;

import seedu.brokeMan.entry.Category;
import seedu.brokeMan.entry.Expense;
import seedu.brokeMan.entry.ExpenseList;

import java.time.LocalDateTime;

public class AddExpenseCommand extends Command {
    public static final String COMMAND_WORD = "addExpense";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": add expense to the expense list.\n" +
            "|  Parameters: a/ <amount> d/ <description> t/ <time> c/ <category>\n" +
            "|  Example: " + COMMAND_WORD + " addExpense a/ 4.00 d/ lunch t/ 2022 09 08 12 14 c/ FOOD";
    private final Expense expense;

    public AddExpenseCommand(double cost, String info, LocalDateTime time, Category category) {
        this.expense = new Expense(cost, info, time, category);
    }

    public void execute() {
        ExpenseList.addExpense(expense);
    }
}
