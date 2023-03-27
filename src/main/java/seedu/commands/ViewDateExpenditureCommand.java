package seedu.commands;

import seedu.expenditure.ExpenditureList;

import java.time.LocalDate;

public class ViewDateExpenditureCommand extends Command {
    public static final String COMMAND_WORD = "viewdate";
    public final LocalDate date;

    public ViewDateExpenditureCommand(String userInput) {
        this.date = LocalDate.parse(userInput);
    }

    @Override
    public CommandResult execute(ExpenditureList expenditures) {
        return new CommandResult("Here are the specified expenditures: \n"
                + ExpenditureList.specificDateString(date));
    }
}
