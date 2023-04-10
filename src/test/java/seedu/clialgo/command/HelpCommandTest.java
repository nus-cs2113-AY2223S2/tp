package seedu.clialgo.command;

import org.junit.jupiter.api.Test;
import seedu.clialgo.Buffer;
import seedu.clialgo.logic.TopicManager;
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

        String testDataPath = "./testdata";
        TopicManager topicManager = new TopicManager();
        Ui ui = new Ui();
        FileManager fileManager = new FileManager(testDataPath, topicManager.getTopicNames());
        fileManager.initialize();
        Buffer buffer = Buffer.getInstance();

        String os = System.getProperty("os.name");
        String expectedOutput = "";

        // Generate the expected output
        if (os.contains("Windows")) {
            expectedOutput = "======================================================\r\n" +
                    "The available COMMAND_TYPE(s) are:\r\n" +
                    "\r\n" +
                    "[add]: add CS2040CFile\r\n" +
                    "[remove]: remove CS2040CFile\r\n" +
                    "[list]: displays all CS2040CFiles\r\n" +
                    "[filter]: filters CS2040CFiles by topic\r\n" +
                    "[topo]: displays all CS2040CFiles before the selected topic\r\n" +
                    "[export]: places CS2040CFiles sorted by filter/topo in a file\r\n" +
                    "[exit]: close the application\r\n" +
                    "\r\n" +
                    "For more help on a specific command, type `help c/COMMAND_TYPE`.\r\n" +
                    "======================================================\r\n";
        } else {
            expectedOutput = "======================================================\n" +
                    "The available COMMAND_TYPE(s) are:\n" +
                    "\n" +
                    "[add]: add CS2040CFile\n" +
                    "[remove]: remove CS2040CFile\n" +
                    "[list]: displays all CS2040CFiles\n" +
                    "[filter]: filters CS2040CFiles by topic\n" +
                    "[topo]: displays all CS2040CFiles before the selected topic\n" +
                    "[export]: places CS2040CFiles sorted by filter/topo in a file\n" +
                    "[exit]: close the application\n" +
                    "\n" +
                    "For more help on a specific command, type `help c/COMMAND_TYPE`.\n" +
                    "======================================================\n";
        }

        // Generate the actual output
        new HelpCommand().execute(topicManager, ui, fileManager, buffer);
        assertEquals(expectedOutput, actualOutput.toString());
        FileManager.deleteAll(new File(testDataPath));
    }

    @Test
    void execute_addCommandInput_expectAddHelpMessage() {
        ByteArrayOutputStream actualOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(actualOutput));

        String testDataPath = "./testdata";
        TopicManager topicManager = new TopicManager();
        Ui ui = new Ui();
        FileManager fileManager = new FileManager(testDataPath, topicManager.getTopicNames());
        fileManager.initialize();
        Buffer buffer = Buffer.getInstance();

        String os = System.getProperty("os.name");
        String expectedOutput = "";

        if (os.contains("Windows")) {
            expectedOutput = "======================================================\r\n" +
                    "Add a CS2040CFile to a topic using:\r\n" +
                    "\r\n" +
                    "    `add n/NAME t/TOPIC [i/IMPORTANCE]`\r\n" +
                    "\r\n" +
                    "NAME: String name of the CS2040CFile file.\r\n" +
                    "TOPIC: String topic that NAME will be tagged to.\r\n" +
                    "IMPORTANCE: int level of importance on a scale of 1-10 (optional field).\r\n" +
                    "\r\n" +
                    "Valid TOPIC's are 'SORTING', 'LINKED_LIST', 'GRAPH_STRUCTURES',\r\n" +
                    "'BINARY_HEAP', 'HASH_TABLE', 'GRAPH_TRAVERSAL', 'BINARY_SEARCH_TREE',\r\n" +
                    "'SS_SHORTEST_PATH', 'UNION_FIND_DS' and 'MINIMUM_SPANNING_TREE'.\r\n" +
                    "======================================================\r\n";
        } else {
            expectedOutput = "======================================================\n" +
                    "Add a CS2040CFile to a topic using:\n" +
                    "\n" +
                    "    `add n/NAME t/TOPIC [i/IMPORTANCE]`\n" +
                    "\n" +
                    "NAME: String name of the CS2040CFile file.\n" +
                    "TOPIC: String topic that NAME will be tagged to.\n" +
                    "IMPORTANCE: int level of importance on a scale of 1-10 (optional field).\n" +
                    "\n" +
                    "Valid TOPIC's are 'SORTING', 'LINKED_LIST', 'GRAPH_STRUCTURES',\n" +
                    "'BINARY_HEAP', 'HASH_TABLE', 'GRAPH_TRAVERSAL', 'BINARY_SEARCH_TREE',\n" +
                    "'SS_SHORTEST_PATH', 'UNION_FIND_DS' and 'MINIMUM_SPANNING_TREE'.\n" +
                    "======================================================\n";
        }

        new HelpCommand("add").execute(topicManager, ui, fileManager, buffer);
        assertEquals(expectedOutput, actualOutput.toString());
        FileManager.deleteAll(new File(testDataPath));
    }

    @Test
    void execute_removeCommandInput_expectRemoveHelpMessage() {
        ByteArrayOutputStream actualOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(actualOutput));

        String testDataPath = "./testdata";
        TopicManager topicManager = new TopicManager();
        Ui ui = new Ui();
        FileManager fileManager = new FileManager(testDataPath, topicManager.getTopicNames());
        fileManager.initialize();
        Buffer buffer = Buffer.getInstance();

        String os = System.getProperty("os.name");
        String expectedOutput = "";

        if (os.contains("Windows")) {
            expectedOutput = "======================================================\r\n" +
                    "Remove a CS2040CFile using:\r\n" +
                    "\r\n" +
                    "    `remove n/NAME`\r\n" +
                    "\r\n" +
                    "NAME: String name of the CS2040CFile file.\r\n" +
                    "======================================================\r\n";
        } else {
            expectedOutput = "======================================================\n" +
                    "Remove a CS2040CFile using:\n" +
                    "\n" +
                    "    `remove n/NAME`\n" +
                    "\n" +
                    "NAME: String name of the CS2040CFile file.\n" +
                    "======================================================\n";
        }

        new HelpCommand("remove").execute(topicManager, ui, fileManager, buffer);
        assertEquals(expectedOutput, actualOutput.toString());
        FileManager.deleteAll(new File(testDataPath));
    }

    @Test
    void execute_filterCommandInput_expectFilterHelpMessage() {
        ByteArrayOutputStream actualOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(actualOutput));

        String testDataPath = "./testdata";
        TopicManager topicManager = new TopicManager();
        Ui ui = new Ui();
        FileManager fileManager = new FileManager(testDataPath, topicManager.getTopicNames());
        fileManager.initialize();
        Buffer buffer = Buffer.getInstance();

        String os = System.getProperty("os.name");
        String expectedOutput = "";

        if (os.contains("Windows")) {
            expectedOutput = "======================================================\r\n" +
                    "Filter by topic/importance and topic name using:\r\n" +
                    "\r\n" +
                    "    `filter k/KEYWORD [t/TOPIC]`\r\n" +
                    "\r\n" +
                    "KEYWORD: String keyword has to be either `topic` or `importance`.\r\n" +
                    "TOPIC: String topic chosen from list below (optional field).\r\n" +
                    "\r\n" +
                    "Valid TOPIC's are 'SORTING', 'LINKED_LIST', 'GRAPH_STRUCTURES',\r\n" +
                    "'BINARY_HEAP', 'HASH_TABLE', 'GRAPH_TRAVERSAL', 'BINARY_SEARCH_TREE',\r\n" +
                    "'SS_SHORTEST_PATH', 'UNION_FIND_DS' and 'MINIMUM_SPANNING_TREE'.\r\n" +
                    "======================================================\r\n";
        } else {
            expectedOutput = "======================================================\n" +
                    "Filter by topic/importance and topic name using:\n" +
                    "\n" +
                    "    `filter k/KEYWORD [t/TOPIC]`\n" +
                    "\n" +
                    "KEYWORD: String keyword has to be either `topic` or `importance`.\n" +
                    "TOPIC: String topic chosen from list below (optional field).\n" +
                    "\n" +
                    "Valid TOPIC's are 'SORTING', 'LINKED_LIST', 'GRAPH_STRUCTURES',\n" +
                    "'BINARY_HEAP', 'HASH_TABLE', 'GRAPH_TRAVERSAL', 'BINARY_SEARCH_TREE',\n" +
                    "'SS_SHORTEST_PATH', 'UNION_FIND_DS' and 'MINIMUM_SPANNING_TREE'.\n" +
                    "======================================================\n";
        }

        new HelpCommand("filter").execute(topicManager, ui, fileManager, buffer);
        assertEquals(expectedOutput, actualOutput.toString());
        FileManager.deleteAll(new File(testDataPath));
    }

    @Test
    void execute_listCommandInput_expectListHelpMessage() {
        ByteArrayOutputStream actualOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(actualOutput));

        String testDataPath = "./testdata";
        TopicManager topicManager = new TopicManager();
        Ui ui = new Ui();
        FileManager fileManager = new FileManager(testDataPath, topicManager.getTopicNames());
        fileManager.initialize();
        Buffer buffer = Buffer.getInstance();

        String os = System.getProperty("os.name");
        String expectedOutput = "";

        if (os.contains("Windows")) {
            expectedOutput = "======================================================\r\n" +
                    "List all stored CS2040CFiles using:\r\n" +
                    "\r\n" +
                    "    `list`\r\n" +
                    "\r\n" +
                    "Command should only contain one word (i.e. no extensions).\r\n" +
                    "======================================================\r\n";
        } else {
            expectedOutput = "======================================================\n" +
                    "List all stored CS2040CFiles using:\n" +
                    "\n" +
                    "    `list`\n" +
                    "\n" +
                    "Command should only contain one word (i.e. no extensions).\n" +
                    "======================================================\n";
        }

        new HelpCommand("list").execute(topicManager, ui, fileManager, buffer);
        assertEquals(expectedOutput, actualOutput.toString());
        FileManager.deleteAll(new File(testDataPath));
    }

    @Test
    void execute_topoCommandInput_expectTopoHelpMessage() {
        ByteArrayOutputStream actualOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(actualOutput));

        String testDataPath = "./testdata";
        TopicManager topicManager = new TopicManager();
        Ui ui = new Ui();
        FileManager fileManager = new FileManager(testDataPath, topicManager.getTopicNames());
        fileManager.initialize();
        Buffer buffer = Buffer.getInstance();

        String os = System.getProperty("os.name");
        String expectedOutput = "";

        if (os.contains("Windows")) {
            expectedOutput = "======================================================\r\n" +
                    "Print all files before the user-defined filename using:\r\n" +
                    "\r\n" +
                    "    `topo n/NAME`\r\n" +
                    "\r\n" +
                    "NAME: String name of the CS2040CFile file.\r\n" +
                    "======================================================\r\n";
        } else {
            expectedOutput = "======================================================\n" +
                    "Print all files before the user-defined filename using:\n" +
                    "\n" +
                    "    `topo n/NAME`\n" +
                    "\n" +
                    "NAME: String name of the CS2040CFile file.\n" +
                    "======================================================\n";
        }

        new HelpCommand("topo").execute(topicManager, ui, fileManager, buffer);
        assertEquals(expectedOutput, actualOutput.toString());
        FileManager.deleteAll(new File(testDataPath));
    }

    @Test
    void execute_exportCommandInput_expectExportHelpMessage() {
        ByteArrayOutputStream actualOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(actualOutput));

        String testDataPath = "./testdata";
        TopicManager topicManager = new TopicManager();
        Ui ui = new Ui();
        FileManager fileManager = new FileManager(testDataPath, topicManager.getTopicNames());
        fileManager.initialize();
        Buffer buffer = Buffer.getInstance();

        String os = System.getProperty("os.name");
        String expectedOutput = "";

        if (os.contains("Windows")) {
            expectedOutput = "======================================================\r\n" +
                    "Export your filter/topo sorted files using:\r\n" +
                    "\r\n" +
                    "    `export`\r\n" +
                    "\r\n" +
                    "Command should only contain one word (i.e. no extensions).\r\n" +
                    "======================================================\r\n";
        } else {
            expectedOutput = "======================================================\n" +
                    "Export your filter/topo sorted files using:\n" +
                    "\n" +
                    "    `export`\n" +
                    "\n" +
                    "Command should only contain one word (i.e. no extensions).\n" +
                    "======================================================\n";
        }

        new HelpCommand("export").execute(topicManager, ui, fileManager, buffer);
        assertEquals(expectedOutput, actualOutput.toString());
        FileManager.deleteAll(new File(testDataPath));
    }

    @Test
    void execute_exitCommandInput_expectExitHelpMessage() {
        ByteArrayOutputStream actualOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(actualOutput));

        String testDataPath = "./testdata";
        TopicManager topicManager = new TopicManager();
        Ui ui = new Ui();
        FileManager fileManager = new FileManager(testDataPath, topicManager.getTopicNames());
        fileManager.initialize();
        Buffer buffer = Buffer.getInstance();

        String os = System.getProperty("os.name");
        String expectedOutput = "";

        if (os.contains("Windows")) {
            expectedOutput = "======================================================\r\n" +
                    "Exits CLIAlgo using:\r\n" +
                    "\r\n" +
                    "    `exit`\r\n" +
                    "\r\n" +
                    "Command should only contain one word (i.e. no extensions).\r\n" +
                    "======================================================\r\n";
        } else {
            expectedOutput = "======================================================\n" +
                    "Exits CLIAlgo using:\n" +
                    "\n" +
                    "    `exit`\n" +
                    "\n" +
                    "Command should only contain one word (i.e. no extensions).\n" +
                    "======================================================\n";
        }

        new HelpCommand("exit").execute(topicManager, ui, fileManager, buffer);
        assertEquals(expectedOutput, actualOutput.toString());
        FileManager.deleteAll(new File(testDataPath));
    }
}
