package functionalities.commands;

import exception.SniffException;
import functionalities.SniffTasks;
import functionalities.ui.Ui;

public class HelpCommand extends Command {
    public HelpCommand() {
    }

    @Override
    public void executeCommand(SniffTasks tasks) throws SniffException {
        Ui.showHelpMessage();
    }
}
