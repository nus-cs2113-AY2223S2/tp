package seedu.commands;


public class IncorrectSyntaxCommand extends Command {
    private static final String ERROR_MESSAGE = "Invalid Syntax for ";
    private final String error;

    public IncorrectSyntaxCommand(String error) {
        this.error = error;
    }

    @Override
    public String execute() {
        return ERROR_MESSAGE + error;
    }
}
