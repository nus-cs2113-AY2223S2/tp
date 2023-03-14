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

public class FilterCommandTest {
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
        String input = "filter k/topic";
        Command command = parser.parse(input, topicManager);
        command.execute(topicManager, ui, fileManager);

        input = "filter k/topic t/";

        String os = System.getProperty("os.name");
        String expectedOutput;

        if (os.contains("Windows")) {
            expectedOutput = "======================================================\r\n" +
                    "Unsuccessful!\r\n" +
                    "Type 'help c/filter' for assistance.\r\n" +
                    "======================================================\r\n";
        } else {
            expectedOutput = "======================================================\n" +
                    "Unsuccessful!\n" +
                    "Type 'help c/filter' for assistance.\n" +
                    "======================================================\n";
        }
        String newExpectedOutput = expectedOutput;
        for (String string : topicManager.getTopicNames()) {
            String newInput = input + string;
            command = parser.parse(newInput, topicManager);
            command.execute(topicManager, ui, fileManager);
            newExpectedOutput += expectedOutput;
        }
        assertEquals(newExpectedOutput, outputStream.toString());
    }
}
