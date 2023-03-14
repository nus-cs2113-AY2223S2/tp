package seedu.clialgo.storage;

import org.junit.jupiter.api.Test;
import seedu.clialgo.Topic;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FileManagerTest {

    /**
     * Deletes folder at <code>pathToFolder</code> and all the files within.
     * @param pathToFolder The <code>File</code> representing the folder to delete.
     */
    public void deleteAll(File pathToFolder) {
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
     * Test if folder is created correctly when <code>initialize</code> is called from a <code>FileManager</code>
     * object.
     */
    @Test
    void isFolderCorrectlyCreated_noInput_expectTrue() {
        ArrayList<String> test = new ArrayList<>();
        String path = ".\\testdata";
        FileManager fm = new FileManager(path, test);
        fm.initialize();
        File file = new File(path);
        assertTrue(file.exists());
        deleteAll(file);
    }

    /**
     * Test if a single .txt <code>File</code> is created correctly when <code>initialize</code>
     * is called from a <code>FileManager</code> object.
     */
    @Test
    void isOneFileCorrectlyCreated_oneInput_expectTrue() {
        ArrayList<String> test = new ArrayList<>();
        test.add("test");
        String path = ".\\testdata";
        FileManager fm = new FileManager(path, test);
        fm.initialize();
        File file = new File(path + "\\test.txt");
        assertTrue(file.exists());
        deleteAll(new File(path));
    }

    /**
     * Test if a multiple .txt <code>Files</code> are created correctly when <code>initialize</code>
     * is called from a <code>FileManager</code> object.
     */
    @Test
    void areMultipleFilesCorrectlyCreated_multipleInputs_expectTrue() {
        ArrayList<String> test = new ArrayList<>(Arrays.asList("test1", "test2"));
        String path = ".\\testdata";
        FileManager fm = new FileManager(path, test);
        fm.initialize();
        for (String s : test) {
            File file = new File(path + "\\" + s + ".txt");
            assertTrue(file.exists());
        }
        deleteAll(new File(path));
    }

    /** Test if a <code>HashMap</code> with the correctly named <code>keys</code> is created. */
    @Test
    void areCorrectKeysWhenDecodeAll_multipleInputs_expectTrue() {
        ArrayList<String> test = new ArrayList<>(Arrays.asList("test1", "test2"));
        String path = ".\\testdata";
        FileManager fm = new FileManager(path, test);
        fm.initialize();
        HashMap<String, Topic> testOutput = fm.decodeAll();
        for (String s: test) {
            assertNotNull(testOutput.get(s));
        }
        deleteAll(new File(path));
    }
}
