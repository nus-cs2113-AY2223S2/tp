package seedu.todolist.logic;

import seedu.todolist.constants.Formats;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Config {
    private int checkFrequency = 0;
    private LocalDateTime lastChecked = LocalDateTime.now();
    private int repeatFrequency = 7;

    public String toString() {
        String lastCheckedString = FormatterUtil.getDeadlineAsString(lastChecked);
        return String.format(Formats.CONFIG_INFO.getFormat(), repeatFrequency, checkFrequency, lastCheckedString);
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
