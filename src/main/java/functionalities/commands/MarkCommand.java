package functionalities.commands;
import exception.SniffException;
import functionalities.SniffTasks;

/**
 * Command to execute Mark functionality
 */
public class MarkCommand extends Command {
    public String uid;

    public MarkCommand (String uid){
        this.uid = uid;
    }
    /**
     * Executes Mark Command method located in SniffTasks class
     * @param tasks The SniffTasks Class
     * @throws SniffException thrown when the input does not follow the format
     */
    @Override
    public void executeCommand(SniffTasks tasks) throws SniffException {
        tasks.markAppointment(uid);
    }

}
