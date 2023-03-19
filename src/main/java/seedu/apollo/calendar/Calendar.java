package seedu.apollo.calendar;

import seedu.apollo.exception.utils.InvalidSaveFile;
import seedu.apollo.module.CalendarModule;
import seedu.apollo.module.Module;
import seedu.apollo.module.Timetable;

import java.util.ArrayList;

public class Calendar extends ArrayList<ArrayList<CalendarModule>> {
    public Calendar() {
        super(7);
        for (int i = 0; i < 7; i++) {
            this.add(new ArrayList<>());
        }
    }

    public void addModule(Module module) throws InvalidSaveFile {

        try {
            for (Timetable timetable : module.getModuleTimetable()) {
                CalendarModule calendarModule = new CalendarModule(module.getCode(),
                        module.getTitle(), module.getModuleCredits());

                calendarModule.setSchedule(timetable);
                int index = calendarModule.getDayIndex();
                if (index == -1) {
                    throw new InvalidSaveFile();
                }

                this.get(index).add(calendarModule);

            }
        } catch (NullPointerException e) {
            return;
        }

    }

    public void clearCalendar() {
        for (int i = 0; i < 7; i++) {
            this.get(i).clear();
        }
    }

}
