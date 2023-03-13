package seedu.duke.task;

import seedu.duke.Storage;

import java.util.Comparator;
import java.util.StringJoiner;

public class Task {
    /**
     * Compares the task1 deadline and task2 deadline. Used for sorting the ArrayList tasks by deadline.
     */
    public static Comparator<Task> taskDeadlineComparator = new Comparator<Task>() {
        @Override
        public int compare(Task task1, Task task2) {
            return task1.deadline.compareTo(task2.deadline);
        }
    };

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

    public boolean isDone() {
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

    public String toSaveString() {
        StringJoiner saveString = new StringJoiner(Storage.DELIMITER);
        String[] taskParameters = {isDone ? "1" : "0", description, deadline};
        for (String string : taskParameters) {
            saveString.add(string);
        }
        return saveString.toString();
    }
}
