package seedu.clialgo.command;

import seedu.clialgo.Buffer;
import seedu.clialgo.logic.TopicManager;
import seedu.clialgo.Ui;
import seedu.clialgo.storage.FileManager;

public class EmptyBufferCommand extends Command {
    /**
     * This method prints a message informing the user that the buffer is empty.
     *
     * @param topicManager The <code>TopicManager</code> object which handles all CS2040CFiles stored in CLIAlgo.
     * @param ui The <code>Ui</code> object which handles outputs to the user.
     * @param fileManager The <code>FileManager</code> object responsible for saving information in CLIAlgo.
     * @param buffer The <code>Buffer</code> object responsible to export filtered files.
     */
    @Override
    public void execute(TopicManager topicManager, Ui ui, FileManager fileManager, Buffer buffer) {
        ui.printBufferEmpty();
    }

    /**
     * An overridden method that checks for equality of <code>EmptyBufferCommand</code> objects.
     *
     * @param otherCommand The other <code>EmptyBufferCommandCommand</code> object to be checked against.
     * @return A boolean value to determine whether the <code>EmptyBufferCommandCommand</code> objects are equal.
     */
    @Override
    public boolean equals(Command otherCommand) {
        return otherCommand instanceof EmptyBufferCommand;
    }
}
