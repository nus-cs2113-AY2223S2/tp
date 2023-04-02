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

            workoutList.countSetsReps(dayInSpecificWeekDate);
        //return "End your current workout before asking for Count command";
        return " ";
    }
}