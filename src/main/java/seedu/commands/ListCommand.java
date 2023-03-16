package seedu.commands;

import seedu.workout.Workout;

import java.util.Date;

public class ListCommand extends Command {

    public ListCommand() {
    }

    /**
     * Show the list of date of the workout
     */
    @Override
    public void execute() {
        workoutList.showWorkoutList();
    }

}