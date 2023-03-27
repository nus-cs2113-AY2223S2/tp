package seedu.duke.command;

import seedu.duke.Deadline;
import seedu.duke.Storage;

public class AddDeadlineCommand extends Command {

    private Deadline deadlineToAdd;
    private Storage storage;

    public AddDeadlineCommand(Deadline deadline, Storage storage) {
        deadlineToAdd = deadline;
        this.storage = storage;
    }

    public Deadline getDeadlineToAdd() {
        return deadlineToAdd;
    }

    public Storage getStorage() {
        return storage;
    }

    @Override
    public void execute() {
        storage.addDeadlineToDeadlines(deadlineToAdd);
        ui.printAddDeadlineMessage();
    }
}
