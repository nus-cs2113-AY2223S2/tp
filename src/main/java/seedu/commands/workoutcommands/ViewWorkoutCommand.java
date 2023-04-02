package seedu.commands.workoutcommands;


import com.sun.jdi.PrimitiveValue;
import seedu.commands.Command;
import seedu.parser.DateFormatter;
import seedu.ui.Ui;
import seedu.workout.Day;
import seedu.workout.Exercise;
import seedu.workout.Workout;
import seedu.workout.WorkoutList;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ViewWorkoutCommand extends Command {
    private static final String WORKOUT_NOT_FOUND_MESSAGE = "No workout done on ";
    private static String FAILE_TO_FIND_DATE = " does not exit in the list";
    private final Date workoutToViewDate;
    private HashMap<String, Workout> workout;


    public ViewWorkoutCommand(Date workoutToViewDate) {
        super();
        this.workoutToViewDate = workoutToViewDate;
        //workout = new Day(workoutToViewDate).getWorkoutsByDate();
    }

    @Override
    public String execute() {
        // convert the date to string for display purposes
        String formattedDate = DateFormatter.dateToString(workoutToViewDate);
        // get the Day object associated with the given date
        Day workoutsOnDate = workoutList.getWorkouts().get(workoutToViewDate);
        // if the Day object exists, retrieve the workouts and print them
        if (workoutsOnDate != null) {
            HashMap<String, Workout> singleWorkout;
            singleWorkout = workoutsOnDate.getWorkoutsByDate();
            StringBuilder string = new StringBuilder();
            if (singleWorkout.isEmpty()) {
                return "No workouts found on " + formattedDate + System.lineSeparator() + Ui.showSeparator();
            }
            string.append("Workouts on ").append(formattedDate).append(":").append(System.lineSeparator());
            // retrieve all workouts for the day and add them to the StringBuilder
            for (String workoutName : singleWorkout.keySet()) {
                string.append(workoutName).append(":").append(System.lineSeparator());
                int index = 1;
                for (Exercise exercise : singleWorkout.get(workoutName).getExercises()) {
                    string.append(index).append(". ").append(exercise.toString()).append(System.lineSeparator());
                    index++;
                }
            }
            return string + Ui.showSeparator();
        }
        // if the Day object doesn't exist, return an error message
        return formattedDate + FAILE_TO_FIND_DATE;
    }
}
