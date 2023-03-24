package seedu.duke.command;

import seedu.duke.budget.BudgetPlanner;

public class EditAirplaneTicketCommand extends EditCostCommand {

    public EditAirplaneTicketCommand(int cost, BudgetPlanner budgetPlanner) {
        super(cost, budgetPlanner);
    }

    @Override
    public void execute() {
        int initialCost = budgetPlanner.getAirplaneTicketTotalCost();
        budgetPlanner.setAirplaneTicketTotalCost(cost);
        if (initialCost == budgetPlanner.getAirplaneTicketTotalCost()) {
            System.out.println("Budget has not been changed");
        } else {
            System.out.println("Airplane budget has been changed to: " + budgetPlanner.getAirplaneTicketTotalCost());
        }
    }
}
