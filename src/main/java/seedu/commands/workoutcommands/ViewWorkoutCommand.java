package seedu.commands.workoutcommands;


import seedu.commands.Command;
import seedu.parser.DateFormatter;
import seedu.ui.Ui;
import seedu.workout.Day;
import seedu.workout.Exercise;
import seedu.workout.Workout;
import seedu.workout.WorkoutList;
import java.util.Date;
import java.util.HashMap;


public class ViewWorkoutCommand extends Command {

    private static final String FAIL_TO_FIND_DATE = " does not exit in the list";
    private final Date workoutToViewDate;


    //@@ author ZIZI-czh
    public ViewWorkoutCommand(Date workoutToViewDate) {
        super();
        this.workoutToViewDate = workoutToViewDate;
    }

    //@@ author ZIZI-czh
    public ViewWorkoutCommand(Date workoutToViewDate, WorkoutList workoutList) {
        this.workoutToViewDate = workoutToViewDate;
        //workout = new Day(workoutToViewDate).getWorkoutsByDate();
        this.workoutList = workoutList;
    }

    //@@ author ZIZI-czh
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
                string.append("Workout Name: ").append(workoutName).append(System.lineSeparator());
                string.append("Exercise Info: ").append(System.lineSeparator());
                int index = 1;
                for (Exercise exercise : singleWorkout.get(workoutName).getExercises()) {
                    string.append(index).append(". ").append("Name: ")
                            .append(exercise.getName())
                            .append(", weight: ")
                            .append(exercise.getWeight())
                            .append(", rps: ")
                            .append(exercise.getRepsPerSet())
                            .append(System.lineSeparator());
                    index++;
                }
                string.append(Ui.showSeparator()).append(System.lineSeparator());
            }
            return string.toString();
        }
        // if the Day object doesn't exist, return an error message
        return formattedDate + FAIL_TO_FIND_DATE;
    }
}
