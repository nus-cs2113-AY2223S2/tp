package seedu.clialgo.command;

import seedu.clialgo.Buffer;
import seedu.clialgo.logic.TopicManager;
import seedu.clialgo.Ui;
import seedu.clialgo.storage.FileManager;

/**
 * Object representing the <code>Command</code> to end test mode such that data in test mode is not saved.
 */
public class ExitTestModeCommand extends Command {

    /**
     * An overridden method to end the application in test mode.
     *
     * @param topicManager The <code>TopicManager</code> object which handles all CS2040CFiles stored in CLIAlgo.
     * @param ui The <code>Ui</code> object which handles outputs to the user.
     * @param fileManager The <code>FileManager</code> object responsible for saving information in CLIAlgo.
     * @param buffer The object responsible to export filtered files.
     */
    public void execute(TopicManager topicManager, Ui ui, FileManager fileManager, Buffer buffer) {
        if (!topicManager.getIsTestModeOn()) {
            ui.printTestModeEndFail();
            return;
        }
        ui.printTestModeEnd();
        fileManager.exitTestMode();
        topicManager.testModeEnd();
    }

    @Override
    public boolean equals(Command otherCommand) {
        return otherCommand instanceof ExitTestModeCommand;
    }
}
