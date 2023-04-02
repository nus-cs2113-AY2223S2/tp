package seedu.dukeofbooks.command;

import seedu.dukeofbooks.controller.IController;
import seedu.dukeofbooks.data.exception.LoanRecordNotFoundException;

public abstract class UserCommand extends Command{
    protected IController controller;
    public UserCommand() {
    }

    public abstract CommandResult execute() throws LoanRecordNotFoundException;

    public void setData(IController controller) {
        this.controller = controller;
    }
}
