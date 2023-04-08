package seedu.commands.caloriecommands;

import seedu.commands.Command;
import seedu.exceptions.InvalidSyntaxException;

import java.util.Date;

//@@author calebcjl
/**
 * Represents command to add an entry to calorie tracker.
 */
public class AddCalorieCommand extends Command {
    public static final int CALORIES_NOT_GIVEN = -1;
    private final Date date;
    private final String foodName;
    private final int foodCalories;

    public AddCalorieCommand(Date date, String foodName, int foodCalories) {
        this.date = date;
        this.foodName = foodName;
        this.foodCalories = foodCalories;
    }

    public AddCalorieCommand(Date date, String foodName) {
        this.date = date;
        this.foodName = foodName;
        foodCalories = CALORIES_NOT_GIVEN;
    }

    @Override
    public String execute() throws InvalidSyntaxException {
        return calorieTracker.addCalories(date, foodName, foodCalories);
    }
}
