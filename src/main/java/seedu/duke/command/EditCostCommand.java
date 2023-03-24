package seedu.duke.command;

import seedu.duke.budget.BudgetPlanner;

public abstract class EditCostCommand extends BudgetCommand {
    public int cost;

    public EditCostCommand(int cost, BudgetPlanner budgetPlanner) {
        super(budgetPlanner);
        this.cost = cost;
    }

}
