package seedu.commands;

import seedu.expenditure.ExpenditureList;

public class ExitCommand extends Command {
    public static final String COMMAND_WORD = "exit";

    /**
     * Contructor.
     */
    public ExitCommand() {
    }

    @Override
    public CommandResult execute(ExpenditureList expenditures) {
        return new CommandResult(
                "Goodbye. Your progress towards your financial goals is important to us. See you again soon!");
    }
}
