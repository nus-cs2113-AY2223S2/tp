package seedu.clialgo;

import seedu.clialgo.file.CS2040CFile;
import seedu.clialgo.file.Code;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

//@@author nikkiDEEE
/** Text UI of the application. */
public class Ui {

    private static final String DIVIDER = "======================================================";
    private static final String EXIT_COMMAND = "exit";
    private static final String NOTE_LABEL = "[NOTE] ";
    private static final String CODE_LABEL = "[CODE] ";
    private static final int SERIAL_NUMBER_START = 1;
    private final Scanner in;

    /** Ui constructor */
    public Ui() {
        in = new Scanner(System.in);
    }

    /**
     * Returns user input.
     *
     * @return userInput A String input by the user
     */
    public String getUserInput() {
        String fullUserInput;
        try {
            fullUserInput = in.nextLine();
        } catch (NoSuchElementException | IllegalStateException e) {
            printReadFail();
            return EXIT_COMMAND;
        }
        return fullUserInput;
    }

    /**
     * Prints a divider.
     */
    public void printDivider() {
        System.out.println(DIVIDER);
    }

    /** Prints the intro message when application starts. */
    public void printWelcomeMessage() {
        printDivider();
        System.out.println("Hello! Welcome to CLIAlgo Notes!");
        printDivider();
    }

    /** Prints the outro message when the application is closed. */
    public void printExitMessage() {
        printDivider();
        System.out.println("Thank you for using CLIAlgo! Study hard!");
        printDivider();
    }

    /** Prints message informing the user that there is an error reading in the input */
    public void printReadFail() {
        printDivider();
        System.out.println("It seems that there is an error reading in your input.");
        System.out.println("Please restart the application.");
        printDivider();
    }

    /**
     * Prints a list of valid (available) user commands.
     * To be called when userInput.equals("help").
     */
    public void printHelpPage() {
        printDivider();
        System.out.println("The available COMMAND_TYPE(s) are:");
        System.out.println();
        System.out.println("[add]: add CS2040CFile");
        System.out.println("[remove]: remove CS2040CFile");
        System.out.println("[list]: displays all CS2040CFiles");
        System.out.println("[filter]: filters CS2040CFiles by topic");
        System.out.println("[topo]: displays all CS2040CFiles before the selected topic");
        System.out.println("[export]: places CS2040CFiles sorted by filter/topo in a file");
        System.out.println("[exit]: close the application");
        System.out.println();
        System.out.println("For more help on a specific command, type `help c/COMMAND_TYPE`.");
        printDivider();
    }

    /**
     * Prints a message indicating successful addition of the CS2040CFile to the tagged topic in the list.
     *
     * @param name the name of the CS2040CFile defined by the user.
     * @param topic the CS2040C topic.
     */
    public void printAddSuccess(String name, String topic) {
        assert name != null;
        assert topic != null;
        printDivider();
        System.out.println("Successfully added " + name + " into " + topic + ".");
        printDivider();
    }

    /**
     * Prints a message indicating the failure to add CS2040CFile to a topic (which does not exist in CS2040C).
     *
     * @param topic the CS2040C topic.
     */
    public void printAddFail(String topic) {
        assert topic != null;
        printDivider();
        System.out.println("Unsuccessful! " + topic + " is not a topic in CS2040C.");
        System.out.println("Type 'help c/add' for assistance.");
        printDivider();
    }

    /**
     * Prints an error message indicating that a CS2040CFile of a specific name already exists.
     */
    public void printCS2040CFileExists() {
        printDivider();
        System.out.println("Unsuccessful! A CS2040CFile with that name already exists.");
        System.out.println("Type 'list' to view the list of CS2040CFiles.");
        printDivider();
    }

    /** Prints the list of CS2040CFiles saved by the user. */
    public void printListSuccess() {
        printDivider();
        System.out.println("Here are all your CS2040CFiles:");
        printDivider();
    }

    /** Prints an error message if user has no saved CS2040CFiles. */
    public void printListFail() {
        printDivider();
        System.out.println("You have no CS2040CFiles!");
        System.out.println("Type 'help c/add' for assistance on how to add a CS2040CFile.");
        printDivider();
    }

    /**
     * Prints a message indicating successful purge of a user-defined CS2040CFile.
     *
     * @param name the name of the CS2040CFile defined by the user.
     */
    public void printRemoveSuccess(String name) {
        assert name != null;
        printDivider();
        System.out.println("Successfully removed " + name + ".");
        printDivider();
    }

