package seedu.commands.workoutcommands;

import seedu.commands.Command;
import seedu.parser.DateFormatter;
import seedu.workout.Day;
import seedu.workout.WorkoutList;
import seedu.workout.Workout;

import java.util.Date;
import java.util.HashMap;

public class StartDayCommand extends Command {
    private  Date date;


    private Day day = new Day();
    /*public  boolean isDayEntered() {
        return isDayEntered;
    }
*/
    /*public  void setDayEntered(boolean dayEntered) {
        isDayEntered = dayEntered;
    }*/


    public StartDayCommand(Date date) {
        super();
        this.date = date;
        //day = new Day(date);


    }

    public Date getDate() {
        return date;
    }

    @Override
    public String execute() {
        HashMap<Date, Day> workouts = workoutList.getWorkouts();
       // String formattedDate = DateFormatter.dateToString(date);
        StringBuilder stringBuilder = new StringBuilder();
        Day day = workouts.get(date);
        if (day == null) {
            day = new Day(date);
            //workouts.put(formattedDate, day);
            workoutList.addWorkoutToList(date, day);
            String formattedDate = DateFormatter.dateToString(date);
            stringBuilder.append("Great! You have added a new workout for ")
                    .append(formattedDate)
                    .append(".");

        }else {
            stringBuilder.append("You had started workout on this day before, " + System.lineSeparator()
                    + "Please use '/wadd' to add a new workout.");
        }
        return stringBuilder.toString();
    }


}
