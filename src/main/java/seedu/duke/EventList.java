package seedu.duke;

import java.util.ArrayList;

// import seedu.duke.storage.EventListAdapter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static seedu.duke.Parser.SEMESTER_START_DATES;
import static seedu.duke.Parser.SEMESTER_END_DATES;
import static seedu.duke.UserUtility.getUser;


public class EventList {
    private static final String DTINIT = "2000/01/01 01:01";
    private static final String TIMEPLACEHOLDER = " 00:00";
    private static DateTimeFormatter dfWithTime = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");

    // exceptions information
    private static final String WRONG_DATE_TIME_FORMAT_E = 
        "Wrong date/time format! \nPlease use yyyy/MM/dd for date and HH:mm for time.";
    private static final String START_TIME_AFTER_END_TIME_E = "Starting time is after ending time!";
    private static final String TIME_CONFLICTION_E = "Events/classes confliction!";
    private static final String EVENT_NOT_EXIST_E = "Event cannot be found!";

    protected ArrayList<Schedule> taskList;
    protected int listSize;

    public EventList() {
        this.taskList = new ArrayList<Schedule>();
        this.listSize = 0;
    }

    public EventList(ArrayList<Schedule> events) {
        this.taskList = events;
        this.listSize = events.size();
    }

    public int getSize() {
        return listSize;
    }

    public String getDetails(int index) {
        return taskList.get(index).toString();
    }

    public String getDescription(int index) {
        return taskList.get(index).getDescription();
    }

    public String getTime(int index) {
        return taskList.get(index).getTime();
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
        return LocalDateTime.parse(date + TIMEPLACEHOLDER, dfWithTime);
    }

    /**
     * @param time String representing Time to be converted to dateTime format in combinedTime. Format "HH:MM".
     * @param day String representing Date to be converted to dateTime format in combinedTime. Format "YYYY/MM/DD".
     * @return TimeAndFlag
     * @throws NPExceptions if format of time or day is not as specified above
     * @see TimeAndFlag
     */
    public TimeAndFlag convertToTimeInfo(String time, String day) throws NPExceptions {
        try {
            boolean hasTime = true;
            LocalDateTime combinedTime = LocalDateTime.parse(DTINIT, dfWithTime);

            if (time.equals("")) {
                hasTime = false;
                combinedTime = changeToDate(day);
            } else {
                combinedTime = changeToDate(time, day);
            }

            TimeAndFlag result = new TimeAndFlag(hasTime, combinedTime);
            return result;
        } catch (Exception e) {
            throw new NPExceptions(WRONG_DATE_TIME_FORMAT_E);
        }
    }

    private boolean checkSingleOverlap(Schedule eventA, Schedule eventB) {
        return ((eventA.getStartTime().compareTo(eventB.getStartTime()) >= 0
                && eventA.getStartTime().compareTo(eventB.getEndTime()) <= 0)
                || (eventA.getEndTime().compareTo(eventB.getStartTime()) >= 0
                        && eventA.getEndTime().compareTo(eventB.getEndTime()) <= 0));
    }

    /**
     * Check whether there is overlap between different events.
     * @param newEvent the new event to be added.
     * @param index newEvent will not check overlap with index event in event list.
     * @return true if there is no overlap.
     */
    public boolean canAddNewEvent(Event newEvent, int index) {
        boolean isOverlap = true;
        for (int i = 0; i < listSize; i++) {
            if (!taskList.get(i).hasEndInfo() || i == index) {
                continue;
            }

            Event eventA = (taskList.get(i) instanceof Event) ? (Event) taskList.get(i) : null;
            Event eventB = newEvent;

            LocalDate semStart = SEMESTER_START_DATES.get(getUser().getSemester());
            LocalDate semEnd = SEMESTER_END_DATES.get(getUser().getSemester());
            LocalDate eventADate = eventA.getStartTime().toLocalDate();
            LocalDate eventBDate = eventB.getStartTime().toLocalDate();

            long minKa = !eventA.isRecurring() || eventADate.isAfter(semStart) ? 0
                    : (semStart.toEpochDay() - eventADate.toEpochDay()) / eventA.getActualInterval();
            long maxKa = !eventA.isRecurring() || eventADate.isAfter(semEnd) ? 0
                    : (semEnd.toEpochDay() - eventADate.toEpochDay()) / eventA.getActualInterval();
            long minKb = !eventB.isRecurring() || eventBDate.isAfter(semStart) ? 0
                    : (semStart.toEpochDay() - eventBDate.toEpochDay()) / eventB.getActualInterval();
            long maxKb = !eventB.isRecurring() || eventBDate.isAfter(semEnd) ? 0
                    : (semEnd.toEpochDay() - eventBDate.toEpochDay()) / eventB.getActualInterval();
            boolean breakFlag = false;

            for (long j = minKa; j <= maxKa; j++) {
                for (long k = minKb; k <= maxKb; k++) {
                    Schedule curA = new Schedule(eventA.getStartTime(), eventA.getEndTime(), true, true);
                    Schedule curB = new Schedule(eventB.getStartTime(), eventB.getEndTime(), true, true);

                    if (maxKa != 0) {
                        curA = new Schedule(eventA.getStartTime().plusDays(eventA.getActualInterval() * j),
                                eventA.getEndTime().plusDays(eventA.getActualInterval() * j), true, true);
                    }
                    if (maxKb != 0) {
                        curB = new Schedule(eventB.getStartTime().plusDays(eventB.getActualInterval() * k),
                                eventB.getEndTime().plusDays(eventB.getActualInterval() * k), true, true);
                    }

                    if ((checkSingleOverlap(curA, curB) || checkSingleOverlap(curB, curA))) {
                        isOverlap = false;

                        Ui.printOverlapInfo(eventA.toString(), curA.getTime());
                        breakFlag = true;
                        break;
                    }
                }
                if (breakFlag) {
                    break;
                }
            }
        }
        return isOverlap;
    }

