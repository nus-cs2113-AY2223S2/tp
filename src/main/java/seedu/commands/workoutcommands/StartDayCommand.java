package seedu.commands.workoutcommands;

import seedu.commands.Command;
import seedu.parser.DateFormatter;
import seedu.workout.Day;

import java.util.Date;
import java.util.HashMap;

//@@ author ZIZI-czh
public class StartDayCommand extends Command {
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
        Day day = workouts.get(date);
        if (day == null) {
            day = new Day(date);
            workoutList.addWorkoutToList(date, day);
            String formattedDate = DateFormatter.dateToString(date);
            stringBuilder.append("Great! You have added a new workout for ")
                    .append(formattedDate);

        } else {
            stringBuilder.append("You had started workout on this day before, " + System.lineSeparator()
                    + "Please use '/wadd' to add a new workout.");
        }
        return stringBuilder.toString();
    }

}
