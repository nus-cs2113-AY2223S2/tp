package seedu.commands.workoutcommands;


import seedu.commands.Command;
import seedu.parser.DateFormatter;

/**
 * This is the class for executing the list command
 */
public class ListWorkoutCommand extends Command {

    public static final int EMPTY = 0;
    private static final String EMPTY_WORKOUT_LIST_MESSAGE = "No workout recorded.";
    private static final String LINE_SEPARATOR = "----------------------------------";
    private static final String WORKOUT_LIST_HEADER =
            "Here are the list of dates of your workouts:";


    //@@ author ZIZI-czh
    public ListWorkoutCommand() {
    }

    /**
     * Show the list of date of the workout by calling the method in workoutList
     */
    //@@ author ZIZI-czh
    @Override
    public String execute() {
        workoutArrayList = workoutList.getWorkoutArrayList();
        if (workoutArrayList.size() == EMPTY) {
            return EMPTY_WORKOUT_LIST_MESSAGE;
        }
        System.out.println(WORKOUT_LIST_HEADER);
        for (int i = 0; i < workoutArrayList.size(); i += 1) {
            System.out.println(i + 1 + ". "
                    + DateFormatter.dateToString(workoutArrayList.get(i).getDate()));
        }
        return LINE_SEPARATOR;
        // return workoutList.toString();
    }
}

