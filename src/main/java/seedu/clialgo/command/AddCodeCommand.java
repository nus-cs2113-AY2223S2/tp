package seedu.clialgo.command;

import seedu.clialgo.Buffer;
import seedu.clialgo.logic.TopicManager;
import seedu.clialgo.Ui;
import seedu.clialgo.file.Code;
import seedu.clialgo.storage.FileManager;

import java.util.Objects;

public class AddCodeCommand extends AddCommand {
    private static final String CODE_FILE_EXTENSION = ".cpp";

    /**
     * Constructor for command to add code file to topic list.
     *
     * @param name Name of the code file.
     * @param topic The topic that this file is tagged to.
     * @param importance The importance of the code file.
     */
    public AddCodeCommand(String name, String topic, int importance) {
        super(name, topic, importance);
    }

    /**
     * An overridden method to execute the user command to add new code files into CLIAlgo.
     *
     * @param topicManager The <code>TopicManager</code> object which handles all files stored in CLIAlgo.
     * @param ui The <code>Ui</code> object which handles outputs to the user.
     * @param fileManager The <code>FileManager</code> object responsible for saving information in CLIAlgo.
     * @param buffer The object responsible to export filtered files.
     */
    @Override
    public void execute(TopicManager topicManager, Ui ui, FileManager fileManager, Buffer buffer) {
        String codePath = name + CODE_FILE_EXTENSION;
        fileManager.recreateAll();
        Code newCode = new Code(name, codePath, topic, importance);
        boolean isAddedToFile = fileManager.addEntry(name, newCode);

        if (!isAddedToFile) {
            return;
        }

        boolean isAdded = topicManager.addCS2040CFile(name, topic, newCode);

        if (!isAdded) {
            new InvalidCommand().execute(topicManager, ui, fileManager, buffer);
        }
    }

    /**
     * An overridden method that checks for equality of <code>AddCodeCommand</code> objects.
     *
     * @param otherCommand The other <code>AddCodeCommand</code> object to be checked against.
     * @return A boolean value to determine whether the <code>AddCodeCommand</code> objects are equal.
     */
    @Override
    public boolean equals(Command otherCommand) {
        AddCodeCommand otherAddCodeCommand = (AddCodeCommand) otherCommand;
        boolean isNameEqual = Objects.equals(this.name, otherAddCodeCommand.name);
        boolean isTopicEqual = Objects.equals(this.topic, otherAddCodeCommand.topic);
        boolean isImportanceEqual = this.importance == otherAddCodeCommand.importance;
        return isNameEqual && isTopicEqual && isImportanceEqual;
    }

}
