package seedu.commands.workoutcommands;


import seedu.commands.Command;
import seedu.workout.Day;
import seedu.workout.Workout;


//@@ author ZIZI-czh
public class StartWorkoutCommand extends Command {
    private static final String START_WORKOUT_MESSAGE = "Started new workout." + System.lineSeparator()
            + "Use add command to add exercises to your workout!";
    private static final String ONGOING_WORKOUT_MESSAGE = "End your current workout before starting a new " +
            "one!";

    private static final String FAIL_TO_START_WORKOUT= "Please enter the day for your workout record first";

    private static boolean isWorkoutEntered;
    private  String workoutName;


    //@@ author ZIZI-czh
    public StartWorkoutCommand(String workoutName) {
        super();
        this.workoutName = workoutName;
    }



    //@@ author ZIZI-czh
    @Override
    public String execute() {
        StringBuilder stringBuilder = new StringBuilder();

        if (isDayEntered) {
            Day singleWorkout = workoutList.getSingleWorkout();
            Workout workout = singleWorkout.getWorkout();
            if (workout.getWorkoutName() == null) {
                workout = new Workout(workoutName);
                singleWorkout.addWorkout(workoutName, workout);
                //workouts.addWorkout(formattedDate, new Workout())
                stringBuilder.append("Great! You have added a new workout for ")
                        .append(workoutName)
                        .append(".");

            } else if (workout.getWorkoutName().equals(workoutName)) {
                stringBuilder.append("You had started workout with the same name before, ")
                        .append("Please use '/wadd' to add exercises to the existing workout.");
            }else {
                stringBuilder.append("Great! You have added a new workout for ")
                        .append(workoutName)
                        .append(".");
            }
            return stringBuilder.toString();
        } else {
            return FAIL_TO_START_WORKOUT;
        }
    }
}
