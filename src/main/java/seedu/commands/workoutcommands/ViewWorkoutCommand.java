package seedu.commands.workoutcommands;

import seedu.commands.Command;

import seedu.ui.Ui;
import seedu.workout.Workout;

import java.util.Date;

public class ViewWorkoutCommand extends Command {
    Date workoutToViewDate;
    public ViewWorkoutCommand(Date workoutToViewDate) {

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
