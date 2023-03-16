package seedu.commands;

import seedu.workout.Workout;

import java.util.Date;

public class ListCommand extends Command {

    public ListCommand() {
    }

    @Override
    public void execute() {
        workoutList.showWorkoutList();
    }

}