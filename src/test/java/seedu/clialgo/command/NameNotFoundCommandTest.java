package seedu.clialgo.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.clialgo.Buffer;
import seedu.clialgo.Parser;
import seedu.clialgo.logic.TopicManager;
import seedu.clialgo.Ui;
import seedu.clialgo.file.Note;
import seedu.clialgo.storage.FileManager;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * JUnit test for <code>NameNotFoundCommand</code> object.
 */
public class NameNotFoundCommandTest {

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
     * Checks if the <code>execute</code> method of a <code>NameNotFoundCommand<</code> object works as expected.
     */
    @Test
    void isExecutedCorrectly_inputWithNonExistentName_expectTrue() {
        topicManager.addCS2040CFile("something", "SORTING", new Note("something",
                "path", "SORTING"));
        String input = "remove n/nothing";
        Command command = parser.parse(input, topicManager);
        command.execute(topicManager, ui, fileManager, buffer);

        String os = System.getProperty("os.name");
        String expectedOutput;

        if (os.contains("Windows")) {
            expectedOutput = "======================================================\r\n" +
                    "Unsuccessful! A CS2040CFile of that name does not exist.\r\n" +
                    "Only CS2040CFiles in your list can be removed.\r\n" +
                    "Type 'list' to see CS2040CFiles you can remove.\r\n" +
                    "======================================================\r\n";
        } else {
            expectedOutput = "======================================================\n" +
                    "Unsuccessful! A CS2040CFile of that name does not exist.\n" +
                    "Only CS2040CFiles in your list can be removed.\n" +
                    "Type 'list' to see CS2040CFiles you can remove.\n" +
                    "======================================================\n";
        }
        assertEquals(expectedOutput, outputStream.toString());
    }
}

