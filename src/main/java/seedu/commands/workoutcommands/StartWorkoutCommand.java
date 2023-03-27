package seedu.commands.workoutcommands;

import seedu.commands.Command;
import seedu.workouttracker.workout.Workout;
import seedu.workouttracker.workout.WorkoutList;

import java.util.Date;

public class StartWorkoutCommand extends Command {

    private Date date;
    public StartWorkoutCommand(Date date) {
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
