package seedu.duke.command;

import seedu.duke.Module;
import seedu.duke.UI;

import java.util.ArrayList;

public abstract class Command {
    private boolean isExit;
    public UI ui;
    public Command() {
        isExit = false;
        ui = new UI();
    }

    public boolean getIsExit() {
        return isExit;
    }

    public void setExit(boolean exit) {
        isExit = exit;
    }

    public abstract void execute(ArrayList<Module> modules);
}
