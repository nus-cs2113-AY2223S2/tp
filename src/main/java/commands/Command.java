package commands;

import dinerDeadline.DeadlineList;
import ui.TextUi;

/**
 * A abstract Command class to handle user commands
 */

public abstract class Command {
    /**
     * Executes the command the user provided.
     */
    public abstract void execute(DeadlineList deadlineList, TextUi ui);

    public abstract boolean isExit();

}
