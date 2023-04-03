package seedu.commands.workoutcommands;

import seedu.commands.Command;
import seedu.workout.Exercise;

//@@ author ZIZI-czh
public class AddWorkoutCommand extends Command {
    private static Exercise exercise;
    private static final String SHOW_EXERCISES_ADDED = "  has been added";
    private static final String MISSING_WORKOUT = "Please enter your workout before adding exercises.";

    //@@ author ZIZI-czh
    public AddWorkoutCommand(Exercise exercise) {
        this.exercise = exercise;
    }

    //@@ author ZIZI-czh
    @Override
    public String execute() {
        //ArrayList<Exercise> exercises = Workout.getExercises();
        if (isWorkoutEntered) {
            day = workoutList.getSingleWorkout();
            workoutForOneDay = day.getWorkout();
            workoutForOneDay.addExercise(exercise);

            return exercise + SHOW_EXERCISES_ADDED;
        } else {
            return MISSING_WORKOUT;
        }
    }
}
