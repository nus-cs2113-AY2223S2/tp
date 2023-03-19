package seedu.apollo.calendar;

import seedu.apollo.exception.utils.IllegalCommandException;
import seedu.apollo.module.CalendarModule;

import java.util.ArrayList;

public class Calendar extends ArrayList<ArrayList<CalendarModule>> {
    public Calendar() {
        super(7);
        for (int i = 0; i < 7; i++) {
            this.add(new ArrayList<>());
        }
    }

    public void addModule(CalendarModule module) throws IllegalCommandException {
        String day = module.getSchedule().getDay();
        int dayIndex = getDayIndex(day);

        if (dayIndex == -1) {
            throw new IllegalCommandException();
        }

        this.get(dayIndex).add(module);
    }

    private static int getDayIndex(String day) {

        switch (day) {

        case "Monday":
            return 0;
        case "Tuesday":
            return 1;
        case "Wednesday":
            return 2;
        case "Thursday":
            return 3;
        case "Friday":
            return 4;
        case "Saturday":
            return 5;
        case "Sunday":
            return 6;
        default:
            return -1;
        }
    }
}
