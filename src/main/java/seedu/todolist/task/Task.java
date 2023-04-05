package seedu.todolist.task;

import seedu.todolist.constants.Formats;
import seedu.todolist.logic.FormatterUtil;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.StringJoiner;
import java.util.TreeSet;
import java.util.function.Predicate;

public class Task implements Serializable {
    //@@author KedrianLoh
    /**
     * Comparator for the task class, used for sorting the task list by deadline.
     * Tasks without deadlines are placed at the bottom.
     */
    public static Comparator<Task> deadlineComparator = (task1, task2) -> {
        if (task1.getDeadline() == null) {
            return 1;
        }
        if (task2.getDeadline() == null) {
            return -1;
        }
        return task1.deadline.compareTo(task2.deadline);
    };

    //@@author clement559
    /**
     * Comparator for the task class, used for sorting the task list by deadline.
     * Tasks without deadlines are placed at the bottom.
     */
    public static Comparator<Task> priorityComparator = (task1, task2) -> {
        if (task1.priority > task2.priority) {
            return -1;
        } else if (task1.priority == task2.priority) {
            return 0;
        } else {
            return 1;
        }
    };

    //@@author jeromeongithub
    int id;
    private String description;
    private String email;
    private LocalDateTime deadline;
    private TreeSet<String> tags;
    private boolean isDone = false;
    private int repeatDuration;
    private int priority;

    public Task(int id, String description, LocalDateTime deadline, String email, TreeSet<String> tags,
                int repeatDuration, int priority) {
        this.id = id;
        this.description = description;
        this.email = email;
        this.deadline = deadline;
        this.tags = tags;
        this.repeatDuration = repeatDuration;
        this.priority = priority;
    }

    public String toString() {
        String descriptionString = description.length() > 35 ? description.substring(0, 35) + "..." : description;
        String isDoneString = isDone ? "X" : (isDue() ? "!" : " ");
        String taskString = String.format(Formats.TASK_STRING, id, isDoneString, descriptionString);
        if (deadline != null) {
            taskString += "[Due by: " + FormatterUtil.getDeadlineAsString(deadline) + "]";
        }
        return taskString;
    }

    public String getFullInfo() {
        StringJoiner infoString = new StringJoiner(System.lineSeparator());
        infoString.add("Description: " + description);
        infoString.add("Completed: " + (isDone ? "Yes" : (isDue() ? "Overdue" : "No")));
        infoString.add("Priority: " + FormatterUtil.getPriorityAsString(priority));
        if (deadline != null) {
            infoString.add("Due: " + FormatterUtil.getDeadlineAsString(deadline));
        }
        if (email != null) {
            infoString.add("Email: " + email);
        }
        if (!tags.isEmpty()) {
            infoString.add("Tags: " + FormatterUtil.getTagsAsString(tags));
        }
        if (repeatDuration > 0) {
            infoString.add("Repeat duration: " + repeatDuration);
        }
        return infoString.toString();
    }

    public int getId() {
        return id;
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

    public TreeSet<String> getTags() {
        return tags;
    }

    public boolean isDone() {
        return this.isDone;
    }

    public int getRepeatDuration() {
        return this.repeatDuration;
    }

    public int getPriority() {
        return this.priority;
    }

    public String setDone(boolean isDone) {
        this.isDone = isDone;
        return toString();
    }

    public String setDescription(String description) {
        this.description = description;
        return toString();
    }

    public String setEmail(String email) {
        this.email = email;
        return toString();
    }

    public String setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
        return toString();
    }

    public String setRepeatDuration(int newRepeatDuration) {
        this.repeatDuration = newRepeatDuration;
        return toString();
    }

    public String setTags(TreeSet<String> tags) {
        this.tags = tags;
        return toString();
    }

    public String setPriority(int priority) {
        this.priority = priority;
        return toString();
    }

    public String addTags(TreeSet<String> tags) {
        this.tags.addAll(tags);
        return toString();
    }

    public String removeTags(TreeSet<String> tags) {
        this.tags.removeAll(tags);
        return toString();
    }

    public boolean isDue() {
        return deadline != null && !deadline.isAfter(LocalDateTime.now());
    }

    public static Predicate<Task> isDonePredicate() {
        return task -> task.isDone;
    }

    public static Predicate<Task> beforeDate(LocalDate date) {
        return task -> task.deadline != null && task.deadline.toLocalDate().isBefore(date);
    }

    public static Predicate<Task> afterDate(LocalDate date) {
        return task -> task.deadline != null && task.deadline.toLocalDate().isAfter(date);
    }

    //@@author clement559
    public static Predicate<Task> isOverdue() {
        return task -> !task.isDone && task.isDue();
    }
}
