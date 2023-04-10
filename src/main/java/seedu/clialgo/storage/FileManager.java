package seedu.clialgo.storage;

import seedu.clialgo.logic.Topic;
import seedu.clialgo.Ui;
import seedu.clialgo.file.CS2040CFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

//@@author lohjooh
/**
 * Object containing all the raw data for the application, able to update each .txt file which stores the information
 * of the <code>CS2040CFile</code> in each <code>Topic</code>, where the .txt files are named in the convention of
 * <code>topicName</code>.txt.
 */
public class FileManager {
    private final FileEncoder encoder;
    private final FileDecoder decoder;
    private final String initialPath;
    private final String testModePath = "./testdata";
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
        String separator = "&@";
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
        String fileDivider = "/";
        String noteFileExtension = ".txt";
        String pathToFile = path + fileDivider + name + noteFileExtension;
        File file = new File(pathToFile);
        SingleFile newFile = new SingleFile(file, name, decoder);
        try {
            if (file.createNewFile()) {
                newFile.setFile(file);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            ui.printSecurityDenied();
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
                singleFile.recreateFile();
            }
        }
    }

    /**
     * Deletes folder at <code>pathToFolder</code> and all the files within.
     * @param pathToFolder The <code>File</code> representing the folder to delete.
     */
    public static void deleteAll(File pathToFolder) {
        try {
            File[] files = pathToFolder.listFiles();
            if (files != null) {
                for (File f : files) {
                    if (!f.delete()) {
                        Ui.printDeleteFail();
                    }
                }
            }
            if (!pathToFolder.delete()) {
                Ui.printDeleteFail();
            } else {
                Ui.printDeleteSuccess();
            }
        } catch (SecurityException e) {
            Ui.printStaticSecurityDenied();
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
        } catch (SecurityException e) {
            ui.printSecurityDenied();
        }
    }

    /**
     * Process a <code>CS2040CFile</code> and add it to the stored data in the <code>SingleFile</code> object and
     * append the processed <code>CS2040CFile</code> as a <code>String</code> to the .txt file. If the file does not
     * exist, <code>IOException</code> is caught and the file would be recreated from the existing data .
     *
     * @param name The name of the CS2040CFile.
     * @param cs2040cFile The <code>CS2040CFile</code> being added.
     * @return true if executed successfully and false if execution failed.
     */
    public boolean addEntry(String name, CS2040CFile cs2040cFile) {
        SingleFile singleFile = topicRawData.get(cs2040cFile.getTopic());
        try {
            String encodedCS2040CFile = encoder.encodeCS2040CFile(name , cs2040cFile);
            singleFile.writeCS2040CFileToFile(name, encodedCS2040CFile, cs2040cFile);
        } catch (IOException e) {
            ui.printFileWriteError();
            singleFile.recreateFile();
            return false;
        } catch (SecurityException e) {
            ui.printSecurityDenied();
        }
        return true;
    }

    /**
     * Deletes <code>CS2040CFile</code> with <code>cs2040cFileName</code> in <code>topicName</code>.txt and rewrite the
     * .txt file. If the file does not exist, <code>IOException</code> is caught and the file would be recreated.
     *
     * @param cs2040cFileName The name of the <code>CS2040CFile</code> being deleted.
     * @return true if executed successfully and false if execution failed.
     */
    public boolean deleteEntry (String cs2040cFileName, String topicName) {
        SingleFile singleFile = topicRawData.get(topicName);
        try {
            singleFile.deleteEntry(cs2040cFileName);
        } catch (IOException e) {
            ui.printFileWriteError();
            singleFile.recreateFile();
            return false;
        } catch (SecurityException e) {
            ui.printSecurityDenied();
        }
        return true;
    }

    /**
     * Deletes all stored raw data in <code>topicName</code>.txt.
     *
     * @param topicName The name of the .txt file being emptied.
     */
    public void deleteAllEntry(String topicName) {
        topicRawData.get(topicName).clearFile();
    }

    /**
     * Reads all the raw data stored in this object and returns a <code>HashMap</code> of <code>Topics</code> that is
     * processed.
     *
     * @return Returns the initialized <code>HashMap</code> of <code>CS2040CFiles</code>.
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
        try {
            File[] files = pathToFolder.listFiles();
            if (files != null) {
                for (File f : files) {
                    if (!f.delete()) {
                        ui.printFileDeleteFail();
                    }
                }
            }
            if (!pathToFolder.delete()) {
                ui.printFileDeleteFail();
            } else {
                ui.printFileDeleteSuccess();
            }
        } catch (SecurityException e) {
            ui.printSecurityDenied();
        }
    }

    /**
     * Recreates the data folder and all the files within for those which are missing.
     */
    public void recreateAll() {
        if (!new File(path).exists()) {
            createFolder();
        }
        for (SingleFile singleFile: topicRawData.values()) {
            singleFile.recreateFile();
        }
    }
}
