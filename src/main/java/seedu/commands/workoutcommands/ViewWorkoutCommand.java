package seedu.commands.workoutcommands;

import seedu.commands.Command;
import seedu.exceptions.InvalidArgumentException;
import seedu.workout.Workout;

public class ViewWorkoutCommand extends Command {
    private final int workoutToViewIndex;

    //@@ author ZIZI-czh
    public ViewWorkoutCommand(int workoutToViewIndex) {
        this.workoutToViewIndex = workoutToViewIndex;
    }

    public String execute() throws InvalidArgumentException {
        if (workoutToViewIndex >= workoutList.getWorkouts().size()) {
            throw new InvalidArgumentException("Index cannot be more than " + workoutList.getWorkouts().size());
        }
        Workout toView = workoutList.getWorkout(workoutToViewIndex);
        return toView.toString();
    }
}