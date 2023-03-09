package seedu.duke;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

public class Event {
    private String description;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private boolean hasEndTime;

    static SimpleDateFormat dateTimeFormatter = new SimpleDateFormat("MMM/dd/yyyy HH:mm");

    public Event(String eventDescription, LocalDateTime start, LocalDateTime End) {
        this.description = eventDescription;
        this.startTime = start;
        this.endTime = End;
        this.hasEndTime = true;
    }

    public Event(String eventDescription, LocalDateTime start) {
        this.description = eventDescription;
        this.startTime = start;
        this.hasEndTime = false;
    }

    public String getDescription() {
        return description;
    }

    public String getTime() {
        if (hasEndTime) {
            return dateTimeFormatter.format(startTime) + "to" + dateTimeFormatter.format(endTime);
        } else {
            return dateTimeFormatter.format(startTime);
        }
    }

    public String toString() {
        return "[E]" + getDescription() + " (" + getTime() + ")";
    }
}
