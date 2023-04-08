package seedu.commands.workoutcommands;

import seedu.commands.Command;
import seedu.parser.DateFormatter;
import seedu.ui.Ui;
import seedu.workout.Workout;


/**
 * This is the class for executing the list command
 */
//@@ author ZIZI-czh
public class ListWorkoutCommand extends Command {

    private static final String EMPTY_LIST_MESSAGE = "Workout list is empty";
    private static final String WORKOUT_LIST_HEADER =
            "Here is the list of dates of your workouts:" + System.lineSeparator();

    //@@ author ZIZI-czh
    public ListWorkoutCommand() {
    }

    //@@author calebcjl
    @Override
    public String execute() {
        if (workoutList.isEmptyList()) {
            return EMPTY_LIST_MESSAGE;
        }

        StringBuilder listOfWorkouts = new StringBuilder(WORKOUT_LIST_HEADER);
        int counter = 1;
        for (Workout workout : workoutList.getWorkouts()) {
            String listNumber = counter + ". ";
            String date = DateFormatter.dateToString(workout.getDate()) + ' ';
            String workoutName = workout.getWorkoutName();
            listOfWorkouts.append(listNumber).append(date).append(workoutName).append(System.lineSeparator());
            counter += 1;
        }
        return listOfWorkouts.append(Ui.line()).toString();
    }
}


