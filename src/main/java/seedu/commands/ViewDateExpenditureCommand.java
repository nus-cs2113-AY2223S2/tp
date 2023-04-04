package seedu.commands;

import seedu.exceptions.WrongInputException;
import seedu.expenditure.CurrencyValue;
import seedu.expenditure.ExpenditureList;

import java.time.LocalDate;

public class ViewDateExpenditureCommand extends Command {
    public static final String COMMAND_WORD = "viewdate";
    private final LocalDate date;
    private final String currency;

    public ViewDateExpenditureCommand(String userInput) throws WrongInputException {
        String[] splitValues = userInput.split(" ");
        if (CurrencyValue.isValidCurrency(splitValues[1])) {
            currency = splitValues[1];
        } else {
            throw new WrongInputException();
        }
        this.date = LocalDate.parse(splitValues[0]);
    }

    @Override
    public CommandResult execute(ExpenditureList expenditures) {
        return new CommandResult("Here are the specified expenditures in " + currency + ": \n"
                + ExpenditureList.specificDateString(date, currency));
    }
}
