package seedu.clialgo.command;

import org.junit.jupiter.api.Test;
import seedu.clialgo.TopicManager;
import seedu.clialgo.Ui;
import seedu.clialgo.storage.FileManager;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HelpCommandTest {
    @Test
    void execute_noCommandInput_expectGenericHelpMessage() {
        ByteArrayOutputStream actualOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(actualOutput));

        String testDataPath = ".\\testdata";
        TopicManager topicManager = new TopicManager();
        Ui ui = new Ui();
        FileManager fileManager = new FileManager(testDataPath, topicManager.getTopicNames());
        fileManager.initialize();

        String os = System.getProperty("os.name");
        String expectedOutput = "";

        // Generate the expected output
        if (os.contains("Windows")) {
            expectedOutput = "======================================================\r\n" +
                    "The available COMMAND_TYPE(s) are:\r\n" +
                    "[add]: add note\r\n" +
                    "[remove]: remove note\r\n" +
                    "[list]: displays all notes\r\n" +
                    "[filter]: filters notes by topic\r\n" +
                    "[exit]: close the application\r\n" +
                    "For more help on a specific command, type `help c/COMMAND_TYPE`\r\n" +
                    "======================================================\r\n";
        } else {
            expectedOutput = "======================================================\n" +
                    "The available COMMAND_TYPE(s) are:\n" +
                    "[add]: add note\n" +
                    "[remove]: remove note\n" +
                    "[list]: displays all notes\n" +
                    "[filter]: filters notes by topic\n" +
                    "[exit]: close the application\n" +
                    "For more help on a specific command, type `help c/COMMAND_TYPE`\n" +
                    "======================================================\n";
        }

        // Generate the actual output
        new HelpCommand().execute(topicManager, ui, fileManager);
        assertEquals(expectedOutput, actualOutput.toString());
        FileManager.deleteAll(new File(testDataPath));
    }

    @Test
    void execute_addCommandInput_expectAddHelpMessage() {
        ByteArrayOutputStream actualOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(actualOutput));

        String testDataPath = ".\\testdata";
        TopicManager topicManager = new TopicManager();
        Ui ui = new Ui();
        FileManager fileManager = new FileManager(testDataPath, topicManager.getTopicNames());
        fileManager.initialize();

        String os = System.getProperty("os.name");
        String expectedOutput = "";

        if (os.contains("Windows")) {
            expectedOutput = "======================================================\r\n" +
                    "This function adds a note and tags it to a topic.\r\n" +
                    "The syntax for the 'add' command is: add n/NAME t/TOPIC.\r\n" +
                    "NAME refers to the notes' file name.\r\n" +
                    "TOPIC refers to the topic that NAME will be tagged to.\r\n" +
                    "Case sensitive. NAME and TOPIC fields must be non-empty.\r\n" +
                    "Invalid NAME or TOPIC will cause an error.\r\n" +
                    "Valid TOPIC's are 'SORTING', 'LINKED_LIST', 'GRAPH_STRUCTURES',\r\n" +
                    "'BINARY_HEAP', 'HASH_TABLE', 'GRAPH_TRAVERSAL', 'BINARY_SEARCH_TREE',\r\n" +
                    "'SS_SHORTEST_PATH', 'UNION_FIND_DS' and 'MINIMUM_SPANNING_TREE'.\r\n" +
                    "======================================================\r\n";
        } else {
            expectedOutput = "======================================================\n" +
                    "This function adds a note and tags it to a topic.\n" +
                    "The syntax for the 'add' command is: add n/NAME t/TOPIC.\n" +
                    "NAME refers to the notes' file name.\n" +
                    "TOPIC refers to the topic that NAME will be tagged to.\n" +
                    "Case sensitive. NAME and TOPIC fields must be non-empty.\n" +
                    "Invalid NAME or TOPIC will cause an error.\n" +
                    "Valid TOPIC's are 'SORTING', 'LINKED_LIST', 'GRAPH_STRUCTURES',\n" +
                    "'BINARY_HEAP', 'HASH_TABLE', 'GRAPH_TRAVERSAL', 'BINARY_SEARCH_TREE',\n" +
                    "'SS_SHORTEST_PATH', 'UNION_FIND_DS' and 'MINIMUM_SPANNING_TREE'.\n" +
                    "======================================================\n";
        }

        new HelpCommand("add").execute(topicManager, ui, fileManager);
        assertEquals(expectedOutput, actualOutput.toString());
        FileManager.deleteAll(new File(testDataPath));
    }

    @Test
    void execute_removeCommandInput_expectRemoveHelpMessage() {
        ByteArrayOutputStream actualOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(actualOutput));

        String testDataPath = ".\\testdata";
        TopicManager topicManager = new TopicManager();
        Ui ui = new Ui();
        FileManager fileManager = new FileManager(testDataPath, topicManager.getTopicNames());
        fileManager.initialize();

        String os = System.getProperty("os.name");
        String expectedOutput = "";

        if (os.contains("Windows")) {
            expectedOutput = "======================================================\r\n" +
                    "This function removes a note from the tagged topic.\r\n" +
                    "The syntax for the 'remove' command is: remove n/NAME.\r\n" +
                    "NAME refers to the notes' file name.\r\n" +
                    "'n/' must be included else NAME will not be read.\r\n" +
                    "Invalid NAME will cause an error.\r\n" +
                    "======================================================\r\n";
        } else {
            expectedOutput = "======================================================\n" +
                    "This function removes a note from the tagged topic.\n" +
                    "The syntax for the 'remove' command is: remove n/NAME.\n" +
                    "NAME refers to the notes' file name.\n" +
                    "'n/' must be included else NAME will not be read.\n" +
                    "Invalid NAME will cause an error.\n" +
                    "======================================================\n";
        }

        new HelpCommand("remove").execute(topicManager, ui, fileManager);
        assertEquals(expectedOutput, actualOutput.toString());
        FileManager.deleteAll(new File(testDataPath));
    }

    @Test
    void execute_filterCommandInput_expectFilterHelpMessage() {
        ByteArrayOutputStream actualOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(actualOutput));

        String testDataPath = ".\\testdata";
        TopicManager topicManager = new TopicManager();
        Ui ui = new Ui();
        FileManager fileManager = new FileManager(testDataPath, topicManager.getTopicNames());
        fileManager.initialize();

        String os = System.getProperty("os.name");
        String expectedOutput = "";

        if (os.contains("Windows")) {
            expectedOutput = "======================================================\r\n" +
                    "This function filters by topic/importance and topic name.\r\n" +
                    "The syntax for the 'filter' command is: filter k/KEYWORD t/TOPIC_NAME\r\n" +
                    "KEYWORD has to be either 'topic' or 'importance'.\r\n" +
                    "TOPIC_NAME can be any (one) of the pre-defined topics in CS2040C.\r\n" +
                    "Case sensitive. KEYWORD and TOPIC_NAME fields must be non-empty.\r\n" +
                    "Invalid KEYWORD and/or TOPIC_NAME will cause an error.\r\n" +
                    "======================================================\r\n";
        } else {
            expectedOutput = "======================================================\n" +
                    "This function filters by topic/importance and topic name.\n" +
                    "The syntax for the 'filter' command is: filter k/KEYWORD t/TOPIC_NAME\n" +
                    "KEYWORD has to be either 'topic' or 'importance'.\n" +
                    "TOPIC_NAME can be any (one) of the pre-defined topics in CS2040C.\n" +
                    "Case sensitive. KEYWORD and TOPIC_NAME fields must be non-empty.\n" +
                    "Invalid KEYWORD and/or TOPIC_NAME will cause an error.\n" +
                    "======================================================\n";
        }

        new HelpCommand("filter").execute(topicManager, ui, fileManager);
        assertEquals(expectedOutput, actualOutput.toString());
        FileManager.deleteAll(new File(testDataPath));
    }

    @Test
    void execute_listCommandInput_expectListHelpMessage() {
        ByteArrayOutputStream actualOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(actualOutput));

        String testDataPath = ".\\testdata";
        TopicManager topicManager = new TopicManager();
        Ui ui = new Ui();
        FileManager fileManager = new FileManager(testDataPath, topicManager.getTopicNames());
        fileManager.initialize();

        String os = System.getProperty("os.name");
        String expectedOutput = "";

        if (os.contains("Windows")) {
            expectedOutput = "======================================================\r\n" +
                    "This function lists all stored notes.\r\n" +
                    "The syntax for the 'list' command is: list.\r\n" +
                    "Command should only contain one word (i.e. no extensions).\r\n" +
                    "======================================================\r\n";
        } else {
            expectedOutput = "======================================================\n" +
                    "This function lists all stored notes.\n" +
                    "The syntax for the 'list' command is: list.\n" +
                    "Command should only contain one word (i.e. no extensions).\n" +
                    "======================================================\n";
        }

        new HelpCommand("list").execute(topicManager, ui, fileManager);
        assertEquals(expectedOutput, actualOutput.toString());
        FileManager.deleteAll(new File(testDataPath));
    }

    @Test
    void execute_exitCommandInput_expectExitHelpMessage() {
        ByteArrayOutputStream actualOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(actualOutput));

        String testDataPath = ".\\testdata";
        TopicManager topicManager = new TopicManager();
        Ui ui = new Ui();
        FileManager fileManager = new FileManager(testDataPath, topicManager.getTopicNames());
        fileManager.initialize();

        String os = System.getProperty("os.name");
        String expectedOutput = "";

        if (os.contains("Windows")) {
            expectedOutput = "======================================================\r\n" +
                    "This function exits the application.\r\n" +
                    "The syntax for the 'exit' command is: exit.\r\n" +
                    "Command should only contain one word (i.e. no extensions).\r\n" +
                    "======================================================\r\n";
        } else {
            expectedOutput = "======================================================\n" +
                    "This function exits the application.\n" +
                    "The syntax for the 'exit' command is: exit.\n" +
                    "Command should only contain one word (i.e. no extensions).\n" +
                    "======================================================\n";
        }

        new HelpCommand("exit").execute(topicManager, ui, fileManager);
        assertEquals(expectedOutput, actualOutput.toString());
        FileManager.deleteAll(new File(testDataPath));
    }
}
