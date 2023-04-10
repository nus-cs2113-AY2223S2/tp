package seedu.brokeMan.command;

import seedu.brokeMan.budget.Budget;

import java.util.Optional;

public class SetBudgetCommand extends Command {
    public static final String COMMAND_WORD = "setBudget";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": sets your budget for current month.\n" +
            "|  " + COMMAND_WORD + " <amount> t/ <date in YYYY/MM>: sets your budget for specified month\n" +
            "|  Compulsory Parameter: <amount>\n" +
            "|  Optional Parameter: t/ <date in YYYY/MM>\n" +
            "|  Example: " + COMMAND_WORD + " 500\n" +
            "|  Example: " + COMMAND_WORD + " 500 t/ 2023/03";
    private final double budget;
    private final Optional<String> date;

    public SetBudgetCommand(double budget) {
        this.budget = budget;
        date = Optional.empty();
    }

    public SetBudgetCommand(double budget, String date) {
        this.budget = budget;
        this.date = Optional.of(date);
    }

    public void execute() {
        Budget.setBudget(budget, this.date);
    }
}
