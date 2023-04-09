package seedu.duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Schedule {
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");

    private static final String RECUR_DAILY = "D";
    private static final String RECUR_WEEKLY = "W";
    private static final String RECUR_MONTHLY = "M";

    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private boolean hasEndInfo;
    private boolean hasStartTime;
    private boolean hasEndTime;

    private boolean isRecurring;
    // format: x D for x day, or x W for x weeks and x M for x month.
    private String timeInterval;

    private String location;
    private boolean hasLocation;

    public Schedule(LocalDateTime start, boolean hasSt) {
        this(
            start,
            "", 
            "",
            hasSt,
            false,
            false
        );
    }

    public Schedule(LocalDateTime start, boolean hasSt, String recurringTime) {
        this(
            start,
            "",
            recurringTime,
            hasSt,
            false,
            true
        );
    }

    public Schedule(LocalDateTime start, LocalDateTime end, boolean hasSt, boolean hasEd) {
        this(
            start,
            end,
            "",
            "",
            hasSt,
            hasEd,
            false,
            false
        );
    }

    public Schedule(LocalDateTime start, LocalDateTime end, boolean hasSt, boolean hasEd,
            String recurringTime) {
        this(
            start,
            end,
            "",
            recurringTime,
            hasSt,
            hasEd,
            false,
            true
        );
    }

    public Schedule(LocalDateTime start, String location, boolean hasSt, boolean hasLocation) {
        this(
            start,
            location,
            "",
            hasSt,
            hasLocation,
            false
        );
    }

    public Schedule(LocalDateTime start, LocalDateTime end, String location, boolean hasSt, boolean hasEd,
            boolean hasLocation) {
        this(
            start,
            end,
            location,
            "",
            hasSt,
            hasEd,
            hasLocation,
            false
        );
    }
    
    public Schedule(LocalDateTime start, String location, boolean hasSt, boolean hasLocation,
            String recurringTime) {
        this(
            start,
            location,
            recurringTime,
            hasSt,
            hasLocation,
            true
        );
    }
    
    public Schedule(LocalDateTime start, LocalDateTime end, String location, boolean hasSt, boolean hasEd,
            boolean hasLocation, String recurringTime) {
        this(
            start,
            end,
            location,
            recurringTime,
            hasSt,
            hasEd,
            hasLocation,
            true
        );
    }

    public Schedule(LocalDateTime start, LocalDateTime end, String location, String recurringTime, boolean hasSt,
        boolean hasEd, boolean hasLocation, boolean isRecurring) {
        this.startTime = start;
        this.endTime = end;
        this.hasEndInfo = true;
        this.hasStartTime = hasSt;
        this.hasEndTime = hasEd;
        this.isRecurring = isRecurring;
        this.timeInterval = recurringTime;
        this.hasLocation = hasLocation;
        this.location = location;
    }

    public Schedule(LocalDateTime start, String location, String recurringTime, boolean hasSt,
            boolean hasLocation, boolean isRecurring) {
        this.startTime = start;
        this.hasEndInfo = false;
        this.hasStartTime = hasSt;
        this.hasEndTime = false;
        this.isRecurring = isRecurring;
        this.timeInterval = recurringTime;
        this.hasLocation = hasLocation;
        this.location = location;
    }

    private String getOutputFormat(LocalDateTime timeInfo, boolean hasTimeDetail) {
        if (!hasTimeDetail) {
            String timeInString = formatter.format(timeInfo).split(" ")[0];
            return timeInString;
        }
        return formatter.format(timeInfo);
    }

    public boolean isRecurring() {
        return isRecurring;
    }

    /**
     * Get a String describing time information.
     * @return String containing time information, [start time] to [end time] [recur and time interval]
     */
    public String getTime() {
        String recur = " not recurring";
        if (isRecurring) {
            String[] details = timeInterval.split(" ");
            String interval = "";
            switch (details[1].trim()) {
            case RECUR_DAILY:
                interval = "Day(s)";
                break;
            case RECUR_WEEKLY:
                interval = "Week(s)";
                break;
            case RECUR_MONTHLY:
                interval = "Month(s)";
                break;
            default:
                break;
            }
            recur = " | recurring, time interval: " + details[0] + " " + interval;
        }

        if (hasEndInfo) {
            return getOutputFormat(startTime, hasStartTime) + " to " + getOutputFormat(endTime, hasEndTime)
                    + recur;
        } else {
            return getOutputFormat(startTime, hasStartTime) + recur;
        }
    }

    public void changeTimeInfo(LocalDateTime start, LocalDateTime end, boolean hasSt, boolean hasEd) {
        this.startTime = start;
        this.endTime = end;
        this.hasEndInfo = true;
        this.hasStartTime = hasSt;
        this.hasEndTime = hasEd;
    }

    public void changeTimeInfo(LocalDateTime start, boolean hasSt) {
        this.startTime = start;
        this.hasEndTime = false;
        this.hasStartTime = hasSt;
        this.hasEndInfo = false;
    }

    /**
     * Calculate time interval in days.
     * @return time interval in days.
     */
    public int getActualInterval() {
        String[] details = this.timeInterval.split(" ");

        int actualDays = 0;
        switch (details[1].trim()) {
        case RECUR_DAILY:
            actualDays = Integer.parseInt(details[0].trim());
            break;
        case RECUR_WEEKLY:
            actualDays = Integer.parseInt(details[0].trim()) * 7;
            break;
        default:
            break;
        }
        return actualDays;
    }

    /**
     * Add recurring events to event list.
     */
    public ArrayList<Schedule> getNextNTimes(int n) {
        ArrayList<Schedule> list = new ArrayList<>();
        int actualDays = getActualInterval();

        LocalDateTime stTime = startTime;
        LocalDateTime edTime = endTime;

        for (int i = 0; i < n; i++) {
            stTime = stTime.plusDays(actualDays);
            edTime = edTime.plusDays(actualDays);

            list.add(new Schedule(stTime, edTime, true, true, timeInterval));
        }

        return list;
    }

    // need exception
    public void reviseTimeinterval(String recurringTime) {
        this.timeInterval = recurringTime;
    }

    public String getTimeInterval() {
        if (isRecurring) {
            return timeInterval;
        } else {
            return "Not a recurring event!";
        }
    }

    public void changeLocation(String location) {
        if (!location.equals("")) {
            hasLocation = true;
        }
        this.location = location;
    }

    public boolean hasEndTime() {
        return hasEndTime;
    }

    public boolean hasStartTime() {
        return hasStartTime;
    }

    public boolean hasEndInfo() {
        return hasEndInfo;
    }

    public boolean hasLocation() {
        return hasLocation;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public String getLocation() {
        return location;
    }

    public String getDescription() {
        return "";
    }
}
