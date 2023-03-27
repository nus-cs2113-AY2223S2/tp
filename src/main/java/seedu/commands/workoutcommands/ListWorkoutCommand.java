package seedu.commands.workoutcommands;


<<<<<<< HEAD:src/main/java/seedu/commands/workoutcommands/ListWorkoutCommand.java
import seedu.commands.Command;
=======
import seedu.ui.Ui;
import seedu.workout.Workout;

import java.text.SimpleDateFormat;
>>>>>>> 7ac8556 (Refactor the code edit Junit Test for list and delete calss):src/main/java/seedu/commands/ListCommand.java

/**
 * This is the class for executing the list command
 */
public class ListWorkoutCommand extends Command {

    //@@ author ZIZI-czh
<<<<<<< HEAD:src/main/java/seedu/commands/workoutcommands/ListWorkoutCommand.java
    public ListWorkoutCommand() {
=======
    public ListCommand() {

>>>>>>> 7ac8556 (Refactor the code edit Junit Test for list and delete calss):src/main/java/seedu/commands/ListCommand.java
    }

    /**
     * Show the list of date of the workout by calling the method in workoutList
     */
    //@@ author ZIZI-czh
    @Override
    public void execute() {
        try {
            if (!workoutList.workoutArrayList.isEmpty()) {
                System.out.println("Here are the list of dates for your workout: ");
                for (Workout workout : workoutList.workoutArrayList) {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yy");
                    String formattedDate = dateFormat.format(workout.getDate());
                    System.out.println(formattedDate);
                }
                Ui.showseperator();
            } else {
                //if there is no workout have been done
                System.out.println("Haven't start your workout, please enter your workout");
            }

        } catch (NullPointerException e) {
            System.out.println("Haven't start your workout, please enter your workout");
        }

    }
}

