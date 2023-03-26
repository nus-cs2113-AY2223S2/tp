package seedu.duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Schedule {
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");

    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private boolean hasEndInfo;
    private boolean hasStartTime;
    private boolean hasEndTime;

    private boolean isRecurring;
    // format: x D for x day, or x W for x weeks and x M for x month.
    private String timeInterval;

    public Schedule(LocalDateTime start, boolean hasSt) {
        this.startTime = start;
        this.hasEndTime = false;
        this.hasStartTime = hasSt;
        this.hasEndInfo = false;
        this.isRecurring = false;
    }

    public Schedule(LocalDateTime start, boolean hasSt, String recurringTime) {
        this.startTime = start;
        this.hasEndTime = false;
        this.hasStartTime = hasSt;
        this.hasEndInfo = false;
        this.isRecurring = true;
        this.timeInterval = recurringTime;
    }

    public Schedule(LocalDateTime start, LocalDateTime end, boolean hasSt, boolean hasEd) {
        this.startTime = start;
        this.endTime = end;
        this.hasEndInfo = true;
        this.hasStartTime = hasSt;
        this.hasEndTime = hasEd;
        this.isRecurring = false;
    }

    public Schedule(LocalDateTime start, LocalDateTime end, boolean hasSt, boolean hasEd,
            String recurringTime) {
        this.startTime = start;
        this.endTime = end;
        this.hasEndInfo = true;
        this.hasStartTime = hasSt;
        this.hasEndTime = hasEd;
        this.isRecurring = false;
        this.timeInterval = recurringTime;
    }

    private String getOutputFormat(LocalDateTime timeInfo, boolean hasTimeDetail) {
        if (!hasTimeDetail) {
            String timeInString = formatter.format(timeInfo).split(" ")[0];
            return timeInString;
        }
        return formatter.format(timeInfo);
    }

    public String getTime() {
        String recur = " not recurring";
        if(isRecurring) {
            recur = " recurring, time intervel: " + timeInterval;
        }

        if (hasEndInfo) {
            return getOutputFormat(startTime, hasStartTime) + " to " + getOutputFormat(endTime, hasEndTime) + recur;
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

    // fuinction mayhbe useful in arlarm?
    public ArrayList<Schedule> getNextNTimes(int n) {
        ArrayList<Schedule> list = new ArrayList<>();
        String[] details = timeInterval.split(" ");
        int actualDays = 0;
        switch(details[1].trim()) {
        case("D"):
            actualDays = Integer.parseInt(details[0].trim());
            break;
        case("W"):
            actualDays = Integer.parseInt(details[0].trim()) *7;
            break;                
        case("M"):
            // dk how to do this, once in a month does it mean once in 30 days or 31?
            //or we just stipulate it as 30......?
            break;
        default:
            //exceptions
            break;
        }
        
        for(int i =0; i <n; i++) {
            startTime =  startTime.plusDays(actualDays);
            endTime = startTime.plusDays(actualDays);
            
            
        }
        
        return list;
    }

    // public ArrayList<Schedule> schedulesInThisWeek() {

    // }

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

    public boolean hasEndTime() {
        return hasEndTime;
    }

    public boolean hasStartTime() {
        return hasStartTime;
    }

    public boolean hasEndInfo() {
        return hasEndInfo;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public String getDescription() {
        return "";
    }
}
