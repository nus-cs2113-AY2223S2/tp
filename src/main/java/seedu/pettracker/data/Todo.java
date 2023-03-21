package seedu.pettracker.data;

public class Todo {
    protected String description;
    protected boolean isDone;

    public Todo(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return '[' + (isDone ? "X" : " ") + ']';
    }

}
