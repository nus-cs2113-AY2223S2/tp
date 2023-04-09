package seedu.commands.workoutcommands;

import seedu.commands.Command;
import seedu.exceptions.InvalidArgumentException;
import seedu.parser.DateFormatter;

import java.util.Date;

//@@ author ZIZI-czh
/**
 * Represents a command to delete a workout from the workout list, identified by the index of the workout to delete.
 * The index of the workout to delete is passed in through the constructor.
 */
public class DeleteWorkoutCommand extends Command {
    private final int workoutToDeleteIndex;

    //@@ author ZIZI-czh
    /**
     * Creates a new instance of DeleteWorkoutCommand with the specified index of the workout to delete.
     * @param workoutToDeleteIndex the index of the workout to delete
     */
    public DeleteWorkoutCommand(int workoutToDeleteIndex) {
        this.workoutToDeleteIndex = workoutToDeleteIndex;
    }

    //@@author calebcjl
    @Override
    public String execute() throws InvalidArgumentException {
        if (workoutToDeleteIndex >= workoutList.getWorkouts().size()) {
            throw new InvalidArgumentException("index");
        }
        String workoutName = workoutList.getWorkout(workoutToDeleteIndex).getWorkoutName();
        Date workoutDate = workoutList.getWorkout(workoutToDeleteIndex).getDate();
        workoutList.deleteWorkout(workoutToDeleteIndex);
        return "Deleted " + workoutName + " on " + DateFormatter.dateToString(workoutDate) + '.';
    }
}


