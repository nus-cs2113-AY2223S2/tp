package seedu.clialgo.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.clialgo.Note;
import seedu.clialgo.Parser;
import seedu.clialgo.TopicManager;
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
        fileManager = new FileManager(".\\testdata", new ArrayList<>());
    }

    /**
     * Checks correct execution of <code>execute</code> method when printing empty <code>allNotes</code>.
     */
    @Test
    void isEmptyCheck_expectTrue() {
        String input = "list";
        Command command = parser.parse(input, topicManager);
        command.execute(topicManager, ui, fileManager);

        String os = System.getProperty("os.name");
        String expectedOutput;

        if (os.contains("Windows")) {
            expectedOutput = "======================================================\r\n" +
                    "You have no notes!\r\n" +
                    "Type 'help c/add' for assistance on how to add a note.\r\n" +
                    "======================================================\r\n";
        } else {
            expectedOutput = "======================================================\n" +
                    "You have no notes!\n" +
                    "Type 'help c/add' for assistance on how to add a note.\n" +
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
        topicManager.addNote(note.getName(), note.getTopic(), note);
        Command command = parser.parse(input, topicManager);
        command.execute(topicManager, ui, fileManager);

        String os = System.getProperty("os.name");
        String expectedOutput;

        if (os.contains("Windows")) {
            expectedOutput = "======================================================\r\n" +
                    "Here are all your notes:\r\n" +
                    "======================================================\r\n" +
                    "1. test\r\n" +
                    "======================================================\r\n";
        } else {
            expectedOutput = "======================================================\n" +
                    "Here are all your notes:\n" +
                    "======================================================\n" +
                    "1. test\n" +
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
        topicManager.addNote(note1.getName(), note1.getTopic(), note1);
        topicManager.addNote(note2.getName(), note2.getTopic(), note2);
        Command command = parser.parse(input, topicManager);
        command.execute(topicManager, ui, fileManager);

        String os = System.getProperty("os.name");
        String expectedOutput;

        if (os.contains("Windows")) {
            expectedOutput = "======================================================\r\n" +
                    "Here are all your notes:\r\n" +
                    "======================================================\r\n" +
                    "1. test2\r\n" +
                    "2. test1\r\n" +
                    "======================================================\r\n";
        } else {
            expectedOutput = "======================================================\n" +
                    "Here are all your notes:\n" +
                    "======================================================\n" +
                    "1. test2\n" +
                    "2. test1\n" +
                    "======================================================\n";
        }
        assertEquals(expectedOutput, outputStream.toString());
    }
}

