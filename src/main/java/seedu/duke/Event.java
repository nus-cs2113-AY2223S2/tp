package seedu.duke;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

public class Event {
    private String description;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private boolean hasEndInfo;
    private boolean hasStartTime;
    private boolean hasEndTime;

    static SimpleDateFormat dateTimeFormatter = new SimpleDateFormat("yyyy/MM/dd HH:mm");

    public Event(String eventDescription, LocalDateTime start, LocalDateTime End, boolean hasSt, boolean hasEd) {
        this.description = eventDescription;
        this.startTime = start;
        this.endTime = End;
        this.hasEndInfo = true;
        this.hasStartTime = hasSt;
        this.hasEndTime = hasEd;
    }

    public Event(String eventDescription, LocalDateTime start, boolean hasSt) {
        this.description = eventDescription;
        this.startTime = start;
        this.hasEndTime = false;
        this.hasStartTime = hasSt;
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
