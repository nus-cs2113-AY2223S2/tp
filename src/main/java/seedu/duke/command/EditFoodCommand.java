package seedu.duke.command;

import seedu.duke.UI;
import seedu.duke.budget.BudgetPlanner;
import seedu.duke.budget.Food;

public class EditFoodCommand extends EditCostCommand {

    public EditFoodCommand(int cost, BudgetPlanner budgetPlanner) {
        super(cost, budgetPlanner);
    }

    @Override
    public void execute() {
        int initialCost = budgetPlanner.getFoodTotalCost();
        budgetPlanner.setFoodTotalCost(cost);
        if (initialCost == budgetPlanner.getFoodTotalCost()) {
            UI.printCostNoChangeMessage();
            return;
        }
        UI.printEditCostMessage(cost, new Food(cost));
    }
}
