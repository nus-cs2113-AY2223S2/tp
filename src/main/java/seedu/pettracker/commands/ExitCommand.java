package seedu.pettracker.commands;

import seedu.pettracker.ui.Ui;

public class ExitCommand extends Command {
    //@@author shawntangy-reused
    //Reused from https://github.com/se-edu/addressbook-level2/blob/master/src/seedu/addressbook/commands/ExitCommand.java
    // with minor modifications

    /**
     * Terminates the program.
     */

    public static final String command = "exit";

    public ExitCommand() {
    }
    @Override
    public void execute(Ui ui) {
        ui.exitCommandMessage();
    }

    public  boolean isExit() {
        return true;
    }

}
