package seedu.commands.workoutcommands;

import seedu.commands.Command;
import seedu.ui.Ui;
import seedu.workout.Exercise;

//@@ author ZIZI-czh
public class AddWorkoutCommand extends Command {
    private static Exercise exercise;
    private static final String SHOW_EXERCISES_ADDED = "  has been added";
    private static final String MISSING_WORKOUT = "Please enter your workout before adding exercises.";

    //@@ author ZIZI-czh
    public AddWorkoutCommand(Exercise exercise) {
        this.exercise = exercise;
    }

    //@@ author ZIZI-czh
    @Override
    public String execute() {
        StringBuilder string = new StringBuilder();
        if (isWorkoutEntered) {
            day = workoutList.getSingleWorkout();
            workoutForOneDay = day.getWorkout();
            workoutForOneDay.addExercise(exercise);
            string.append("name: ")
                    .append(exercise.getName())
                    .append(", weight: ")
                    .append(exercise.getWeight())
                    .append(", rps: ")
                    .append(exercise.getRepsPerSet())
                    .append(SHOW_EXERCISES_ADDED)
                    .append(System.lineSeparator())
                    .append(Ui.showSeparator());
            return string.toString();
        } else {
            return MISSING_WORKOUT;
        }
    }
}
