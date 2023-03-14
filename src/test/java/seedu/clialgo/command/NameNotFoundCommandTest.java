package seedu.clialgo.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.clialgo.Parser;
import seedu.clialgo.TopicManager;
import seedu.clialgo.Ui;
import seedu.clialgo.storage.FileManager;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * JUnit test for <code>NameNotFoundCommand</code> object.
 */
public class NameNotFoundCommandTest {

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
     * Checks if the <code>execute</code> method of a <code>NameNotFoundCommand<</code> object works as expected.
     */
    @Test
    void isExecutedCorrectly_inputWithNonExistentName_expectTrue() {
        String input = "remove n/nothing";
        Command command = parser.parse(input, topicManager);
        command.execute(topicManager, ui, fileManager);

        String os = System.getProperty("os.name");
        String expectedOutput;

        if (os.contains("Windows")) {
            expectedOutput = "======================================================\r\n" +
                    "Unsuccessful! A note of that name does not exist.\r\n" +
                    "Only notes in your list can be removed.\r\n" +
                    "Type 'list' to see notes you can remove.\r\n" +
                    "======================================================\r\n";
        } else {
            expectedOutput = "======================================================\n" +
                    "Unsuccessful! A note of that name does not exist.\n" +
                    "Only notes in your list can be removed.\n" +
                    "Type 'list' to see notes you can remove.\n" +
                    "======================================================\n";
        }
        assertEquals(expectedOutput, outputStream.toString());
    }

    /**
     * Checks if the <code>equals</code> method of a <code>NameNotFoundCommand<</code> object works as expected.
     */
    @Test
    void isEqualsMethodWorking_expectTrue() {
        String input = "remove n/nothing";
        Command command = parser.parse(input, topicManager);
        Command testCommand = new NameNotFoundCommand();
        assertTrue(testCommand.equals(command));
    }

}

