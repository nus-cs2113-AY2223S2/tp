package seedu.duke.command;

import seedu.duke.budget.BudgetPlanner;

public class EditFoodCommand extends EditCostCommand {

    public EditFoodCommand(int cost, BudgetPlanner budgetPlanner) {
        super(cost, budgetPlanner);
    }

    @Override
    public void execute() {
        int initialCost = budgetPlanner.getFoodTotalCost();
        budgetPlanner.setFoodTotalCost(cost);
        if (initialCost == budgetPlanner.getFoodTotalCost()) {
            System.out.println("Budget has not been changed");
        } else {
            System.out.println("Food budget has been changed to: " + budgetPlanner.getFoodTotalCost());
        }
    }
}
