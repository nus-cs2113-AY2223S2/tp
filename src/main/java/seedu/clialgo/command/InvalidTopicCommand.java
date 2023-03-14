package seedu.clialgo.command;

import seedu.clialgo.TopicManager;
import seedu.clialgo.Ui;
import seedu.clialgo.storage.FileManager;

import java.util.Objects;

public class InvalidTopicCommand extends Command {

    private String topic;

    /**
     * Constructor for command when an invalid topic is being tagged to a note file.
     *
     * @param topic The invalid topic for the note file.
     */
    public InvalidTopicCommand(String topic) {
        this.topic = topic;
    }

    /**
     * An overridden method to execute the command when
     * an invalid topic is being tagged to a note file.
     *
     * @param topicManager The <code>TopicManager</code> object.
     * @param ui The <code>Ui</code> object.
     * @param fileManager The <code>FileManager</code> object.
     */
    @Override
    public void execute (TopicManager topicManager, Ui ui, FileManager fileManager) {
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
