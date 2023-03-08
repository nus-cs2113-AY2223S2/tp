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
     * Returns whether a command should terminate the program.
     *
     * @return True for the exit command, false otherwise.
     */
    public boolean isExit() {
        return false;
    }
}
