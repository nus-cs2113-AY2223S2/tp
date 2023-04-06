package seedu.commands.workoutcommands;


import seedu.commands.Command;
import seedu.parser.DateFormatter;
import seedu.ui.Ui;
import seedu.workout.Day;
import seedu.workout.WorkoutList;
import java.util.Date;
import java.util.HashMap;

/**
 * This is the class for executing the list command
 */
//@@ author ZIZI-czh
public class ListWorkoutCommand extends Command {

    private static final String EMPTY_DAY_LIST_MESSAGE = "No days have been found in the list";
    private static final String WORKOUT_LIST_HEADER =
            "Here is the list of dates of your workouts:";

    //@@ author ZIZI-czh
    public ListWorkoutCommand() {
        super();
    }
    public ListWorkoutCommand(WorkoutList workoutListParameter){
        workoutList = workoutListParameter;
    }

    /**
     * Show the list of date of the workout by calling the method in workoutList
     */
    //@@ author ZIZI-czh
    @Override
    public String execute() {
        HashMap<Date, Day> workouts = workoutList.getWorkouts();
        if (workouts != null && !workouts.isEmpty()) {
            StringBuilder string = new StringBuilder();
            string.append( WORKOUT_LIST_HEADER).append(System.lineSeparator());
            for (Date date : workouts.keySet()) {
                String formattedDate = DateFormatter.dateToString(date);
                string.append(formattedDate).append(System.lineSeparator());
            }
            return string + Ui.showSeparator();
        } else {
            return EMPTY_DAY_LIST_MESSAGE;
        }
    }
}


