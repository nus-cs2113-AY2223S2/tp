package seedu.commands;

import seedu.Expenditure.ExpenditureList;

public abstract class Command {

    public boolean isExit() {
        return this instanceof ExitCommand;
    }

    public abstract CommandResult execute(ExpenditureList expenditures);
}
