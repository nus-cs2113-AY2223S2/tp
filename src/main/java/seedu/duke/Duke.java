package seedu.duke;

import seedu.duke.command.Command;
import seedu.duke.command.CommandParser;
import seedu.duke.exception.ToDoListException;
import seedu.duke.task.TaskList;
import seedu.duke.ui.Ui;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Duke {
    private static boolean isInUse = true;
    private static final String DEFAULT_SAVE_FILE = "./data.txt";

    public static void main(String[] args) {
        Ui ui = new Ui();
        TaskList taskList = new TaskList();
        CommandParser parser = new CommandParser();
        try {
            taskList = Storage.loadData(DEFAULT_SAVE_FILE, ui);
        } catch (FileNotFoundException e) {
            ui.printFileNotFoundMessage();
        } catch (NullPointerException e) {
            ui.printNullFilepathErrorMessage(); // something went wrong with filename given, we got null as filename
        } catch (IOException e) {
            ui.printLoadingErrorMessage(); // something went wrong while accessing your file
        } catch (ClassNotFoundException e) {
            ui.printClassNotFoundErrorMessage(); // something went wrong while looking for a class - not user issue
        }

        ui.printWelcomeMessage(taskList);
        try (Scanner in = new Scanner(System.in)) {
            while (isInUse) {
                try {
                    String userInput = in.nextLine();
                    Command parsedCommand = parser.parseCommand(userInput);
                    parsedCommand.execute(taskList, ui);
                    Storage.saveData(DEFAULT_SAVE_FILE, taskList, ui);
                    isInUse = !parsedCommand.isExit();
                } catch (IOException e) {
                    ui.printSavingErrorMessage();
                } catch (NullPointerException e) {
                    ui.printNullFilepathErrorMessage();
                } catch (ToDoListException e) {
                    ui.printError(e);
                }
            }
        }
    }
}

