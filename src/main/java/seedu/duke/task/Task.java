package seedu.duke.task;

import java.util.StringJoiner;

public class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean getIsDone() {
        return this.isDone;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    public String toString() {
        return "[" + (isDone ? "X" : " ") + "] " + description;
    }

    public String toSaveString() {
        StringJoiner saveString = new StringJoiner(Storage.DELIMITER);
        String[] taskParameters = {isDone ? "1" : "0", description};
        for (String string : taskParameters) {
            saveString.add(string);
        }
        return saveString.toString();
    }
}

