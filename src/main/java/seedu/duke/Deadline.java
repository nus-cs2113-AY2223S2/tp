package seedu.duke;

public class Deadline {
    private String task;
    private String dueDate;

    public Deadline(String task, String dueDate) {
        this.task = task;
        this.dueDate = dueDate;
    }

    public String getDueDate() {
        return dueDate;
    }

    public String getTask() {
        return task;
    }

    @Override
    public String toString() {
        return task + "//" + dueDate;
    }
}
