package seedu.commands.caloriecommands;

import seedu.commands.Command;
import seedu.parser.DateFormat;

import java.util.Date;

import static seedu.calorietracker.CalorieTracker.CALORIES_NOT_TRACKED;

public class ViewCaloriesCommand extends Command {

    Date dateToView;
    public ViewCaloriesCommand(Date dateToView) {
        this.dateToView = dateToView;
    }

    @Override
    public void execute() {
        DateFormat dateFormat = new DateFormat(dateToView);
        String formattedDate = dateFormat.formatDate();
        System.out.println("test");
        if (calorieTracker.getCalories(dateToView) == CALORIES_NOT_TRACKED) {
            System.out.println("Calories not tracked on " + formattedDate);
        } else {
            System.out.println("Calories consumed on " + formattedDate + ": "
                    + calorieTracker.getCalories(dateToView) + "kcal.");
        }
    }
}
