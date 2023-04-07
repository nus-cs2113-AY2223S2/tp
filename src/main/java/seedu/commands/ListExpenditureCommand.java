package seedu.commands;

import seedu.exceptions.WrongInputException;
import seedu.expenditure.CurrencyValue;
import seedu.expenditure.ExpenditureList;

public class ListExpenditureCommand extends Command {
    // Edit file accordingly
    public static final String COMMAND_WORD = "list";
    private String currency;

    public ListExpenditureCommand(String currency) throws WrongInputException {
        if (CurrencyValue.isValidCurrency(currency)) {
            this.currency = currency;
        } else {
            throw new WrongInputException();
        }
    }

    @Override
    public CommandResult execute(ExpenditureList expenditures) {
        if(expenditures.getSize() == 0) {
            return new CommandResult("Your list is currently empty. Start adding some transactions now!\n");
        }
        
        return new CommandResult("Here is your list of expenditures in " + currency + ": \n"
                + expenditures.listString(currency));
    }
}
