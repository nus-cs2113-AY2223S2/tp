package seedu.duke.command;

import seedu.duke.budget.BudgetPlanner;

public class EditAccommodationCommand extends EditCostCommand {

    public EditAccommodationCommand(int cost, BudgetPlanner budgetPlanner) {
        super(cost, budgetPlanner);
    }

    @Override
    public void execute() {
        budgetPlanner.setAccommodationTotalCost(cost);
        System.out.println("Accommodation Budget has been changed to: " + cost);
    }
}
