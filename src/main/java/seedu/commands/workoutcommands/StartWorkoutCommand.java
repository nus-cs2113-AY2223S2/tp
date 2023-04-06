package seedu.commands.workoutcommands;


import seedu.commands.Command;
import seedu.workout.Day;
import seedu.workout.Workout;

import java.util.Date;
import java.util.HashMap;


//@@ author ZIZI-czh
public class StartWorkoutCommand extends Command {
    private static final String START_WORKOUT_MESSAGE = "Started new workout."
            + System.lineSeparator()
            + "Use add command to add exercises to your workout!";

    private static final String FAIL_TO_START_WORKOUT = "Please enter the day for your workout record first.";

    private static final String WORKOUT_START_BEFORE_FIRST = "You had started workout with the same name before, ";
    private static final String WORKOUT_START_BEFORE_SECOND = "Please use '/wadd' to add " +
            "exercises to the existing workout.";

    private static final String STARTED_WORKOUT = "Great! You have added a new workout for ";
    private static final String SYMBOL = ".";
    private static boolean isWorkoutEntered;
    private String workoutName;


    //@@ author ZIZI-czh
    public StartWorkoutCommand(String workoutName) {
        super();
        this.workoutName = workoutName;
    }


    //@@ author ZIZI-czh
    @Override
    public String execute() {
        StringBuilder stringBuilder = new StringBuilder();
        HashMap<Date, Day> workouts = workoutList.getWorkouts();
        //update the workout name
        day.setWorkoutName(workoutName);
        if (isDayEntered) {
            Day singleWorkout = workoutList.getSingleWorkout();
            if (!workouts.get(workoutList.getDate()).getWorkoutsByDate().containsKey(workoutName)) {
                singleWorkout.addWorkout(workoutName, new Workout(workoutName));
                stringBuilder.append(STARTED_WORKOUT)
                        .append(workoutName)
                        .append(SYMBOL);
            } else {
                stringBuilder.append(WORKOUT_START_BEFORE_FIRST)
                        .append(WORKOUT_START_BEFORE_SECOND);
            }
            return stringBuilder.toString();
        } else {
            return FAIL_TO_START_WORKOUT;
        }

    }
}
