package seedu.mealcompanion;

/**
 * Represents the control flow state of the current MealCompanion session.
 */
public class MealCompanionControlFlow {
    /**
     * Whether the current session has been quit
     */
    private boolean isQuit;

    public MealCompanionControlFlow() {
        this.isQuit = false;
    }

    /**
     * Sets whether the current session should be quit.
     *
     * @param isQuit current quit state of the session.
     */
    public void setQuit(boolean isQuit) {
        this.isQuit = isQuit;
    }

    /**
     * Returns true if the session should continue running, false otherwise.
     *
     * @return whether the session should continue running.
     */
    public boolean shouldRun() {
        return !this.isQuit;
    }
}
