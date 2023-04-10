package seedu.duke.command;

import seedu.duke.UI;
import seedu.duke.budget.Accommodation;
import seedu.duke.budget.BudgetPlanner;

public class EditAccommodationCommand extends EditCostCommand {

    public EditAccommodationCommand(int cost, BudgetPlanner budgetPlanner) {
        super(cost, budgetPlanner);
    }

    /**
     * Executes the EditAccommodationCommand where it gets the previous saved cost of the accommodation.
     * It then sets the new cost and compares between the two. It there was no change or the setting of the new cost
     * is of an invalid value, a cost no change message would be printed.
     * If changed successfully, and edit cost message would be printed.
     */
    @Override
    public void execute() {
        int initialCost = budgetPlanner.getAccommodationTotalCost();
        budgetPlanner.setAccommodationTotalCost(cost);
        if (initialCost == budgetPlanner.getAccommodationTotalCost()) {
            UI.printCostNoChangeMessage();
            return;
        }
        UI.printEditCostMessage(cost, new Accommodation(cost));
    }
}
