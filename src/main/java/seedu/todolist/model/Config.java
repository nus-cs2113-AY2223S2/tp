//@@author clement559
package seedu.todolist.model;

import seedu.todolist.constants.Formats;

import java.time.LocalDateTime;

public class Config {
    private int repeatFrequency = 7;
    private int checkFrequency = 0;
    private LocalDateTime lastChecked = LocalDateTime.now();

    public String toString() {
        return String.format(Formats.CONFIG_STRING, repeatFrequency, checkFrequency);
    }

    public void reset() {
        repeatFrequency = 7;
        checkFrequency = 0;
    }

    public void setCheckFrequency(int checkFrequency) {
        this.checkFrequency = checkFrequency;
    }

    public void setRepeatFrequency(int repeatFrequency) {
        this.repeatFrequency = repeatFrequency;
    }

    public void setLastChecked(LocalDateTime lastChecked) {
        this.lastChecked = lastChecked;
    }

    public int getCheckFrequency() {
        return checkFrequency;
    }

    public int getRepeatFrequency() {
        return repeatFrequency;
    }

    public LocalDateTime getLastChecked() {
        return lastChecked;
    }
}
