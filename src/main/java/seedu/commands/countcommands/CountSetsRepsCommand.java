package seedu.commands.countcommands;

import seedu.commands.Command;

import java.util.Date;

//@@ author guillaume-grn
public class CountSetsRepsCommand extends Command {
    Date dayInSpecificWeekDate;

    public CountSetsRepsCommand(Date dayInSpecificWeekDate) {
        this.dayInSpecificWeekDate = dayInSpecificWeekDate;
    }

    @Override
    public String execute() {
        return workoutList.countSetsReps(dayInSpecificWeekDate);
    }
}
