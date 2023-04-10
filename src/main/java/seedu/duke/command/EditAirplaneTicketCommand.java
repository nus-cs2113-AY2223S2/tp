package seedu.duke.command;

import seedu.duke.UI;
import seedu.duke.budget.AirplaneTicket;
import seedu.duke.budget.BudgetPlanner;

public class EditAirplaneTicketCommand extends EditCostCommand {

    public EditAirplaneTicketCommand(int cost, BudgetPlanner budgetPlanner) {
        super(cost, budgetPlanner);
    }

    /**
     * Executes the EditAirplaneTicketCommand where it gets the previous saved cost of the AirplaneTicket.
     * It then sets the new cost and compares between the two. It there was no change or the setting of the new cost
     * is of an invalid value, a cost no change message would be printed.
     * If changed successfully, and edit cost message would be printed.
     */
    @Override
    public void execute() {
        int initialCost = budgetPlanner.getAirplaneTicketTotalCost();
        budgetPlanner.setAirplaneTicketTotalCost(cost);
        if (initialCost == budgetPlanner.getAirplaneTicketTotalCost()) {
            UI.printCostNoChangeMessage();
            return;
        }
        UI.printEditCostMessage(cost, new AirplaneTicket(cost));
    }
}
