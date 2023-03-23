package seedu.commands;


/**
 * This is the class for executing the list command
 */
public class ListCommand extends Command {

    //@@ author ZIZI-czh
    public ListCommand() {
    }


    /**
     * Show the list of date of the workout by calling the method in workoutList
     */
    //@@ author ZIZI-czh
    @Override
    public void execute() {
        workoutList.showWorkoutList();
    }
}

