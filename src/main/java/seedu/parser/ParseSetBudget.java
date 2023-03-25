package seedu.parser;

import seedu.commands.Command;
import seedu.commands.InvalidCommand;
import seedu.exceptions.EmptyStringException;
import seedu.exceptions.NotPositiveValueException;
import seedu.commands.SetBudgetCommand;

public class ParseSetBudget {
    public static final String BLANK = "";
    private final String budget;

    public ParseSetBudget(String userInput) {
        this.budget = userInput;
    }

    public Command setBudget() {
        try {
            String amount = ParseIndividualValue.parseIndividualValue(budget,BLANK,BLANK);
            double budgetAmount = Double.parseDouble(amount);
            checkPositiveValue(budgetAmount);
            return new SetBudgetCommand(budgetAmount);
        } catch (NumberFormatException e) {
            return new InvalidCommand("Budget amount is not a numerical value!");
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
