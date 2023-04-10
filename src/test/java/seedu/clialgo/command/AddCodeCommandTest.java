package seedu.clialgo.command;

import org.junit.jupiter.api.Test;
import seedu.clialgo.logic.TopicManager;
import seedu.clialgo.Ui;
import seedu.clialgo.Buffer;
import seedu.clialgo.file.CS2040CFile;
import seedu.clialgo.file.Code;
import seedu.clialgo.storage.FileManager;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class AddCodeCommandTest {

    @Test
    void equals_checkEqualAddCodeCommand_expectTrue() {
        AddCodeCommand myObj = new AddCodeCommand("queue", "LINKED_LIST", 10);
        AddCodeCommand myOtherObj = new AddCodeCommand("queue", "LINKED_LIST", 10);
        assertTrue(myObj.equals(myOtherObj));
    }

    @Test
    void equals_checkUnequalNameAddCodeCommand_expectFalse() {
        AddCodeCommand myObj = new AddCodeCommand("queue", "LINKED_LIST", 10);
        AddCodeCommand myOtherObj = new AddCodeCommand("queue1", "LINKED_LIST", 10);
        assertFalse(myObj.equals(myOtherObj));
    }

    @Test
    void equals_checkUnequalTopicAddCodeCommand_expectFalse() {
        AddCodeCommand myObj = new AddCodeCommand("queue", "LINKED_LIST", 10);
        AddCodeCommand myOtherObj = new AddCodeCommand("queue", "SORTING", 10);
        assertFalse(myObj.equals(myOtherObj));
    }

    @Test
    void equals_checkUnequalImportanceAddCodeCommand_expectFalse() {
        AddCodeCommand myObj = new AddCodeCommand("queue", "LINKED_LIST", 10);
        AddCodeCommand myOtherObj = new AddCodeCommand("queue", "LINKED_LIST", 5);
        assertFalse(myObj.equals(myOtherObj));
    }

    @Test
    void execute_addCodeFileThatAlreadyExists_expectInvalidCommandPrinted() {
        TopicManager topicManager = new TopicManager();
        CS2040CFile cs2040CFile = new Code("queue", "dummypath", "LINKED_LIST", 10);
        topicManager.addCS2040CFile("queue", "LINKED_LIST", cs2040CFile);

        String testDataPath = "./testdata";
        Ui ui = new Ui();
        FileManager fileManager = new FileManager(testDataPath, topicManager.getTopicNames());
        fileManager.initialize();
        Buffer buffer = Buffer.getInstance();

        ByteArrayOutputStream actualOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(actualOutput));

        new AddCodeCommand("queue", "LINKED_LIST", 10).execute(topicManager, ui, fileManager, buffer);

        String os = System.getProperty("os.name");
        String expectedOutput = "";

        if (os.contains("Windows")) {
            expectedOutput = "======================================================\r\n" +
                    "This is an invalid command, please ensure all your fields are correct.\r\n" +
                    "Type 'help' for additional assistance.\r\n" +
                    "======================================================\r\n";
        } else {
            expectedOutput = "======================================================\n" +
                    "This is an invalid command, please ensure all your fields are correct.\n" +
                    "Type 'help' for additional assistance.\n" +
                    "======================================================\n";
        }

        assertEquals(expectedOutput, actualOutput.toString());
        FileManager.deleteAll(new File(testDataPath));
    }

    @Test
    void execute_addCodeFileThatDoesExists_expectCS2040CFileInsideTopicManager() {
        TopicManager topicManager = new TopicManager();

        String testDataPath = "./testdata";
        Ui ui = new Ui();
        FileManager fileManager = new FileManager(testDataPath, topicManager.getTopicNames());
        fileManager.initialize();
        Buffer buffer = Buffer.getInstance();

        AddCodeCommand addCodeCommandObj = new AddCodeCommand("queue", "LINKED_LIST", 10);
        addCodeCommandObj.execute(topicManager, ui, fileManager, buffer);

        assertTrue(topicManager.getAllFilesAsFiles().contains(new Code("queue", "queue.cpp", "LINKED_LIST", 10)));
        FileManager.deleteAll(new File(testDataPath));
    }
}
