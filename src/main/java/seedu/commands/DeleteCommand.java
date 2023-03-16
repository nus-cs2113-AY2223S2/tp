package seedu.commands;

public class DeleteCommand extends Command {
    String workoutToDeleteDate;
    public DeleteCommand(String workoutToDeleteDate) {
        this.workoutToDeleteDate = workoutToDeleteDate;
    }

    @Override
    public void execute() {
        workoutList.removeWorkout(workoutToDeleteDate);
    }
}
