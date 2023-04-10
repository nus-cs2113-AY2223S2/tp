package seedu.clialgo.command;

import seedu.clialgo.Buffer;
import seedu.clialgo.logic.TopicManager;
import seedu.clialgo.Ui;
import seedu.clialgo.storage.FileManager;

import java.util.Objects;

public class InvalidTopicCommand extends Command {
    private final String topic;

    /**
     * Constructor for command when an invalid topic is being tagged to a CS2040CFile.
     *
     * @param topic The invalid topic for the CS2040CFile.
     */
    public InvalidTopicCommand(String topic) {
        this.topic = topic;
    }

    /**
     * An overridden method to execute the command when
     * an invalid topic is being tagged to a CS2040CFile.
     *
     * @param topicManager The <code>TopicManager</code> object which handles all CS2040CFiles stored in CLIAlgo.
     * @param ui The <code>Ui</code> object which handles outputs to the user.
     * @param fileManager The <code>FileManager</code> object responsible for saving information in CLIAlgo.
     * @param buffer The <code>Buffer</code> object responsible for exporting filtered files.
     */
    @Override
    public void execute (TopicManager topicManager, Ui ui, FileManager fileManager, Buffer buffer) {
        ui.printAddFail(topic);
    }

    /**
     * An overridden method that checks for equality of <code>InvalidTopicCommand</code> objects.
     *
     * @param otherCommand The other object to be checked against.
     * @return A boolean value to determine whether the <code>InvalidTopicCommand</code> objects are equal.
     */
    @Override
    public boolean equals(Command otherCommand) {
        InvalidTopicCommand otherInvalidTopicCommand = (InvalidTopicCommand) otherCommand;

        return Objects.equals(this.topic, otherInvalidTopicCommand.topic);
    }
}
