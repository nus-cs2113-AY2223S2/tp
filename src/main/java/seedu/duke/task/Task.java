package seedu.duke.task;

public class Task {
    private String description;
    private String deadline;
    private boolean isDone;
    public Task(String description, String deadline) {
        this.description = description;
        this.deadline = deadline;
        this.isDone = false;
    }

    public String getDescription() {
        return this.description;
    }

    public String getDeadline() {
        return this.deadline;
    }

    public boolean getIsDone() {
        return this.isDone;
    }
    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }
    public void editDeadline (String deadline) {
        this.deadline = deadline;
    }

    public String toString() {
        return "[" + (isDone ? "X" : " ") + "] " + description + " due by: " + deadline;
    }
}
