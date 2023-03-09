package seedu.duke;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Event extends Task {
    private String description;
    private Date startTime;
    private Date endTime;
    private boolean hasEndTime;

    static SimpleDateFormat dateTimeFormatter = DateTimeFormatter.ofPattern("MMM/dd/yyyy HH:mm");

    public Event(String eventDescription, Date start, Date End) {
        this.description = eventDescription;
        this.startTime = start;
        this.endTime = End;
        this.hasEndTime = true;
    }

    public Event(String eventDescription, Date start) {
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
