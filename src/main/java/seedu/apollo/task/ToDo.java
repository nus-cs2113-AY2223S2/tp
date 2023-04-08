package seedu.apollo.task;

/**
 * ToDos are a type of Task that have only the default description and status.
 */
public class ToDo extends Task {

    public static final String TODO_LABEL = "T";

    /**
     * Initialises as in Task.
     *
     * @param description String describing the Task.
     */
    public ToDo(String description) {
        super(description);
    }

    public String getDescription() {
        return description;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public String getType() {
        return "todo";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "[" + TODO_LABEL + "][" + getStatus() + "] " + description;
    }

}
