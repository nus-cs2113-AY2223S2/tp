package functionalities.commands;
import exception.SniffException;
import functionalities.SniffTasks;

/**
 * Command to execute UnMark functionality
 */

public class UnMarkCommand extends Command {
    public String uid;

    public UnMarkCommand (String uid){
        this.uid = uid;
    }

    /**
     * Executes UnMark Command method located in SniffTasks class
     * @param tasks The SniffTasks Class
     * @throws SniffException thrown when the input does not follow the format
     */
    @Override
    public void executeCommand(SniffTasks tasks) throws SniffException {
        tasks.unMarkAppointment(uid);
    }

}
