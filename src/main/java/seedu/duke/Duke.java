package seedu.duke;

import seedu.duke.command.Command;
import seedu.duke.command.CommandParser;
import seedu.duke.exception.FailedLoadException;
import seedu.duke.exception.FailedSaveException;
import seedu.duke.exception.ToDoListException;
import seedu.duke.task.TaskList;
import seedu.duke.ui.Ui;

import java.util.Scanner;

public class Duke {
    private static boolean isInUse = true;
    private static final String DEFAULT_SAVE_FILE = "./data.txt";

    public static void main(String[] args) {
        Ui ui = new Ui();
        Storage storage = new Storage();
        TaskList taskList = new TaskList();
        CommandParser parser = new CommandParser();
        ui.printWelcomeMessage();
        try {
            taskList = storage.loadData(DEFAULT_SAVE_FILE, ui);
        } catch (FailedLoadException e) {
            ui.printError(e);
        }
        ui.listTasks(taskList);
        try (Scanner in = new Scanner(System.in)) {
            while (isInUse) {
                try {
                    String userInput = in.nextLine();
                    Command parsedCommand = parser.parseCommand(userInput);
                    parsedCommand.execute(taskList, ui);
                    storage.saveData(DEFAULT_SAVE_FILE, taskList, ui);
                    isInUse = !parsedCommand.isExit();
                } catch (FailedSaveException e) {
                    ui.printError(e);
                } catch (ToDoListException e) {
                    ui.printError(e);
                }
            }
        }
    }
}

