package seedu.commands;

import seedu.expenditure.ExpenditureList;

public class SetBudgetCommand extends Command{

    public static final String COMMAND_WORD = "set";
    public final double budget;
    public SetBudgetCommand(double budget) {
        this.budget = budget;
    }

    @Override
    public CommandResult execute(ExpenditureList expenditures) {
        expenditures.setNewBudget(budget);
        return new CommandResult("New budget of " + expenditures.getBudgetSet() + " has been set!");
    }
}
