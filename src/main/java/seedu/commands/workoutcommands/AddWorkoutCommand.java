package seedu.commands.workoutcommands;


import seedu.commands.Command;
import seedu.workout.Day;
import seedu.workout.Exercise;
import seedu.workout.Workout;

import java.util.Date;
import java.util.HashMap;


public class AddWorkoutCommand extends Command {
    //private final WorkoutList workoutList;

    //private Workout workout;
    private static Exercise exercise;
    private final String SHOW_EXERCISES_ADDED = "  has been added";
    private final String MISSING_WORKOUT = "Please enter your workout before adding exercises.";

    public AddWorkoutCommand(Exercise exercise) {
       this.exercise = exercise;
    }

    @Override
    public String execute() {
        //ArrayList<Exercise> exercises = Workout.getExercises();
        if(isWorkoutEntered) {
            day = workoutList.getSingleWorkout();
            workoutForOneDay = day.getWorkout();
           workoutForOneDay.addExercise(exercise);
            return exercise + SHOW_EXERCISES_ADDED;
        }else{
            return MISSING_WORKOUT;
        }
    }


}
