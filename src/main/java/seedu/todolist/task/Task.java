package seedu.todolist.task;

import seedu.todolist.constants.Formats;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;

public class Task implements Serializable {
    //@@ KedrianLoh
    /**
     * Compares the task1 deadline and task2 deadline, used for sorting the task list by deadline.
     */
    public static Comparator<Task> taskDeadlineComparator = Comparator.comparing(task -> task.deadline);

    //@@ jeromeongithub
    private String description;
    private LocalDateTime deadline;
    private boolean isDone = false;

    public Task(String description, LocalDateTime deadline) {
        this.description = description;
        this.deadline = deadline;
    }

    public String toString() {
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern(Formats.TIME_OUT.FORMAT);
        return "[" + (isDone ? "X" : " ") + "] " + description + ", due: " + deadline.format(outputFormatter);
    }

    public boolean isDone() {
        return this.isDone;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    //@@ clement559
    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }
}
