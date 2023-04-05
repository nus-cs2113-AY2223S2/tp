package seedu.commands.countcommands;

import seedu.commands.Command;

import java.util.Date;

//@@ author guillaume-grn
public class CountSetsRepsCommand extends Command {

    public static final String EMPTY_DAY = "You haven't create a record for this day!";
    Date dayInSpecificWeekDate;


    public CountSetsRepsCommand(Date dayInSpecificWeekDate) {
        this.dayInSpecificWeekDate = dayInSpecificWeekDate;
    }

    @Override
    public String execute() {
        workouts = workoutList.getWorkouts();
        if(!workouts.containsKey(dayInSpecificWeekDate)){
            return EMPTY_DAY;
        }
        return workoutList.countSetsReps(dayInSpecificWeekDate);
    }
}
