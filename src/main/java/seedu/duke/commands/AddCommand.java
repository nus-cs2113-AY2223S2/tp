package seedu.duke.commands;

import seedu.duke.food.Food;
import seedu.duke.food.FoodList;

public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";
    public static final String ADD_MESSAGE = "I have added this product! :) \n";

    public static final String NAME_SEPARATOR = " -n ";
    public static final String EXPIRY_SEPARATOR = " -e ";
    public String details;

    public AddCommand(String details) {
        this.details = details;
    }

    public CommandResult execute(FoodList foodList) {
        String[] nameAndDate = splitDetails(details);
        String name = nameAndDate[0];
        String date = nameAndDate[1];
        Food newFood = new Food(name, date);
        System.out.println(newFood.printFoodDetails());
        foodList.addFood(newFood);

        return new CommandResult(ADD_MESSAGE);
    }

    public String[] splitDetails(String details) {
        String[] nameAndDate;
        String[] temp = details.split(NAME_SEPARATOR);
        if (temp[0].isEmpty()) {
            nameAndDate = temp[1].split(EXPIRY_SEPARATOR);
        } else {
            nameAndDate = temp[0].split(EXPIRY_SEPARATOR);
            nameAndDate[0] = temp[1];
        }

        return nameAndDate;
    }
}
