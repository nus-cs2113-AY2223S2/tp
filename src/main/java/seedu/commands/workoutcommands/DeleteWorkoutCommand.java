package seedu.commands.workoutcommands;

import seedu.commands.Command;

import seedu.ui.Ui;
import seedu.workout.Workout;

import java.util.Date;


public class DeleteWorkoutCommand extends Command {
    Date workoutToDeleteDate;
<<<<<<< HEAD:src/main/java/seedu/commands/workoutcommands/DeleteWorkoutCommand.java
<<<<<<< HEAD:src/main/java/seedu/commands/workoutcommands/DeleteWorkoutCommand.java
    public DeleteWorkoutCommand(Date workoutToDeleteDate) {
=======
=======

>>>>>>> d324e17 (solve codeStyle issue):src/main/java/seedu/commands/DeleteCommand.java
    public DeleteCommand(Date workoutToDeleteDate) {
        super();
>>>>>>> 7ac8556 (Refactor the code edit Junit Test for list and delete calss):src/main/java/seedu/commands/DeleteCommand.java
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
            if (workout.getDate().equals(workoutToDeleteDate)) {
                workoutList.workoutArrayList.remove(workout);
                System.out.println("Workout deleted successfully.");
                Ui.showseperator();
                return;
            }
        }
        System.out.println("No workout found with the specified date.");
    }

}

