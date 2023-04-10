package seedu.clialgo.command;

import org.junit.jupiter.api.Test;
import seedu.clialgo.Buffer;
import seedu.clialgo.Ui;
import seedu.clialgo.logic.TopicManager;
import seedu.clialgo.storage.FileManager;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;

class FilterByTopicCommandTest {
    @Test
    void equals_checkEqualFilterByTopicCommand_expectTrue() {
        String actualKeyword = "dummyKeyword";
        String expectedKeyword = "dummyKeyword";
        String actualTopic = "dummyTopic";
        String expectedTopic = "dummyTopic";
        FilterByTopicCommand actualFilterByTopicCommand = new FilterByTopicCommand(actualKeyword, actualTopic);
        FilterByTopicCommand expectedFilterByTopicCommand = new FilterByTopicCommand(expectedKeyword, expectedTopic);
        assertTrue(actualFilterByTopicCommand.equals(expectedFilterByTopicCommand));
    }

    @Test
    void equals_checkUnequalKeywordInFilterByTopicCommand_expectFalse() {
        String actualKeyword = "actualKeyword";
        String expectedKeyword = "expectedKeyword";
        String actualTopic = "dummyTopic";
        String expectedTopic = "dummyTopic";
        FilterByTopicCommand actualFilterByTopicCommand = new FilterByTopicCommand(actualKeyword, actualTopic);
        FilterByTopicCommand expectedFilterByTopicCommand = new FilterByTopicCommand(expectedKeyword, expectedTopic);
        assertFalse(actualFilterByTopicCommand.equals(expectedFilterByTopicCommand));
    }

    @Test
    void equals_checkUnequalTopicInFilterByTopicCommand_expectFalse() {
        String actualKeyword = "dummyKeyword";
        String expectedKeyword = "dummyKeyword";
        String actualTopic = "actualTopic";
        String expectedTopic = "expectedTopic";
        FilterByTopicCommand actualFilterByTopicCommand = new FilterByTopicCommand(actualKeyword, actualTopic);
        FilterByTopicCommand expectedFilterByTopicCommand = new FilterByTopicCommand(expectedKeyword, expectedTopic);
        assertFalse(actualFilterByTopicCommand.equals(expectedFilterByTopicCommand));
    }

    @Test
    void execute_noNotesAdded_expectPrintFilterAllTopicsEmpty() {
        String testDataPath = "./testdata";
        TopicManager topicManager = new TopicManager();
        Ui ui = new Ui();
        FileManager fileManager = new FileManager(testDataPath, topicManager.getTopicNames());
        fileManager.initialize();
        Buffer buffer = Buffer.getInstance();

        ByteArrayOutputStream actualOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(actualOutput));

        String actualKeyword = "topic";
        String actualTopic = "LINKED_LIST";
        new FilterByTopicCommand(actualKeyword, actualTopic).execute(topicManager, ui, fileManager, buffer);

        String os = System.getProperty("os.name");
        String expectedOutput = "";

        if (os.contains("Windows")) {
            expectedOutput = "======================================================\r\n" +
                    "You have no files!\r\n" +
                    "You can add a file to a specific topic using the add command.\r\n" +
                    "Type 'help c/add' for more information on how to add a CS2040CFile.\r\n" +
                    "======================================================\r\n";
        } else {
            expectedOutput = "======================================================\n" +
                    "You have no files!\n" +
                    "You can add a file to a specific topic using the add command.\n" +
                    "Type 'help c/add' for more information on how to add a CS2040CFile.\n" +
                    "======================================================\n";
        }

        assertEquals(expectedOutput, actualOutput.toString());
        FileManager.deleteAll(new File(testDataPath));
    }

    @Test
    void execute_topicIsNull_expectPrintAllTopics() {
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

        String actualKeyword = "topic";
        String actualTopic = null;
        new FilterByTopicCommand(actualKeyword, actualTopic).execute(topicManager, ui, fileManager, buffer);

        String os = System.getProperty("os.name");
        String expectedOutput = "";

        if (os.contains("Windows")) {
            expectedOutput = "======================================================\r\n" +
                    "Here are the filtered CS2040CFiles:\r\n" +
                    "======================================================\r\n" +
                    "[LINKED_LIST]\r\n" +
                    "1. [NOTE] queue\r\n" +
                    "======================================================\r\n";
        } else {
            expectedOutput = "======================================================\n" +
                    "Here are the filtered CS2040CFiles:\n" +
                    "======================================================\n" +
                    "[LINKED_LIST]\n" +
                    "1. [NOTE] queue\n" +
                    "======================================================\n";
        }

        assertEquals(expectedOutput, actualOutput.toString());
        FileManager.deleteAll(new File(testDataPath));
    }

    @Test
    void execute_invalidTopic_expectInvalidTopicCommand() {
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

        String actualKeyword = "topic";
        String actualTopic = "invalidTopic";
        new FilterByTopicCommand(actualKeyword, actualTopic).execute(topicManager, ui, fileManager, buffer);

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

    @Test
    void execute_filteredTopicIsEmpty_expectprintFilterTopicEmpty() {
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

        String actualKeyword = "topic";
        String actualTopic = "SORTING";
        new FilterByTopicCommand(actualKeyword, actualTopic).execute(topicManager, ui, fileManager, buffer);

        String os = System.getProperty("os.name");
        String expectedOutput = "";

        if (os.contains("Windows")) {
            expectedOutput = "======================================================\r\n" +
                    "There are no files in this topic!\r\n" +
                    "You can add a file to this topic using the add command.\r\n" +
                    "Type 'help c/add' for more information on how to add a CS2040CFile.\r\n" +
                    "======================================================\r\n";
        } else {
            expectedOutput = "======================================================\n" +
                    "There are no files in this topic!\n" +
                    "You can add a file to this topic using the add command.\n" +
                    "Type 'help c/add' for more information on how to add a CS2040CFile.\n" +
                    "======================================================\n";
        }

        assertEquals(expectedOutput, actualOutput.toString());
        FileManager.deleteAll(new File(testDataPath));
    }
}
