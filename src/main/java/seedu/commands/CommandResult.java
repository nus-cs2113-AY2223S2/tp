package seedu.commands;

public class CommandResult {
    private final String COMMAND_RESULT;

    /**
     * Constructor.
     *
     * @param commandResult result of command executed
     */
    public CommandResult(String commandResult) {
        COMMAND_RESULT = commandResult;
    }

    public String getCommandResult() {
        return COMMAND_RESULT;
    }
}
