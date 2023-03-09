package seedu.duck.task;

public class Task {
    private static int taskCount = 0;
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    public String getStatusIcon() {
        if(isDone) {
            // Mark done task with X
            return "X";
        }
        return " ";
    }

    public static void incrementCount() {
        taskCount++;
    }

    public static void decrementCount() {
        taskCount--;
    }

    public static int getTaskCount() {
        return taskCount;
    }

    public String getDoneConditionString() {
        if (isDone) {
            return "1";
        }
        return "0";
    }

    public String toSaveString() {
        return getDoneConditionString() + " " + getDescription();
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + getDescription();
    }
}
