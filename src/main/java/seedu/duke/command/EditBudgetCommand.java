package seedu.duke.command;

import seedu.duke.UI;
import seedu.duke.budget.BudgetPlanner;

public class EditBudgetCommand extends BudgetCommand {

    private int budget;

    public EditBudgetCommand(int budget, BudgetPlanner budgetPlanner) {
        super(budgetPlanner);
        this.budget = budget;
    }

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
