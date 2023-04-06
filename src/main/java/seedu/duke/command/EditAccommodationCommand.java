package seedu.duke.command;

import seedu.duke.UI;
import seedu.duke.budget.Accommodation;
import seedu.duke.budget.BudgetPlanner;

public class EditAccommodationCommand extends EditCostCommand {

    public EditAccommodationCommand(int cost, BudgetPlanner budgetPlanner) {
        super(cost, budgetPlanner);
    }

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
