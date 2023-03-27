package seedu.commands.workoutcommands;

import seedu.parser.DateFormat;
import seedu.commands.Command;
import seedu.ui.Ui;
import seedu.workouttracker.Workout;

import java.util.Date;


public class DeleteWorkoutCommand extends Command {
    Date workoutToDeleteDate;


    public DeleteWorkoutCommand(Date workoutToDeleteDate) {
        super();
        this.workoutToDeleteDate = workoutToDeleteDate;
        setData(workoutList);

    }

    @Override
    public void execute() {
        if (workoutList == null) {
            System.out.println("WorkoutList is null.");
            return;
        }
        if (workoutList.workoutArrayList == null) {
            System.out.println("the workout array list is empty");
            return;
        }
        for (Workout workout : workoutList.workoutArrayList) {
            DateFormat dateFormat = new DateFormat(workoutToDeleteDate);
            String formattedDate = dateFormat.formatDate();
            if (workout.getDate().equals(formattedDate)) {
                workoutList.workoutArrayList.remove(workout);
                System.out.println("Workout deleted successfully.");
                Ui.showSeparator();
                return;
            }
        }
        System.out.println("No workout found with the specified date.");
    }
}


