package seedu.duke;

// import java.lang.reflect.Array;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static seedu.duke.Parser.SEMESTER_START_DATES;

// Class will hold methods required by user.
public class UserUtility {
    private static User user;

    public static User getUser() {
        if (user == null) {
            user = new User();
        }
        return user;
    }

    public static void printScheduleTable(ArrayList<Schedule> events, int currentWeek) {
        System.out.println("Showing schedule for semester " + UserUtility.getUser().getSemester()
                + " and week " + currentWeek);
        System.out.println();

        // define the starting and ending times for the table
        LocalTime start = LocalTime.of(8, 0);
        LocalTime end = LocalTime.of(18, 0);

        // define the semester start date and the current week number
        LocalDate semesterStartDate = SEMESTER_START_DATES.get(getUser().getSemester());

        // get the start and end dates for the current week
        LocalDate weekStartDate = semesterStartDate.plusWeeks(currentWeek - 1);
        LocalDate weekEndDate = weekStartDate.plusDays(6);

        // print the table header
        System.out.print(String.format("%-10s|", "TIME"));
        for (DayOfWeek day : DayOfWeek.values()) {
            System.out.print(String.format("%-15s|", day));
        }
        System.out.println();
        System.out.print(String.format("%-10s+", "----------"));
        for (int i = 0; i < DayOfWeek.values().length; i++) {
            System.out.print(String.format("%-15s+", "---------------"));
        }
        System.out.println();

        ArrayList<Schedule> eventToShow = new ArrayList<>();

        for (Schedule event : events) {
            if (event.isRecurring() && (event instanceof Event)) {
                Event now = (Event) event;
                ArrayList<Event> recurEventInWeek = eventInThisWeek(now, weekStartDate, weekEndDate);
                eventToShow.addAll(recurEventInWeek);
            } 
            eventToShow.add(event);
        }

        // loop through the starting times and print the table rows
        LocalTime time = start;
        while (time.isBefore(end)) {
            System.out.print(String.format("%-10s|", time.format(DateTimeFormatter.ofPattern("HH:mm"))));
            for (DayOfWeek day : DayOfWeek.values()) {
                boolean found = false;
                for (Schedule event : eventToShow) {
                    LocalDateTime startDateTime = event.getStartTime();
                    LocalDateTime endDateTime = event.getEndTime();
                    if (event.getStartTime().getDayOfWeek() == day
                            && isValidInterval(time, startDateTime.toLocalTime(), endDateTime.toLocalTime())
                            && event.getStartTime().toLocalDate().isAfter(weekStartDate.minusDays(1))
                            && event.getStartTime().toLocalDate().isBefore(weekEndDate.plusDays(1))) {
                        System.out.print(String.format("%-15s|", event.getDescription()));
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    System.out.print(String.format("%-15s|", ""));
                }
            }
            System.out.println();
            System.out.print(String.format("%-10s+", ""));
            for (int i = 0; i < DayOfWeek.values().length; i++) {
                System.out.print(String.format("%-15s+", "---------------"));
            }
            System.out.println();
            time = time.plusMinutes(30);
        }
        System.out.println();
        Ui.printDash();
    }

    //add -e exercise -sd 2023/03/01 -st 10:00 -ed 2023/03/01 -et 11:00 -r 3 D
    public static ArrayList<Event> eventInThisWeek(Event curEvent, LocalDate weekStartDate,
            LocalDate weekEndDate) {
        ArrayList<Event> events = new ArrayList<Event>();

        long timeToStart = weekStartDate.toEpochDay() - curEvent.getStartTime().toLocalDate().toEpochDay();
        int recurTime = curEvent.getActualInterval();
        int weeks = (int) timeToStart / recurTime;
        int remdays = (int) timeToStart % recurTime;

        LocalDateTime stTime = curEvent.getStartTime();
        LocalDateTime edTime = curEvent.getEndTime();
        if (remdays + 7 > recurTime) {
            if (remdays == 0) {
                events.add(new Event(curEvent.getDescription(), stTime, edTime, true, true,
                        curEvent.getTimeInterval()));
            }

            stTime = stTime.plusDays(recurTime);
            edTime = edTime.plusDays(recurTime);

            while ((stTime.toLocalDate().toEpochDay() - weekStartDate.toEpochDay()) < 7) {

                events.add(new Event(curEvent.getDescription(), stTime, edTime, true, true,
                        curEvent.getTimeInterval()));
                stTime = stTime.plusDays(recurTime);
                edTime = edTime.plusDays(recurTime);
            }
        }

        return events;
    }

    private static boolean isValidInterval(LocalTime time, LocalTime startTime, LocalTime endTime) {
        return time.equals(startTime) || (time.isAfter(startTime) && time.isBefore(endTime))
                || time.equals(endTime);
    }
}
