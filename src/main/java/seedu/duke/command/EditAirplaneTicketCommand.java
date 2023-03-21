package seedu.duke.command;

import seedu.duke.budget.BudgetPlanner;

public class EditAirplaneTicketCommand extends EditCostCommand {

    public EditAirplaneTicketCommand(int cost, BudgetPlanner budgetPlanner) {
        super(cost, budgetPlanner);
    }

    @Override
    public void execute() {
        budgetPlanner.setAirplaneTicketTotalCost(cost);
        System.out.println("Entertainment budget has been changed to: " + cost);
    }
}
