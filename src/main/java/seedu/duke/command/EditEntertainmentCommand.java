package seedu.duke.command;

import seedu.duke.UI;
import seedu.duke.budget.BudgetPlanner;
import seedu.duke.budget.Entertainment;

public class EditEntertainmentCommand extends EditCostCommand {

    public EditEntertainmentCommand(int cost, BudgetPlanner budgetPlanner) {
        super(cost, budgetPlanner);
    }

    /**
     * Executes the EditEntertainmentCommand where it gets the previous saved cost of the Entertainment.
     * It then sets the new cost and compares between the two. It there was no change or the setting of the new cost
     * is of an invalid value, a cost no change message would be printed.
     * If changed successfully, and edit cost message would be printed.
     */
    @Override
    public void execute() {
        int initialCost = budgetPlanner.getEntertainmentTotalCost();
        budgetPlanner.setEntertainmentTotalCost(cost);
        if (initialCost == budgetPlanner.getEntertainmentTotalCost()) {
            UI.printCostNoChangeMessage();
            return;
        }
        UI.printEditCostMessage(cost, new Entertainment(cost));
    }
}
