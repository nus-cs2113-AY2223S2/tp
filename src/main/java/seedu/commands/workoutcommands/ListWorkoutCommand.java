package seedu.commands.workoutcommands;


import seedu.commands.Command;

/**
 * This is the class for executing the list command
 */
public class ListWorkoutCommand extends Command {

    //@@ author ZIZI-czh
    public ListWorkoutCommand() {
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

