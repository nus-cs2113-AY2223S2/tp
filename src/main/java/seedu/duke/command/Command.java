package seedu.duke.command;

/**
 * Parent class for Command objects.
 */
public abstract class Command {
    /**
     * Executes the command.
     */
    public abstract void execute();

    /**
     * Returns whether a command, by default, should terminate the program.
     *
     * @return <code>False</code>, since most commands should not terminate the program.
     */
    public boolean isExit() {
        return false;
    }
}
