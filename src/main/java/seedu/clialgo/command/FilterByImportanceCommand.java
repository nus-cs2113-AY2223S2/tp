package seedu.clialgo.command;

import seedu.clialgo.Buffer;
import seedu.clialgo.logic.Topic;
import seedu.clialgo.logic.TopicManager;
import seedu.clialgo.Ui;
import seedu.clialgo.file.CS2040CFile;
import seedu.clialgo.storage.FileManager;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Objects;

//@@author nikkiDEEE
/**
 * Represents an executable command from the user. A <code>FilterByImportanceCommand</code> prints out CS2040CFiles
 * filtered by importance.
 */
public class FilterByImportanceCommand extends FilterCommand {

    /** Comparator to sort CS2040CFiles in decreasing order of importance */
    public Comparator<CS2040CFile> importanceLevel = (cs2040cFile1, cs2040cFile2) -> {
        int cs2040cFile1Importance = cs2040cFile1.getImportance();
        int cs2040cFile2Importance = cs2040cFile2.getImportance();
        return cs2040cFile2Importance - cs2040cFile1Importance;
    };

    /**
     * Constructor for command to filter CS2040CFiles by importance.
     *
     * @param keyWord The keyword to filter by.
     * @param topic The topic that this file is tagged to.
     */
    public FilterByImportanceCommand(String keyWord, String topic) {
        super(keyWord, topic);
    }

    /**
     * This method prints all the CS2040CFiles stored across all non-empty topics in CLIAlgo.
     *
     * @param topicManager The <code>TopicManager</code> object which handles all CS2040CFiles stored in CLIAlgo.
     * @param ui The <code>Ui</code> object which handles outputs to the user.
     */
    public void printAllTopics(TopicManager topicManager, Ui ui) {
        ArrayList<CS2040CFile> cs2040cFiles = topicManager.getAllFilesAsFiles();
        ui.printFilterSuccess();
        cs2040cFiles.sort(importanceLevel);
        ui.printAllFilesWithImportance(cs2040cFiles);
    }

    /**
     * This method prints all the CS2040CFile stored in a single specified topic.
     *
     * @param topicManager The <code>TopicManager</code> object which handles all CS2040CFiles stored in CLIAlgo.
     * @param ui The <code>Ui</code> object which handles outputs to the user.
     */
    public void printSingleTopic(TopicManager topicManager, Ui ui) {
        Topic selectedTopic = topicManager.getOneTopic(this.topic);
        ArrayList<CS2040CFile> cs2040cFiles = selectedTopic.getCS2040CFilesAsArray();
        ui.printFilterSuccess();
        ui.printWithBox(this.topic);
        cs2040cFiles.sort(importanceLevel);
        ui.printAllFilesWithImportance(cs2040cFiles);
    }

    /**
     * Prints the CS2040CFile stored in all topics or just a single topics depending on the importance.
     * If there are no CS2040CFiles stored in CLIAlgo or in the given topic, it prints a string to inform the user.
     * If an invalid topic is given, it prints a string to inform the reader.
     *
     * @param topicManager The <code>TopicManager</code> object which handles all CS2040CFiles stored in CLIAlgo.
     * @param ui The <code>Ui</code> object which handles outputs to the user.
     * @param fileManager The <code>FileManager</code> object responsible for saving information in CLIAlgo.
     * @param buffer The object responsible to export filtered files.
     */
    @Override
    public void execute(TopicManager topicManager, Ui ui, FileManager fileManager, Buffer buffer) {
        if (topicManager.isEmpty()) {
            ui.printFilterAllTopicsEmpty();
            ArrayList<CS2040CFile> emptyBuffer = new ArrayList<>();
            buffer.updateBuffer(emptyBuffer);
            return;
        }
        if (this.topic == null) {
            printAllTopics(topicManager, ui);
            buffer.updateBuffer(topicManager.getAllFilesAsFiles());
            return;
        }
        if (!topicManager.isValidTopic(this.topic)) {
            new InvalidTopicCommand(this.topic).execute(topicManager, ui, fileManager, buffer);
            return;
        }
        if (topicManager.isTopicEmpty(this.topic)) {
            ui.printFilterTopicEmpty();
            buffer.updateBuffer(new ArrayList<>());
            return;
        }
        printSingleTopic(topicManager, ui);
        Topic topic = topicManager.getOneTopic(this.topic);
        ArrayList<CS2040CFile> files = topic.getCS2040CFilesAsArray();
        buffer.updateBuffer(files);
    }

    /**
     * An overridden method that checks for equality of <code>FilterByImportanceCommand</code> objects.
     *
     * @param otherCommand The other <code>FilterByImportanceCommand</code> object to be checked against.
     * @return A boolean value to determine whether the <code>FilterByImportanceCommand</code> objects are equal.
     */
    @Override
    public boolean equals(Command otherCommand) {
        FilterByImportanceCommand otherFilterByImportanceCommand = (FilterByImportanceCommand) otherCommand;

        return Objects.equals(this.keyWord, otherFilterByImportanceCommand.keyWord) &&
                Objects.equals(this.topic, otherFilterByImportanceCommand.topic);
    }
}
