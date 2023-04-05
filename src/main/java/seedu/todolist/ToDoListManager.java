package seedu.todolist;

import seedu.todolist.exception.FailedLoadException;
import seedu.todolist.exception.FailedSaveException;
import seedu.todolist.exception.ToDoListException;
import seedu.todolist.logic.Parser;
import seedu.todolist.logic.command.Command;
import seedu.todolist.logic.command.ProgressBarCommand;
import seedu.todolist.storage.Storage;
import seedu.todolist.task.TaskList;
import seedu.todolist.ui.Ui;

import java.io.FileNotFoundException;


public class ToDoListManager {
    private boolean isRunning = true;
    private Parser parser = new Parser();
    private Storage storage = new Storage(Storage.DEFAULT_SAVE_PATH);
    private TaskList taskList = new TaskList();
    private Ui ui = new Ui();

    public ToDoListManager() {
        ui.printWelcomeMessage();
        try {
            // Save file found, try loading it
            taskList = storage.loadData(Storage.DEFAULT_SAVE_PATH);
            taskList.checkRepeatingTasks();
            ui.printLoadSaveMessage(taskList.size());
            new ProgressBarCommand().execute(taskList, ui);
        } catch (FileNotFoundException e) { // No save file found
            ui.printNewSaveMessage();
            // Loading save file failed, save new empty task list immediately instead of waiting for a command
            try {
                storage.saveData(taskList, Storage.DEFAULT_SAVE_PATH);
            } catch (FailedSaveException e2) {
                ui.printError(e2);
            }
        } catch (FailedLoadException e3) { // caught an error in the saved file
            ui.printError(e3);
            isRunning = false; // terminate the program
        }
    }

    public void run() {
        while (isRunning) {
            String inputCommand = ui.getUserInput();
            try {
                Command command = parser.parseCommand(inputCommand);
                command.execute(taskList, ui);
                taskList.checkRepeatingTasks();
                storage.saveData(taskList, Storage.DEFAULT_SAVE_PATH);
                isRunning = !command.shouldExit();
            } catch (ToDoListException e) {
                ui.printError(e);
            }
        }
        ui.close();
    }
}
