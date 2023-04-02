package seedu.commands.workoutcommands;


import seedu.commands.Command;
import seedu.parser.DateFormatter;
import seedu.ui.Ui;
import seedu.workout.Day;

import java.util.Date;
import java.util.HashMap;

/**
 * This is the class for executing the list command
 */
public class ListWorkoutCommand extends Command {

    public static final int EMPTY = 0;
    private static final String EMPTY_DAY_LIST_MESSAGE = "No days have been found in the list";
    private static final String LINE_SEPARATOR = "----------------------------------";
    private static final String WORKOUT_LIST_HEADER =
            "Here are the list of dates of your workouts:";


    //@@ author ZIZI-czh
    public ListWorkoutCommand() {
        super();
    }

    /**
     * Show the list of date of the workout by calling the method in workoutList
     */
    //@@ author ZIZI-czh
    /*@Override
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
    }*/
    @Override
    public String execute() {
        HashMap<Date, Day> workouts = workoutList.getWorkouts();
        if (workouts != null && !workouts.isEmpty()) {
            StringBuilder string = new StringBuilder();
            string.append("Here is the list of dates:").append(System.lineSeparator());
            for (Date date : workouts.keySet()) {
                String formattedDate = DateFormatter.dateToString(date);
                string.append(formattedDate).append(System.lineSeparator());

            }
            return string + Ui.showSeparator();
        } else {
            return EMPTY_DAY_LIST_MESSAGE;
        }
    }
       // return workoutList.printList();


}

