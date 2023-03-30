package seedu.dukeofbooks.command;

public class LogoutCommand extends UserCommand {
    public static final String COMMAND_WORD = "logout";
    public static final String COMMAND_USAGE = "logout";
    private static final String LOGOUT_MSG = "Logging out...";

    @Override
    public CommandResult execute() {
        return new CommandResult(LOGOUT_MSG);
    }

    public static boolean isLogout(UserCommand userCommand) {
        return userCommand instanceof LogoutCommand;
    }
}
