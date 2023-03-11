package seedu.commands;

public class CommandResult {
    private final String commandResult;

    /**
     * Constructor.
     *
     * @param commandResult result of command executed
     */
    public CommandResult(String commandResult) {
        this.commandResult = commandResult;
    }

    public String getCommandResult() {
        return commandResult;
    }
}
