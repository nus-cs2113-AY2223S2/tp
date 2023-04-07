package functionalities.commands;

import exception.SniffException;
import functionalities.SniffTasks;

public class ArchiveCommand extends Command{

    public ArchiveCommand() {
    }

    @Override
    public void executeCommand(SniffTasks tasks) throws SniffException {
        SniffTasks.listArchivedAppointments();
    }
}
