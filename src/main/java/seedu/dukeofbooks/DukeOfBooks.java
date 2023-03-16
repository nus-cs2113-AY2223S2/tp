package seedu.dukeofbooks;

import seedu.dukeofbooks.command.Command;
import seedu.dukeofbooks.command.CommandResult;
import seedu.dukeofbooks.command.ExitCommand;
import seedu.dukeofbooks.data.exception.IllegalValueException;
import seedu.dukeofbooks.data.loan.LoanRecords;
import seedu.dukeofbooks.data.person.Person;
import seedu.dukeofbooks.parser.Parser;
import seedu.dukeofbooks.ui.TextUi;

/**
 * Main file to run
 */
public class DukeOfBooks {

    public static final String VERSION = "DukeOfBooks - Version 1.0";
    private LoanRecords allLoanRecords;
    private Person currentUser;
    private TextUi ui;

    public static void main(String[] args) {
        // System.out.println("Hello, world!");
        new DukeOfBooks().run();
    }

    public void run() {
        start();
        runCommandLoopUntilExitCommand();
        exit();
    }

    private void start() {
        this.allLoanRecords = new LoanRecords();
        try {
            this.currentUser = new Person("a user");
        } catch (IllegalValueException ive) {
            ive.printStackTrace();
        }
        this.ui = new TextUi();
        ui.showWelcomeMessage(VERSION);
    }

    private void exit() {
        ui.showExitMessage();
        System.exit(0);
    }

    private void runCommandLoopUntilExitCommand() {
        Command command;
        do {
            String userCommandText = ui.getUserCommand();
            command = new Parser(currentUser, allLoanRecords).parseCommand(userCommandText);
            CommandResult result = executeCommand(command);
            ui.showResultToUser(result);

        } while (!ExitCommand.isExit(command));
    }

    private CommandResult executeCommand(Command command) {
        try {
            // command.setData(#placeholder);
            CommandResult result = command.execute();
            return result;
        } catch (Exception e) {
            ui.showToUser(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
