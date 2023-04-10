package seedu.clialgo.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.clialgo.Buffer;
import seedu.clialgo.Parser;
import seedu.clialgo.logic.TopicManager;
import seedu.clialgo.Ui;
import seedu.clialgo.storage.FileManager;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FilterCommandTest {
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private Ui ui;
    private TopicManager topicManager;
    private Parser parser;
    private FileManager fileManager;
    private Buffer buffer;

    /**
     * Runs before each test, initializes  <code>Ui</code>, <code>TopicManager</code>, <code>Parser</code> and
     * <code>FileManager</code>.
     */
    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStream));
        parser = new Parser();
        topicManager = new TopicManager();
        ui = new Ui();
        String path = "./testdata";
        fileManager = new FileManager(path, new ArrayList<>());
        buffer = Buffer.getInstance();
        outputStream.reset();
    }

    /** Checks correct execution of <code>execute</code> method when printing empty <code>allNotes</code>. */
    @Test
    void isEmptyCheck_expectTrue() {
        String input = "filter k/topic";
        Command command = parser.parse(input, topicManager);
        command.execute(topicManager, ui, fileManager, buffer);

        input = "filter k/topic t/";

        String os = System.getProperty("os.name");
        String expectedOutput;

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
        StringBuilder newExpectedOutput = new StringBuilder(expectedOutput);
        for (String string : topicManager.getTopicNames()) {
            String newInput = input + string;
            command = parser.parse(newInput, topicManager);
            command.execute(topicManager, ui, fileManager, buffer);
            newExpectedOutput.append(expectedOutput);
        }
        assertEquals(newExpectedOutput.toString(), outputStream.toString());
    }

    @Test
    void execute_invalidKeyword_expectPrintInvalidFilterKeyword() {
        String testDataPath = ".\\testdata";
        TopicManager topicManager = new TopicManager();
        Ui ui = new Ui();
        FileManager fileManager = new FileManager(testDataPath, topicManager.getTopicNames());
        fileManager.initialize();
        Buffer buffer = Buffer.getInstance();

        new TestModeCommand().execute(topicManager, ui, fileManager, buffer);

        String addedNoteName = "queue";
        String addedNoteTopic = "LINKED_LIST";

        new AddCommand(addedNoteName, addedNoteTopic).execute(topicManager, ui, fileManager, buffer);

        String actualKeyword = "invalidTopic";
        String actualTopic = "LINKED_LIST";

        ByteArrayOutputStream actualOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(actualOutput));

        new FilterCommand(actualKeyword, actualTopic).execute(topicManager, ui, fileManager, buffer);

        String os = System.getProperty("os.name");
        String expectedOutput = "";

        if (os.contains("Windows")) {
            expectedOutput = "======================================================\r\n" +
                    "Invalid keyword! Only `topic` and `importance` are supported keywords.\r\n" +
                    "Type `help c/filter for more information.\r\n" +
                    "======================================================\r\n";
        } else {
            expectedOutput = "======================================================\n" +
                    "Invalid keyword! Only `topic` and `importance` are supported keywords.\n" +
                    "Type `help c/filter for more information.\n" +
                    "======================================================\n";
        }

        assertEquals(expectedOutput, actualOutput.toString());
        FileManager.deleteAll(new File(testDataPath));
    }

    @Test
    void execute_inputTopicKeyword_expectFilterByTopicCommandOutput() {
        String testDataPath = ".\\testdata";
        TopicManager topicManager = new TopicManager();
        Ui ui = new Ui();
        FileManager fileManager = new FileManager(testDataPath, topicManager.getTopicNames());
        fileManager.initialize();
        Buffer buffer = Buffer.getInstance();

        new TestModeCommand().execute(topicManager, ui, fileManager, buffer);

        String addedNoteName = "queue";
        String addedNoteTopic = "LINKED_LIST";

        new AddCommand(addedNoteName, addedNoteTopic).execute(topicManager, ui, fileManager, buffer);

        String actualKeyword = "topic";
        String actualTopic = "LINKED_LIST";

        ByteArrayOutputStream actualOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(actualOutput));

        new FilterCommand(actualKeyword, actualTopic).execute(topicManager, ui, fileManager, buffer);

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
    void execute_inputImportanceKeyword_expectFilterByImportanceCommandOutput() {
        String testDataPath = ".\\testdata";
        TopicManager topicManager = new TopicManager();
        Ui ui = new Ui();
        FileManager fileManager = new FileManager(testDataPath, topicManager.getTopicNames());
        fileManager.initialize();
        Buffer buffer = Buffer.getInstance();

        new TestModeCommand().execute(topicManager, ui, fileManager, buffer);

        String addedNoteName = "queue";
        String addedNoteTopic = "LINKED_LIST";
        int addedNoteImportance = 5;

        new AddCommand(addedNoteName, addedNoteTopic, addedNoteImportance)
                .execute(topicManager, ui, fileManager, buffer);

        String actualKeyword = "importance";
        String actualTopic = null;

        ByteArrayOutputStream actualOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(actualOutput));

        new FilterCommand(actualKeyword, actualTopic).execute(topicManager, ui, fileManager, buffer);

        String os = System.getProperty("os.name");
        String expectedOutput = "";

        if (os.contains("Windows")) {
            expectedOutput = "======================================================\r\n" +
                    "Here are the filtered CS2040CFiles:\r\n" +
                    "======================================================\r\n" +
                    "1. [NOTE] queue [5]\r\n" +
                    "======================================================\r\n";
        } else {
            expectedOutput = "======================================================\n" +
                    "Here are the filtered CS2040CFiles:\n" +
                    "======================================================\n" +
                    "1. [NOTE] queue [5]\n" +
                    "======================================================\n";
        }

        assertEquals(expectedOutput, actualOutput.toString());
        FileManager.deleteAll(new File(testDataPath));
    }

    @Test
    void equals_checkEqualFilterCommand_expectTrue() {
        String actualKeyword = "dummyKeyword";
        String expectedKeyword = "dummyKeyword";
        String actualTopic = "dummyTopic";
        String expectedTopic = "dummyTopic";
        FilterCommand actualFilterCommand = new FilterCommand(actualKeyword, actualTopic);
        FilterCommand expectedFilterCommand = new FilterCommand(expectedKeyword, expectedTopic);
        assertTrue(actualFilterCommand.equals(expectedFilterCommand));
    }

    @Test
    void equals_checkUnequalKeywordInFilterCommand_expectFalse() {
        String actualKeyword = "actualKeyword";
        String expectedKeyword = "expectedKeyword";
        String actualTopic = "dummyTopic";
        String expectedTopic = "dummyTopic";
        FilterCommand actualFilterCommand = new FilterCommand(actualKeyword, actualTopic);
        FilterCommand expectedFilterCommand = new FilterCommand(expectedKeyword, expectedTopic);
        assertFalse(actualFilterCommand.equals(expectedFilterCommand));
    }

    @Test
    void equals_checkUnequalTopicInFilterCommand_expectFalse() {
        String actualKeyword = "dummyKeyword";
        String expectedKeyword = "dummyKeyword";
        String actualTopic = "actualTopic";
        String expectedTopic = "expectedTopic";
        FilterCommand actualFilterCommand = new FilterCommand(actualKeyword, actualTopic);
        FilterCommand expectedFilterCommand = new FilterCommand(expectedKeyword, expectedTopic);
        assertFalse(actualFilterCommand.equals(expectedFilterCommand));
    }
}
