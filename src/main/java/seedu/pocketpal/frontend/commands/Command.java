package seedu.pocketpal.frontend.commands;

import seedu.pocketpal.backend.Backend;
import seedu.pocketpal.frontend.ui.UI;

public abstract class Command {
    private final boolean isExit;

    public Command() {
        this(false);
    }

    public Command(boolean isExit) {
        this.isExit = isExit;
    }

    public void execute(UI ui, Backend backend) throws Exception {
        throw new AssertionError("Unexpected method call. This method has not been implemented");
    }

    public boolean getIsExit() {
        return isExit;
    }
}
