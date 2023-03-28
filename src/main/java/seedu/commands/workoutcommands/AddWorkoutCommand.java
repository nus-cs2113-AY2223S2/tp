package seedu.commands.workoutcommands;


import seedu.commands.Command;
import seedu.workout.Exercise;
import seedu.workout.WorkoutList;


public class AddWorkoutCommand extends Command {
    private final Exercise toAdd;

    public AddWorkoutCommand(Exercise toAdd) {
        this.toAdd = toAdd;
    }

    @Override
    public String execute() {
        if (workoutList.getCurrentWorkoutIndex() == WorkoutList.NO_CURRENT_WORKOUT) {
            return "Start a workout first!";
        }

        workoutList.getCurrentWorkout().addExercise(toAdd);
        return "Added " + toAdd.getName();
    }
}
