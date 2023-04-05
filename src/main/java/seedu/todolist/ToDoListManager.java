package seedu.todolist;

import com.google.gson.JsonParseException;
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
import java.io.IOException;
import java.time.format.DateTimeParseException;

public class ToDoListManager {
    private boolean isRunning = true;
    private Parser parser = new Parser();
    private Storage storage = new Storage(Storage.DEFAULT_SAVE_PATH);
    private Storage configFile = new Storage (Storage.DEFAULT_CONFIG_PATH);
    private TaskList taskList = new TaskList();
    private Ui ui = new Ui();
    private Config config = new Config();
    public ToDoListManager() {
        ui.printWelcomeMessage();

        if (configFile.isNewSave()) {
            ui.printMissingConfigMessage();
        }

        if (storage.isNewSave()) {
            // No save file found
            ui.printNewSaveMessage();
            return;
        }

        try {
            // Config file found, try loading it
            config = configFile.loadConfig(configFile.DEFAULT_CONFIG_PATH);
            ui.printLoadConfigMessage();
        } catch (FileNotFoundException e) {
            ui.printError(e);
            // Loading save file failed, save new empty task list immediately instead of waiting for a command
            try {
                configFile.saveConfig(config, configFile.DEFAULT_CONFIG_PATH);
            } catch (IOException e2) {
                ui.printError(e2);
            }
        }

        try {
            // Save file found, try loading it

            taskList = storage.loadData(Storage.DEFAULT_SAVE_PATH);
            taskList.checkRepeatingTasks(config);
            ui.printLoadSaveMessage(taskList.size());
            new ProgressBarCommand().execute(taskList, ui);
        } catch (FileNotFoundException e) {
            ui.printError(e);
            // Loading save file failed, save new empty task list immediately instead of waiting for a command
            try {
                storage.saveData(taskList, Storage.DEFAULT_SAVE_PATH);
            }
            catch (IOException e2) {
                ui.printError(e2);

            }
        } catch (DateTimeParseException | JsonParseException e3) { // caught an error in the saved file
            ui.printError(e3);
            ui.printSavedFileSyntaxError();
            isRunning = false; // terminate the program
        }
    }

    public void run() {
        while (isRunning) {
            String inputCommand = ui.getUserInput();
            try {
                Command command = parser.parseCommand(inputCommand);
                if (command instanceof EditConfigCommand) {
                    ((EditConfigCommand) command).execute(config, ui);
                    configFile.saveConfig(config, configFile.DEFAULT_CONFIG_PATH);
                } else {
                    command.execute(taskList, ui);
                }
                LocalDateTime nextCheck = config.getLastChecked().plusMinutes(config.getCheckFrequency());
                if (nextCheck.isBefore(LocalDateTime.now())) {
                    taskList.checkRepeatingTasks(config);
                }
                storage.saveData(taskList, Storage.DEFAULT_SAVE_PATH);
                isRunning = !command.shouldExit();
            } catch (ToDoListException e) {
                ui.printError(e);
            } catch (IOException e2) {
                ui.printError(e2);
            }
        }
        ui.close();
    }
}
