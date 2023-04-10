package seedu.clialgo.command;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

import seedu.clialgo.Buffer;
import seedu.clialgo.logic.TopicManager;
import seedu.clialgo.Ui;
import seedu.clialgo.storage.FileManager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * JUnit test for the <code>AddCommand</code> class methods.
 */
class AddCommandTest {

    /**
     * Checks the <code>equals</code> method of the <code>AddCommand</code> class.
     * Inputs two equal <code>AddCommand</code> objects and expects the method to return true.
     */
    @Test
    void equals_checkEqualAddCommand_expectTrue() {
        String actualName = "dummyName";
        String expectedName = "dummyName";
        String actualTopic = "dummyTopic";
        String expectedTopic = "dummyTopic";
        AddCommand actualAddCommand = new AddCommand(actualName, actualTopic);
        AddCommand expectedAddCommand = new AddCommand(expectedName, expectedTopic);
        assertTrue(actualAddCommand.equals(expectedAddCommand));
    }

    /**
     * Checks the <code>equals</code> method of the <code>AddCommand</code> class.
     * Inputs two unequal <code>AddCommand</code> objects and expects the method to return false.
     */
    @Test
    void equals_checkUnequalAddCommand_expectFalse() {
        String actualName = "actualName";
        String expectedName = "expectedName";
        String actualTopic = "actualTopic";
        String expectedTopic = "expectedTopic";
        AddCommand actualAddCommand = new AddCommand(actualName, actualTopic);
        AddCommand expectedAddCommand = new AddCommand(expectedName, expectedTopic);
        assertFalse(actualAddCommand.equals(expectedAddCommand));
    }

    /**
     * Checks the <code>execute</code> method of the <code>AddCommand</code> class.
     * Inputs proper inputs for <code>AddCommand</code> object and expects the method
     * to print message for successful adding of note.
     */
    @Test
    void execute_properInput_expectAddSuccessfulMessage() {
        String testDataPath = "./testdata";
        TopicManager topicManager = new TopicManager();
        Ui ui = new Ui();
        FileManager fileManager = new FileManager(testDataPath, topicManager.getTopicNames());
        fileManager.initialize();
        Buffer buffer = Buffer.getInstance();

        new TestModeCommand().execute(topicManager, ui, fileManager, buffer);

        ByteArrayOutputStream actualOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(actualOutput));

        String actualNoteName = "queue";
        String actualNoteTopic = "LINKED_LIST";

        new AddCommand(actualNoteName, actualNoteTopic).execute(topicManager, ui, fileManager, buffer);

        String os = System.getProperty("os.name");
        String expectedOutput = "";

        if (os.contains("Windows")) {
            expectedOutput = "======================================================\r\n" +
                    "Successfully added queue into LINKED_LIST.\r\n" +
                    "======================================================\r\n";
        } else {
            expectedOutput = "======================================================\n" +
                    "Successfully added queue into LINKED_LIST.\n" +
                    "======================================================\n";
        }

