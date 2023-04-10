package seedu.commands;

import seedu.expenditure.ExpenditureList;
import seedu.expenditure.CurrencyValue;

public class ShowRatesCommand extends Command {
    public static final String COMMAND_WORD = "showrates";

    public ShowRatesCommand() {
    }

    @Override
    public CommandResult execute(ExpenditureList expenditures) {

        return new CommandResult(CurrencyValue.getRates());
    }
}
