package seedu.duke.command;

import seedu.duke.UI;
import seedu.duke.budget.BudgetPlanner;

public class EditBudgetCommand extends BudgetCommand {

    private int budget;

    public EditBudgetCommand(int budget, BudgetPlanner budgetPlanner) {
        super(budgetPlanner);
        this.budget = budget;
    }

    /**
     * Executes the EditBudgetCommand where it gets the previous saved Budget amount.
     * It then sets the new cost and compares between the two. It there was no change or the setting of the new budget
     * is of an invalid value, a budget no change message would be printed.
     * If changed successfully, and edit budget message would be printed.
     */
    @Override
    public void execute() {
        int initialBudget = budgetPlanner.getBudget();
        budgetPlanner.setBudget(budget);
        if (initialBudget == budgetPlanner.getBudget()) {
            UI.printBudgetNoChangeMessage();
            return;
        }
        UI.printEditBudgetMessage(budget);
    }
}
