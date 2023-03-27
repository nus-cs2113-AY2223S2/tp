package seedu.commands.workoutcommands;

import seedu.commands.Command;
import seedu.workouttracker.workout.Exercise;
import seedu.workouttracker.workout.WorkoutList;

public class AddWorkoutCommand extends Command {
    private Exercise toAdd;

    public AddWorkoutCommand(Exercise toAdd) {
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
