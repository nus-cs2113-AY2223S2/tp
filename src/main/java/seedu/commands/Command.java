package seedu.commands;

import seedu.expenditure.ExpenditureList;

public abstract class Command {

    public boolean isExit() {
        return this instanceof ExitCommand;
    }


    public abstract CommandResult execute(ExpenditureList expenditures);
}
