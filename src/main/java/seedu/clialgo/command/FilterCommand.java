package seedu.clialgo.command;

import seedu.clialgo.Buffer;
import seedu.clialgo.file.CS2040CFile;
import seedu.clialgo.logic.TopicManager;
import seedu.clialgo.Ui;
import seedu.clialgo.storage.FileManager;

import java.util.ArrayList;
import java.util.Objects;

//@@author heejet
/**
 * Represents an executable command from the user. A <code>FilterCommand</code> prints out CS2040CFiles filtered either
 * by topics or importance.
 */
public class FilterCommand extends Command {
    private static final String TOPIC_KEYWORD = "topic";
    private static final String IMPORTANCE_KEYWORD = "importance";
    protected final String keyWord;
    protected final String topic;

    public FilterCommand(String keyWord, String topic) {
        this.keyWord = keyWord;
        this.topic = topic;
    }

    /**
     * Checks the keyword and executes the correct variant of <code>FilterCommand</code>.
     * This method prints all the CS2040CFiles stored across all non-empty topics in CLIAlgo.
     * If <codde>keyWord</codde> is "topic" it executes <code>FilterByTopicCommand</code>.
     * If <codde>keyWord</codde> is "importance" it executes <code>FilterByImportanceCommand</code>.
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

        if (keyWord.equals(TOPIC_KEYWORD)) {
            new FilterByTopicCommand(keyWord, topic).execute(topicManager, ui, fileManager, buffer);
        } else if (keyWord.equals(IMPORTANCE_KEYWORD)) {
            new FilterByImportanceCommand(keyWord, topic).execute(topicManager, ui, fileManager, buffer);
        } else {
            new InvalidFilterCommand().execute(topicManager, ui, fileManager, buffer);
        }
    }

    @Override
    public boolean equals(Command otherCommand) {
        FilterCommand otherFilterCommand = (FilterCommand) otherCommand;

        boolean isSameKeyword = Objects.equals(this.keyWord, otherFilterCommand.keyWord);
        boolean isSameTopic = Objects.equals(this.topic, otherFilterCommand.topic);
        return isSameKeyword && isSameTopic;
    }
}
