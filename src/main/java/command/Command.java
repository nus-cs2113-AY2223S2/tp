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

    /**
     * Check if the key is contained in the index of the tasks
     */
    public boolean contains(final int[] arr, final int key) {
        for (final int i : arr) {
            if (i == key) {
                return true;
            }
        }
        return false;
    }
}
