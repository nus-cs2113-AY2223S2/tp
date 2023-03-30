package seedu.duke;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static seedu.duke.Parser.SEMESTER_START_DATES;

// User utility class. This class will hold methods required by user.
public class UserUtility {
    private static User user;

    public static User getUser() {
        if (user == null) {
            user = new User();
        }
        return user;
    }

    public static void printScheduleTable(List<Schedule> events, int currentWeek){
        System.out.println("Showing schedule for semester " +
                UserUtility.getUser().getSemester() + " and week " + currentWeek);

        Ui.listTask((ArrayList<Schedule>) events.stream().filter(e -> e.getEndTime()
                == null).collect(Collectors.toList()));

        events = events.stream().filter(e -> e.getEndTime() != null).collect(Collectors.toList());

        // define the starting and ending times for the table
        LocalTime start = LocalTime.of(8, 0);
        LocalTime end = LocalTime.of(23, 59);

        // define the semester start date and the current week number
        LocalDate semesterStartDate =SEMESTER_START_DATES.get(getUser().getSemester());

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

        // loop through the starting times and print the table rows
        LocalDateTime time = LocalDateTime.of(weekStartDate, start);
        int count = 0;
        while (count++ <= 32) {
            if(time.toLocalTime().equals(LocalTime.of(0, 0))){
                System.out.print(String.format("%-10s|", end.format(DateTimeFormatter.ofPattern("HH:mm"))));
                time = time.minusMinutes(1).plusDays(1);
            } else {
                System.out.print(String.format("%-10s|", time.format(DateTimeFormatter.ofPattern("HH:mm"))));
            }
            for (DayOfWeek day : DayOfWeek.values()) {
                boolean found = false;
                for (Schedule event : events) {
                    LocalDateTime startDateTime = event.getStartTime();
                    LocalDateTime endDateTime = event.getEndTime();

                    if (startDateTime.toLocalTime().getMinute() > 0
                            && startDateTime.toLocalTime().getMinute() < 30) {
                        startDateTime = LocalDateTime.of(startDateTime.toLocalDate(), LocalTime.of(startDateTime.getHour(), 0));
                    }
                    if (endDateTime.toLocalTime().getMinute() > 30
                            && startDateTime.toLocalTime().getMinute() <= 59) {
                        endDateTime = LocalDateTime.of(endDateTime.toLocalDate(), LocalTime.of(endDateTime.getHour() + 1, 0));
                    }

                    if (time.getDayOfWeek() == day && isValidInterval(time, startDateTime, endDateTime)
                            && time.toLocalDate().isAfter(weekStartDate.minusDays(1))
                            && time.toLocalDate().isBefore(weekEndDate.plusDays(1))) {
                        System.out.print(String.format("%-15s|",
                                event.getDescription().substring(0, Math.min(event.getDescription().length(), 15))));
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    System.out.print(String.format("%-15s|", ""));
                }
                time = time.plusDays(1);
            }
            System.out.println();
            System.out.print(String.format("%-10s+", ""));
            for (int i = 0; i < DayOfWeek.values().length; i++) {
                System.out.print(String.format("%-15s+", "---------------"));
            }
            System.out.println();
            time = LocalDateTime.of(weekStartDate, start.plusMinutes(count*30));
        }
        System.out.println();
        Ui.printDash();
    }

    private static boolean isValidInterval(LocalDateTime time, LocalDateTime startTime, LocalDateTime endTime) {
        return time.equals(startTime) || (time.isAfter(startTime) && time.isBefore(endTime)) || time.equals(endTime);
    }
}
