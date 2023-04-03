package seedu.duke.command;

import seedu.duke.Deadline;
import seedu.duke.DeadlineStorage;

public class AddDeadlineCommand extends Command {

    private Deadline deadlineToAdd;
    private DeadlineStorage deadlineStorage;

    public AddDeadlineCommand(Deadline deadline, DeadlineStorage deadlineStorage) {
        deadlineToAdd = deadline;
        this.deadlineStorage = deadlineStorage;
    }

    public Deadline getDeadlineToAdd() {
        return deadlineToAdd;
    }

    public DeadlineStorage getStorage() {
        return deadlineStorage;
    }

    @Override
    public void execute() {
        deadlineStorage.addDeadlineToDeadlines(deadlineToAdd);
        ui.printAddDeadlineMessage();
    }
}
