package seedu.duke.command;

import seedu.duke.UI;
import seedu.duke.budget.BudgetPlanner;
import seedu.duke.budget.Entertainment;

public class EditEntertainmentCommand extends EditCostCommand {

    public EditEntertainmentCommand(int cost, BudgetPlanner budgetPlanner) {
        super(cost, budgetPlanner);
    }

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
