package seedu.clialgo.command;

import seedu.clialgo.Buffer;
import seedu.clialgo.logic.TopicManager;
import seedu.clialgo.Ui;
import seedu.clialgo.storage.FileManager;

/**
 * Object representing the <code>Command</code> to start test mode such that data in test mode is not saved after
 * exiting test mode.
 */
public class TestModeCommand extends Command {

    /**
     * An overridden method to begin the application in test mode.
     *
     * @param topicManager The <code>TopicManager</code> object which handles all CS2040CFiles stored in CLIAlgo.
     * @param ui The <code>Ui</code> object which handles outputs to the user.
     * @param fileManager The <code>FileManager</code> object responsible for saving information in CLIAlgo.
     * @param buffer The <code>Buffer</code> object responsible for exporting filtered files.
     */
    public void execute(TopicManager topicManager, Ui ui, FileManager fileManager, Buffer buffer) {
        ui.printTestModeStart();
        fileManager.testMode();
        topicManager.testModeStart();
    }

    @Override
    public boolean equals(Command otherCommand) {
        return otherCommand instanceof TestModeCommand;
    }
}
