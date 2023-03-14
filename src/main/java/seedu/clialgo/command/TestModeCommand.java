package seedu.clialgo.command;

import seedu.clialgo.TopicManager;
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
     * @param topicManager The <code>TopicManager</code> object which handles all notes stored in CLIAlgo.
     * @param ui The <code>Ui</code> object which handles outputs to the user.
     * @param fileManager The <code>FileManager</code> object responsible for saving information in CLIAlgo.
     */
    public void execute(TopicManager topicManager, Ui ui, FileManager fileManager) {
        ui.printTestModeStart();
        fileManager.testMode();
        topicManager.testModeStart();
    }

    @Override
    public boolean equals(Command otherCommand) {
        return false;
    }
}
