package seedu.clialgo.command;

import seedu.clialgo.TopicManager;
import seedu.clialgo.storage.FileManager;
import seedu.clialgo.Ui;


/**
 * Represents an executable command. A <code>InvalidCommand</code> object is created whenever the user enters an invalid
 * command and is unable to generate the appropriate <code>Command</code> object.
 */
public class InvalidCommand extends Command {

    /**
     * This method informs the user that they have keyed in an invalid command.
     */
    public void execute(TopicManager topicManager, Ui ui, FileManager fileManager) {
        ui.printInvalidCommand();
    }

    /**
     * An overridden method that checks for equality of <code>InvalidCommand</code> objects.
     *
     * @param otherCommand The other <code>InvalidCommand</code> object to be checked against.
     * @return A boolean value to determine whether the <code>InvalidCommand</code> objects are equal.
     */
    @Override
    public boolean equals(Command otherCommand) {
        return otherCommand instanceof InvalidCommand;
    }
}
