package seedu.todolist.task;

import seedu.todolist.constants.Formats;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.HashSet;

public class Task implements Serializable {
    //@@author KedrianLoh
    /**
     * Comparator for the task class, used for sorting the task list by deadline.
     * Tasks without deadlines are placed at the bottom.
     */
    public static Comparator<Task> taskDeadlineComparator = (task1, task2) -> {
        if (task1.getDeadline() == null) {
            return 1;
        }
        if (task2.getDeadline() == null) {
            return -1;
        }
        return task1.deadline.compareTo(task2.deadline);
    };

    //@@author jeromeongithub
    private String description;
    private String email;
    private LocalDateTime deadline;
    private HashSet<String> tags;
    private boolean isDone = false;
    private int repeatDuration;

    public Task(String description, LocalDateTime deadline, String email, HashSet<String> tags, int repeatDuration) {
        this.description = description;
        this.email = email;
        this.deadline = deadline;
        this.tags = tags;
        this.repeatDuration = repeatDuration;
    }

    public Task(Task task) {
        description = task.description;
        email = task.email;
        deadline = task.deadline;
        tags = task.tags;
        isDone = task.isDone;
        repeatDuration = task.repeatDuration;
    }

    public String toString() {
        String taskString = "[" + (isDone ? "X" : " ") + "] " + description;
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern(Formats.TIME_OUT.getFormat());
        if (deadline == null) {
            return taskString;
        }
        return taskString + ", due: " + deadline.format(outputFormatter);
    }

    public String getDescription() {
        return this.description;
    }

    public String getEmail() {
        return email;
    }

    public LocalDateTime getDeadline() {
        return this.deadline;
    }

    public HashSet<String> getTags() {
        return tags;
    }

    public boolean isDone() {
        return this.isDone;
    }

    public int getRepeatDuration() {
        return this.repeatDuration;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    public void setRepeatDuration(int newRepeatDuration) {
        this.repeatDuration = newRepeatDuration;
    }

    public void addTags(HashSet<String> tags) {
        this.tags.addAll(tags);
    }

    public void deleteTags(HashSet<String> tags) {
        this.tags.removeAll(tags);
    }
}
