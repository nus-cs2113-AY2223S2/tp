package seedu.duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Schedule{
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
    
    private String description;

    public Event(String eventDescription, LocalDateTime start, LocalDateTime end, boolean hasSt, boolean hasEd) {
        super(start, end, hasSt, hasEd);
        this.description = eventDescription;
    }

    public Event(String eventDescription, LocalDateTime start, boolean hasSt) {
        super(start, hasSt);
        this.description = eventDescription;
    }

    public String getDescription() {
        return description;
    }

    public String toString() {
        return "[E] " + getDescription() + " (" + super.getTime()+ ")";
    }

}