    /**
     * Add an event.
     * @param description Event description, describes what the Event is.
     * @param startTime Start time of the Event. Format "HH:MM".
     * @param startDay Start day of the Event. Format "YYYY/MM/DD".
     * @param endTime End time of the Event. Foramt "HH:MM".
     * @param endDay End day of the Event. Format "YYYY/MM/DD".
     * @throws NPExceptions when start time is after end time or 
     * new event has time conflict with events already exist.
     */
    public void addEvent(String description, String startTime, String startDay, String endTime, String endDay)
            throws NPExceptions {

        TimeAndFlag startInfo = convertToTimeInfo(startTime, startDay);
        TimeAndFlag endInfo = convertToTimeInfo(endTime, endDay);
        Event newEvent =
                new Event(description, startInfo.time, endInfo.time, startInfo.hasInfo, endInfo.hasInfo);

        if (newEvent.getStartTime().isAfter(newEvent.getEndTime())) {
            throw new NPExceptions(START_TIME_AFTER_END_TIME_E);
        }
        if (!canAddNewEvent(newEvent, -1)) {
            throw new NPExceptions(TIME_CONFLICTION_E);
        }

        taskList.add(newEvent);
        listSize++;
    }

    public void addEvent(String description, String startTime, String startDay) throws NPExceptions {
        TimeAndFlag startInfo = convertToTimeInfo(startTime, startDay);

        Event newEvent = new Event(description, startInfo.time, startInfo.hasInfo);
        taskList.add(newEvent);
        listSize++;
    }

    public void addEvent(String description, String startTime, String startDay, String recurTime)
            throws NPExceptions {
        TimeAndFlag startInfo = convertToTimeInfo(startTime, startDay);

        Event newEvent = new Event(description, startInfo.time, startInfo.hasInfo, recurTime);
        taskList.add(newEvent);
        listSize++;
    }

    /**
     * Add an event.
     * @param description Event description, describes what the Event is.
     * @param startTime Start time of the Event. Format "HH:MM".
     * @param startDay Start day of the Event. Format "YYYY/MM/DD".
     * @param endTime End time of the Event. Foramt "HH:MM".
     * @param endDay End day of the Event. Format "YYYY/MM/DD".
     * @throws NPExceptions when start time is after end time or 
     * new event has time conflict with events already exist.
     */

    /**
     * Add an Event.
     * @param description Event description, describes what the event is.
     * @param startTime Start time of the Event. Format "HH:MM".
     * @param startDay Start day of the Event. Format "YYYY/MM/DD".
     * @param endTime End time of the Event. Foramt "HH:MM".
     * @param endDay End day of the Event. Format "YYYY/MM/DD".
     * @param recurTime Recur time of the Event. 
     * @throws NPExceptions when start time is after end time or 
     * new event has time conflict with events already exist.
     */
    public void addEvent(String description, String startTime, String startDay, String endTime, String endDay,
            String recurTime) throws NPExceptions {

        TimeAndFlag startInfo = convertToTimeInfo(startTime, startDay);
        TimeAndFlag endInfo = convertToTimeInfo(endTime, endDay);

        Event newEvent = new Event(description, startInfo.time, endInfo.time, startInfo.hasInfo,
                endInfo.hasInfo, recurTime);

        if (newEvent.getStartTime().isAfter(newEvent.getEndTime())) {
            throw new NPExceptions(START_TIME_AFTER_END_TIME_E);
        }
        if (!canAddNewEvent(newEvent, -1)) {
            throw new NPExceptions(TIME_CONFLICTION_E);
        }

        taskList.add(newEvent);
        listSize++;
    }

