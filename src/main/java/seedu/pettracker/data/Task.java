package seedu.pettracker.data;

public class Task {
    protected String description;
    protected boolean isDone;
    protected String time;

    public Task(String description, String time) {
        this.description = description;
        this.time = time;
    }

    public Task(String description) {
        this(description, "");
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
