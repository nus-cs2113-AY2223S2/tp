package seedu.commands.workoutcommands;


import seedu.commands.Command;
import seedu.workout.WorkoutList;

public class EndWorkoutCommand extends Command {
    private static final String WORKOUT_COMPLETE_MESSAGE = "Great job completing your workout!";

    public EndWorkoutCommand() {
    }

    @Override
    public String execute() {
        workoutList.setCurrentWorkoutIndex(WorkoutList.NO_CURRENT_WORKOUT);
        return WORKOUT_COMPLETE_MESSAGE;
    }
}

