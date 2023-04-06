package commands;

import ui.TextUi;

public abstract class Command {
    /**
     * Executes the command the user provided.
     *
     * @param ui The Ui instance. Use to display messages to users.
     */
    public abstract void execute(TextUi ui);

    /**
     * Returns the exit value.
     * If false, program continues to run.
     *
     * @return the exit value.
     */
    public abstract boolean isExit();

}
