package seedu.dukeofbooks.command;

public class UserExitCommand extends UserCommand {
    public static final String COMMAND_WORD = "exit";

    @Override
    public CommandResult execute() {
        return new CommandResult("Please log out use logout command before running exit command!");
    }
}
