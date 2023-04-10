package seedu.clialgo.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.clialgo.Buffer;
import seedu.clialgo.file.Note;
import seedu.clialgo.Parser;
import seedu.clialgo.logic.TopicManager;
import seedu.clialgo.Ui;
import seedu.clialgo.storage.FileManager;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ListCommandTest {
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
        fileManager = new FileManager("./testdata", new ArrayList<>());
        buffer = Buffer.getInstance();
    }

    /**
     * Checks correct execution of <code>execute</code> method when printing empty <code>allNotes</code>.
     */
    @Test
    void isEmptyCheck_expectTrue() {
        String input = "list";
        Command command = parser.parse(input, topicManager);
        command.execute(topicManager, ui, fileManager, buffer);

        String os = System.getProperty("os.name");
        String expectedOutput;

        if (os.contains("Windows")) {
            expectedOutput = "======================================================\r\n" +
                    "You have no CS2040CFiles!\r\n" +
                    "Type 'help c/add' for assistance on how to add a CS2040CFile.\r\n" +
                    "======================================================\r\n";
        } else {
            expectedOutput = "======================================================\n" +
                    "You have no CS2040CFiles!\n" +
                    "Type 'help c/add' for assistance on how to add a CS2040CFile.\n" +
                "======================================================\n";
        }
        assertEquals(expectedOutput, outputStream.toString());
    }

    /**
     * Checks correct execution of <code>execute</code> method when printing <code>allNotes</code> when it contains
     * one <code>Note</code>.
     */
    @Test
    void isExecutedCorrectly_oneInput_expectTrue() {
        String input = "list";
        Note note = new Note("test", "", "LINKED_LIST");
        topicManager.addCS2040CFile(note.getName(), note.getTopic(), note);
        Command command = parser.parse(input, topicManager);
        command.execute(topicManager, ui, fileManager, buffer);

        String os = System.getProperty("os.name");
        String expectedOutput;

        if (os.contains("Windows")) {
            expectedOutput = "======================================================\r\n" +
                    "Here are all your CS2040CFiles:\r\n" +
                    "======================================================\r\n" +
                    "1. [NOTE] test\r\n" +
                    "======================================================\r\n";
        } else {
            expectedOutput = "======================================================\n" +
                    "Here are all your CS2040CFiles:\n" +
                    "======================================================\n" +
                    "1. [NOTE] test\n" +
                    "======================================================\n";
        }
        assertEquals(expectedOutput, outputStream.toString());
    }

    /**
     * Checks correct execution of <code>execute</code> method when printing <code>allNotes</code> when it contains
     * multiple <code>Notes</code>.
     */
    @Test
    void isExecutedCorrectly_multipleInput_expectTrue() {
        String input = "list";
        Note note1 = new Note("test1", "", "LINKED_LIST");
        Note note2 = new Note("test2", "", "LINKED_LIST");
        topicManager.addCS2040CFile(note1.getName(), note1.getTopic(), note1);
        topicManager.addCS2040CFile(note2.getName(), note2.getTopic(), note2);
        Command command = parser.parse(input, topicManager);
        command.execute(topicManager, ui, fileManager, buffer);

        String os = System.getProperty("os.name");
        String expectedOutput;

        if (os.contains("Windows")) {
            expectedOutput = "======================================================\r\n" +
                    "Here are all your CS2040CFiles:\r\n" +
                    "======================================================\r\n" +
                    "1. [NOTE] test2\r\n" +
                    "2. [NOTE] test1\r\n" +
                    "======================================================\r\n";
        } else {
            expectedOutput = "======================================================\n" +
                    "Here are all your CS2040CFiles:\n" +
                    "======================================================\n" +
                    "1. [NOTE] test2\n" +
                    "2. [NOTE] test1\n" +
                    "======================================================\n";
        }
        assertEquals(expectedOutput, outputStream.toString());
    }
}

