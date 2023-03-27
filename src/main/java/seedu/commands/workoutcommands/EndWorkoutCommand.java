package seedu.commands.workoutcommands;

import seedu.commands.Command;
import seedu.workouttracker.workout.WorkoutList;

public class EndWorkoutCommand extends Command {
    public EndWorkoutCommand() {
    }

    public void execute() {
        workoutList.setCurrentWorkoutIndex(WorkoutList.NO_CURRENT_WORKOUT);
        System.out.println("Great job completing your workout!");
    }
}

