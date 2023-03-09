package seedu.duke;

import java.util.ArrayList;
import java.util.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EventList {
    protected ArrayList<Event> taskList;
    protected int listSize;
    private static DateTimeFormatter dfWithTime = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");

    private static String DTINIT = "2000/01/01 01:01";
    
    public EventList() {
        this.taskList = new ArrayList<Event>();
        this.listSize = 0;
    }

    public EventList(ArrayList<Event> events) {
        this.taskList = events;
        this.listSize = events.size();
    }

    public int getSize() {
        return listSize;
    }

    public String getDetails(int index) {
        return taskList.get(index).toString();
    }

    public void deleteThisTask(int index) {
        taskList.remove(index);
        listSize--;
    }

    private LocalDateTime changeToDate(String time, String date) {
        String combination = date + " " + time;
        return LocalDateTime.parse(combination, dfWithTime);
    }

    private LocalDateTime changeToDate(String date) {
        return LocalDateTime.parse(date + " 00:00", dfWithTime);
    }

    public void addEvent(String description, String startTime, String startDay, String endTime,
            String endDay) {

        boolean hasStTime = true;
        boolean hasEdTime = false;
        LocalDateTime combinedStartTime = LocalDateTime.parse(DTINIT, dfWithTime);
        LocalDateTime combinedEndTime = LocalDateTime.parse(DTINIT, dfWithTime);

        if (startTime.equals("")) {
            hasStTime = false;
            combinedStartTime = changeToDate(startDay);
        } else {
            combinedStartTime = changeToDate(startTime, startDay);
        }

        if (endTime.equals("")) {
            hasEdTime = false;
            combinedEndTime = changeToDate(endDay);
        } else{
            combinedEndTime = changeToDate(startTime, startDay);
        }

        Event newEvent = new Event(description, combinedStartTime, combinedEndTime, hasStTime, hasEdTime);
        taskList.add(newEvent);
        listSize++;
    }

    public void addEvent(String description, String startTime, String startDay) {
        boolean hasStTime = true;
        LocalDateTime combinedStartTime = LocalDateTime.parse(DTINIT, dfWithTime);

        if (startTime.equals("")) {
            hasStTime = false;
            combinedStartTime = changeToDate(startDay);
        } else {
            combinedStartTime = changeToDate(startTime, startDay);
        }

        Event newEvent = new Event(description, combinedStartTime, hasStTime);
        taskList.add(newEvent);
        listSize++;
    }

    public ArrayList<Event> getFullList() {
        return this.taskList;
    }
}
