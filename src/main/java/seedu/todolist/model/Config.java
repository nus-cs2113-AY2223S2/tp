//@@author clement559
package seedu.todolist.model;

import seedu.todolist.constants.Formats;

import java.time.LocalDateTime;

public class Config {

    // Repeating tasks will recur every this number of days
    private int repeatFrequency = 7;

    // Program will check for repeating tasks every this number of minutes
    private int checkFrequency = 0;
    private LocalDateTime lastChecked = LocalDateTime.now();

    /**
     * Returns the configuration settings in a specified string format.
     *
     * @return String containing configuration settings
     */
    public String toString() {
        return String.format(Formats.CONFIG_STRING, repeatFrequency, checkFrequency);
    }

    /**
     * Reset configuration settings to default.
     *
     */
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
