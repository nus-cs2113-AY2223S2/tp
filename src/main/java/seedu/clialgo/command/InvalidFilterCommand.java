package seedu.clialgo.command;

import seedu.clialgo.Buffer;
import seedu.clialgo.logic.TopicManager;
import seedu.clialgo.Ui;
import seedu.clialgo.storage.FileManager;

public class InvalidFilterCommand extends InvalidCommand {

    /** Constructor for command when an invalid importance is being assigned to a CS2040CFile. */
    public InvalidFilterCommand() {
    }

    /**
     * An overridden method to execute the command when an invalid importance is being assigned to a CS2040CFile.
     *
     * @param topicManager The <code>TopicManager</code> object which handles all CS2040CFiles stored in CLIAlgo.
     * @param ui The <code>Ui</code> object which handles outputs to the user.
     * @param fileManager The <code>FileManager</code> object responsible for saving information in CLIAlgo.
     * @param buffer The <code>Buffer</code> object responsible for exporting filtered files.
     */
    @Override
    public void execute(TopicManager topicManager, Ui ui, FileManager fileManager, Buffer buffer) {
        ui.printInvalidFilterKeyword();
    }
}
