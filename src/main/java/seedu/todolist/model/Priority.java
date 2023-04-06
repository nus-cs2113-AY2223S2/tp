//@@author RuiShengGit
package seedu.todolist.model;

public enum Priority {
    HIGH("High"),
    MEDIUM("Medium"),
    LOW("Low"),
    NONE("None");

    private final String name;

    Priority(String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }
}
