package seedu.duke.command;

import seedu.duke.budget.BudgetPlanner;

public class EditEntertainmentCommand extends EditCostCommand {

    public EditEntertainmentCommand(int cost, BudgetPlanner budgetPlanner) {
        super(cost,budgetPlanner);
    }

    @Override
    public void execute() {
        budgetPlanner.setEntertainmentTotalCost(cost);
        System.out.println("Entertainment budget has been changed to: " + cost);
    }
}
