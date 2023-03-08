package seedu.pettracker.commands;

import seedu.pettracker.ui.Ui;

public class ExitCommand extends Command {
    // @@author shawntangy-reused
    // Reused from
    // https://github.com/se-edu/addressbook-level2/blob/master/src/seedu/addressbook/commands/ExitCommand.java
    // with minor modifications

    /**
     * Terminates the program.
     */
    public static final String EXIT_COMMAND = "exit";

    public static final String COMMAND = EXIT_COMMAND;

    public ExitCommand() {
    }

    /**
     * @param ui Ui class to do prints
     */
    @Override
    public void execute(Ui ui) {
        ui.exitCommandMessage();
    }

    /**
     * Sets isExit to be true to exit the program
     * @return isExit boolean value for program to exit
     */
    public boolean isExit() {
        return true;
    }

}
