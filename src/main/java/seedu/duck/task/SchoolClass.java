package seedu.duck.task;

public class SchoolClass extends Task{
    private String className; // Name of class
    private String start; // Start date/time
    private String end;   // End date/time

    public SchoolClass(String className, String description, String start, String end) {
        super(description);
        this.className = className;
        this.start = start;
        this.end = end;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    @Override
    public String toSaveString() {
        return super.toSaveString() + " /class " + getClassName() + " /from " + getStart() + " /to "
                + getEnd() + " <p>" + getPriorityIndex() + System.lineSeparator();
    }

    @Override
    public String toString() {
        if (getDescription().isBlank()) {
            return "\t [C]" + "[" + getStatusIcon() + "] " + className + " (from: " + start
                    + " to: " + end + ")" + " (" + getPriority() + ")";
        }
        return "\t [C]" + "[" + getStatusIcon() + "] " + className + ": " + getDescription() +
                " (from: " + start + " to: " + end + ")" + " (" + getPriority() + ")";
    }
}
