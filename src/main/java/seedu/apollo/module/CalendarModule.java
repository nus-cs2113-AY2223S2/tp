package seedu.apollo.module;

public class CalendarModule extends Module {

    private Timetable schedule;
    public CalendarModule(String moduleCode, String moduleName, String moduleCredits) {
        super(moduleCode, moduleName, moduleCredits);
    }

    public void setSchedule(Timetable schedule) {
        this.schedule = schedule;
    }

    public Timetable getSchedule() {
        return schedule;
    }

}
