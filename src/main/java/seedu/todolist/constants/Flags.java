package seedu.todolist.constants;

/**
 * Enum that holds all the flags allowed in commands.
 */
public enum Flags {
    DEADLINE("-due");

    public final String FLAG;

    Flags(String flag) {
        FLAG = flag;
    }
}