    /**
     * Change location(vanue) field of an event.
     * @param index Index of the event to be changed in event list.
     * @param location New location.
     */
    public void reviseLocation(int index, String location) {
        taskList.get(index).changeLocation(location);
    }

    public void reviseTimeInfo(int index, String startTime, String startDay, String endTime, String endDay)
            throws NPExceptions {
        TimeAndFlag startInfo = convertToTimeInfo(startTime, startDay);
        TimeAndFlag endInfo = convertToTimeInfo(endTime, endDay);

        Event eventToCheck = new Event(taskList.get(index).getDescription(), startInfo.time, endInfo.time,
                startInfo.hasInfo, endInfo.hasInfo);
        if (!canAddNewEvent(eventToCheck, index)) {
            throw new NPExceptions(TIME_CONFLICTION_E);
        }

        taskList.get(index).changeTimeInfo(startInfo.time, endInfo.time, startInfo.hasInfo, endInfo.hasInfo);

    }

    /**
     * Change time field of an event.
     * @param index Index of the event to be changed in event list.
     * @param startTime New start time of the event. Format "HH:MM".
     * @param startDay New start day of the event. Format "YYYY/MM/DD".
     * @throws NPExceptions if Event does not exist.
     */
    public void reviseTimeInfo(int index, String startTime, String startDay) throws NPExceptions {
        TimeAndFlag startInfo = convertToTimeInfo(startTime, startDay);

        taskList.get(index).changeTimeInfo(startInfo.time, startInfo.hasInfo);
    }
   
    /**
     * Change time field of an event.
     * @param description Description of the event to be changed. 
     * This method will find the event from the event list according to description.
     * @param startTime New start time of the event. Format "HH:MM".
     * @param startDay New start day of the event. Format "YYYY/MM/DD".
     * @param endTime New end time of the event. Format "HH:MM".
     * @param endDay New end day of the event. Format "YYYY/MM/DD".
     * @throws NPExceptions if Event does not exist.
     */
    public void reviseTimeInfo(String description, String startTime, String startDay, String endTime,
            String endDay) throws NPExceptions {
        int index = searchTaskIndex(description);
        if (index == -1) {
            throw new NPExceptions(EVENT_NOT_EXIST_E);
        }
        reviseTimeInfo(index, startTime, startDay, endTime, endDay);
    }

    /**
     * Change time field of an event.
     * @param description Description of the event to be changed. 
     * This method will find the event from the event list according to description.
     * @param startTime New start time of the event. Format "HH:MM".
     * @param startDay New start day of the event. Format "YYYY/MM/DD".
     * @throws NPExceptions if Event does not exist.
     */
    public void reviseTimeInfo(String description, String startTime, String startDay) throws NPExceptions {
        int index = searchTaskIndex(description);
        if (index == -1) {
            throw new NPExceptions(EVENT_NOT_EXIST_E);
        }
        reviseTimeInfo(index, startTime, startDay);
    }

    /**
     * Get the full event list.
     * @return Arraylist of the full event list.
     */
    public ArrayList<Schedule> getFullList() {
        return this.taskList;
    }

    /**
     * Search an event according to its description in event list.
     * @param description description of the task to search.
     * @return index of the task in event list. Return -1 if it's not found.
     */
    public int searchTaskIndex(String description) {
        int index = 0;
        for (Schedule cur : taskList) {
            if (cur.getDescription().trim().equals(description.trim())) {
                return index;
            }
            index++;
        }
        return -1;
    }

    public String getLastTaskDescription() {
        return taskList.get(listSize - 1).toString();
    }

    /**
     * Delete all tasks in event list.
     */
    public void deleteAll() {
        if (this.listSize == 0) {
            Ui.deleteAllError();
        } else {
            this.taskList = new ArrayList<Schedule>();
            this.listSize = 0;
            Ui.deleteAllSuccess();
        }
    }
}


final class TimeAndFlag {
    public boolean hasInfo;
    public LocalDateTime time;

    public TimeAndFlag(boolean info, LocalDateTime timeInfo) {
        this.hasInfo = info;
        this.time = timeInfo;
    }
}
