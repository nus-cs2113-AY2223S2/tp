package seedu.commands.workoutcommands;

import seedu.commands.Command;
import seedu.exceptions.InvalidArgumentException;
import seedu.workout.Workout;
import seedu.workout.WorkoutList;

//@@ author ZIZI-czh

/**
 * View the workout on current date
 */
public class ViewWorkoutCommand extends Command {
    private final int workoutToViewIndex;

    //@@ author ZIZI-czh
    /**
     * Represents a command to view a specific workout from the workout list.
     *
     * @param workoutToViewIndex the index of the workout to view
     */
    public ViewWorkoutCommand(int workoutToViewIndex) {
        this.workoutToViewIndex = workoutToViewIndex;
    }


    //@@author ZIZI-czh
    //used for Junit Test
    public ViewWorkoutCommand(WorkoutList workoutListParamter, int workoutToViewIndex) {
        this.workoutToViewIndex = workoutToViewIndex;
        workoutList = workoutListParamter;
    }
    //@@ author ZIZI-czh
    /**
     * Executes the command to view a specific workout and returns its string representation.
     *
     * @return a string representing the workout to view
     * @throws InvalidArgumentException if the workout index is invalid
     */
    @Override
    public String execute() throws InvalidArgumentException {
        if (workoutToViewIndex >= workoutList.getWorkouts().size()) {
            throw new InvalidArgumentException("Index cannot be more than " + workoutList.getWorkouts().size());
        }
        Workout toView = workoutList.getWorkout(workoutToViewIndex);
        return toView.toString();
    }
}
