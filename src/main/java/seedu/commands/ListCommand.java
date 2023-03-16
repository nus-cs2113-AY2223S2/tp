package seedu.commands;


public class ListCommand extends Command {

    public ListCommand() {
    }

    @Override
    public void execute() {
        workoutList.showWorkoutList();
    }

}
