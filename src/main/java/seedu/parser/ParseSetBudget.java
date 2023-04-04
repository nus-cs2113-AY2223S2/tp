package seedu.parser;

import seedu.commands.Command;
import seedu.commands.InvalidCommand;
import seedu.exceptions.EmptyStringException;
import seedu.exceptions.ExceptionChecker;
import seedu.exceptions.NotPositiveValueException;
import seedu.commands.SetBudgetCommand;
import seedu.exceptions.SmallAmountException;

import static seedu.ui.ErrorMessages.ERROR_BUDGET_NOT_NUMERICAL_MESSAGE;

public class ParseSetBudget {
    public static final String BLANK = "";
    private final String budget;

    public ParseSetBudget(String userInput) {
        this.budget = userInput;
    }

    public Command setBudget() {
        try {
            // Removes empty space from the user input
            String amount = ParseIndividualValue.parseIndividualValue(budget,BLANK,BLANK);
            // Converts from string to double for numerical addition functionalities
            double budgetAmount = Double.parseDouble(amount);
            ExceptionChecker.checkValidAmount(budgetAmount);
            return new SetBudgetCommand(budgetAmount);
        } catch (NumberFormatException e) {
            return new InvalidCommand(ERROR_BUDGET_NOT_NUMERICAL_MESSAGE.toString());
        } catch (EmptyStringException | NotPositiveValueException | SmallAmountException e) {
            return new InvalidCommand(e.getMessage());
        }
    }

}
