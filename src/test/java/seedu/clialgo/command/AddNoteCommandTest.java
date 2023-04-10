package seedu.clialgo.command;

import org.junit.jupiter.api.Test;
import seedu.clialgo.logic.TopicManager;
import seedu.clialgo.Ui;
import seedu.clialgo.Buffer;
import seedu.clialgo.file.CS2040CFile;
import seedu.clialgo.file.Note;
import seedu.clialgo.storage.FileManager;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class AddNoteCommandTest {

    @Test
    void equals_checkEqualAddNoteCommand_expectTrue() {
        AddNoteCommand myObj = new AddNoteCommand("queue", "LINKED_LIST", 10);
        AddNoteCommand myOtherObj = new AddNoteCommand("queue", "LINKED_LIST", 10);
        assertTrue(myObj.equals(myOtherObj));
    }

    @Test
    void equals_checkUnequalNameAddNoteCommand_expectFalse() {
        AddNoteCommand myObj = new AddNoteCommand("queue", "LINKED_LIST", 10);
        AddNoteCommand myOtherObj = new AddNoteCommand("queue1", "LINKED_LIST", 10);
        assertFalse(myObj.equals(myOtherObj));
    }

    @Test
    void equals_checkUnequalTopicAddNoteCommand_expectFalse() {
        AddNoteCommand myObj = new AddNoteCommand("queue", "LINKED_LIST", 10);
        AddNoteCommand myOtherObj = new AddNoteCommand("queue", "SORTING", 10);
        assertFalse(myObj.equals(myOtherObj));
    }

    @Test
    void equals_checkUnequalImportanceAddNoteCommand_expectFalse() {
        AddNoteCommand myObj = new AddNoteCommand("queue", "LINKED_LIST", 10);
        AddNoteCommand myOtherObj = new AddNoteCommand("queue", "LINKED_LIST", 5);
        assertFalse(myObj.equals(myOtherObj));
    }

    @Test
    void execute_addNoteFileThatAlreadyExists_expectInvalidCommandPrinted() {
        TopicManager topicManager = new TopicManager();
        CS2040CFile cs2040CFile = new Note("queue", "dummypath", "LINKED_LIST", 10);
        topicManager.addCS2040CFile("queue", "LINKED_LIST", cs2040CFile);

        String testDataPath = "./testdata";
        Ui ui = new Ui();
        FileManager fileManager = new FileManager(testDataPath, topicManager.getTopicNames());
        fileManager.initialize();
        Buffer buffer = Buffer.getInstance();

        ByteArrayOutputStream actualOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(actualOutput));

        new AddNoteCommand("queue", "LINKED_LIST", 10).execute(topicManager, ui, fileManager, buffer);

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
    void execute_addNoteFileThatDoesExists_expectCS2040CFileInsideTopicManager() {
        TopicManager topicManager = new TopicManager();

        String testDataPath = "./testdata";
        Ui ui = new Ui();
        FileManager fileManager = new FileManager(testDataPath, topicManager.getTopicNames());
        fileManager.initialize();
        Buffer buffer = Buffer.getInstance();

        AddNoteCommand addNoteCommandObj = new AddNoteCommand("queue", "LINKED_LIST", 10);
        addNoteCommandObj.execute(topicManager, ui, fileManager, buffer);

        assertTrue(topicManager.getAllFilesAsFiles().contains(new Note("queue", "queue.txt", "LINKED_LIST", 10)));
        FileManager.deleteAll(new File(testDataPath));
    }
}