    /** Prints an error message if user has not used appropriate flags/no CS2040CFiles exist of the specific name. */
    public void printRemoveFail() {
        printDivider();
        System.out.println("Unsuccessful!");
        System.out.println("Type 'help c/remove' for assistance on how to remove a CS2040CFile.");
        printDivider();
    }

    /** Prints a filtered set of CS2040CFiles user-specified by keyword and topic name. */
    public void printFilterSuccess() {
        printDivider();
        System.out.println("Here are the filtered CS2040CFiles:");
        printDivider();
    }

    /** Prints a filtered set of CS2040CFiles user-specified by keyword and topic name. */
    public void printFilterFail() {
        printDivider();
        System.out.println("Unsuccessful!");
        System.out.println("Type 'help c/filter' for assistance.");
        printDivider();
    }

    /** Prints feedback to user that there are no notes to be filtered. */
    public void printFilterTopicEmpty() {
        printDivider();
        System.out.println("There are no files in this topic!");
        System.out.println("You can add a file to this topic using the add command.");
        System.out.println("Type 'help c/add' for more information on how to add a CS2040CFile.");
        printDivider();
    }

    /** Prints feedback to user that there are no notes to be filtered. */
    public void printFilterAllTopicsEmpty() {
        printDivider();
        System.out.println("You have no files!");
        System.out.println("You can add a file to a specific topic using the add command.");
        System.out.println("Type 'help c/add' for more information on how to add a CS2040CFile.");
        printDivider();
    }

    /**
     * Prints the requirements for using the 'add' command.
     * To be called when userInput.equals("help c/add").
     */
    public void printHelpAdd() {
        printDivider();
        System.out.println("Add a CS2040CFile to a topic using:");
        System.out.println();
        System.out.println("    `add n/NAME t/TOPIC [i/IMPORTANCE]`");
        System.out.println();
        System.out.println("NAME: String name of the CS2040CFile file.");
        System.out.println("TOPIC: String topic that NAME will be tagged to.");
        System.out.println("IMPORTANCE: int level of importance on a scale of 1-10 (optional field).");
        System.out.println();
        System.out.println("Valid TOPIC's are 'SORTING', 'LINKED_LIST', 'GRAPH_STRUCTURES',");
        System.out.println("'BINARY_HEAP', 'HASH_TABLE', 'GRAPH_TRAVERSAL', 'BINARY_SEARCH_TREE',");
        System.out.println("'SS_SHORTEST_PATH', 'UNION_FIND_DS' and 'MINIMUM_SPANNING_TREE'.");
        printDivider();
    }

    /**
     * Prints the requirements for using the 'remove' command.
     * To be called when userInput.equals("help c/remove").
     */
    public void printHelpRemove() {
        printDivider();
        System.out.println("Remove a CS2040CFile using:");
        System.out.println();
        System.out.println("    `remove n/NAME`");
        System.out.println();
        System.out.println("NAME: String name of the CS2040CFile file.");
        printDivider();
    }

    /**
     * Prints the requirements for using the 'list' command.
     * To be called when userInput.equals("help c/list").
     */
    public void printHelpList() {
        printDivider();
        System.out.println("List all stored CS2040CFiles using:");
        System.out.println();
        System.out.println("    `list`");
        System.out.println();
        System.out.println("Command should only contain one word (i.e. no extensions).");
        printDivider();
    }

    /**
     * Prints the requirements for using the 'topo' command.
     * To be called when userInput.equals("help c/topo").
     */
    public void printHelpTopoSort() {
        printDivider();
        System.out.println("Print all files before the user-defined filename using:");
        System.out.println();
        System.out.println("    `topo n/NAME`");
        System.out.println();
        System.out.println("NAME: String name of the CS2040CFile file.");
        printDivider();
    }

    /**
     * Prints the requirements for using the 'filter' command.
     * To be called when userInput.equals("help c/filter").
     */
    public void printHelpFilter() {
        printDivider();
        System.out.println("Filter by topic/importance and topic name using:");
        System.out.println();
        System.out.println("    `filter k/KEYWORD [t/TOPIC]`");
        System.out.println();
        System.out.println("KEYWORD: String keyword has to be either `topic` or `importance`.");
        System.out.println("TOPIC: String topic chosen from list below (optional field).");
        System.out.println();
        System.out.println("Valid TOPIC's are 'SORTING', 'LINKED_LIST', 'GRAPH_STRUCTURES',");
        System.out.println("'BINARY_HEAP', 'HASH_TABLE', 'GRAPH_TRAVERSAL', 'BINARY_SEARCH_TREE',");
        System.out.println("'SS_SHORTEST_PATH', 'UNION_FIND_DS' and 'MINIMUM_SPANNING_TREE'.");
        printDivider();
    }

