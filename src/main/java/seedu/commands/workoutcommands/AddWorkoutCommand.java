package seedu.commands.workoutcommands;

import seedu.commands.Command;
import seedu.workouttracker.Exercise;
import seedu.workouttracker.WorkoutList;

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
        return workoutList.getCurrentWorkout().addExercise(toAdd);
    }

}
