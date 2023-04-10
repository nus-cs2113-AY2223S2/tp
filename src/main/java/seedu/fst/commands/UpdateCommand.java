package seedu.fst.commands;


import seedu.fst.exceptions.FSTException;
import seedu.fst.exceptions.IllegalValueException;
import seedu.fst.exceptions.InvalidFlagException;
import seedu.fst.food.Food;
import seedu.fst.food.FoodList;
import seedu.fst.utils.Validator;

import java.util.Arrays;

//@@author DavidVin357

/**
 * Represents "update" command - updates a product attributes with given flag values
 */
public class UpdateCommand extends Command {
    public static final String COMMAND_WORD = "update";
    private static final String FLAG_SEPARATOR = "--";
    String index;
    String[] flags;

    /**
     * Initializes UpdateCommand object
     * with index and flags obtained from the arguments provided
     *
     * @param arguments the user input
     * @throws FSTException
     */
    public UpdateCommand(String arguments) throws FSTException {
        if (!arguments.contains(FLAG_SEPARATOR)) {
            throw new FSTException("No field to update is specified");
        }
        String[] details = arguments.split(FLAG_SEPARATOR);
        this.index = details[0];
        this.flags = Arrays.copyOfRange(details, 1, details.length);
    }

    /**
     * A core logic method for updating the current food attributes with given flag values
     *
     * @param currentFood
     * @param flagName
     * @param flagValue
     * @throws FSTException
     */
    private void updateFoodAttribute(Food currentFood,
                                     String flagName,
                                     String flagValue,
                                     String[] allFlags) throws FSTException {
        try {
            switch (flagName) {
            case "n":
                currentFood.setName(flagValue);
                break;
            case "e":
                Validator.isExpiryDateValid(flagValue);
                currentFood.setExpiryDate(flagValue);
                break;
            case "q":
                currentFood.setQuantity(Double.parseDouble(flagValue));
                String currentFoodUnit = currentFood.getUnit();
                currentFood.setUnit(currentFoodUnit);
                break;
            case "u":
                if (currentFood.getQuantity() == 0 && Arrays.stream(allFlags).noneMatch(f -> f.startsWith("q"))) {
                    throw new FSTException("Please set the quantity to change a unit");
                }
                currentFood.setUnit(flagValue);
                break;
            case "c":
                currentFood.setCategory(flagValue);
                break;
            default:
                throw new InvalidFlagException(flagName);
            }
        } catch (FSTException e) {
            throw e;
        } catch (Exception e) {
            throw new IllegalValueException("Illegal value for the flag " + flagName);
        }
    }

    /**
     * Returns a Command Result according to the updating message
     *
     * @param foodList
     * @return CommandResult
     * @throws FSTException
     */
    @Override
    public CommandResult execute(FoodList foodList) throws FSTException {
        int index = 1;
        try {
            index = Integer.parseInt(this.index.trim()) - 1;
        } catch (NumberFormatException e) {
            int numberOfFood = foodList.getNumberOfFood();
            String wrongIndexMessage = "Invalid index format! Please give an index within range of " + numberOfFood;
            throw new FSTException(wrongIndexMessage);
        }
        Food currentFood = foodList.getFood(index);

        for (String flag : flags) {
            String[] flagParts = flag.trim().split(" ", 2);

            if (flagParts.length == 1) {
                String invalidFlagName = flagParts[0];
                throw new InvalidFlagException(invalidFlagName);
            }
            String flagName = flagParts[0].trim().toLowerCase();
            String flagValue = flagParts[1].trim().toLowerCase();

            updateFoodAttribute(currentFood, flagName, flagValue, flags);
        }
        return new CommandResult("Updated food item successfully! \n" + foodList.getFood(index));
    }
}
