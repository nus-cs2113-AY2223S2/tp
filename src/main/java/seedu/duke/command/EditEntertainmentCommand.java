package seedu.duke.command;

import seedu.duke.budget.BudgetPlanner;

public class EditEntertainmentCommand extends EditCostCommand {

    public EditEntertainmentCommand(int cost, BudgetPlanner budgetPlanner) {
        super(cost, budgetPlanner);
    }

    @Override
    public void execute() {
        int initialCost = budgetPlanner.getEntertainmentTotalCost();
        budgetPlanner.setEntertainmentTotalCost(cost);
        if (initialCost == budgetPlanner.getEntertainmentTotalCost()) {
            System.out.println("Budget has not been changed");
        } else {
            System.out.println("Entertainment budget has been changed to: " + budgetPlanner.getEntertainmentTotalCost());
        }
    }
}
