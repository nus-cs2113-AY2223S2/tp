package seedu.duke.command;

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
            System.out.println("Budget has not been changed");
        } else {
            System.out.println("Accommodation Budget has been changed to: " + budgetPlanner.getAccommodationTotalCost());
        }
    }
}
