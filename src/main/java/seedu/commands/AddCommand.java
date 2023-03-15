package seedu.commands;

import seedu.workout.Exercise;
import seedu.workout.WorkoutList;

public class AddCommand extends Command {
    private Exercise toAdd;

    public AddCommand(Exercise toAdd) {
        this.toAdd = toAdd;
    }

    @Override
    public void execute() {
        if (workoutList.currentWorkoutIndex == WorkoutList.NO_CURRENT_WORKOUT) {
            System.out.println("Start a workout first!");
            return;
        }
        workoutList.getCurrentWorkout().addExercise(toAdd);
    }

}
