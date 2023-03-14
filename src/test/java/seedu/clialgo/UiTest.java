package seedu.clialgo;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UiTest {
    @Test
    void printWelcomeMessage() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

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
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    void printExitMessage() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

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
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    void printHelpPage() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Ui ui = new Ui();
        ui.printHelpPage();

        String os = System.getProperty("os.name");
        String expectedOutput = "";

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
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    void printAddSuccess() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Ui ui = new Ui();
        String name = "queue";
        String topic = "LINKED_LIST";
        ui.printAddSuccess(name, topic);

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
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    void printAddFail() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Ui ui = new Ui();
        String topic = "FENWICK_TREE";
        ui.printAddFail(topic);

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
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    void printListSuccess() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Ui ui = new Ui();
        ui.printListSuccess();

        String os = System.getProperty("os.name");
        String expectedOutput = "";

        if (os.contains("Windows")) {
            expectedOutput = "======================================================\r\n" +
                    "Here are all your notes:\r\n" +
                    "======================================================\r\n";
        } else {
            expectedOutput = "======================================================\n" +
                    "Here are all your notes:\n" +
                    "======================================================\n";
        }
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    void printListFail() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Ui ui = new Ui();
        ui.printListFail();

        String os = System.getProperty("os.name");
        String expectedOutput = "";

        if (os.contains("Windows")) {
            expectedOutput = "======================================================\r\n" +
                    "You have no notes!\r\n" +
                    "Type 'help c/add' for assistance on how to add a note.\r\n" +
                    "======================================================\r\n";
        } else {
            expectedOutput =  "======================================================\n" +
                    "You have no notes!\n" +
                    "Type 'help c/add' for assistance on how to add a note.\n" +
                    "======================================================\n";
        }
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    void printRemoveSuccess() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Ui ui = new Ui();
        String name = "queue";
        ui.printRemoveSuccess(name);

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
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    void printRemoveFail() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Ui ui = new Ui();
        ui.printRemoveFail();

        String os = System.getProperty("os.name");
        String expectedOutput = "";

        if (os.contains("Windows")) {
            expectedOutput = "======================================================\r\n" +
                    "Unsuccessful!\r\n" +
                    "Type 'help c/remove' for assistance on how to remove a note.\r\n" +
                    "======================================================\r\n";
        } else {
            expectedOutput = "======================================================\n" +
                    "Unsuccessful!\n" +
                    "Type 'help c/remove' for assistance on how to remove a note.\n" +
                    "======================================================\n";
        }
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    void printFilterSuccess() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Ui ui = new Ui();
        ui.printFilterSuccess();

        String os = System.getProperty("os.name");
        String expectedOutput = "";

        if (os.contains("Windows")) {
            expectedOutput = "======================================================\r\n" +
                    "Here are the filtered notes:\r\n" +
                    "======================================================\r\n";
        } else {
            expectedOutput = "======================================================\n" +
                    "Here are the filtered notes:\n" +
                    "======================================================\n";
        }
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    void printFilterFail() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

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
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    void printHelpAdd() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Ui ui = new Ui();
        ui.printHelpAdd();

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
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    void printHelpRemove() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Ui ui = new Ui();
        ui.printHelpRemove();

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
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    void printHelpList() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Ui ui = new Ui();
        ui.printHelpList();

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
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    void printHelpFilter() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Ui ui = new Ui();
        ui.printHelpFilter();

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
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    void printHelpExit() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Ui ui = new Ui();
        ui.printHelpExit();

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
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    void printInvalidCommand() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

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
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    void printNameNotFoundCommand() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Ui ui = new Ui();
        ui.printNameNotFoundCommand();

        String os = System.getProperty("os.name");
        String expectedOutput = "";

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
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    void printTestModeStart() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

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
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    void printTestModeEnd() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

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
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    void printFileWriteError() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Ui ui = new Ui();
        ui.printFileWriteError();

        String os = System.getProperty("os.name");
        String expectedOutput = "";

        if (os.contains("Windows")) {
            expectedOutput = "======================================================\r\n" +
                    "File write error.\r\n" +
                    "======================================================\r\n";
        } else {
            expectedOutput = "======================================================\n" +
                    "File write error.\n" +
                    "======================================================\n";
        }
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    void printFolderCreateError() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

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
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    void printFileDeleteSuccess() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

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
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    void printFileDeleteFail() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

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
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    void printInvalidNote() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Ui ui = new Ui();
        ui.printInvalidNote();

        String os = System.getProperty("os.name");
        String expectedOutput = "";

        if (os.contains("Windows")) {
            expectedOutput = "======================================================\r\n" +
                    "Invalid note.\r\n" +
                    "Type 'help c/add' for how to add a note.\r\n" +
                    "======================================================\r\n";
        } else {
            expectedOutput = "======================================================\n" +
                    "Invalid note.\n" +
                    "Type 'help c/add' for how to add a note.\n" +
                    "======================================================\n";
        }
        assertEquals(expectedOutput, outContent.toString());
    }
}
