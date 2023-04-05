package seedu.todolist.constants;

public enum PriorityLevels {
    HIGH("High"),
    MEDIUM("Medium"),
    LOW("Low"),
    UNEXPECTED("Unexpected value");

    private final String PRIORITY_LEVEL;

    PriorityLevels(String message) {
        this.PRIORITY_LEVEL = message;
    }

    public String getPriority() {
        return PRIORITY_LEVEL;
    }
}
