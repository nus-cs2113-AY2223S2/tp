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
     */
    public void reset() {
        repeatFrequency = 7;
        checkFrequency = 0;
    }

    /**
     * Checks if this configuration settings is still valid after loading it from the save file.
     * Certain attributes if deleted or edited to invalid values will cause the configs to be invalid.
     *
     * @return If this configuration settings is still valid.
     */
    public boolean isValid() {
        if (repeatFrequency < 1 || checkFrequency < 0) {
            // Repeat frequency must be positive; check frequency must not be negative
            return false;
        }
        // Last checked date-time cannot be after the current date-time
        return !lastChecked.isAfter(LocalDateTime.now());
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
