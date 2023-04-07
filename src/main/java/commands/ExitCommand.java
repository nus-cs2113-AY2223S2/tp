package commands;

import ui.TextUi;

public class ExitCommand extends Command {
    public static final String COMMAND_WORD = "exit";
    private boolean isExit;

    /**
     * A constructor that sets the isExit value to false.
     */
    public ExitCommand() {
        this.isExit = false;
    }

    /**
     * Setter to set the isExit value.
     *
     * @param exit the exit value to set.
     */
    public void setExit(boolean exit) {
        isExit = exit;
    }

    /**
     * Method to return the isExit value.
     *
     * @return the isExit value.
     */
    @Override
    public boolean isExit() {
        return isExit;
    }

    /**
     * Executes the command the user provided.
     *
     * @param ui The Ui instance. Use to display messages to users.
     */
    @Override
    public void execute(TextUi ui) {
        setExit(true);
    }
}
