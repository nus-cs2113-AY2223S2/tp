package seedu.commands;

//@@author calebcjl

import seedu.ui.Ui;

/**
 * Represents command to exit the program.
 */
public class ExitCommand extends Command {
    private static final String EXIT_MESSAGE = "Thank you, hope you had a great workout!!!";

    /**
     * Check if exit command has been created.
     *
     * @param command Command to check.
     * @return True if command is an ExitCommand. Returns false otherwise.
     */
    public static boolean isExit(Command command) {
        return command instanceof ExitCommand;
    }

    @Override
    public String execute() {
        return EXIT_MESSAGE + System.lineSeparator() + Ui.line();
    }
}
