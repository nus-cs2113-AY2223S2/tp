package seedu.duke;

/**
 * Represents the control flow state of the current Duke session.
 */
public class DukeControlFlow {
    /** Whether the current session has been quit */
    private boolean isQuit;

    public DukeControlFlow() {
        this.isQuit = false;
    }

    /**
     * Sets whether the current session should be quit.
     * @param isQuit current quit state of the session.
     */
    public void setQuit(boolean isQuit) {
        this.isQuit = isQuit;
    }

    /**
     * Returns true if the session should continue running, false otherwise.
     * @return whether the session should continue running.
     */
    public boolean shouldRun() {
        return !this.isQuit;
    }
}
