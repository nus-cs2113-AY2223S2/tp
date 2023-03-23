package seedu.brokeMan.command;

import seedu.brokeMan.entry.Category;
import seedu.brokeMan.entry.Income;
import seedu.brokeMan.entry.IncomeList;

import java.time.LocalDateTime;

public class AddIncomeCommand extends Command {
    public static final String COMMAND_WORD = "addIncome";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": add expense to the expense list.\n" +
            "|  Parameters: a/ <amount> d/ <description> t/ <time> c/ <category>\n" +
            "|  Example: " + COMMAND_WORD + " a/ 3000 d/ salary t/ 1400H c/ SALARY";
    private final Income income;

    public AddIncomeCommand(double amount, String info, LocalDateTime time, Category category) {
        this.income = new Income(amount, info, time, category);
    }

    public void execute() {
        IncomeList.addIncome(income);
    }
}
