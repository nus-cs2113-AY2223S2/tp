package seedu.commands.workoutcommands;


import seedu.commands.Command;
import seedu.workout.Workout;
import seedu.workout.WorkoutList;

import java.util.Date;

public class StartWorkoutCommand extends Command {
    private static final String START_WORKOUT_MESSAGE = "Started new workout." + System.lineSeparator()
            + "Use add command to add exercises to your workout!";
    private static final String ONGOING_WORKOUT_MESSAGE = "End your current workout before starting a new " +
            "one!";
    private final Date date;

    public StartWorkoutCommand(Date date) {
        this.date = date;
    }

    @Override
    public String execute() {
        if (workoutList.getCurrentWorkoutIndex() == WorkoutList.NO_CURRENT_WORKOUT) {
            Workout toStart = new Workout(date);
            workoutList.addWorkout(toStart);
            return START_WORKOUT_MESSAGE;
        }
        return ONGOING_WORKOUT_MESSAGE;
    }
}
