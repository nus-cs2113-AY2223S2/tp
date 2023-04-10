package seedu.clialgo.command;

import seedu.clialgo.Buffer;
import seedu.clialgo.logic.Topic;
import seedu.clialgo.logic.TopicManager;
import seedu.clialgo.Ui;
import seedu.clialgo.file.CS2040CFile;
import seedu.clialgo.storage.FileManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

//@@author heejet
/**
 * Represents an executable command from the user. A <code>FilterByTopicCommand</code> prints out CS2040CFiles filtered
 * by topics.
 */
public class FilterByTopicCommand extends FilterCommand {
    public FilterByTopicCommand(String keyWord, String topic) {
        super(keyWord, topic);
    }

    /**
     * This method prints all the CS2040CFiles stored across all non-empty topics in CLIAlgo.
     *
     * @param topicManager The <code>TopicManager</code> object which handles all CS2040CFiles stored in CLIAlgo.
     * @param ui The <code>Ui</code> object which handles outputs to the user.
     */
    private void printAllTopics(TopicManager topicManager, Ui ui) {
        HashMap<String, ArrayList<String>> cs2040cFiles = topicManager.getAllCS2040CFilesGroupedByTopicToPrint();
        ui.printFilterSuccess();
        for (Map.Entry<String, ArrayList<String>> entry : cs2040cFiles.entrySet()) {
            ArrayList<String> currentTopicCS2040CFiles = entry.getValue();
            String topicName = entry.getKey();
            ui.printWithBox(topicName);
            ui.printListOfCS2040CFiles(currentTopicCS2040CFiles);
        }
        ui.printDivider();
    }

    /**
     * This method prints all the CS2040CFile stored in a single specified topic.
     *
     * @param topicManager The <code>TopicManager</code> object which handles all CS2040CFiles stored in CLIAlgo.
     * @param ui The <code>Ui</code> object which handles outputs to the user.
     */
    private void printSingleTopic(TopicManager topicManager, Ui ui) {
        ArrayList<String> toPrintCS2040CFiles = topicManager.getCS2040CFilesByTopicToPrint(this.topic);
        ui.printFilterSuccess();
        ui.printWithBox(this.topic);
        ui.printListOfCS2040CFiles(toPrintCS2040CFiles);
        ui.printDivider();
    }

    /**
     * Prints the CS2040CFile stored in all topics or just a single topics depending on the topic.
     * If there are no CS2040CFiles stored in CLIAlgo or in the given topic, it prints a string to inform the user.
     * If an invalid topic is given, it prints a string to inform the reader.
     *
     * @param topicManager The <code>TopicManager</code> object which handles all CS2040CFiles stored in CLIAlgo.
     * @param ui The <code>Ui</code> object which handles outputs to the user.
     * @param fileManager The <code>FileManager</code> object responsible for saving information in CLIAlgo.
     * @param buffer The object responsible to export filtered files.
     */
    @Override
    public void execute (TopicManager topicManager, Ui ui, FileManager fileManager, Buffer buffer) {
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

    @Override
    public boolean equals(Command otherCommand) {
        FilterByTopicCommand otherFilterCommand = (FilterByTopicCommand) otherCommand;

        boolean isSameKeyword = Objects.equals(this.keyWord, otherFilterCommand.keyWord);
        boolean isSameTopic = Objects.equals(this.topic, otherFilterCommand.topic);
        return isSameKeyword && isSameTopic;
    }
}
