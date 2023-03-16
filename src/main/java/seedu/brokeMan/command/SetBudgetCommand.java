package seedu.brokeMan.command;

import seedu.brokeMan.budget.Budget;

public class SetBudgetCommand extends Command {
    public final static String COMMAND_WORD = "setBudget";
    public final static String MESSAGE_USAGE = COMMAND_WORD + ": sets your budget.\n" +
            "|  Parameter: <amount>\n" +
            "|  Example: " + COMMAND_WORD + " 500";
    private final double budget;

    public SetBudgetCommand(double budget) {
        this.budget = budget;
    }

    public void execute() {
        Budget.setBudget(budget);
    }
}
