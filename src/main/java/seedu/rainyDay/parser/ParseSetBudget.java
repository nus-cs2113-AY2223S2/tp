package seedu.rainyDay.parser;

import seedu.rainyDay.command.Command;
import seedu.rainyDay.command.SetBudgetCommand;
import seedu.rainyDay.exceptions.ErrorMessage;
import seedu.rainyDay.exceptions.RainyDayException;

import java.util.logging.Logger;

//@@author BenjaminPoh
public class ParseSetBudget extends Parser {
    public static final double MAX_AMOUNT = 21474836.47;
    private static final Logger logger = Logger.getLogger(ParseSetBudget.class.getName());

    public Command setUserBudgetGoal(String userInput) throws RainyDayException {
        userInput = userInput.substring(9).trim();
        try {
            double amount = Double.parseDouble(userInput);
            if (amount > MAX_AMOUNT || amount < 0) {
                throw new RainyDayException(ErrorMessage.INVALID_GOAL.toString());
            }
            amount = (int) (amount * 100);
            amount /= 100;
            return new SetBudgetCommand(amount);
        } catch (RainyDayException e) {
            logger.warning("set budget details provided incorrectly");
            throw new RainyDayException(e.getMessage());
        } catch (Exception e) {
            logger.warning("set budget details provided incorrectly");
            throw new RainyDayException(ErrorMessage.WRONG_SET_BUDGET_FORMAT.toString());
        }
    }
}
