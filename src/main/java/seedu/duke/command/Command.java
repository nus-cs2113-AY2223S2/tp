package seedu.duke.command;

import seedu.duke.UI;

public abstract class Command {

    public UI ui;
    private boolean isExit;

    public Command() {
        isExit = false;
        ui = UI.getUiOneInstance();
    }

    public boolean getIsExit() {
        return isExit;
    }

    public void setExit(boolean isExit) {
        this.isExit = isExit;
    }

    public abstract void execute();
}
