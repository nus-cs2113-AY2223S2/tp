package seedu.commands.workoutcommands;

import seedu.commands.Command;
import seedu.exceptions.InvalidArgumentException;
import seedu.parser.DateFormatter;

import java.util.Date;

//@@ author ZIZI-czh
public class DeleteWorkoutCommand extends Command {
    private final int workoutToDeleteIndex;

    //@@ author ZIZI-czh
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


