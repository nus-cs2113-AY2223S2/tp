package seedu.duke.command;

import seedu.duke.UI;

public abstract class Command {

    public UI ui;
    private boolean isExit;

    /**
     * Constructor of Command class that sets isExit to false initially.
     */
    public Command() {
        isExit = false;
        ui = UI.getUiOneInstance();
    }

    /**
     * Returns true is it is time for the program to exit.
     * Returns false if it is not time for the program to exit.
     *
     * @return a boolean value, either true or false on when the program exits.
     */
    public boolean getIsExit() {
        return isExit;
    }

    /**
     * Sets the isExit variable to either true or false
     *
     * @param isExit the boolean variable to set to whether the program exits
     */
    public void setExit(boolean isExit) {
        this.isExit = isExit;
    }

    public abstract void execute();
}