        assertEquals(expectedOutput, actualOutput.toString());
        FileManager.deleteAll(new File(testDataPath));
    }

    /**
     * Checks the <code>execute</code> method of the <code>AddCommand</code> class.
     * Inputs invalid topic for <code>AddCommand</code> object and expects the method
     * to print message for unsuccessful adding of note due to invalid topic.
     */
    @Test
    void execute_invalidTopicInput_expectAddUnsuccessfulDueToInvalidTopicMessage() {
        String testDataPath = "./testdata";
        TopicManager topicManager = new TopicManager();
        Ui ui = new Ui();
        FileManager fileManager = new FileManager(testDataPath, topicManager.getTopicNames());
        fileManager.initialize();
        Buffer buffer = Buffer.getInstance();

        new TestModeCommand().execute(topicManager, ui, fileManager, buffer);

        ByteArrayOutputStream actualOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(actualOutput));

        String actualNoteName = "queue";
        String actualNoteTopic = "invalidTopic";

        new AddCommand(actualNoteName, actualNoteTopic).execute(topicManager, ui, fileManager, buffer);

        String os = System.getProperty("os.name");
        String expectedOutput = "";

        if (os.contains("Windows")) {
            expectedOutput = "======================================================\r\n" +
                    "Unsuccessful! invalidTopic is not a topic in CS2040C.\r\n" +
                    "Type 'help c/add' for assistance.\r\n" +
                    "======================================================\r\n";
        } else {
            expectedOutput = "======================================================\n" +
                    "Unsuccessful! invalidTopic is not a topic in CS2040C.\n" +
                    "Type 'help c/add' for assistance.\n" +
                    "======================================================\n";
        }

        assertEquals(expectedOutput, actualOutput.toString());
        FileManager.deleteAll(new File(testDataPath));
    }

    /**
     * Checks the <code>execute</code> method of the <code>AddCommand</code> class.
     * Inputs repeated name for <code>AddCommand</code> object and expects the method
     * to print message for unsuccessful adding of note due to invalid command.
     */
    @Test
    void execute_repeatedInput_expectAddUnsuccessfulDueToInvalidCommandMessage() {
        String testDataPath = "./testdata";
        File file = new File("./" + "queue" + ".txt");
        try {
            if (file.createNewFile()) {
                System.out.println("File created");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        TopicManager topicManager = new TopicManager();
        Ui ui = new Ui();
        FileManager fileManager = new FileManager(testDataPath, topicManager.getTopicNames());
        fileManager.initialize();
        Buffer buffer = Buffer.getInstance();

        new TestModeCommand().execute(topicManager, ui, fileManager, buffer);

        ByteArrayOutputStream actualOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(actualOutput));

        String actualNoteName = "queue";
        String actualNoteTopic = "LINKED_LIST";

        // Adding the note with same name twice into same topic
        new AddCommand(actualNoteName, actualNoteTopic).execute(topicManager, ui, fileManager, buffer);
        new AddCommand(actualNoteName, actualNoteTopic).execute(topicManager, ui, fileManager, buffer);

        String os = System.getProperty("os.name");
        String expectedOutput = "";

        if (os.contains("Windows")) {
            expectedOutput = "======================================================\r\n" +
                    "Successfully added queue into LINKED_LIST.\r\n" +
                    "======================================================\r\n" +
                    "======================================================\r\n" +
                    "Unsuccessful! A CS2040CFile with that name already exists.\r\n" +
                    "Type 'list' to view the list of CS2040CFiles.\r\n" +
                    "======================================================\r\n";
        } else {
            expectedOutput = "======================================================\n" +
                    "Successfully added queue into LINKED_LIST.\n" +
                    "======================================================\n" +
                    "======================================================\n" +
                    "Unsuccessful! A CS2040CFile with that name already exists.\n" +
                    "Type 'list' to view the list of CS2040CFiles.\n" +
                    "======================================================\n";
        }

        assertEquals(expectedOutput, actualOutput.toString());
        FileManager.deleteAll(new File(testDataPath));
    }

    /**
     * Checks the <code>execute</code> method of the <code>AddCommand</code> class.
     * Inputs a name of a file that does not exist into the  <code>AddCommand</code> object and expects the method
     * to print file does not exit.
     */
    @Test
    void execute_fileDoesNotExist_expectPrintFileDoesNotExist() {
        String testDataPath = "./testdata";
        TopicManager topicManager = new TopicManager();
        Ui ui = new Ui();
        FileManager fileManager = new FileManager(testDataPath, topicManager.getTopicNames());
        fileManager.initialize();
        Buffer buffer = Buffer.getInstance();

        ByteArrayOutputStream actualOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(actualOutput));

        // Adding a file that does not exist
        new AddCommand("thisFileDoesNotExist", "LINKED_LIST", 10).execute(topicManager, ui,
                fileManager, buffer);

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
     * Checks the <code>execute</code> method of the <code>AddCommand</code> class.
     * Inputs a name of a file that does not exist into the  <code>AddCommand</code> object and expects the method
     * to print file does not exit.
     */
    @Test
    void execute_fileDoesNotExist_expectPrintFileDoesExist() {
        String testDataPath = "./testdata";
        File file = new File("./" + "queue" + ".txt");
        try {
            if (file.createNewFile()) {
                System.out.println("File created");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        TopicManager topicManager = new TopicManager();
        Ui ui = new Ui();
        FileManager fileManager = new FileManager(testDataPath, topicManager.getTopicNames());
        fileManager.initialize();
        Buffer buffer = Buffer.getInstance();

        ByteArrayOutputStream actualOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(actualOutput));

        // Adding a file that does not exist
        new AddCommand("queue", "LINKED_LIST", 10).execute(topicManager, ui, fileManager, buffer);

        String os = System.getProperty("os.name");
        String expectedOutput = "";
        if (os.contains("Windows")) {
            expectedOutput = "======================================================\r\n" +
                    "Successfully added queue into LINKED_LIST.\r\n" +
                    "======================================================\r\n";
        } else {
            expectedOutput = "======================================================\n" +
                    "Successfully added queue into LINKED_LIST.\n" +
                    "======================================================\n";
        }

        assertEquals(expectedOutput, actualOutput.toString());
        FileManager.deleteAll(new File(testDataPath));
    }



}
