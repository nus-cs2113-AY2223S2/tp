package seedu.commands.workoutcommands;

import seedu.commands.Command;
import seedu.parser.DateFormatter;
import seedu.workout.Day;

import java.util.Date;
import java.util.HashMap;

//@@ author ZIZI-czh
public class StartDayCommand extends Command {

    private static final String STARTED_DAY_FIRST = "You had started workout on this day before.";
    private static final String STARTED_DAY_SECOND = "Please use '/start' to add a new workout!";
    private static final String STARTED_WORKOUT = "Great! You have added a new workout for ";
    private Date date;
    private Day day = new Day();


    //@@ author ZIZI-czh
    public StartDayCommand(Date date) {
        super();
        this.date = date;
    }

    //@@ author ZIZI-czh
    public Date getDate() {
        return date;
    }

    //@@ author ZIZI-czh
    @Override
    public String execute() {
        HashMap<Date, Day> workouts = workoutList.getWorkouts();
        StringBuilder stringBuilder = new StringBuilder();

        //update the current date
        workoutList.setDate(date);
        if (!workouts.containsKey(date)) {
            Day day = workouts.get(date);
            if (day == null) {
                day = new Day(date);
                workoutList.addWorkoutToList(date, day);
                String formattedDate = DateFormatter.dateToString(date);
                stringBuilder.append(STARTED_WORKOUT)
                        .append(formattedDate);

            } else {
                stringBuilder.append(STARTED_DAY_FIRST)
                        .append(System.lineSeparator())
                        .append(STARTED_DAY_SECOND);
            }
        } else {
            stringBuilder.append(STARTED_DAY_FIRST)
                    .append(System.lineSeparator())
                    .append(STARTED_DAY_SECOND);
        }
        return stringBuilder.toString();
    }

}
