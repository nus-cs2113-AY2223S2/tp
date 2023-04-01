package seedu.dukeofbooks;

import seedu.dukeofbooks.command.*;
import seedu.dukeofbooks.controller.SearchController;
import seedu.dukeofbooks.data.exception.IllegalValueException;
import seedu.dukeofbooks.data.inventory.Inventory;
import seedu.dukeofbooks.data.loan.LoanRecords;
import seedu.dukeofbooks.data.user.User;
import seedu.dukeofbooks.data.user.UserRecords;
import seedu.dukeofbooks.parser.AccessCommandParser;
import seedu.dukeofbooks.parser.UserCommandParser;
import seedu.dukeofbooks.ui.TextUi;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Main file to run
 */
public class DukeOfBooks {

    public static final String VERSION = "DukeOfBooks - Version 1.0";
    private LoanRecords allLoanRecords;
    private UserRecords userRecords;
    private SearchController searchController = new SearchController();
    private User currentUser;
    private TextUi ui;

    private Inventory inventory = new Inventory();

    public static void main(String[] args) {
        new DukeOfBooks().run();
    }

    public void run() {
        start();
        runCommandLoopUntilSystemExit();
        exit();
    }

    private void start() {
        this.allLoanRecords = new LoanRecords();
        this.userRecords = new UserRecords();
        this.ui = new TextUi();
        // Add book data into inventory
        try {
            ReadWriteData.readData((inventory));
        } catch (FileNotFoundException e) {
            File f = new File("database/books.txt");
        }
        ui.showWelcomeMessage(VERSION);
    }

    private void exit() {
        ui.showExitMessage();
        // Write book data into inventory
        try {
            ReadWriteData.writeData(inventory);
        } catch (IOException e) {
            System.out.println("Failed to save data!");
        }
        System.exit(0);
    }

    private void runCommandLoopUntilSystemExit() {
        AccessCommand accessCommand;
        do{
            ui.showLoginMessage();
            String commandText = ui.getUserCommand();
            accessCommand = new AccessCommandParser(userRecords).parseCommand(commandText);

            currentUser = login(accessCommand);
            if (currentUser != null) {
                ui.showGreetingMessage(currentUser.getName().toString());
                runCommandLoopUntilUserLogout(currentUser);
            }
        } while (!ExitCommand.isExit(accessCommand));
    }

    private void runCommandLoopUntilUserLogout(User user) {
        UserCommand userCommand;
        do {
            String userCommandText = ui.getUserCommand();
            userCommand = new UserCommandParser(currentUser, allLoanRecords, searchController)
                    .parseCommand(userCommandText);
            CommandResult result = executeUserCommand(userCommand);
            ui.showResultToUser(result);
        } while (!LogoutCommand.isLogout(userCommand));
    }

    private User login(AccessCommand accessCommand) {
        if (AccessHelpCommand.isHelp(accessCommand)) {
            ui.showToUser(AccessHelpCommand.getUsage());
            return null;
        }

        try {
            return accessCommand.execute();
        } catch (IllegalValueException ive) {
            ui.showToUser(ive.getMessage());
            return null;
        } catch (Exception e) {
            ui.showToUser(e.getMessage());
            throw new RuntimeException();
        }
    }

    private CommandResult executeUserCommand(UserCommand command) {
        try {
            // command.setData(#placeholder);
            return command.execute();
        } catch (Exception e) {
            ui.showToUser(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
