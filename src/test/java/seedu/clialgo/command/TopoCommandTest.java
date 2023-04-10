package seedu.clialgo.command;

import org.junit.jupiter.api.Test;
import seedu.clialgo.Buffer;
import seedu.clialgo.logic.TopicManager;
import seedu.clialgo.Ui;
import seedu.clialgo.storage.FileManager;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

//@@author ong-ck
/** JUnit test for the <code>TopoCommand</code> class methods. */
class TopoCommandTest {

    /**
     * Checks the <code>execute</code> method of the <code>TopoCommand</code> class.
     * Inputs proper inputs for <code>TopoCommand</code> object and expects the method
     * to print the topological sort of the notes.
     */
    @Test
    void execute_properInput_expectTopoSortToBePrinted() {
        String testDataPath = "./testdata";
        TopicManager topicManager = new TopicManager();
        Ui ui = new Ui();
        FileManager fileManager = new FileManager(testDataPath, topicManager.getTopicNames());
        fileManager.initialize();
        Buffer buffer = Buffer.getInstance();

        new TestModeCommand().execute(topicManager, ui, fileManager, buffer);

        String actualNoteName = "queue";
        String actualNoteTopic = "LINKED_LIST";

        new AddCommand(actualNoteName, actualNoteTopic).execute(topicManager, ui, fileManager, buffer);

        ByteArrayOutputStream actualOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(actualOutput));

        new TopoCommand(actualNoteName).execute(topicManager, ui, fileManager, buffer);

        String os = System.getProperty("os.name");
        String expectedOutput = "";

        if (os.contains("Windows")) {
            expectedOutput = "======================================================\r\n" +
                    "Here are the topologically sorted CS2040CFiles:\r\n" +
                    "======================================================\r\n" +
                    "[LINKED_LIST]\r\n" +
                    "1. [NOTE] queue\r\n" +
                    "======================================================\r\n";
        } else {
            expectedOutput = "======================================================\n" +
                    "Here are the topologically sorted CS2040CFiles:\n" +
                    "======================================================\n" +
                    "[LINKED_LIST]\n" +
                    "1. [NOTE] queue\n" +
                    "======================================================\n";
        }

