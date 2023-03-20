package functionalities.commands;

import exception.SniffException;
import functionalities.SniffTasks;


public class ViewCommand extends Command{

    protected String uid;
    public ViewCommand(String uid) {
        this.uid = uid;
    }
    @Override
    public void executeCommand(SniffTasks tasks) throws SniffException {
        tasks.viewAppointment(uid);
    }
}
