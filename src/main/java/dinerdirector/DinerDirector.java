package dinerdirector;

import commands.Command;
import common.Messages;
import exceptions.DinerDirectorException;
import ui.TextUi;
import utils.DeadlineStorage;
import utils.DishStorage;
import utils.MeetingStorage;
import utils.Parser;
import utils.StaffStorage;
import utils.Storage;

import java.io.FileNotFoundException;
import java.io.IOException;

public class DinerDirector {
    private TextUi ui;
    private Storage storage;


    //Solution below adopted from:
    //https://github.com/nus-cs2113-AY2223S2/personbook/blob/main/src/main/java/seedu/personbook/Main.java
    //Implemented the main, run, start method in the similar way.
    public static void main(String[] args) {
        new DinerDirector().run();
    }

    private void run() {
        start();
        runCommandLoopUntilExit();
        exit();
    }

    /**
     * Start of program.
     * Initializing of Ui, creating of storage/loading of file will be done and checked here.
     */
    private void start() {
        this.ui = new TextUi();
        this.storage = new Storage();
        DeadlineStorage deadlineStorage = new DeadlineStorage();
        MeetingStorage meetingStorage = new MeetingStorage();
        DishStorage dishStorage = new DishStorage();
        StaffStorage staffStorage = new StaffStorage();
        try {
            storage.createDirectory();
        } catch (IOException | DinerDirectorException e) {
            ui.printMessage(String.format(Messages.ERROR_CREATE_DIRECTORY, e.getMessage()));
        }

        try {
            deadlineStorage.readAndLoadFromDeadlineFile();
        } catch (FileNotFoundException e) {
            ui.printMessage(String.format(Messages.ERROR_STORAGE_FILE_NOT_FOUND, DeadlineStorage.FILENAME_DEADLINE));
        }

        try {
            meetingStorage.readAndLoadFromMeetingFile();
        } catch (FileNotFoundException e) {
            ui.printMessage(String.format(Messages.ERROR_STORAGE_FILE_NOT_FOUND, MeetingStorage.FILENAME_MEETING));
        }

        try {
            dishStorage.readAndLoadFromDishFile();
        } catch (FileNotFoundException e) {
            ui.printMessage(String.format(Messages.ERROR_STORAGE_FILE_NOT_FOUND, DishStorage.FILENAME_DISH));
        }

        try {
            staffStorage.readAndLoadFromStaffFile();
        } catch (FileNotFoundException e) {
            ui.printMessage(String.format(Messages.ERROR_STORAGE_FILE_NOT_FOUND, StaffStorage.FILENAME_STAFF));
        }

        ui.printMessage(Messages.MESSAGE_FINISHED_LOADING);
        ui.printBanner();

    }

    private void runCommandLoopUntilExit() {
        boolean isExit = false;
        while (!isExit) {
            String userInput = ui.getUserInput();
            Command command = new Parser().parseCommand(userInput);
            assert command instanceof Command : "command should be a command class";
            command.execute(ui);
            isExit = command.isExit();
        }
        ui.printMessage(Messages.MESSAGE_COMMAND_EXIT);
    }

    /**
     * Exits the program.
     */
    private void exit() {
        System.exit(0);
    }


}
