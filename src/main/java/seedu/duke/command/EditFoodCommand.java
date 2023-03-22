package seedu.duke.command;

import seedu.duke.budget.BudgetPlanner;

public class EditFoodCommand extends EditCostCommand {

    public EditFoodCommand(int cost, BudgetPlanner budgetPlanner) {
        super(cost, budgetPlanner);
    }

    @Override
    public void execute() {
        budgetPlanner.setFoodTotalCost(cost);
        System.out.println("Food budget has been changed to: " + budgetPlanner.getFoodTotalCost());
    }
}
