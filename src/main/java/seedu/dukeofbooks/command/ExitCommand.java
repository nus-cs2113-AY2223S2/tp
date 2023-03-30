package seedu.dukeofbooks.command;


import seedu.dukeofbooks.data.user.User;

public class ExitCommand extends AccessCommand{
    public static final String COMMAND_WORD = "exit";
    public static final String COMMAND_USAGE = COMMAND_WORD + ": exits the application";
    public static final String MESSAGE_EXIT_ACKNOWLEDGEMENT = "Exiting DukeOfBooks as requested ...";

    public ExitCommand() {
        super(null);
    }

    @Override
    public User execute() {
        return null;
    }

    public static boolean isExit(Command command) {
        return command instanceof ExitCommand; // instanceof returns false if it is null
    }
}
