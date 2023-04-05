package seedu.brokeMan.command;

import seedu.brokeMan.entry.Category;
import seedu.brokeMan.entry.expense.Expense;
import seedu.brokeMan.entry.expense.ExpenseList;

import java.time.LocalDateTime;

public class AddExpenseCommand extends Command {
    public static final String COMMAND_WORD = "addExpense";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": add expense to the expense list.\n" +
            "|  Parameters: a/ <amount> d/ <description> t/ <time in YYYY MM DD HH mm> c/ <category>\n" +
            "|  Valid categories are: FOOD, SHOPPING, GROCERIES, " +
            "TRANSPORTATION, ENTERTAINMENT, TRAVEL, SALARY, INVESTMENT, and OTHERS\n" +
            "|  Example: " + COMMAND_WORD + " a/ 4.5 d/ lunch t/ 2023 03 22 20 12 c/ FOOD";
    private final Expense expense;

    public AddExpenseCommand(double cost, String info, LocalDateTime time, Category category) {
        this.expense = new Expense(cost, info, time, category);
    }

    public void execute() {
        ExpenseList.addExpense(expense);
    }
}
