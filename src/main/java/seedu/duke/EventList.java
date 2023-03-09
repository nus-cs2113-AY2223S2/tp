package seedu.duke;

import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EventList {
    protected ArrayList<Event> taskList;
    protected int listSize;
    private static DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

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
        String combination = time + " " + date;
        return LocalDateTime.parse(combination, df);
    }

    private LocalDateTime ChangeToDate(String date) {
        return LocalDateTime.parse(date, df);
    }

    public void addEvent(String description, String startTime, String startDay, String endTime,
            String endDay) {
        Event newEvent =
                new Event(description, changeToDate(startTime, startDay), changeToDate(endTime, endDay));
        taskList.add(newEvent);
        listSize++;
    }

    public void addEvent(String description, String startTime, String startDay) {
        Event newEvent =
                new Event(description, changeToDate(startTime, startDay));
        taskList.add(newEvent);
        listSize++;
    }

    public ArrayList<Event> getFullList() {
        return this.taskList;
    }
}
