package seedu.duck.task;

import java.time.DayOfWeek;
public class RecurringDeadline extends Deadline{
    private DayOfWeek day;

    public RecurringDeadline(String description, String by, DayOfWeek day) {
        super(description, by);
        this.day = day;
    }

    @Override
    public String toSaveString() {
        return getDoneConditionString() + " " + getDescription() + " /by " + getDeadline() + " <p>" +
                getPriorityIndex() + " /day " + day + System.lineSeparator();
    }

    @Override
    public String toString() {
        return "\t [D]" + "[" + getStatusIcon() + "] " + getDescription() + " (by: " + super.by + ")" +
                " (every " + day + ") (" + getPriority() + ")";
    }
}
