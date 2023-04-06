//@@author RuiShengGit
package seedu.todolist.model;

public enum Priority {
    HIGH("High", "III"),
    MEDIUM("Medium", "II "),
    LOW("Low", "I  "),
    NONE("None", "   ");

    private final String name;
    private final String display;

    Priority(String name, String display) {
        this.name = name;
        this.display = display;
    }

    public String toString() {
        return name;
    }

    public String toDisplayString() {
        return display;
    }
}
