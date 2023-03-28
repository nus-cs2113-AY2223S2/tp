package seedu.commands.countcommands;


import seedu.commands.Command;
import seedu.workout.WorkoutList;

import java.util.Date;

//@@ author guillaume-grn
public class CountSetsRepsCommand extends Command {
    Date dayInSpecificWeekDate;
    public CountSetsRepsCommand(Date dayInSpecificWeekDate) {
        this.dayInSpecificWeekDate = dayInSpecificWeekDate;
    }

    @Override
    public String execute() {
        if (workoutList.getCurrentWorkoutIndex() == WorkoutList.NO_CURRENT_WORKOUT) {
            workoutList.countSetsReps(dayInSpecificWeekDate);
            return "";
        }
        return "End your current workout before asking for Count command";
    }
}
