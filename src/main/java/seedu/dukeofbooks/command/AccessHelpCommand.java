package seedu.dukeofbooks.command;

public class AccessHelpCommand extends AccessCommand {
    public static final String COMMAND_WORD = "help";

    public AccessHelpCommand() {
        super(null);
    }

    public static String[] getUsage() {
        return new String[] {LoginCommand.COMMAND_USAGE, SignupCommand.COMMAND_USAGE, PasswordCommand.COMMAND_USAGE,
            ExitCommand.COMMAND_USAGE};
    }

    public static boolean isHelp(Command command) {
        return command instanceof AccessHelpCommand;
    }

    @Override
    public AccessResponse execute() {
        return new AccessResponse(null, "");
    }
}
