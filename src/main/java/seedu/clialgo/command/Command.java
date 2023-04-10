package seedu.clialgo.command;

import seedu.clialgo.Buffer;
import seedu.clialgo.logic.TopicManager;
import seedu.clialgo.storage.FileManager;
import seedu.clialgo.Ui;

/**
 * Represents an executable command from the user. A <code>Command</code> object contains the details needed to execute
 * the command. Each subclass of <code>Command</code> will have an overridden method <code>execute</code> which has its
 * own implementation.
 */
public abstract class Command {

    /**
     * A method to be overridden by the subclasses to execute specific commands by the user.
     *
     * @param topicManager The <code>TopicManager</code> object which handles all CS2040CFiles stored in CLIAlgo.
     * @param ui The <code>Ui</code> object which handles outputs to the user.
     * @param fileManager The <code>FileManager</code> object responsible for saving information in CLIAlgo.
     * @param buffer The object responsible to export filtered files.
     */
    public abstract void execute(TopicManager topicManager, Ui ui, FileManager fileManager, Buffer buffer);

    /**
     * A method to be overridden by the subclasses to check for equality of the instantiated objects.
     *
     * @param otherCommand The other object to be checked against.
     * @return A boolean value to determine whether the commands are equal.
     */
    public abstract boolean equals(Command otherCommand);
}
