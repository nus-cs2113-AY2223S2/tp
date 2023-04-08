package seedu.commands.errorcommands;


import seedu.commands.Command;

public class InvalidCommand extends Command {
    private static final String INVALID_COMMAND_MESSAGE = " is not a valid command!";
    private final String command;
    public InvalidCommand(String command) {
        this.command = command;
    }

    @Override
    public String execute() {
        return command + INVALID_COMMAND_MESSAGE;
    }
}
