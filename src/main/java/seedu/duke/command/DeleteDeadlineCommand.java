package seedu.duke.command;

import seedu.duke.Deadline;
import seedu.duke.Storage;

import java.util.ArrayList;

public class DeleteDeadlineCommand extends Command {

    private Storage storage;

    private int indexToRemove;

    private ArrayList<Deadline> deadlines;

    public DeleteDeadlineCommand(Storage storage, int indexToRemove, ArrayList<Deadline> deadlines) {
        this.storage = storage;
        this.indexToRemove = indexToRemove;
        this.deadlines = deadlines;
    }

    @Override
    public void execute() {
        boolean isDeleteSuccessful = Storage.deleteDeadline(indexToRemove, deadlines, storage);
        if (!isDeleteSuccessful) {
            return;
        }
        ui.printDeleteModMessage();
    }
}
