package seedu.clialgo;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UiTest {
    @Test
    void printDivider() {
        ByteArrayOutputStream actualOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(actualOutput));

        Ui ui = new Ui();
        ui.printDivider();

        String os = System.getProperty("os.name");
        String expectedOutput = "";

        if (os.contains("Windows")) {
            expectedOutput = "======================================================\r\n";
        } else {
            expectedOutput = "======================================================\n";
        }
        assertEquals(expectedOutput, actualOutput.toString());
    }

    @Test
    void printWelcomeMessage() {
        ByteArrayOutputStream actualOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(actualOutput));

        Ui ui = new Ui();
        ui.printWelcomeMessage();

        String os = System.getProperty("os.name");
        String expectedOutput = "";

        if (os.contains("Windows")) {
            expectedOutput = "======================================================\r\n" +
                    "Hello! Welcome to CLIAlgo Notes!\r\n" +
                    "======================================================\r\n";
        } else {
            expectedOutput = "======================================================\n" +
                    "Hello! Welcome to CLIAlgo Notes!\n" +
                    "======================================================\n";
        }
        assertEquals(expectedOutput, actualOutput.toString());
    }

    @Test
    void printExitMessage() {
        ByteArrayOutputStream actualOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(actualOutput));

        Ui ui = new Ui();
        ui.printExitMessage();

        String os = System.getProperty("os.name");
        String expectedOutput = "";

        if (os.contains("Windows")) {
            expectedOutput = "======================================================\r\n" +
                    "Thank you for using CLIAlgo! Study hard!\r\n" +
                    "======================================================\r\n";
        } else {
            expectedOutput = "======================================================\n" +
                    "Thank you for using CLIAlgo! Study hard!\n" +
                    "======================================================\n";
        }
        assertEquals(expectedOutput, actualOutput.toString());
    }

    @Test
    void printHelpPage() {
        ByteArrayOutputStream actualOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(actualOutput));

        Ui ui = new Ui();
        ui.printHelpPage();

        String os = System.getProperty("os.name");
        String expectedOutput = "";

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
        assertEquals(expectedOutput, actualOutput.toString());
    }

    @Test
    void printAddSuccess() {
        ByteArrayOutputStream actualOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(actualOutput));

        Ui ui = new Ui();
        String actualName = "queue";
        String actualTopic = "LINKED_LIST";
        ui.printAddSuccess(actualName, actualTopic);

        String os = System.getProperty("os.name");
        String expectedOutput = "";

        if (os.contains("Windows")) {
            expectedOutput = "======================================================\r\n" +
                    "Successfully added queue into LINKED_LIST.\r\n" +
                    "======================================================\r\n";
        } else {
            expectedOutput = "======================================================\n" +
                    "Successfully added queue into LINKED_LIST.\n" +
                    "======================================================\n";
        }
        assertEquals(expectedOutput, actualOutput.toString());
    }

    @Test
    void printAddFail() {
        ByteArrayOutputStream actualOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(actualOutput));

        Ui ui = new Ui();
        String actualTopic = "FENWICK_TREE";
        ui.printAddFail(actualTopic);

        String os = System.getProperty("os.name");
        String expectedOutput = "";

        if (os.contains("Windows")) {
            expectedOutput = "======================================================\r\n" +
                    "Unsuccessful! FENWICK_TREE is not a topic in CS2040C.\r\n" +
                    "Type 'help c/add' for assistance.\r\n" +
                    "======================================================\r\n";
        } else {
            expectedOutput = "======================================================\n" +
                    "Unsuccessful! FENWICK_TREE is not a topic in CS2040C.\n" +
                    "Type 'help c/add' for assistance.\n" +
                    "======================================================\n";
        }
        assertEquals(expectedOutput, actualOutput.toString());
    }

    @Test
    public void printNoteExists() {
        ByteArrayOutputStream actualOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(actualOutput));

        Ui ui = new Ui();
        ui.printCS2040CFileExists();

        String os = System.getProperty("os.name");
        String expectedOutput = "";

        if (os.contains("Windows")) {
            expectedOutput = "======================================================\r\n" +
                    "Unsuccessful! A CS2040CFile with that name already exists.\r\n" +
                    "Type 'list' to view the list of CS2040CFiles.\r\n" +
                    "======================================================\r\n";
        } else {
            expectedOutput = "======================================================\n" +
                    "Unsuccessful! A CS2040CFile with that name already exists.\n" +
                    "Type 'list' to view the list of CS2040CFiles.\n" +
                    "======================================================\n";
        }
        assertEquals(expectedOutput, actualOutput.toString());
    }

    @Test
    void printListSuccess() {
        ByteArrayOutputStream actualOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(actualOutput));

        Ui ui = new Ui();
        ui.printListSuccess();

        String os = System.getProperty("os.name");
        String expectedOutput = "";

        if (os.contains("Windows")) {
            expectedOutput = "======================================================\r\n" +
                    "Here are all your CS2040CFiles:\r\n" +
                    "======================================================\r\n";
        } else {
            expectedOutput = "======================================================\n" +
                    "Here are all your CS2040CFiles:\n" +
                    "======================================================\n";
        }
        assertEquals(expectedOutput, actualOutput.toString());
    }

    @Test
    void printListFail() {
        ByteArrayOutputStream actualOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(actualOutput));

        Ui ui = new Ui();
        ui.printListFail();

        String os = System.getProperty("os.name");
        String expectedOutput = "";

        if (os.contains("Windows")) {
            expectedOutput = "======================================================\r\n" +
                    "You have no CS2040CFiles!\r\n" +
                    "Type 'help c/add' for assistance on how to add a CS2040CFile.\r\n" +
                    "======================================================\r\n";
        } else {
            expectedOutput =  "======================================================\n" +
                    "You have no CS2040CFiles!\n" +
                    "Type 'help c/add' for assistance on how to add a CS2040CFile.\n" +
                    "======================================================\n";
        }
        assertEquals(expectedOutput, actualOutput.toString());
    }

    @Test
    void printRemoveSuccess() {
        ByteArrayOutputStream actualOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(actualOutput));

        Ui ui = new Ui();
        String actualName = "queue";
        ui.printRemoveSuccess(actualName);

        String os = System.getProperty("os.name");
        String expectedOutput = "";

        if (os.contains("Windows")) {
            expectedOutput = "======================================================\r\n" +
                    "Successfully removed queue.\r\n" +
                    "======================================================\r\n";
        } else {
            expectedOutput =  "======================================================\n" +
                    "Successfully removed queue.\n" +
                    "======================================================\n";
        }
        assertEquals(expectedOutput, actualOutput.toString());
    }

    @Test
    void printRemoveFail() {
        ByteArrayOutputStream actualOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(actualOutput));

        Ui ui = new Ui();
        ui.printRemoveFail();

        String os = System.getProperty("os.name");
        String expectedOutput = "";

        if (os.contains("Windows")) {
            expectedOutput = "======================================================\r\n" +
                    "Unsuccessful!\r\n" +
                    "Type 'help c/remove' for assistance on how to remove a CS2040CFile.\r\n" +
                    "======================================================\r\n";
        } else {
            expectedOutput = "======================================================\n" +
                    "Unsuccessful!\n" +
                    "Type 'help c/remove' for assistance on how to remove a CS2040CFile.\n" +
                    "======================================================\n";
        }
        assertEquals(expectedOutput, actualOutput.toString());
    }

    @Test
    void printFilterSuccess() {
        ByteArrayOutputStream actualOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(actualOutput));

        Ui ui = new Ui();
        ui.printFilterSuccess();

        String os = System.getProperty("os.name");
        String expectedOutput = "";

        if (os.contains("Windows")) {
            expectedOutput = "======================================================\r\n" +
                    "Here are the filtered CS2040CFiles:\r\n" +
                    "======================================================\r\n";
        } else {
            expectedOutput = "======================================================\n" +
                    "Here are the filtered CS2040CFiles:\n" +
                    "======================================================\n";
        }
        assertEquals(expectedOutput, actualOutput.toString());
    }

    @Test
    void printFilterFail() {
        ByteArrayOutputStream actualOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(actualOutput));

        Ui ui = new Ui();
        ui.printFilterFail();

        String os = System.getProperty("os.name");
        String expectedOutput = "";

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
        assertEquals(expectedOutput, actualOutput.toString());
    }

    @Test
    void printFilterTopicEmpty() {
        ByteArrayOutputStream actualOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(actualOutput));

        Ui ui = new Ui();
        ui.printFilterTopicEmpty();

        String os = System.getProperty("os.name");
        String expectedOutput = "";

        if (os.contains("Windows")) {
            expectedOutput = "======================================================\r\n" +
                    "There are no files in this topic!\r\n" +
                    "You can add a file to this topic using the add command.\r\n" +
                    "Type 'help c/add' for more information on how to add a CS2040CFile.\r\n" +
                    "======================================================\r\n";
        } else {
            expectedOutput = "======================================================\n" +
                    "There are no files in this topic!\n" +
                    "You can add a file to this topic using the add command.\n" +
                    "Type 'help c/add' for more information on how to add a CS2040CFile.\n" +
                    "======================================================\n";
        }
        assertEquals(expectedOutput, actualOutput.toString());
    }

    @Test
    void printFilterAllTopicsEmpty() {
        ByteArrayOutputStream actualOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(actualOutput));

        Ui ui = new Ui();
        ui.printFilterAllTopicsEmpty();

        String os = System.getProperty("os.name");
        String expectedOutput = "";

        if (os.contains("Windows")) {
            expectedOutput = "======================================================\r\n" +
                    "You have no files!\r\n" +
                    "You can add a file to a specific topic using the add command.\r\n" +
                    "Type 'help c/add' for more information on how to add a CS2040CFile.\r\n" +
                    "======================================================\r\n";
        } else {
            expectedOutput = "======================================================\n" +
                    "You have no files!\n" +
                    "You can add a file to a specific topic using the add command.\n" +
                    "Type 'help c/add' for more information on how to add a CS2040CFile.\n" +
                    "======================================================\n";
        }
        assertEquals(expectedOutput, actualOutput.toString());
    }

    @Test
    void printHelpAdd() {
        ByteArrayOutputStream actualOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(actualOutput));

        Ui ui = new Ui();
        ui.printHelpAdd();

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
        assertEquals(expectedOutput, actualOutput.toString());
    }

    @Test
    void printHelpRemove() {
        ByteArrayOutputStream actualOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(actualOutput));

        Ui ui = new Ui();
        ui.printHelpRemove();

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
        assertEquals(expectedOutput, actualOutput.toString());
    }

    @Test
    void printHelpList() {
        ByteArrayOutputStream actualOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(actualOutput));

        Ui ui = new Ui();
        ui.printHelpList();

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
        assertEquals(expectedOutput, actualOutput.toString());
    }

    @Test
    void printHelpTopoSort() {
        ByteArrayOutputStream actualOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(actualOutput));

        Ui ui = new Ui();
        ui.printHelpTopoSort();

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
        assertEquals(expectedOutput, actualOutput.toString());
    }

    @Test
    void printHelpFilter() {
        ByteArrayOutputStream actualOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(actualOutput));

        Ui ui = new Ui();
        ui.printHelpFilter();

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
        assertEquals(expectedOutput, actualOutput.toString());
    }

    @Test
    void printHelpExport() {
        ByteArrayOutputStream actualOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(actualOutput));

        Ui ui = new Ui();
        ui.printHelpExport();

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
        assertEquals(expectedOutput, actualOutput.toString());
    }

    @Test
    void printHelpExit() {
        ByteArrayOutputStream actualOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(actualOutput));

        Ui ui = new Ui();
        ui.printHelpExit();

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
        assertEquals(expectedOutput, actualOutput.toString());
    }

    @Test
    void printInvalidCommand() {
        ByteArrayOutputStream actualOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(actualOutput));

        Ui ui = new Ui();
        ui.printInvalidCommand();

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
    }

    @Test
     void printInvalidFilterKeyword() {
        ByteArrayOutputStream actualOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(actualOutput));

        Ui ui = new Ui();
        ui.printInvalidFilterKeyword();

        String os = System.getProperty("os.name");
        String expectedOutput = "";

        if (os.contains("Windows")) {
            expectedOutput = "======================================================\r\n" +
                    "Invalid keyword! Only `topic` and `importance` are supported keywords.\r\n" +
                    "Type `help c/filter for more information.\r\n" +
                    "======================================================\r\n";
        } else {
            expectedOutput = "======================================================\n" +
                    "Invalid keyword! Only `topic` and `importance` are supported keywords.\n" +
                    "Type `help c/filter for more information.\n" +
                    "======================================================\n";
        }
        assertEquals(expectedOutput, actualOutput.toString());
    }

    @Test
    void printNameNotFoundCommand() {
        ByteArrayOutputStream actualOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(actualOutput));

        Ui ui = new Ui();
        ui.printNameNotFoundCommand();

        String os = System.getProperty("os.name");
        String expectedOutput = "";

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
        assertEquals(expectedOutput, actualOutput.toString());
    }

    @Test
    void printSaveFail() {
        ByteArrayOutputStream actualOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(actualOutput));

        Ui ui = new Ui();
        ui.printSaveFail();

        String os = System.getProperty("os.name");
        String expectedOutput = "";

        if (os.contains("Windows")) {
            expectedOutput = "======================================================\r\n" +
                    "Saving data was unsuccessful. Please try again.\r\n" +
                    "======================================================\r\n";
        } else {
            expectedOutput = "======================================================\n" +
                    "Saving data was unsuccessful. Please try again.\n" +
                    "======================================================\n";
        }
        assertEquals(expectedOutput, actualOutput.toString());
    }

    @Test
    void printTestModeStart() {
        ByteArrayOutputStream actualOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(actualOutput));

        Ui ui = new Ui();
        ui.printTestModeStart();

        String os = System.getProperty("os.name");
        String expectedOutput = "";

        if (os.contains("Windows")) {
            expectedOutput = "======================================================\r\n" +
                    "Starting test mode.\r\n" +
                    "======================================================\r\n";
        } else {
            expectedOutput = "======================================================\n" +
                    "Starting test mode.\n" +
                    "======================================================\n";
        }
        assertEquals(expectedOutput, actualOutput.toString());
    }

    @Test
    void printTestModeEnd() {
        ByteArrayOutputStream actualOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(actualOutput));

        Ui ui = new Ui();
        ui.printTestModeEnd();

        String os = System.getProperty("os.name");
        String expectedOutput = "";

        if (os.contains("Windows")) {
            expectedOutput = "======================================================\r\n" +
                    "Ending test mode.\r\n" +
                    "======================================================\r\n";
        } else {
            expectedOutput = "======================================================\n" +
                    "Ending test mode.\n" +
                    "======================================================\n";
        }
        assertEquals(expectedOutput, actualOutput.toString());
    }

    @Test
    void printFileWriteError() {
        ByteArrayOutputStream actualOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(actualOutput));

        Ui ui = new Ui();
        ui.printFileWriteError();

        String os = System.getProperty("os.name");
        String expectedOutput = "";

        if (os.contains("Windows")) {
            expectedOutput = "======================================================\r\n" +
                    "File not found.\r\n" +
                    "======================================================\r\n";
        } else {
            expectedOutput = "======================================================\n" +
                    "File not found.\n" +
                    "======================================================\n";
        }
        assertEquals(expectedOutput, actualOutput.toString());
    }

    @Test
    void printFolderCreateError() {
        ByteArrayOutputStream actualOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(actualOutput));

        Ui ui = new Ui();
        ui.printFolderCreateError();

        String os = System.getProperty("os.name");
        String expectedOutput = "";

        if (os.contains("Windows")) {
            expectedOutput = "======================================================\r\n" +
                    "Folder not created.\r\n" +
                    "======================================================\r\n";
        } else {
            expectedOutput = "======================================================\n" +
                    "Folder not created.\n" +
                    "======================================================\n";
        }
        assertEquals(expectedOutput, actualOutput.toString());
    }

    @Test
    void printFileDeleteSuccess() {
        ByteArrayOutputStream actualOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(actualOutput));

        Ui ui = new Ui();
        ui.printFileDeleteSuccess();

        String os = System.getProperty("os.name");
        String expectedOutput = "";

        if (os.contains("Windows")) {
            expectedOutput = "======================================================\r\n" +
                    "Successfully deleted file.\r\n" +
                    "======================================================\r\n";
        } else {
            expectedOutput = "======================================================\n" +
                    "Successfully deleted file.\n" +
                    "======================================================\n";
        }
        assertEquals(expectedOutput, actualOutput.toString());
    }

    @Test
    void printFileDeleteFail() {
        ByteArrayOutputStream actualOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(actualOutput));

        Ui ui = new Ui();
        ui.printFileDeleteFail();

        String os = System.getProperty("os.name");
        String expectedOutput = "";

        if (os.contains("Windows")) {
            expectedOutput = "======================================================\r\n" +
                    "Unsuccessful! Delete failed.\r\n" +
                    "======================================================\r\n";
        } else {
            expectedOutput = "======================================================\n" +
                    "Unsuccessful! Delete failed.\n" +
                    "======================================================\n";
        }
        assertEquals(expectedOutput, actualOutput.toString());
    }

    @Test
    void printInvalidNote() {
        ByteArrayOutputStream actualOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(actualOutput));

        Ui ui = new Ui();
        ui.printInvalidCS2040CFile();

        String os = System.getProperty("os.name");
        String expectedOutput = "";

        if (os.contains("Windows")) {
            expectedOutput = "======================================================\r\n" +
                    "Invalid CS2040CFile.\r\n" +
                    "Type 'help c/add' for how to add a CS2040CFile.\r\n" +
                    "======================================================\r\n";
        } else {
            expectedOutput = "======================================================\n" +
                    "Invalid CS2040CFile.\n" +
                    "Type 'help c/add' for how to add a CS2040CFile.\n" +
                    "======================================================\n";
        }
        assertEquals(expectedOutput, actualOutput.toString());
    }

    @Test
    void printFileDoesNotExist() {
        ByteArrayOutputStream actualOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(actualOutput));

        Ui ui = new Ui();
        ui.printFileDoesNotExist();

        String os = System.getProperty("os.name");
        String expectedOutput = "";

        if (os.contains("Windows")) {
            expectedOutput = "======================================================\r\n" +
                    "This file does not exist.\r\n" +
                    "Please add the file into the folder and try again.\r\n" +
                    "======================================================\r\n";
        } else {
            expectedOutput = "======================================================\n" +
                    "This file does not exist.\n" +
                    "Please add the file into the folder and try again.\n" +
                    "======================================================\n";
        }
        assertEquals(expectedOutput, actualOutput.toString());
    }

    @Test
    void printTopoSortSuccess() {
        ByteArrayOutputStream actualOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(actualOutput));

        Ui ui = new Ui();
        ui.printTopoSortSuccess();

        String os = System.getProperty("os.name");
        String expectedOutput = "";

        if (os.contains("Windows")) {
            expectedOutput = "======================================================\r\n" +
                    "Here are the topologically sorted CS2040CFiles:\r\n" +
                    "======================================================\r\n";
        } else {
            expectedOutput = "======================================================\n" +
                    "Here are the topologically sorted CS2040CFiles:\n" +
                    "======================================================\n";
        }
        assertEquals(expectedOutput, actualOutput.toString());
    }

    @Test
    void printNoCS2040CFilesSaved() {
        ByteArrayOutputStream actualOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(actualOutput));

        Ui ui = new Ui();
        ui.printNoCS2040CFilesSaved();

        String os = System.getProperty("os.name");
        String expectedOutput = "";

        if (os.contains("Windows")) {
            expectedOutput = "======================================================\r\n" +
                    "You have no CS2040CFiles at the moment.\r\n" +
                    "======================================================\r\n";
        } else {
            expectedOutput = "======================================================\n" +
                    "You have no CS2040CFiles at the moment.\n" +
                    "======================================================\n";
        }
        assertEquals(expectedOutput, actualOutput.toString());
    }

    @Test
    void printFileMissing() {
        ByteArrayOutputStream actualOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(actualOutput));

        Ui ui = new Ui();
        ui.printFileMissing();

        String os = System.getProperty("os.name");
        String expectedOutput = "";

        if (os.contains("Windows")) {
            expectedOutput = "======================================================\r\n" +
                    "File missing from root directory.\r\n" +
                    "======================================================\r\n";
        } else {
            expectedOutput = "======================================================\n" +
                    "File missing from root directory.\n" +
                    "======================================================\n";
        }
        assertEquals(expectedOutput, actualOutput.toString());
    }

    @Test
    void printBufferEmpty() {
        ByteArrayOutputStream actualOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(actualOutput));

        Ui ui = new Ui();
        ui.printBufferEmpty();

        String os = System.getProperty("os.name");
        String expectedOutput = "";

        if (os.contains("Windows")) {
            expectedOutput = "======================================================\r\n" +
                    "You have no CS2040CFiles in the buffer.\r\n" +
                    "======================================================\r\n";
        } else {
            expectedOutput = "======================================================\n" +
                    "You have no CS2040CFiles in the buffer.\n" +
                    "======================================================\n";
        }
        assertEquals(expectedOutput, actualOutput.toString());
    }

    @Test
    void printInvalidImportance() {
        ByteArrayOutputStream actualOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(actualOutput));

        Ui ui = new Ui();
        String importance = "INVALID_IMPORTANCE";
        ui.printInvalidImportance(importance);

        String os = System.getProperty("os.name");
        String expectedOutput = "";

        if (os.contains("Windows")) {
            expectedOutput = "======================================================\r\n" +
                    "INVALID_IMPORTANCE is not in the valid 1-10 range for importance.\r\n" +
                    "======================================================\r\n";
        } else {
            expectedOutput = "======================================================\n" +
                    "INVALID_IMPORTANCE is not in the valid 1-10 range for importance.\n" +
                    "======================================================\n";
        }
        assertEquals(expectedOutput, actualOutput.toString());
    }

    @Test
    void printListOfCS2040CFilesWithGivenIndex() {
        ByteArrayOutputStream actualOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(actualOutput));

        int startingSerialNumber = 1;
        ArrayList<String> notes = new ArrayList<>(Arrays.asList("NotesA", "NotesB", "NotesC"));

        Ui ui = new Ui();
        int actualSerialNumber;
        int expectedSerialNumber = notes.size() + 1;
        actualSerialNumber = ui.printListOfCS2040CFilesWithGivenIndex(notes, startingSerialNumber);

        String os = System.getProperty("os.name");
        String expectedOutput = "";

        if (os.contains("Windows")) {
            expectedOutput = "1. NotesA\r\n" +
                    "2. NotesB\r\n" +
                    "3. NotesC\r\n";
        } else {
            expectedOutput = "1. NotesA\n" +
                    "2. NotesB\n" +
                    "3. NotesC\n";
        }
        assertEquals(expectedOutput, actualOutput.toString());
        assertEquals(expectedSerialNumber, actualSerialNumber);
    }

    @Test
    void printHelpPrompt() {
        ByteArrayOutputStream actualOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(actualOutput));

        Ui ui = new Ui();
        ui.printHelpPrompt();

        String os = System.getProperty("os.name");
        String expectedOutput = "";

        if (os.contains("Windows")) {
            expectedOutput = "======================================================\r\n" +
                    "The extension entered after `c/` is not supported.\r\n" +
                    "Type `help` for a list of supported help extensions.\r\n" +
                    "======================================================\r\n";
        } else {
            expectedOutput = "======================================================\n" +
                    "The extension entered after `c/` is not supported.\n" +
                    "Type `help` for a list of supported help extensions.\n" +
                    "======================================================\n";
        }
        assertEquals(expectedOutput, actualOutput.toString());
    }

    @Test
    void printReadFail() {
        ByteArrayOutputStream actualOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(actualOutput));

        Ui ui = new Ui();
        ui.printReadFail();

        String os = System.getProperty("os.name");
        String expectedOutput = "";

        if (os.contains("Windows")) {
            expectedOutput = "======================================================\r\n" +
                    "It seems that there is an error reading in your input.\r\n" +
                    "Please restart the application.\r\n" +
                    "======================================================\r\n";
        } else {
            expectedOutput = "======================================================\n" +
                    "It seems that there is an error reading in your input.\n" +
                    "Please restart the application.\n" +
                    "======================================================\n";
        }
        assertEquals(expectedOutput, actualOutput.toString());
    }

    @Test
    void printTestModeEndFail() {
        ByteArrayOutputStream actualOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(actualOutput));

        Ui ui = new Ui();
        ui.printTestModeEndFail();

        String os = System.getProperty("os.name");
        String expectedOutput = "";

        if (os.contains("Windows")) {
            expectedOutput = "======================================================\r\n" +
                    "Unsuccessful, test mode has not been started.\r\n" +
                    "======================================================\r\n";
        } else {
            expectedOutput = "======================================================\n" +
                    "Unsuccessful, test mode has not been started.\n" +
                    "======================================================\n";
        }
        assertEquals(expectedOutput, actualOutput.toString());
    }

    @Test
    void printExportSuccess() {
        ByteArrayOutputStream actualOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(actualOutput));

        Ui ui = new Ui();
        ui.printExportSuccess();

        String os = System.getProperty("os.name");
        String expectedOutput = "";

        if (os.contains("Windows")) {
            expectedOutput = "======================================================\r\n" +
                    "Successfully exported file(s).\r\n" +
                    "======================================================\r\n";
        } else {
            expectedOutput = "======================================================\n" +
                    "Successfully exported file(s).\n" +
                    "======================================================\n";
        }
        assertEquals(expectedOutput, actualOutput.toString());
    }

    @Test
    void printWithBox() {
        ByteArrayOutputStream actualOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(actualOutput));

        Ui ui = new Ui();
        String topicName = "dummy topicName";
        ui.printWithBox(topicName);

        String os = System.getProperty("os.name");
        String expectedOutput = "";

        if (os.contains("Windows")) {
            expectedOutput = "[" + topicName + "]\r\n";
        } else {
            expectedOutput = "[" + topicName + "]\n";
        }
        assertEquals(expectedOutput, actualOutput.toString());
    }

    @Test
    void printListOfCS2040CFiles() {
        ByteArrayOutputStream actualOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(actualOutput));

        ArrayList<String> notes = new ArrayList<>(Arrays.asList("NotesA", "NotesB", "NotesC"));

        Ui ui = new Ui();
        ui.printListOfCS2040CFiles(notes);

        String os = System.getProperty("os.name");
        String expectedOutput = "";

        if (os.contains("Windows")) {
            expectedOutput = "1. NotesA\r\n" +
                    "2. NotesB\r\n" +
                    "3. NotesC\r\n";
        } else {
            expectedOutput = "1. NotesA\n" +
                    "2. NotesB\n" +
                    "3. NotesC\n";
        }
        assertEquals(expectedOutput, actualOutput.toString());
    }

    @Test
    void printOpenFolderNotSupported() {
        ByteArrayOutputStream actualOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(actualOutput));

        Ui ui = new Ui();
        ui.printOpenFolderNotSupported();

        String os = System.getProperty("os.name");
        String expectedOutput = "";

        if (os.contains("Windows")) {
            expectedOutput = "======================================================\r\n" +
                    "Your OS does not support `export`.\r\n" +
                    "Try opening the folder manually.\r\n" +
                    "======================================================\r\n";
        } else {
            expectedOutput = "======================================================\n" +
                    "Your OS does not support `export`.\n" +
                    "Try opening the folder manually.\n" +
                    "======================================================\n";
        }
        assertEquals(expectedOutput, actualOutput.toString());
    }

    @Test
    void printFolderMissing() {
        ByteArrayOutputStream actualOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(actualOutput));

        Ui ui = new Ui();
        ui.printFolderMissing();

        String os = System.getProperty("os.name");
        String expectedOutput = "";

        if (os.contains("Windows")) {
            expectedOutput = "======================================================\r\n" +
                    "The export folder seems to be missing.\r\n" +
                    "The export folder has been recreated.\r\n" +
                    "Try the `export` command again.\r\n" +
                    "======================================================\r\n";
        } else {
            expectedOutput = "======================================================\n" +
                    "The export folder seems to be missing.\n" +
                    "The export folder has been recreated.\n" +
                    "Try the `export` command again.\n" +
                    "======================================================\n";
        }
        assertEquals(expectedOutput, actualOutput.toString());
    }

    @Test
    void printCorruptedFileDiscarded() {
        ByteArrayOutputStream actualOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(actualOutput));

        Ui ui = new Ui();
        int corruptCount = 3;
        String name = "dummy name";
        ui.printCorruptedFileDiscarded(corruptCount, name);

        String os = System.getProperty("os.name");
        String expectedOutput = "";

        if (os.contains("Windows")) {
            expectedOutput = "======================================================\r\n" +
                    corruptCount + " corrupted entries detected in " + name + ".txt.\r\n" +
                    "Note that the corrupted entries are discarded!\r\n" +
                    "======================================================\r\n";
        } else {
            expectedOutput = "======================================================\n" +
                    corruptCount + " corrupted entries detected in " + name + ".txt.\n" +
                    "Note that the corrupted entries are discarded!\n" +
                    "======================================================\n";
        }
        assertEquals(expectedOutput, actualOutput.toString());
    }

    @Test
    void printDeleteSuccess() {
        ByteArrayOutputStream actualOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(actualOutput));

        Ui ui = new Ui();
        ui.printDeleteSuccess();

        String os = System.getProperty("os.name");
        String expectedOutput = "";

        if (os.contains("Windows")) {
            expectedOutput = "Delete successful.\r\n";
        } else {
            expectedOutput = "Delete successful.\n";
        }
        assertEquals(expectedOutput, actualOutput.toString());
    }

    @Test
    void printDeleteFail() {
        ByteArrayOutputStream actualOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(actualOutput));

        Ui ui = new Ui();
        ui.printDeleteFail();

        String os = System.getProperty("os.name");
        String expectedOutput = "";

        if (os.contains("Windows")) {
            expectedOutput = "Delete failed.\r\n";
        } else {
            expectedOutput = "Delete failed.\n";
        }
        assertEquals(expectedOutput, actualOutput.toString());
    }

    @Test
    void getUserInput_withEmptyInput() {
        String expected = "exit";
        InputStream in = new ByteArrayInputStream(expected.getBytes());
        System.setIn(in);
        Ui ui = new Ui();

        String actual = ui.getUserInput();

        assertEquals(expected, actual);
    }

    @Test
    void getUserInput_withValidInput() {
        String expected = "Hello Duke!";
        InputStream in = new ByteArrayInputStream(expected.getBytes());
        System.setIn(in);
        Ui ui = new Ui();

        String actual = ui.getUserInput();

        assertEquals(expected, actual);
    }

    @Test
    void getUserInput_withException() {
        String expected = "exit";
        InputStream in = new ByteArrayInputStream("".getBytes());
        System.setIn(in);
        Ui ui = new Ui();

        String actual = ui.getUserInput();

        assertEquals(expected, actual);
    }
}
