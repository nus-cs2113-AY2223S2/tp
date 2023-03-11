package seedu.dukeofbooks.command;

import seedu.dukeofbooks.controller.IController;

public abstract class Command {
    private IController controller;
    public Command() {
    }

    public abstract CommandResult execute();

    /**
     * Sets the controller to use
     * @param controller
     */
    public void setData(IController controller) {
        this.controller = controller;
    }
}