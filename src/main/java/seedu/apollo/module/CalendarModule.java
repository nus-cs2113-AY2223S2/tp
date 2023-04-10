package seedu.apollo.module;

import java.util.ArrayList;

public class CalendarModule extends Module {

    private Timetable schedule;

    /**
     * Constructor for CalendarModule.
     *
     * @param moduleCode The module code of the module.
     * @param moduleName The module name of the module.
     * @param moduleCredits The module credits of the module.
     */
    public CalendarModule(String moduleCode, String moduleName, String moduleCredits) {
        super(moduleCode, moduleName, moduleCredits);
    }

    /**
     * Sets the schedule of the module.
     *
     * @param schedule The schedule of the module.
     */
    public void setSchedule(Timetable schedule) {
        this.schedule = schedule;
    }

    /**
     * Gets the schedule of the module.
     *
     * @return The schedule of the module.
     */
    public Timetable getSchedule() {
        return schedule;
    }

    /**
     * Gets the day of the module lesson.
     *
     * @return The day of the module lesson.
     */
    public String getDay() {
        return schedule.getDay();
    }


    /**
     * Checks if the module is scheduled for the current week.
     *
     * @param week The current week.
     * @return True if the module is scheduled for the current week.
     */
    public boolean isCurrentWeek(int week) {
        ArrayList<Integer> weeks = schedule.getWeeks();
        return weeks.contains(week);
    }

}
