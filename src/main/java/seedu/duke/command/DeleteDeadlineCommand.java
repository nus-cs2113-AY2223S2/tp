package seedu.duke.command;

import seedu.duke.Deadline;
import seedu.duke.DeadlineStorage;

import java.util.ArrayList;

public class DeleteDeadlineCommand extends Command {

    private DeadlineStorage deadlineStorage;

    private int indexToRemove;

    private ArrayList<Deadline> deadlines;

    public DeleteDeadlineCommand(DeadlineStorage deadlineStorage, int indexToRemove, ArrayList<Deadline> deadlines) {
        this.deadlineStorage = deadlineStorage;
        this.indexToRemove = indexToRemove;
        this.deadlines = deadlines;
    }

    @Override
    public void execute() {
        boolean isDeleteSuccessful = DeadlineStorage.deleteDeadline(indexToRemove, deadlines, deadlineStorage);
        if (!isDeleteSuccessful) {
            return;
        }
        ui.printDeleteDeadlineMessage();
    }
}
