package seedu.clialgo.command;

import java.util.Objects;
import java.io.File;

import seedu.clialgo.Buffer;
import seedu.clialgo.FileType;
import seedu.clialgo.logic.TopicManager;
import seedu.clialgo.storage.FileManager;
import seedu.clialgo.Ui;

//@@author ong-ck
/**
 * The <code>AddCommand</code> objects represents the user command to add new CS2040CFiles into CLIAlgo.
 */
public class AddCommand extends Command {
    public static final String NOTE_FILE_EXTENSION = ".txt";
    public static final String CODE_FILE_EXTENSION = ".cpp";
    public static final String CURRENT_DIRECTORY_PATH = "./";
    public static final int DEFAULT_IMPORTANCE = 5;
    protected final String name;
    protected final Ui ui;
    protected final String topic;
    protected final int importance;

    /**
     * Constructor for command to add CS2040CFile to topic list.
     *
     * @param name Name of the CS2040CFile.
     * @param topic The topic that this file is tagged to.
     */
    public AddCommand(String name, String topic) {
        this.name = name;
        this.topic = topic;
        this.ui = new Ui();
        importance = DEFAULT_IMPORTANCE;
    }

    /**
     * Constructor for command to add CS2040CFile to topic list.
     *
     * @param name Name of the CS2040CFile.
     * @param topic The topic that this file is tagged to.
     * @param importance The importance of the CS2040CFile.
     */
    public AddCommand(String name, String topic, int importance) {
        this.name = name;
        this.topic = topic;
        this.ui = new Ui();
        this.importance = importance;
    }

    int getImportance() {
        return this.importance;
    }

    String getName() {
        return this.name;
    }

    String getTag() {
        return this.topic;
    }

    /**
     * Checks if the file exists as a .txt or .cpp.
     *
     * @return A <code>FileType</code> enum that determines whether the file DOESNOTEXIST, is a TXT or CPP file.
     */
    public FileType checkFileType() {
        String pathInTxt = this.name + NOTE_FILE_EXTENSION;
        String pathInCpp = this.name + CODE_FILE_EXTENSION;

        if (new File(CURRENT_DIRECTORY_PATH + pathInTxt).isFile()) {
            return FileType.TXT;
        } else if (new File(CURRENT_DIRECTORY_PATH + pathInCpp).isFile()) {
            return FileType.CPP;
        } else {
            return FileType.DOESNOTEXIST;
        }
    }

    /**
     * An overridden method to execute the user command to add new CS2040CFiles into CLIAlgo.
     *
     * @param topicManager The <code>TopicManager</code> object which handles all CS2040CFiles stored in CLIAlgo.
     * @param ui The <code>Ui</code> object which handles outputs to the user.
     * @param fileManager The <code>FileManager</code> object responsible for saving information in CLIAlgo.
     * @param buffer The object responsible to export filtered files.
     */
    @Override
    public void execute(TopicManager topicManager, Ui ui, FileManager fileManager, Buffer buffer) {
        boolean isTestModeOn = topicManager.getIsTestModeOn();

        // Check if the file exists
        if ((checkFileType() == FileType.DOESNOTEXIST) && !isTestModeOn) {
            ui.printFileDoesNotExist();
            return;
        }
        // Check if topicName is valid
        if (!topicManager.isValidTopic(topic)) {
            new InvalidTopicCommand(topic).execute(topicManager, ui, fileManager, buffer);
            return;
        }
        // Check if the note is repeated
        if (topicManager.isRepeatedCS2040CFile(name)) {
            assert topicManager.isRepeatedCS2040CFile(name);
            ui.printCS2040CFileExists();
            return;
        }

        if (checkFileType() == FileType.TXT) {
            new AddNoteCommand(name, topic, importance).execute(topicManager, ui, fileManager, buffer);
        } else if (checkFileType() == FileType.CPP) {
            new AddCodeCommand(name, topic, importance).execute(topicManager, ui, fileManager, buffer);
        } else if (checkFileType() == FileType.DOESNOTEXIST && isTestModeOn) {
            new AddNoteCommand(name, topic, importance).execute(topicManager, ui, fileManager, buffer);
        }

        assert this.topic != null;
        assert topicManager.isValidTopic(topic);

        ui.printAddSuccess(name, topic);
    }

    /**
     * An overridden method that checks for equality of <code>AddCommand</code> objects.
     *
     * @param otherCommand The other <code>AddCommand</code> object to be checked against.
     * @return A boolean value to determine whether the <code>AddCommand</code> objects are equal.
     */
    @Override
    public boolean equals(Command otherCommand) {
        AddCommand otherAddCommand = (AddCommand) otherCommand;

        boolean isSameName = Objects.equals(this.getName(), otherAddCommand.getName());
        boolean isSameTag = Objects.equals(this.getTag(), otherAddCommand.getTag());
        return isSameName && isSameTag;
    }
}
