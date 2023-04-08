package seedu.commands.errorcommands;

//@@author calebcjl

import seedu.commands.Command;

/**
 * Represents a command entered with incorrect syntax.
 */
public class IncorrectSyntaxCommand extends Command {
    private static final String ERROR_MESSAGE = "Invalid syntax for ";
    private final String syntaxError;

    public IncorrectSyntaxCommand(String syntaxError) {
        this.syntaxError = syntaxError;
    }

    @Override
    public String execute() {
        return ERROR_MESSAGE + syntaxError;
    }
}
