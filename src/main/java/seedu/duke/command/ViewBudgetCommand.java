package seedu.duke.command;

import seedu.duke.UI;
import seedu.duke.budget.BudgetPlanner;

public class ViewBudgetCommand extends BudgetCommand {

    public ViewBudgetCommand(BudgetPlanner budgetPlanner) {
        super(budgetPlanner);
    }

    @Override
    public void execute() {
        UI.printViewBudget(budgetPlanner);
    }
}
