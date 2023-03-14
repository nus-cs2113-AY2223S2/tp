package seedu.clialgo.storage;

import seedu.clialgo.Note;
import seedu.clialgo.Topic;
import seedu.clialgo.Ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

/**
 * Object containing all the raw data for the application, able to update each .txt file which stores the information
 * of the <code>Notes</code> in each <code>Topic</code>, where the .txt files are named in the convention of
 * <code>topicName</code>.txt.
 */
public class FileManager {

    private final FileEncoder encoder;
    private final FileDecoder decoder;
    private final String initialPath;
    private final String testModePath = ".\\testdata";
    private final ArrayList<String> topicNames;
    private final Ui ui;
    private HashMap<String, SingleFile> topicRawData;
    private HashMap<String, SingleFile> topicRawDataOutsideTestMode;
    private String path;

    /**
     * Constructor for class containing <code>codeDecoder</code>, <code>codeEncoder</code> and raw data from the
     * .txt file stored as strings.
     */
    public FileManager(String path, ArrayList<String> topicNames) {
        this.path = path;
        this.initialPath = path;
        this.ui = new Ui();
        String separator = "&@*";
        this.topicRawData = new HashMap<>();
        this.topicNames = topicNames;
        this.encoder = new FileEncoder(separator);
        this.decoder = new FileDecoder(separator);
    }

    /**
     * Creates a <code>SingleFile</code> object and creates a .txt file if the .txt does not exist.
     *
     * @param name Name of the .txt file.
     * @return The SingleFile object created.
     */
    public SingleFile createSingleFile(String name) {
        File file = new File(path + "\\" + name + ".txt");
        SingleFile newFile = new SingleFile(file, name, decoder);
        try {
            if (file.createNewFile()) {
                newFile.setFile(file);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return newFile;
    }

    /**
     * Creates all the <code>SingleFiles</code> corresponding to each valid <code>Topic</code>. If the .txt file does
     * not exist, it creates a blank .txt file with <code>topicNames</code>.txt. For all the <code>SingleFiles</code>,
     * reads the <code>File</code> corresponding to it.
     */
    public void initialize() {
        createFolder();
        for (String string : topicNames) {
            topicRawData.put(string, createSingleFile(string));
        }
        for (SingleFile singleFile : topicRawData.values()) {
            try {
                singleFile.readFile();
            } catch (FileNotFoundException e) {
                ui.printFileWriteError();
            }
        }
    }

    /**
     * Deletes folder at <code>pathToFolder</code> and all the files within.
     * @param pathToFolder The <code>File</code> representing the folder to delete.
     */
    public static void deleteAll(File pathToFolder) {
        for (File f : Objects.requireNonNull(pathToFolder.listFiles())) {
            if (!f.delete()) {
                System.out.println("Delete failed");
            }
        }
        if (!pathToFolder.delete()) {
            System.out.println("Delete failed");
        } else {
            System.out.println("Delete successful");
        }
    }

    /**
     * Creates a folder at <code>path</code>.
     */
    public void createFolder() {
        try {
            Path directory = Paths.get(path);
            Files.createDirectories(directory);
        } catch (IOException e) {
            ui.printFolderCreateError();
        }
    }

    /**
     * Process a <code>Note</code> and add it to the stored data in the <code>SingleFile</code> object and append the
     * processed <code>Note</code> as a <code>String</code> to the .txt file.
     *
     * @param name The name of the note.
     * @param note The <code>Note</code> being added.
     */
    public void addEntry (String name, Note note) {
        SingleFile singleFile = topicRawData.get(note.getTopic());
        try {
            singleFile.writeNoteToFile(encoder.encodeNote(name ,note));
        } catch (IOException e) {
            ui.printFileWriteError();
        }
    }

    /**
     * Deletes <code>Note</code> with <code>noteName</code> in <code>topicName</code>.txt and rewrite the .txt file.
     *
     * @param noteName The name of the <code>Note</code> being deleted.
     */
    public void deleteEntry (String noteName) {
        for (SingleFile singleFile : topicRawData.values()) {
            singleFile.deleteEntry(noteName);
        }
    }

    /**
     * Deletes all stored raw data in <code>topicName</code>.txt.
     *
     * @param topicName The name of the .txt file being emptied.
     */
    public void deleteAllEntry (String topicName) {
        topicRawData.get(topicName).clearFile();
    }

    /**
     * Reads all the raw data stored in this object and returns a <code>HashMap</code> of <code>Topics</code> that is
     * processed.
     *
     * @return Returns the initialized <code>HashMap</code> of <code>Notes</code>.
     */
    public HashMap<String, Topic> decodeAll() {
        HashMap<String, Topic> topics = new HashMap<>();
        for (String s: topicRawData.keySet()) {
            topics.put(s, topicRawData.get(s).convertFileToTopic());
        }
        return topics;
    }

    /**
     * Changes the folder location to a new folder meant to store testdata. Remembers state of
     * <code>topicRawData</code> before start of test mode.
     */
    public void testMode() {
        this.path = testModePath;
        this.topicRawDataOutsideTestMode = this.topicRawData;
        this.topicRawData = new HashMap<>();
        initialize();
    }

    /**
     * Exits test mode and deletes all the files in the folder storing testdata. Retrieves and restores state of object
     * to before test mode start.
     */
    public void exitTestMode() {
        this.path = initialPath;
        this.topicRawData = topicRawDataOutsideTestMode;
        deleteTestData();
    }

    /**
     * Deletes folder at <code>.\\testdata</code> and all the files within.
     */
    public void deleteTestData() {
        File pathToFolder = new File(testModePath);
        for (File f : Objects.requireNonNull(pathToFolder.listFiles())) {
            if (!f.delete()) {
                ui.printFileDeleteFail();
            }
        }
        if (!pathToFolder.delete()) {
            ui.printFileDeleteFail();
        } else {
            ui.printFileDeleteSuccess();
        }
    }
}
