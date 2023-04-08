package functionalities.commands;

import exception.SniffException;
import functionalities.SniffTasks;

/**
 * The Command class represents a command to be executed.
 * This class is abstract and provides methods executing the command and checking the status of the program.
 */
public abstract class Command {
    public boolean isExit;

    protected Command() {
        this.isExit = false;
    }

    public boolean isExit() {
        return isExit;
    }

    public void executeCommand(SniffTasks tasks) throws SniffException {
    }
}
