package seedu.commands;

import seedu.workout.Workout;
import seedu.workout.WorkoutList;

import java.util.Date;

public class StartCommand extends Command {

    private Date date;
    public StartCommand(Date date) {
        this.date = date;
    }

    @Override
    public void execute() {
        if (workoutList.currentWorkoutIndex == WorkoutList.NO_CURRENT_WORKOUT) {
            Workout toStart = new Workout(date);
            workoutList.addWorkout(toStart);
            System.out.println("Started new workout.");
            System.out.println("Use add command to add exercises to your workout!");
            return;
        }
        System.out.println("End your current workout before starting a new one!");
    }
}
