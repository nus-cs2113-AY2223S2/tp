package seedu.commands;

import seedu.expenditure.Expenditure;
import seedu.expenditure.ExpenditureList;

public class CheckBudgetCommand extends Command {
    public static final String COMMAND_WORD = "check";
    public CheckBudgetCommand() {

    }

    @Override
    public CommandResult execute(ExpenditureList expenditures) {
        double budget = expenditures.getBudgetSet();
        double totalAmount = 0;
        for (Expenditure individualExpenditure : expenditures.getExpenditures()){
            totalAmount += individualExpenditure.getValue();
        }
        return getCommandResult(budget, totalAmount);
    }

    private static CommandResult getCommandResult(double budget, double totalAmount) {
        if (budget == 0) {
            return new CommandResult("Your current budget is set at 0, please use the 'set' command to set a budget.");
        } else if (budget >= totalAmount) {
            // Remaining budget available
            double difference = budget - totalAmount;
            return new CommandResult(String.format("You are $%.2f away from hitting your budget of $%.2f." +
                    " Your current spending stands at $%.2f",
                    difference, budget, totalAmount
            ));
        } else {
            double difference = totalAmount - budget;
            return new CommandResult(String.format(
                    "You have exceeded your budget of $%.2f by $%.2f! Your current spending stands at $%.2f",
                    budget, difference, totalAmount
            ));
        }
    }
}
