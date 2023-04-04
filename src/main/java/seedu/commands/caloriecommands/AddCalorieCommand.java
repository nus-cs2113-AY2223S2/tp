package seedu.commands.caloriecommands;


import seedu.commands.Command;
import java.util.Date;

public class AddCalorieCommand extends Command {
    public static final int CALORIES_NOT_GIVEN = -1;
    private final Date date;
    private final String food;
    private final int calories;


    public AddCalorieCommand(Date date, String food, int calories) {
        this.date = date;
        this.food = food;
        this.calories = calories;
    }




    @Override
    public String execute() {
        calorieTracker.setFoodList(foodList);
        return calorieTracker.addCalories(date, food, calories);
    }
}
