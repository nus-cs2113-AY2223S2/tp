package seedu.duke;

// import java.time.LocalDate;
import java.time.LocalDateTime;
// import java.util.ArrayList;

public class Event extends Schedule {
    private boolean isModule;
    private String description;

    public Event(String eventDescription, LocalDateTime start, LocalDateTime end, boolean hasSt,
            boolean hasEd) {
        super(start, end, hasSt, hasEd);
        this.description = eventDescription;
    }

    public Event(String eventDescription, LocalDateTime start, boolean hasSt) {
        super(start, hasSt);
        this.description = eventDescription;
    }

    public Event(String eventDescription, LocalDateTime start, LocalDateTime end, boolean hasSt,
            boolean hasEd, String recurTime) {
        super(start, end, hasSt, hasEd, recurTime);
        this.description = eventDescription;
    }

    public Event(String eventDescription, LocalDateTime start, boolean hasSt, String recurTime) {
        super(start, hasSt, recurTime);
        this.description = eventDescription;
    }

    public Event(String eventDescription, LocalDateTime start, LocalDateTime end, String location, 
            boolean hasSt, boolean hasEd, boolean hasLocation) {
        super(start, end, location, hasSt, hasEd, hasLocation);
        this.description = eventDescription;
    }

    public Event(String eventDescription, LocalDateTime start, String location, boolean hasSt, 
            boolean hasLocation) {
        super(start, location, hasSt, hasLocation);
        this.description = eventDescription;
    }

    public Event(String eventDescription, LocalDateTime start, LocalDateTime end, String location, 
            boolean hasSt, boolean hasEd, boolean hasLocation, String recurTime) {
        super(start, end, location, hasSt, hasEd, hasLocation, recurTime);
        this.description = eventDescription;
    }

    public Event(String eventDescription, LocalDateTime start, String location, boolean hasSt, 
            boolean hasLocation, String recurTime) {
        super(start, location, hasSt, hasLocation, recurTime);
        this.description = eventDescription;
    }

    public String getDescription() {
        return description;
    }

    public String toString() {
        return "[E] " + getDescription() + " (" + super.getTime() + ")"
             + (hasLocation() ? ("\n         @ " + getLocation()) : "");
    }

}