    /**
     * Prints the requirements for using the 'filter' command.
     * To be called when userInput.equals("help c/filter").
     */
    public void printHelpExport() {
        printDivider();
        System.out.println("Export your filter/topo sorted files using:");
        System.out.println();
        System.out.println("    `export`");
        System.out.println();
        System.out.println("Command should only contain one word (i.e. no extensions).");
        printDivider();
    }

    /**
     * Prints the requirements for using the 'exit' command.
     * To be called when userInput.equals("help c/exit").
     */
    public void printHelpExit() {
        printDivider();
        System.out.println("Exits CLIAlgo using:");
        System.out.println();
        System.out.println("    `exit`");
        System.out.println();
        System.out.println("Command should only contain one word (i.e. no extensions).");
        printDivider();
    }

    /** Prints an error message indicating invalid user-input. */
    public void printInvalidCommand() {
        printDivider();
        System.out.println("This is an invalid command, please ensure all your fields are correct.");
        System.out.println("Type 'help' for additional assistance.");
        printDivider();
    }

    /** Close the System.in scanner. */
    public void closeScanner() {
        this.in.close();
    }

    /** Prints an error message when the user tries to remove a file whose name does not exist. */
    public void printNameNotFoundCommand() {
        printDivider();
        System.out.println("Unsuccessful! A CS2040CFile of that name does not exist.");
        System.out.println("Only CS2040CFiles in your list can be removed.");
        System.out.println("Type 'list' to see CS2040CFiles you can remove.");
        printDivider();
    }

    /** Prints an error message when saving data is unsuccessful. */
    public void printSaveFail() {
        printDivider();
        System.out.println("Saving data was unsuccessful. Please try again.");
        printDivider();
    }

    /** Prints a message indicating the start of test mode. */
    public void printTestModeStart() {
        printDivider();
        System.out.println("Starting test mode.");
        printDivider();
    }

    /** Prints a message indicating the end of test mode. */
    public void printTestModeEnd() {
        printDivider();
        System.out.println("Ending test mode.");
        printDivider();
    }

    /** Prints an error message when test mode exit failed. */
    public void printTestModeEndFail() {
        printDivider();
        System.out.println("Unsuccessful, test mode has not been started.");
        printDivider();
    }

    /** Prints an error message indicating failure to write to file. */
    public void printFileWriteError() {
        printDivider();
        System.out.println("File not found.");
        printDivider();
    }

    /** Prints an error message indicating failure to create folder. */
    public void printFolderCreateError() {
        printDivider();
        System.out.println("Folder not created.");
        printDivider();
    }

    /** Prints an error message indicating file delete success. */
    public void printFileDeleteSuccess() {
        printDivider();
        System.out.println("Successfully deleted file.");
        printDivider();
    }

    /** Prints an error message indicating failure to delete the file. */
    public void printFileDeleteFail() {
        printDivider();
        System.out.println("Unsuccessful! Delete failed.");
        printDivider();
    }

    /** Prints an error message indicating that CS2040CFile is invalid. */
    public void printInvalidCS2040CFile() {
        printDivider();
        System.out.println("Invalid CS2040CFile.");
        System.out.println("Type 'help c/add' for how to add a CS2040CFile.");
        printDivider();
    }

    /** Prints an error message to inform the user that the file does not exist. */
    public void printFileDoesNotExist() {
        printDivider();
        System.out.println("This file does not exist.");
        System.out.println("Please add the file into the folder and try again.");
        printDivider();
    }

    /** Prints the header statement when toposort is successful. */
    public void printTopoSortSuccess() {
        printDivider();
        System.out.println("Here are the topologically sorted CS2040CFiles:");
        printDivider();
    }

    /** Prints an error message indicating that no CS2040CFiles are in CLIAlgo. */
    public void printNoCS2040CFilesSaved() {
        printDivider();
        System.out.println("You have no CS2040CFiles at the moment.");
        printDivider();
    }

    /** Prints a message informing the user that the file is missing from the root directory. */
    public void printFileMissing() {
        printDivider();
        System.out.println("File missing from root directory.");
        printDivider();
    }

    /** Prints a message informing the user that the Buffer is empty. */
    public void printBufferEmpty() {
        printDivider();
        System.out.println("You have no CS2040CFiles in the buffer.");
        printDivider();
    }

