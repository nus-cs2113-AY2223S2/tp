package functionalities.commands;

import functionalities.SniffTasks;

/**
 * Command to execute Archive functionality
 * */
public class ArchiveCommand extends Command{

    public ArchiveCommand() {
    }

    /**
     * Executes Archive Command method located in SniffTasks class
     *
     * @param tasks The SniffTasks Class
     * */
    @Override
    public void executeCommand(SniffTasks tasks) {
        SniffTasks.listArchivedAppointments();
    }
}
