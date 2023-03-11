package seedu.Commands;

import seedu.Database.FoodStorage;
import seedu.Database.MealStorage;
import seedu.Database.UserStorage;
import seedu.Entities.Food;
import seedu.Exceptions.LifeTrackerException;
import seedu.Output.UI;

public class AddFoodCommand extends Command{
    private Food newFood;
    public AddFoodCommand (String commandDescriptor){
        int foodIndex;
        foodIndex = Integer.parseInt(commandDescriptor);
        //this.newFood =
    }
    @Override
    public void execute(UI ui, FoodStorage foodStorage, MealStorage mealStorage, UserStorage userStorage) throws LifeTrackerException {

    }
}
