package seedu.todolist;

import seedu.todolist.exception.FailedLoadDataException;
import seedu.todolist.exception.FailedSaveException;
import seedu.todolist.exception.FailedLoadConfigException;
import seedu.todolist.exception.ToDoListException;
import seedu.todolist.logic.Config;
import seedu.todolist.logic.Parser;
import seedu.todolist.logic.command.Command;
import seedu.todolist.logic.command.EditConfigCommand;
import seedu.todolist.logic.command.ProgressBarCommand;
import seedu.todolist.storage.Storage;
import seedu.todolist.task.TaskList;
import seedu.todolist.ui.Ui;

import java.time.LocalDateTime;
import java.io.FileNotFoundException;

public class ToDoListManager {
    private boolean isRunning = true;
    private Parser parser = new Parser();
    private Storage storage = new Storage();
    private TaskList taskList = new TaskList();
    private Config config = new Config();
    private Ui ui = new Ui();

    //@@author clement559
    private void loadConfig() {
        try {
            // Config file found, try loading it
            config = storage.loadConfig();
            ui.printLoadConfigMessage();
        } catch (FileNotFoundException e) {
            // No config file found, generate new configs
            ui.printNewConfigMessage();
        } catch (FailedLoadConfigException e3) {
            ui.printError(e3);
        }
    }

    //@@author jeromeongithub
    private void loadTaskList() {
        try {
            // Save file found, try loading it
            taskList = storage.loadData();
            taskList.checkRepeatingTasks(config);
            ui.printLoadSaveMessage(taskList.size());
            new ProgressBarCommand().execute(taskList, ui);
        } catch (FileNotFoundException e) {
            // No save file found, generate new task list
            ui.printNewSaveMessage();
        } catch (FailedLoadDataException e3) {
            ui.printError(e3);
            // Caught an error in the saved file, terminate first
            isRunning = false;
        }
    }

    public ToDoListManager() {
        ui.printWelcomeMessage();
        loadConfig();
        loadTaskList();
        try {
            storage.save(taskList, config);
        } catch (FailedSaveException e) {
            ui.printError(e);
        }
    }

    public void run() {
        while (isRunning) {
            String inputCommand = ui.getUserInput();
            try {
                Command command = parser.parseCommand(inputCommand);
                if (command instanceof EditConfigCommand) {
                    ((EditConfigCommand) command).execute(config, ui);
                } else {
                    command.execute(taskList, ui);
                }
                LocalDateTime nextCheck = config.getLastChecked().plusMinutes(config.getCheckFrequency());
                if (nextCheck.isBefore(LocalDateTime.now())) {
                    taskList.checkRepeatingTasks(config);
                }
                storage.save(taskList, config);
                isRunning = !command.shouldExit();
            } catch (ToDoListException e) {
                ui.printError(e);
            }
        }
        ui.close();
    }
}
