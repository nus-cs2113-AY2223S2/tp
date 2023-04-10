package seedu.clialgo.command;

import seedu.clialgo.Buffer;
import seedu.clialgo.logic.TopicManager;
import seedu.clialgo.storage.FileManager;

import seedu.clialgo.Ui;

/**
 * Represents an executable command. A <code>ExitCommand</code> object is created whenever the
 * user decides to exit the program.
 */
public class ExitCommand extends Command {
    /**
     * This method closes the scanner and closes the program.
     *
     * @param topicManager The <code>TopicManager</code> object which handles all CS2040CFiles stored in CLIAlgo.
     * @param ui The <code>Ui</code> object which handles outputs to the user.
     * @param fileManager The <code>FileManager</code> object responsible for saving information in CLIAlgo.
     * @param buffer The <code>Buffer</code> object responsible for exporting filtered files.
     */
    @Override
    public void execute (TopicManager topicManager, Ui ui, FileManager fileManager, Buffer buffer) {
        ui.printExitMessage();
        ui.closeScanner();
        System.exit(0);
    }

    /**
     * An overridden method that checks for equality of <code>ExitCommand</code> objects.
     *
     * @param otherCommand The other <code>ExitCommand</code> object to be checked against.
     * @return A boolean value to determine whether the <code>ExitCommand</code> objects are equal.
     */
    @Override
    public boolean equals(Command otherCommand) {
        return otherCommand instanceof ExitCommand;
    }
}
