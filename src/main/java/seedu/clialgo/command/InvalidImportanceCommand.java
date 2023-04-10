package seedu.clialgo.command;

import seedu.clialgo.Buffer;
import seedu.clialgo.logic.TopicManager;
import seedu.clialgo.Ui;
import seedu.clialgo.storage.FileManager;

import java.util.Objects;

//@@author nikkiDEEE
public class InvalidImportanceCommand extends Command {
    private final String importance;

    /**
     * Constructor for command when an invalid importance is being assigned to a CS2040CFile.
     *
     * @param importance The invalid importance assigned to the CS2040CFile.
     */
    public InvalidImportanceCommand(String importance) {
        this.importance = importance;
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
        ui.printInvalidImportance(importance);
    }

    /**
     * An overridden method that checks for equality of <code>InvalidImportanceCommand</code> objects.
     *
     * @param otherCommand The other object to be checked against.
     * @return A boolean value to determine whether the <code>InvalidImportanceCommand</code> objects are equal.
     */
    @Override
    public boolean equals(Command otherCommand) {
        InvalidImportanceCommand otherInvalidImportanceCommand = (InvalidImportanceCommand) otherCommand;

        return Objects.equals(this.importance, otherInvalidImportanceCommand.importance);
    }
}
//@@author
