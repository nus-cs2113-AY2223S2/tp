package seedu.clialgo.command;

import org.junit.jupiter.api.Test;
import seedu.clialgo.Buffer;
import seedu.clialgo.Ui;
import seedu.clialgo.file.CS2040CFile;
import seedu.clialgo.file.Code;
import seedu.clialgo.file.Note;
import seedu.clialgo.logic.TopicManager;
import seedu.clialgo.storage.FileManager;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class FilterByImportanceCommandTest {

    @Test
    void equals_checkEqualFilterByImportanceCommand_expectTrue() {
        FilterByImportanceCommand myObj = new FilterByImportanceCommand("importance", "LINKED_LIST");
        FilterByImportanceCommand myOtherObj = new FilterByImportanceCommand("importance", "LINKED_LIST");
        assertTrue(myObj.equals(myOtherObj));
    }

    @Test
    void equals_checkUnequalKeywordFilterByImportanceCommand_expectFalse() {
        FilterByImportanceCommand myObj = new FilterByImportanceCommand("importance", "LINKED_LIST");
        FilterByImportanceCommand myOtherObj = new FilterByImportanceCommand("topic", "LINKED_LIST");
        assertFalse(myObj.equals(myOtherObj));
    }

    @Test
    void equals_checkUnequalTopicFilterByImportanceCommand_expectFalse() {
        FilterByImportanceCommand myObj = new FilterByImportanceCommand("importance", "LINKED_LIST");
        FilterByImportanceCommand myOtherObj = new FilterByImportanceCommand("importance", "SORTING");
        assertFalse(myObj.equals(myOtherObj));
    }

    @Test
    void execute_topicManagerEmpty_expectPrintFilterAllTopicEmpty() {
        TopicManager topicManager = new TopicManager();

        String testDataPath = "./testdata";
        Ui ui = new Ui();
        FileManager fileManager = new FileManager(testDataPath, topicManager.getTopicNames());
        fileManager.initialize();
        Buffer buffer = Buffer.getInstance();

        ByteArrayOutputStream actualOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(actualOutput));

        new FilterByImportanceCommand("importance", "LINKED_LIST").execute(topicManager, ui, fileManager, buffer);

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
    void execute_topicManagerNonEmptyTopicIsNull_expectPrintFilterAllTopics() {
        TopicManager topicManager = new TopicManager();
        CS2040CFile cs2040CFile1 = new Code("queue", "queue.cpp", "LINKED_LIST", 10);
        CS2040CFile cs2040CFile2 = new Note("bubble", "bubble.txt", "SORTING", 5);
        topicManager.addCS2040CFile("queue", "LINKED_LIST", cs2040CFile1);
        topicManager.addCS2040CFile("bubble", "SORTING", cs2040CFile2);

        String testDataPath = "./testdata";
        Ui ui = new Ui();
        FileManager fileManager = new FileManager(testDataPath, topicManager.getTopicNames());
        fileManager.initialize();
        Buffer buffer = Buffer.getInstance();

        ByteArrayOutputStream actualOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(actualOutput));

        new FilterByImportanceCommand("importance", null).execute(topicManager, ui, fileManager, buffer);

        String os = System.getProperty("os.name");
        String expectedOutput = "";

        if (os.contains("Windows")) {
            expectedOutput = "======================================================\r\n" +
                    "Here are the filtered CS2040CFiles:\r\n" +
                    "======================================================\r\n" +
                    "1. [CODE] queue [10]\r\n" +
                    "2. [NOTE] bubble [5]\r\n" +
                    "======================================================\r\n";
        } else {
            expectedOutput = "======================================================\n" +
                    "Here are the filtered CS2040CFiles:\n" +
                    "======================================================\n" +
                    "1. [CODE] queue [10]\n" +
                    "2. [NOTE] bubble [5]\n" +
                    "======================================================\n";
        }

        assertEquals(expectedOutput, actualOutput.toString());
        FileManager.deleteAll(new File(testDataPath));
    }

    @Test
    void execute_topicManagerNonEmptyTopicIsNonNull_expectPrintSingleTopic() {
        TopicManager topicManager = new TopicManager();
        CS2040CFile cs2040CFile1 = new Code("queue", "queue.cpp", "LINKED_LIST", 10);
        CS2040CFile cs2040CFile2 = new Note("bubble", "bubble.txt", "SORTING", 5);
        topicManager.addCS2040CFile("queue", "LINKED_LIST", cs2040CFile1);
        topicManager.addCS2040CFile("bubble", "SORTING", cs2040CFile2);

        String testDataPath = "./testdata";
        Ui ui = new Ui();
        FileManager fileManager = new FileManager(testDataPath, topicManager.getTopicNames());
        fileManager.initialize();
        Buffer buffer = Buffer.getInstance();

        ByteArrayOutputStream actualOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(actualOutput));

        new FilterByImportanceCommand("importance", "SORTING").execute(topicManager, ui, fileManager, buffer);

        String os = System.getProperty("os.name");
        String expectedOutput = "";

        if (os.contains("Windows")) {
            expectedOutput = "======================================================\r\n" +
                    "Here are the filtered CS2040CFiles:\r\n" +
                    "======================================================\r\n" +
                    "[SORTING]\r\n" +
                    "1. [NOTE] bubble [5]\r\n" +
                    "======================================================\r\n";
        } else {
            expectedOutput = "======================================================\n" +
                    "Here are the filtered CS2040CFiles:\n" +
                    "======================================================\n" +
                    "[SORTING]\n" +
                    "1. [NOTE] bubble [5]\n" +
                    "======================================================\n";
        }

        assertEquals(expectedOutput, actualOutput.toString());
        FileManager.deleteAll(new File(testDataPath));
    }

    @Test
    void execute_topicManagerNonEmptyInvalidTopic_expectInvalidTopicCommand() {
        TopicManager topicManager = new TopicManager();
        CS2040CFile cs2040CFile1 = new Code("queue", "queue.cpp", "LINKED_LIST", 10);
        topicManager.addCS2040CFile("queue", "LINKED_LIST", cs2040CFile1);

        String testDataPath = "./testdata";
        Ui ui = new Ui();
        FileManager fileManager = new FileManager(testDataPath, topicManager.getTopicNames());
        fileManager.initialize();
        Buffer buffer = Buffer.getInstance();

        ByteArrayOutputStream actualOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(actualOutput));

        String invalidTopicName = "linked list";
        new FilterByImportanceCommand("importance", invalidTopicName).execute(topicManager, ui, fileManager, buffer);

        String os = System.getProperty("os.name");
        String expectedOutput = "";

        if (os.contains("Windows")) {
            expectedOutput = "======================================================\r\n" +
                    "Unsuccessful! linked list is not a topic in CS2040C.\r\n" +
                    "Type 'help c/add' for assistance.\r\n" +
                    "======================================================\r\n";
        } else {
            expectedOutput = "======================================================\n" +
                    "Unsuccessful! linked list is not a topic in CS2040C.\n" +
                    "Type 'help c/add' for assistance.\n" +
                    "======================================================\n";
        }

        assertEquals(expectedOutput, actualOutput.toString());
        FileManager.deleteAll(new File(testDataPath));
    }

    @Test
    void execute_topicManagerNonEmptyTopicEmpty_expectPrintFilterTopicEmpty() {
        TopicManager topicManager = new TopicManager();
        CS2040CFile cs2040CFile1 = new Code("queue", "queue.cpp", "LINKED_LIST", 10);
        topicManager.addCS2040CFile("queue", "LINKED_LIST", cs2040CFile1);

        String testDataPath = "./testdata";
        Ui ui = new Ui();
        FileManager fileManager = new FileManager(testDataPath, topicManager.getTopicNames());
        fileManager.initialize();
        Buffer buffer = Buffer.getInstance();

        ByteArrayOutputStream actualOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(actualOutput));

        String emptyTopic = "BINARY_HEAP";
        new FilterByImportanceCommand("importance", emptyTopic).execute(topicManager, ui, fileManager, buffer);

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
