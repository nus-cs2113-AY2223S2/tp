package functionalities.commands;

import exception.SniffException;
import functionalities.SniffTasks;
import functionalities.ui.Ui;

/**
 * The HelpCommand class represents a help command to be executed.
 * This class is extends the abstract Command class and contains methods to executing the show help message command.
 */
public class HelpCommand extends Command {
    public HelpCommand() {
    }

    /**
     * Executes show help message method located in Ui class.
     *
     * @param tasks The SniffTasks Class
     * @throws SniffException if any details are of the wrong format.
     */
    @Override
    public void executeCommand(SniffTasks tasks) throws SniffException {
        Ui.showHelpMessage();
    }
}
