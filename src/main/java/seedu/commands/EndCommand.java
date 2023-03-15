package seedu.commands;

import seedu.workout.WorkoutList;

public class EndCommand extends Command {
    public EndCommand() {
    }

    public void execute() {
        workoutList.setCurrentWorkoutIndex(WorkoutList.NO_CURRENT_WORKOUT);
        System.out.println("Great job completing your workout!");
    }
}

