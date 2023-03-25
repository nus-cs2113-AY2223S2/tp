package seedu.pettracker.data;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return '[' + (isDone ? "X" : " ") + ']';
    }

    public String saveFormat() {
        String toSave = "0";
        if (isDone) {
            toSave = "1";
        }
        return toSave + "|" + description;
    }
}
