package seedu.clialgo.command;

import seedu.clialgo.Buffer;
import seedu.clialgo.logic.TopicManager;
import seedu.clialgo.Ui;
import seedu.clialgo.storage.FileManager;

//@@author lohjooh
public class ExportCommand extends Command {

    /**
     * Exports the files currently stored in the buffer
     *
     * @param topicManager The <code>TopicManager</code> object which handles all CS2040CFiles stored in CLIAlgo.
     * @param ui The <code>Ui</code> object which handles outputs to the user.
     * @param fileManager The <code>FileManager</code> object responsible for saving information in CLIAlgo.
     * @param buffer The <code>Buffer</code> object responsible to export filtered files.
     */
    public void execute (TopicManager topicManager, Ui ui, FileManager fileManager, Buffer buffer) {
        if (buffer.isEmpty()) {
            new EmptyBufferCommand().execute(topicManager, ui, fileManager, buffer);
            return;
        }
        ui.printExportSuccess();
        buffer.exportBuffer();
    }
    /**
     * An overridden method that checks for equality of <code>ExportCommand </code> objects.
     *
     * @param otherCommand The other <code>ExportCommand </code> object to be checked against.
     * @return A boolean value to determine whether the <code>ExportCommand </code> objects are equal.
     */
    public boolean equals(Command otherCommand) {
        return otherCommand instanceof ExportCommand;
    }
}
