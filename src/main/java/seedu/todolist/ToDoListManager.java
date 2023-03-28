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

public class ToDoListManager {
    private boolean isRunning = true;
    private Parser parser = new Parser();
    private Storage storage = new Storage(Storage.DEFAULT_SAVE_PATH);
    private TaskList taskList = new TaskList();
    private Ui ui = new Ui();

    public ToDoListManager() {
        ui.printWelcomeMessage();

        if (storage.isNewSave()) {
            // No save file found
            ui.printNewSaveMessage();
            return;
        }
        try {
            // Save file found, try loading it
            taskList = storage.loadData();
            taskList.checkRepeatingTasks();
            ui.printLoadSaveMessage(taskList.size());
            new ProgressBarCommand().execute(taskList, ui);
        } catch (FailedLoadException e) {
            ui.printError(e);
            // Loading save file failed, save new empty task list immediately instead of waiting for a command
            try {
                storage.saveData(taskList);
            } catch (FailedSaveException e2) {
                ui.printError(e2);
            }
        }
    }

    public void run() {
        while (isRunning) {
            String inputCommand = ui.getUserInput();
            try {
                Command command = parser.parseCommand(inputCommand);
                command.execute(taskList, ui);
                storage.saveData(taskList);
                isRunning = !command.shouldExit();
            } catch (ToDoListException e) {
                ui.printError(e);
            }
        }
        ui.close();
    }
}
