package seedu.todolist.constants;

public enum Priority {
    HIGH("High"),
    MEDIUM("Medium"),
    LOW("Low"),
    NONE("None");

    private final String PRIORITY_LEVEL;

    Priority(String message) {
        this.PRIORITY_LEVEL = message;
    }

    public String toString() {
        return PRIORITY_LEVEL;
    }
}
