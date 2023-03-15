package seedu.dukeofbooks.command;

public class ExitCommand extends Command{
    public static final String COMMAND_WORD = "exit";
    public static final String MESSAGE_EXIT_ACKNOWEDGEMENT = "Exiting DukeOfBooks as requested ...";

    @Override
    public CommandResult execute() {
        return new CommandResult(MESSAGE_EXIT_ACKNOWEDGEMENT);
    }

    public static boolean isExit(Command command) {
        return command instanceof ExitCommand; // instanceof returns false if it is null
    }
}
