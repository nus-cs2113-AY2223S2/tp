package seedu.duke.command;

import seedu.duke.UI;
import seedu.duke.budget.BudgetPlanner;

public class ViewBudgetCommand extends BudgetCommand {

    public ViewBudgetCommand(BudgetPlanner budgetPlanner) {
        super(budgetPlanner);
    }

    /**
     * Executes the ViewBudgetCommand where it prints out all the components of the Budget, including Budget,
     * Accommodation cost, Airplane Ticket cost, Entertainment cost, Food cost, and surplus/deficit.
     */
    @Override
    public void execute() {
        UI.printViewBudget(budgetPlanner);
    }
}