        assertEquals(expectedOutput, actualOutput.toString());
        FileManager.deleteAll(new File(testDataPath));
    }

    /**
     * Checks the <code>execute</code> method of the <code>TopoCommand</code> class.
     * Calls the method without any prior files added and expects the method
     * to print the message for no CS2040C files saved.
     */
    @Test
    void execute_noAddedFiles_expectPrintNoCS2040CFilesSaved() {
        String testDataPath = "./testdata";
        TopicManager topicManager = new TopicManager();
        Ui ui = new Ui();
        FileManager fileManager = new FileManager(testDataPath, topicManager.getTopicNames());
        fileManager.initialize();
        Buffer buffer = Buffer.getInstance();

        new TestModeCommand().execute(topicManager, ui, fileManager, buffer);

        String actualNoteName = "queue";

        ByteArrayOutputStream actualOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(actualOutput));

        new TopoCommand(actualNoteName).execute(topicManager, ui, fileManager, buffer);

        String os = System.getProperty("os.name");
        String expectedOutput = "";

        if (os.contains("Windows")) {
            expectedOutput = "======================================================\r\n" +
                    "You have no CS2040CFiles at the moment.\r\n" +
                    "======================================================\r\n";
        } else {
            expectedOutput = "======================================================\n" +
                    "You have no CS2040CFiles at the moment.\n" +
                    "======================================================\n";
        }

        assertEquals(expectedOutput, actualOutput.toString());
        FileManager.deleteAll(new File(testDataPath));
    }

    /**
     * Checks the <code>execute</code> method of the <code>TopoCommand</code> class.
     * Inputs name of a non-existent file for <code>TopoCommand</code> object and expects the method
     * to print the message that the file does not exist.
     */
    @Test
    void execute_nonExistentFile_expectPrintFileDoesNotExist() {
        String testDataPath = "./testdata";
        TopicManager topicManager = new TopicManager();
        Ui ui = new Ui();
        FileManager fileManager = new FileManager(testDataPath, topicManager.getTopicNames());
        fileManager.initialize();
        Buffer buffer = Buffer.getInstance();

        new TestModeCommand().execute(topicManager, ui, fileManager, buffer);

        String addedNoteName = "queue";
        String addedNoteTopic = "LINKED_LIST";

        new AddCommand(addedNoteName, addedNoteTopic).execute(topicManager, ui, fileManager, buffer);

        ByteArrayOutputStream actualOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(actualOutput));

        String actualNoteName = "sorting";
        new TopoCommand(actualNoteName).execute(topicManager, ui, fileManager, buffer);

        String os = System.getProperty("os.name");
        String expectedOutput = "";

        if (os.contains("Windows")) {
            expectedOutput = "======================================================\r\n" +
                    "This file does not exist.\r\n" +
                    "Please add the file into the folder and try again.\r\n" +
                    "======================================================\r\n";
        } else {
            expectedOutput = "======================================================\n" +
                    "This file does not exist.\n" +
                    "Please add the file into the folder and try again.\n" +
                    "======================================================\n";
        }

        assertEquals(expectedOutput, actualOutput.toString());
        FileManager.deleteAll(new File(testDataPath));
    }

    /**
     * Checks the <code>equals</code> method of the <code>TopoCommand</code> class.
     * Inputs two equal <code>TopoCommand</code> objects containing an empty hashmap
     * and expects the method to return true.
     */
    @Test
    void equals_checkEqualTopoCommandWithEmptyHashMap_expectTrue() {
        String actualName = "dummyName";
        String expectedName = "dummyName";
        TopoCommand actualTopoCommand = new TopoCommand(actualName);
        TopoCommand expectedTopoCommand = new TopoCommand(expectedName);
        assertTrue(actualTopoCommand.equals(expectedTopoCommand));
    }

    /**
     * Checks the <code>equals</code> method of the <code>TopoCommand</code> class.
     * Inputs two equal <code>TopoCommand</code> objects containing a filled hashmap
     * and expects the method to return true.
     */
    @Test
    void equals_checkEqualTopoCommandWithFilledHashMap_expectTrue() {
        String testDataPath = "./testdata";
        TopicManager topicManager = new TopicManager();
        Ui ui = new Ui();
        FileManager fileManager = new FileManager(testDataPath, topicManager.getTopicNames());
        fileManager.initialize();
        Buffer buffer = Buffer.getInstance();

        new TestModeCommand().execute(topicManager, ui, fileManager, buffer);

        String actualNoteName = "queue";
        String actualNoteTopic = "LINKED_LIST";
        new AddCommand(actualNoteName, actualNoteTopic).execute(topicManager, ui, fileManager, buffer);

        TopoCommand actualTopoCommand = new TopoCommand(actualNoteName);

        actualTopoCommand.execute(topicManager, ui, fileManager, buffer);

        String expectedNoteName = "queue";
        String expectedNoteTopic = "LINKED_LIST";
        String expectedPreviousNoteTopic = "SORTING";
        ArrayList<String> expectedNoteList = new ArrayList<>(List.of("[NOTE] " + expectedNoteName));
        LinkedHashMap<String, ArrayList<String>> expectedTopoSortedCS2040CFiles = new LinkedHashMap<>() {
            {
                put(expectedNoteTopic, expectedNoteList);
                put(expectedPreviousNoteTopic, new ArrayList<>());
            }
        };

        TopoCommand expectedTopoCommand = new TopoCommand(expectedNoteName, expectedTopoSortedCS2040CFiles);

        assertTrue(actualTopoCommand.equals(expectedTopoCommand));
        FileManager.deleteAll(new File(testDataPath));
    }

    /**
     * Checks the <code>equals</code> method of the <code>TopoCommand</code> class.
     * Inputs two unequal <code>TopoCommand</code> objects with different name attributes
     * and expects the method to return false.
     */
    @Test
    void equals_checkUnequalName_expectFalse() {
        String actualName = "actualName";
        String expectedName = "expectedName";
        TopoCommand actualTopoCommand = new TopoCommand(actualName);
        TopoCommand expectedTopoCommand = new TopoCommand(expectedName);
        assertFalse(actualTopoCommand.equals(expectedTopoCommand));
    }

    /**
     * Checks the <code>equals</code> method of the <code>TopoCommand</code> class.
     * Inputs two unequal <code>TopoCommand</code> objects containing different topics
     * and expects the method to return false.
     */
    @Test
    void equals_checkUnequalTopoCommandDueToDifferentTopic_expectFalse() {
        String testDataPath = "./testdata";
        TopicManager topicManager = new TopicManager();
        Ui ui = new Ui();
        FileManager fileManager = new FileManager(testDataPath, topicManager.getTopicNames());
        fileManager.initialize();
        Buffer buffer = Buffer.getInstance();

        new TestModeCommand().execute(topicManager, ui, fileManager, buffer);

        String actualNoteName = "queue";
        String actualNoteTopic = "SORTING";
        new AddCommand(actualNoteName, actualNoteTopic).execute(topicManager, ui, fileManager, buffer);

        TopoCommand actualTopoCommand = new TopoCommand(actualNoteName);

        actualTopoCommand.execute(topicManager, ui, fileManager, buffer);

        String expectedNoteName = "queue";
        String expectedNoteTopic = "LINKED_LIST";
        String expectedPreviousNoteTopic = "SORTING";
        ArrayList<String> expectedNoteList = new ArrayList<>(List.of("[NOTE] " + expectedNoteName));
        LinkedHashMap<String, ArrayList<String>> expectedTopoSortedCS2040CFiles = new LinkedHashMap<>() {
            {
                put(expectedNoteTopic, expectedNoteList);
                put(expectedPreviousNoteTopic, new ArrayList<>());
            }
        };

        TopoCommand expectedTopoCommand = new TopoCommand(expectedNoteName, expectedTopoSortedCS2040CFiles);

        assertFalse(actualTopoCommand.equals(expectedTopoCommand));
        FileManager.deleteAll(new File(testDataPath));
    }

    /**
     * Checks the <code>equals</code> method of the <code>TopoCommand</code> class.
     * Inputs two unequal <code>TopoCommand</code> objects containing different <code>CS2040CFile</code>
     * and expects the method to return false.
     */
    @Test
    void equals_checkUnequalTopoCommandDueToDifferentCS2040CFile_expectFalse() {
        String testDataPath = "./testdata";
        TopicManager topicManager = new TopicManager();
        Ui ui = new Ui();
        FileManager fileManager = new FileManager(testDataPath, topicManager.getTopicNames());
        fileManager.initialize();
        Buffer buffer = Buffer.getInstance();

        new TestModeCommand().execute(topicManager, ui, fileManager, buffer);

        String actualNoteName = "queue";
        String actualNoteTopic = "LINKED_LIST";
        new AddCommand(actualNoteName, actualNoteTopic).execute(topicManager, ui, fileManager, buffer);

        TopoCommand actualTopoCommand = new TopoCommand(actualNoteName);

        actualTopoCommand.execute(topicManager, ui, fileManager, buffer);

        String expectedNoteName = "queue";
        String additionalExpectedNoteName = "linked_list";
        String expectedNoteTopic = "LINKED_LIST";
        String expectedPreviousNoteTopic = "SORTING";
        ArrayList<String> expectedNoteList = new ArrayList<>(List.of("[NOTE] " + expectedNoteName,
                "[NOTE] " + additionalExpectedNoteName));
        LinkedHashMap<String, ArrayList<String>> expectedTopoSortedCS2040CFiles = new LinkedHashMap<>() {
            {
                put(expectedNoteTopic, expectedNoteList);
                put(expectedPreviousNoteTopic, new ArrayList<>());
            }
        };

        TopoCommand expectedTopoCommand = new TopoCommand(expectedNoteName, expectedTopoSortedCS2040CFiles);

        assertFalse(actualTopoCommand.equals(expectedTopoCommand));
        FileManager.deleteAll(new File(testDataPath));
    }

    /**
     * Checks the <code>equals</code> method of the <code>TopoCommand</code> class.
     * Inputs two unequal <code>TopoCommand</code> objects containing unequal number
     * of <code>CS2040CFile</code> and expects the method to return false.
     */
    @Test
    void equals_checkUnequalTopoCommandDueToUnequalNumberOfCS2040CFile_expectFalse() {
        String testDataPath = "./testdata";
        TopicManager topicManager = new TopicManager();
        Ui ui = new Ui();
        FileManager fileManager = new FileManager(testDataPath, topicManager.getTopicNames());
        fileManager.initialize();
        Buffer buffer = Buffer.getInstance();

        new TestModeCommand().execute(topicManager, ui, fileManager, buffer);

        String actualNoteName = "queue";
        String actualNoteTopic = "LINKED_LIST";
        new AddCommand(actualNoteName, actualNoteTopic).execute(topicManager, ui, fileManager, buffer);

        String additionalNoteName = "linked_list";
        new AddCommand(additionalNoteName, actualNoteTopic).execute(topicManager, ui, fileManager, buffer);

        TopoCommand actualTopoCommand = new TopoCommand(actualNoteName);

        actualTopoCommand.execute(topicManager, ui, fileManager, buffer);

        String expectedNoteName = "queue";
        String expectedNoteTopic = "LINKED_LIST";
        String expectedPreviousNoteTopic = "SORTING";
        ArrayList<String> expectedNoteList = new ArrayList<>(List.of("[NOTE] " + expectedNoteName));
        LinkedHashMap<String, ArrayList<String>> expectedTopoSortedCS2040CFiles = new LinkedHashMap<>() {
            {
                put(expectedNoteTopic, expectedNoteList);
                put(expectedPreviousNoteTopic, new ArrayList<>());
            }
        };

        TopoCommand expectedTopoCommand = new TopoCommand(expectedNoteName, expectedTopoSortedCS2040CFiles);

        assertFalse(actualTopoCommand.equals(expectedTopoCommand));
        FileManager.deleteAll(new File(testDataPath));
    }
}
