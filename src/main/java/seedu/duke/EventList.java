package seedu.duke;

import java.util.ArrayList;

import seedu.duke.storage.EventListAdapter;

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
     * For two addEvent funcs below: if user doesn't input endDay(which means there is also no endTime), you can just call
     * .addEvent(description, startTime, startDay) I also make the specific time(hh:mm) optional, so if user doesn't input
     * the specfic time, you can just pass an empty String to that param and it will handle the rest things
     * e.g.addEvent(descrption, "", startDay, "", endDay) addEvent(descrption, "", startDay, endTime, endDay)
     * addEvent(descrption, "", startDay) so only startDay is strictly required. and the same for reviseTimeInfo()
     * 
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
            throw new NPExceptions(
                    "Wrong date/time format! \nPlease use yyyy/MM/dd for date and HH:mm for time.");
        }
    }

    private boolean checkSingleOverlap(Event eventA, Event eventB) {
        return ((eventA.getStartTime().isAfter(eventB.getStartTime()) && eventA.getStartTime().isBefore(eventB.getEndTime()))
                ||(eventA.getEndTime().isAfter(eventB.getStartTime()) && eventA.getEndTime().isBefore(eventB.getEndTime())));
    }

    public boolean canAddNewEvent(Event newEvent) {
        boolean isOverlap = false;
        for (int i =0; i< listSize; i++) {
            if (!taskList.get(i).isRecurring()) {
                if(!(checkSingleOverlap(newEvent, taskList.get(i)) && checkSingleOverlap(taskList.get(i), newEvent))) {
                    isOverlap = true;
                    Ui.printOverlapInfo(taskList.get(i).toString());
                    break;
                }    
            } else {
                Boolean swapFlag = false;
                Event eventA = (taskList.get(i) instanceof Event) ? taskList.get(i) : null;
                Event eventB = newEvent;
                if(eventA.getStartTime().isAfter(newEvent.getStartTime())) {
                    Event tmp = eventA;
                    eventA = eventB;
                    eventB = tmp;
                    swapFlag = true;
                }
                LocalDate semStart = SEMESTER_START_DATES.get(getUser().getSemester());
                LocalDate semEnd = SEMESTER_END_DATES.get(getUser().getSemester());
                LocalDate eventADate = eventA.getStartTime().toLocalDate();    
                LocalDate eventBDate = eventB.getStartTime().toLocalDate();
                
                int minKa = eventADate.isBefore(semStart) ? 0 : (eventADate.toEpochDay() - semStart.toEpochDay()) / eventA.getActualInterval();
                int maxKa = eventADate.isAfter(semEnd) ? 0 : (semEnd.toEpochDay() - eventADate.toEpochDay()) / eventA.getActualInterval();
                int minKb = eventBDate.isBefore(semStart) ? 0 : (eventBDate.toEpochDay() - semStart.toEpochDay()) / eventB.getActualInterval();
                int maxKb = eventBDate.isAfter(semEnd) ? 0 : (semEnd.toEpochDay() - eventBDate.toEpochDay()) / eventB.getActualInterval();
                
                if(maxKa != 0 && minKb != 0) {
                    for(int j = minKa; j<= maxKa; i++) {
                        for (int k = minKb; k <= maxKb; k++) {
                            Schedule curA = new Schedule(eventA.getStartTime().plusDays(eventA.getActualInterval()*j),
                                    eventA.getEndTime().plusDays(eventA.getActualInterval()*j, true, true));
                            Schedule curB = new Schedule(eventB.getStartTime().plusDays(eventB.getActualInterval()*k),
                                    eventB.getEndTime().plusDays(eventB.getActualInterval()*k, true, true));
                        
                            if((checkSingleOverlap(curA, curB) && checkSingleOverlap(curB, curA))) {
                                isOverlap = true;
                                Ui.printOverlapInfo(curB.toString()); 
                                break;
                            }
                        }
                    }
                }
            }
        }
        return !isOverlap;
    }

    public void addEvent(String description, String startTime, String startDay, String endTime, String endDay)
            throws NPExceptions {

        TimeAndFlag startInfo = convertToTimeInfo(startTime, startDay);
        TimeAndFlag endInfo = convertToTimeInfo(endTime, endDay);
        Event newEvent =
                new Event(description, startInfo.time, endInfo.time, startInfo.hasInfo, endInfo.hasInfo);

        if (!canAddNewEvent(newEvent)) {
        throw new NPExceptions("Events/classes conflition!");
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

    public void addEvent(String description, String startTime, String startDay, String endTime, String endDay,
            String recurTime) throws NPExceptions {

        TimeAndFlag startInfo = convertToTimeInfo(startTime, startDay);
        TimeAndFlag endInfo = convertToTimeInfo(endTime, endDay);

        Event newEvent = new Event(description, startInfo.time, endInfo.time, startInfo.hasInfo,
                endInfo.hasInfo, recurTime);
        taskList.add(newEvent);
        listSize++;
    }

    public void reviseLocation(int index, String location) {
        taskList.get(index).changeLocation(location);
    }

    public void reviseTimeInfo(int index, String startTime, String startDay, String endTime, String endDay)
            throws NPExceptions {
        TimeAndFlag startInfo = convertToTimeInfo(startTime, startDay);
        TimeAndFlag endInfo = convertToTimeInfo(endTime, endDay);

        taskList.get(index).changeTimeInfo(startInfo.time, endInfo.time, startInfo.hasInfo, endInfo.hasInfo);
    }

    public void reviseTimeInfo(int index, String startTime, String startDay) throws NPExceptions {
        TimeAndFlag startInfo = convertToTimeInfo(startTime, startDay);

        taskList.get(index).changeTimeInfo(startInfo.time, startInfo.hasInfo);
    }

    // need handle exceptions when index = -1
    public void reviseTimeInfo(String description, String startTime, String startDay, String endTime,
            String endDay) throws NPExceptions {
        int index = searchTaskIndex(description);
        if (index == -1) {
            throw new NPExceptions("Event cannot be found!");
        }
        reviseTimeInfo(index, startTime, startDay, endTime, endDay);
    }

    // need handle exceptions when index = -1
    public void reviseTimeInfo(String description, String startTime, String startDay) throws NPExceptions {
        int index = searchTaskIndex(description);
        if (index == -1) {
            throw new NPExceptions("Event cannot be found!");
        }
        reviseTimeInfo(index, startTime, startDay);
    }

    public ArrayList<Schedule> getFullList() {
        return this.taskList;
    }

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
