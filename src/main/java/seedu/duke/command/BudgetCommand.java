package seedu.duke.command;

import seedu.duke.budget.BudgetPlanner;

public abstract class BudgetCommand extends Command {
    BudgetPlanner budgetPlanner;

    public BudgetCommand(BudgetPlanner budgetPlanner) {
        this.budgetPlanner = budgetPlanner;
    }
}
