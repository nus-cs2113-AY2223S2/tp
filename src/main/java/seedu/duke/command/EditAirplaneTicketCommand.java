package seedu.duke.command;

import seedu.duke.UI;
import seedu.duke.budget.AirplaneTicket;
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
            UI.printCostNoChangeMessage();
            return;
        }
        UI.printEditCostMessage(cost, new AirplaneTicket(cost));
    }
}
