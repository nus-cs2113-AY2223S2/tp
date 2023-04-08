package command;

public class Command {
    public String commandType;

    protected Command(String commandType) {
        this.commandType = commandType;
    }

    /**
     * Execution of the command
     */
    public CommandRes execute() {
        throw new UnsupportedOperationException("Child classes' method");
    }
}
