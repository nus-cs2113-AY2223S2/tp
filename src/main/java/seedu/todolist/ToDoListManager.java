package seedu.todolist;

import com.google.gson.JsonSyntaxException;
import seedu.todolist.exception.ToDoListException;
import seedu.todolist.logic.Parser;
import seedu.todolist.logic.command.Command;
import seedu.todolist.logic.command.ProgressBarCommand;
import seedu.todolist.storage.Storage;
import seedu.todolist.task.TaskList;
import seedu.todolist.ui.Ui;

import java.io.FileNotFoundException;
import java.io.IOException;

public class ToDoListManager {
    private boolean isRunning = true;
    private Parser parser = new Parser();
    private Storage storage = new Storage(Storage.DEFAULT_SAVE_PATH);
    private TaskList taskList;
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
            taskList = storage.loadData(Storage.DEFAULT_SAVE_PATH);
            taskList.checkRepeatingTasks();
            ui.printLoadSaveMessage(taskList.size());
            new ProgressBarCommand().execute(taskList, ui);
        } catch (FileNotFoundException | JsonSyntaxException e) {
            ui.printError(e);
            // Loading save file failed, save new empty task list immediately instead of waiting for a command
            try {
                taskList = new TaskList();
                storage.saveData(taskList, Storage.DEFAULT_SAVE_PATH);
            }
            catch (IOException e2) {
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
                taskList.checkRepeatingTasks();
                storage.saveData(taskList, Storage.DEFAULT_SAVE_PATH);
                isRunning = !command.shouldExit();
            } catch (ToDoListException e) {
                ui.printError(e);
            } catch (IOException e2) {

            }
        }
        ui.close();
    }
}
