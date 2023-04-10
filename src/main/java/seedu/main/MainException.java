package seedu.main;

public class MainException extends Exception {
    public String description;

    /**
     * Creates an object of the class DukeException.
     * This has inputs, and it will be stored into the description.
     *
     * @param description This will contain the description of the error found in Parser.
     */
    public MainException(String description) {
        this.description = description;
    }

    /**
     * Gets the description of the error to be stored in DukeException.
     *
     * @return description of the error stored in DukeException.
     */
    public String getDescription() {
        return this.description;
    }
}
