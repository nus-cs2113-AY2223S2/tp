package seedu.commands;

import seedu.exceptions.DateLimitException;
import seedu.exceptions.ExceptionChecker;
import seedu.exceptions.WrongInputException;
import seedu.expenditure.CurrencyValue;
import seedu.expenditure.ExpenditureList;

import java.time.LocalDate;

public class ViewDateExpenditureCommand extends Command {
    public static final String COMMAND_WORD = "viewdate";
    private final LocalDate date;
    private final String currency;

    public ViewDateExpenditureCommand(String userInput) throws WrongInputException, DateLimitException {
        String[] splitValues = userInput.split(" ");
        if (CurrencyValue.isValidCurrency(splitValues[1])) {
            currency = splitValues[1];
        } else {
            throw new WrongInputException();
        }
        LocalDate currentDate = LocalDate.parse(splitValues[0]);
        ExceptionChecker.checkDateLimit(currentDate);
        this.date = currentDate;
    }

    @Override
    public CommandResult execute(ExpenditureList expenditures) {
        return new CommandResult(ExpenditureList.specificDateString(date, currency));
    }
}
