package seedu.clialgo.command;

import seedu.clialgo.TopicManager;
import seedu.clialgo.storage.FileManager;
import seedu.clialgo.Ui;


/**
 * Represents an executable command. A <code>NameNotFoundCommand</code> object is created whenever the user enters a
 * command that does not contain any existing name of any notes and is unable to generate the appropriate
 * <code>Command</code> object.
 */
public class NameNotFoundCommand extends Command {

    /**
     * This method informs the user that they have keyed in a command that does not contain any existing name of any
     * notes.
     */
    public void execute(TopicManager topicManager, Ui ui, FileManager fileManager) {
        ui.printNameNotFoundCommand();
    }

    /**
     * An overridden method that checks for equality of <code>NameNotFoundCommand</code> objects.
     *
     * @param otherCommand The other <code>NameNotFoundCommand</code> object to be checked against.
     * @return A boolean value to determine whether the <code>NameNotFoundCommand</code> objects are equal.
     */
    @Override
    public boolean equals(Command otherCommand) {
        return otherCommand instanceof NameNotFoundCommand;
    }
}

