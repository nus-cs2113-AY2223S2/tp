package seedu.brokeMan.command;

import seedu.brokeMan.entry.Category;
import seedu.brokeMan.entry.income.Income;
import seedu.brokeMan.entry.income.IncomeList;

import java.time.LocalDateTime;

public class AddIncomeCommand extends Command {
    public static final String COMMAND_WORD = "addIncome";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": add income to the income list.\n" +
            "|  Parameters: a/ <amount> d/ <description> t/ <time in YYYY MM DD HH mm> c/ <category>\n" +
            "|  Valid categories are: FOOD, SHOPPING, GROCERIES, " +
            "TRANSPORTATION, ENTERTAINMENT, TRAVEL, SALARY, INVESTMENT, and OTHERS\n" +
            "|  Example: " + COMMAND_WORD + " a/ 3000 d/ salary t/ 2023 03 10 10 10 c/ SALARY";
    private final Income income;

    public AddIncomeCommand(double amount, String info, LocalDateTime time, Category category) {
        this.income = new Income(amount, info, time, category);
    }

    public void execute() {
        IncomeList.addIncome(income);
    }
}
