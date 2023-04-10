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
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExitTestModeCommandTest {
    private static final String PATH = "./testdata";
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
        fileManager = new FileManager(PATH, new ArrayList<>());
        buffer = Buffer.getInstance();
        outputStream.reset();
    }

    /**
     * Test if the error message for the check for test mode not being started prints correctly.
     */
    @Test
    public void isNotStartedCheck_expectTrue() {
        String input = "exit-test-mode";
        Command command = parser.parse(input, topicManager);
        command.execute(topicManager, ui, fileManager, buffer);

        String os = System.getProperty("os.name");
        String expectedOutput;

        if (os.contains("Windows")) {
            expectedOutput = "======================================================\r\n" +
                    "Unsuccessful, test mode has not been started.\r\n" +
                    "======================================================\r\n";
        } else {
            expectedOutput = "======================================================\n" +
                    "Unsuccessful, test mode has not been started.\n" +
                    "======================================================\n";
        }
        assertEquals(expectedOutput, outputStream.toString());
    }

    /**
     * Check if all the messages indicating the successful run and exiting of test mode are printed correctly.
     */
    @Test
    public void isRunSuccessfully_expectTrue() {
        String input = "start-test-mode";
        Command command = parser.parse(input, topicManager);
        command.execute(topicManager, ui, fileManager, buffer);

        String pathToFile = "/test.txt";
        File test = new File(PATH + pathToFile);
        try {
            if (!test.createNewFile()) {
                System.out.println("ERROR HERE");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        input = "exit-test-mode";
        command = parser.parse(input, topicManager);
        command.execute(topicManager, ui, fileManager, buffer);

        String os = System.getProperty("os.name");
        String expectedOutput;

        if (os.contains("Windows")) {
            expectedOutput ="======================================================\r\n" +
                    "Starting test mode.\r\n" +
                    "======================================================\r\n" +
                    "======================================================\r\n" +
                    "Ending test mode.\r\n" +
                    "======================================================\r\n" +
                    "======================================================\r\n" +
                    "Successfully deleted file.\r\n" +
                    "======================================================\r\n";
        } else {
            expectedOutput = "======================================================\n" +
                    "Starting test mode.\n" +
                    "======================================================\n" +
                    "======================================================\n" +
                    "Ending test mode.\n" +
                    "======================================================\n" +
                    "======================================================\n" +
                    "Successfully deleted file.\n" +
                    "======================================================\n";
        }
        assertEquals(expectedOutput, outputStream.toString());
    }
}
