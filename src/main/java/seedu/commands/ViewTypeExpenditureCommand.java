package seedu.commands;

import seedu.expenditure.ExpenditureList;

public class ViewTypeExpenditureCommand extends Command {
    public static final String COMMAND_WORD = "viewtype";
    public final String userInput;

    public ViewTypeExpenditureCommand(String userInput) {
        this.userInput = userInput;
    }

    @Override
    public CommandResult execute(ExpenditureList expenditures) {
        return new CommandResult("Here are the specified expenditures: \n"
                + expenditures.toString());
    }
}
