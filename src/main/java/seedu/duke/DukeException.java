package seedu.duke;

public class DukeException extends Exception {
    private static final String DEFAULT_EMPTY_DESCRIPTION = "No Description";

    public String description;

    /**
     * Creates an object of the class DukeException.
     * This has no inputs and a default empty description is applied.
     */
    public DukeException() {
        this.description = DEFAULT_EMPTY_DESCRIPTION;
    }

    /**
     * Creates an object of the class DukeException.
     * This has inputs, and it will be stored into the description.
     *
     * @param description This will contain the description of the error found in commandChecker.
     */
    public DukeException(String description) {
        this.description = description;
    }

    /**
     * Sets the description of the error to be stored in DukeException.
     *
     * @param description Contains the description of the error given in commandChecker.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Sets the description of the error to be stored in DukeException.
     *
     * @return description of the error stored in DukeException.
     */
    public String getDescription() {
        return this.description;
    }
}
