package seedu.commands.workoutcommands;

import seedu.commands.Command;

import seedu.ui.Ui;
import seedu.workout.Workout;

import java.util.Date;

public class ViewWorkoutCommand extends Command {
    Date workoutToViewDate;
<<<<<<< HEAD:src/main/java/seedu/commands/workoutcommands/ViewWorkoutCommand.java
    public ViewWorkoutCommand(Date workoutToViewDate) {
=======

    public ViewCommand(Date workoutToViewDate) {
>>>>>>> d324e17 (solve codeStyle issue):src/main/java/seedu/commands/ViewCommand.java

        this.workoutToViewDate = workoutToViewDate;
    }


    public void execute() {

        for (Workout workout : workoutList.workoutArrayList) {
            if (workout.getDate().equals(workoutToViewDate)) {
                System.out.println(workout.getExercises());
                Ui.showseperator();
                return;
            }
        }

    }
}
