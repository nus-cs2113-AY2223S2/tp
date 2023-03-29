package seedu.dukeofbooks.command;

import seedu.dukeofbooks.controller.IController;

public abstract class UserCommand extends Command{
    protected IController controller;
    public UserCommand() {
    }

    public abstract CommandResult execute();

    public void setData(IController controller) {
        this.controller = controller;
    }
}
