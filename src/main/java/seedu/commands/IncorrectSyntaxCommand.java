package seedu.commands;


public class IncorrectSyntaxCommand extends seedu.commands.Command {
    private static final String ERROR_MESSAGE = "Invalid syntax for ";
    private final String syntaxError;

    public IncorrectSyntaxCommand(String syntaxError) {
        this.syntaxError = syntaxError;
    }

    @Override
    public String execute() {
        return ERROR_MESSAGE + syntaxError
                + InfoMessage.showLinesAfterExecution();
    }
}
