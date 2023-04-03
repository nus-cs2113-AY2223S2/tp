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
        double borrowedAmount = 0;
        double lentAmount = 0;
        for (Expenditure individualExpenditure : expenditures.getExpenditures()){
            if (individualExpenditure.getExpenditureType().equals("B")) {
                // For amounts classified under borrows, expenditure will be separated from totalAmount
                borrowedAmount += individualExpenditure.getValue();
            } else if (individualExpenditure.getExpenditureType().equals("L")) {
                // For amounts classified under lend, expenditure will be separated from totalAmount
                lentAmount += individualExpenditure.getValue();
            } else if (!individualExpenditure.getPaidIcon().equals("[X]")) {
                // For expenditures that are marked, it will not be included in totalAmount
                totalAmount += individualExpenditure.getValue();
            }
        }
        return getCheckCommandResult(budget, totalAmount, borrowedAmount, lentAmount);
    }

    private static CommandResult getCheckCommandResult(double budget, double totalAmount,
                                                       double borrowedAmount, double lentAmount) {
        if (budget == 0) {
            // Prevents the user from checking when budget is 0, as it does not provide any insight
            return new CommandResult("Your current budget is set at 0, please use the 'set' command to set a budget.");
        } else if (budget >= totalAmount) {
            // Remaining budget available
            double difference = budget - totalAmount;
            return new CommandResult(String.format(
                    "You are $%.2f away from exceeding your budget of $%.2f." + "\n" +
                    "Your current spending stands at: $%.2f" + "\n" + "Sum of money borrowed: $%.2f"
                    + "\n" + "Sum of money lent: $%.2f",
                    difference, budget, totalAmount, borrowedAmount, lentAmount
            ));
        } else {
            // Amount of budget exceeded by
            double difference = totalAmount - budget;
            return new CommandResult(String.format(
                    "You have exceeded your budget of $%.2f by $%.2f! " + "\n" +
                    "Your current spending stands at: $%.2f " + "\n" + "Sum of money borrowed: $%.2f" + "\n"
                    + "Sum of money lent: $%.2f",
                    budget, difference, totalAmount, borrowedAmount, lentAmount
            ));
        }
    }
}
