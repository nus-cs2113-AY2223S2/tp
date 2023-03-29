package seedu.pettracker.data;

import java.time.LocalDate;

public class Task {
    protected String description;
    protected boolean isDone;
    protected LocalDate deadline;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, LocalDate deadline) {
        this.description = description;
        this.isDone = false;
        this.deadline = deadline;
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
