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

    public int getDayIndex() {
        String day = schedule.getDay();
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
