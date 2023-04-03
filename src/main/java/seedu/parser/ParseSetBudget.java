package seedu.parser;

import seedu.commands.Command;
import seedu.commands.InvalidCommand;
import seedu.exceptions.EmptyStringException;
import seedu.exceptions.NotPositiveValueException;
import seedu.commands.SetBudgetCommand;

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
            checkPositiveValue(budgetAmount);
            return new SetBudgetCommand(budgetAmount);
        } catch (NumberFormatException e) {
            return new InvalidCommand(ERROR_BUDGET_NOT_NUMERICAL_MESSAGE.toString());
        } catch (EmptyStringException e) {
            return new InvalidCommand(e.getMessage());
        } catch (NotPositiveValueException e) {
            return new InvalidCommand(e.getMessage());
        }
    }

    private void checkPositiveValue(double budgetAmount) throws NotPositiveValueException {
        if (budgetAmount < 0) {
            throw new NotPositiveValueException();
        }
    }

}