    /** Prints a message informing the user that the export operation was successful. */
    public void printExportSuccess() {
        printDivider();
        System.out.println("Successfully exported file(s).");
        printDivider();
    }

    /** Prints an error message indicating the input in the importance field is in the invalid range. */
    public void printInvalidImportance(String importance) {
        printDivider();
        System.out.println(importance + " is not in the valid 1-10 range for importance.");
        printDivider();
    }

    /** Prints a message to the user if the keyword entered after the `k/` field is neither topic nor importance. */
    public void printInvalidFilterKeyword() {
        printDivider();
        System.out.println("Invalid keyword! Only `topic` and `importance` are supported keywords.");
        System.out.println("Type `help c/filter for more information.");
        printDivider();
    }

    /** Prints a message to the user if the keyword entered after the `c/` field is not a valid help extension. */
    public void printHelpPrompt() {
        printDivider();
        System.out.println("The extension entered after `c/` is not supported.");
        System.out.println("Type `help` for a list of supported help extensions.");
        printDivider();
    }

    public void printAllFilesWithImportance(ArrayList<CS2040CFile> cs2040cFiles) {
        int serialNumber = SERIAL_NUMBER_START;
        for (CS2040CFile cs2040cFile : cs2040cFiles) {
            String cs2040cFileType = NOTE_LABEL;
            if (cs2040cFile instanceof Code) {
                cs2040cFileType = CODE_LABEL;
            }
            System.out.println(serialNumber + ". " + cs2040cFileType + cs2040cFile.getName()
                    + " [" + cs2040cFile.getImportance() + "]");
            ++serialNumber;
        }
        printDivider();
    }
    //@@author

    //@@author heejet
    /**
     * This method prints out the CS2040CFiles in the ArrayList provided to the user.
     *
     * @param cs2040cFiles An ArrayList containing the names and labels of all CS2040C files to be printed
     */
    public void printListOfCS2040CFiles(ArrayList<String> cs2040cFiles) {
        assert !cs2040cFiles.isEmpty();
        int serialNumber = SERIAL_NUMBER_START;
        for (String cs2040cFile : cs2040cFiles) {
            System.out.println(serialNumber + ". " + cs2040cFile);
            serialNumber++;
        }
    }

    /** Prints the topic name enclosed with brackets. */
    public void printWithBox(String topicName) {
        System.out.println("[" + topicName + "]");
    }

    //@@author ong-ck
    /**
     * Prints the CS2040CFiles from a provided ArrayList with the index continuing from a given serial number.
     *
     * @param cs2040cFiles An ArrayList containing the names and labels of all CS2040C files to be printed
     * @param currentSerialNumber The serial number of the note for the index to start printing from.
     * @return The serial number of the last note printed.
     */
    public int printListOfCS2040CFilesWithGivenIndex(ArrayList<String> cs2040cFiles, int currentSerialNumber) {
        assert !cs2040cFiles.isEmpty();
        assert currentSerialNumber >= SERIAL_NUMBER_START;
        int serialNumber = currentSerialNumber;
        for (String cs2040cFile : cs2040cFiles) {
            System.out.println(serialNumber + ". " + cs2040cFile);
            serialNumber++;
        }
        return serialNumber;
    }
    //@@author

    public void printOpenFolderNotSupported () {
        printDivider();
        System.out.println("Your OS does not support `export`.");
        System.out.println("Try opening the folder manually.");
        printDivider();
    }

    public void printFolderMissing () {
        printDivider();
        System.out.println("The export folder seems to be missing.");
        System.out.println("The export folder has been recreated.");
        System.out.println("Try the `export` command again.");
        printDivider();
    }

    public void printCorruptedFileDiscarded(int corruptCount, String name) {
        printDivider();
        if (corruptCount == 1) {
            System.out.println(corruptCount + " corrupted save entry detected in " + name + ".txt.");
        } else {
            System.out.println(corruptCount + " corrupted entries detected in " + name + ".txt.");
        }
        System.out.println("Note that the corrupted entries are discarded!");
        printDivider();
    }

    public static void printDeleteSuccess() {
        System.out.println("Delete successful.");
    }

    public static void printDeleteFail() {
        System.out.println("Delete failed.");
    }

    public void printSecurityDenied() {
        printDivider();
        System.out.println("Permissions denied by security manager!");
        System.out.println("Check your security manager!");
        printDivider();
    }

    public static void printStaticSecurityDenied() {
        System.out.println("Permissions denied by security manager!");
        System.out.println("Check your security manager!");
    }
}
