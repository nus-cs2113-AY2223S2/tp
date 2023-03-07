package seedu.Commands;

import seedu.Database.FoodStorage;
import seedu.Database.UserStorage;
import seedu.Entities.Food;
import seedu.Exceptions.LifeTrackerException;

public class AddFoodCommand extends Command{
    private Food newFood;
    public AddFoodCommand (String commandDescriptor){
        int foodIndex;
        foodIndex = Integer.parseInt(commandDescriptor);
        //this.newFood =
    }
    @Override
    public void execute(FoodStorage foodList, UserStorage userList) throws LifeTrackerException {

    }
}
