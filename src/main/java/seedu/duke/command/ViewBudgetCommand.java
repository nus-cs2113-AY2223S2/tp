package seedu.duke.command;

import seedu.duke.budget.BudgetPlanner;

public class ViewBudgetCommand extends BudgetCommand {

    public ViewBudgetCommand(BudgetPlanner budgetPlanner) {
        super(budgetPlanner);
    }

    @Override
    public void execute() {
        System.out.println("Total budget: " + budgetPlanner.getBudget());
        System.out.println("Accommodation cost: " + budgetPlanner.getAccommodationTotalCost());
        System.out.println("Airplane Ticket cost: " + budgetPlanner.getAirplaneTicketTotalCost());
        System.out.println("Food cost: " + budgetPlanner.getFoodTotalCost());
        System.out.println("Entertainment cost: " + budgetPlanner.getEntertainmentTotalCost());
        System.out.println("Surplus/Deficit: " + budgetPlanner.getSurplus());
    }
}
