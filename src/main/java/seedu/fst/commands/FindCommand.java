package seedu.fst.commands;

import seedu.fst.exceptions.FSTException;
import seedu.fst.exceptions.InvalidFlagException;
import seedu.fst.exceptions.InvalidFlagValueException;
import seedu.fst.food.Food;
import seedu.fst.food.FoodList;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Objects;

//@@author DavidVin357

/**
 * Represents "find" command - finds a product with given term and filters matched
 */
public class FindCommand extends Command {
    public static final String COMMAND_WORD = "find";

    private static final String FLAG_SEPARATOR = "--";
    private static final String NOT_FOUND = "No food found for such query";


    String term;
    String[] flags;

    /**
     * Initializes FindCommand object
     * with term and flags obtained from the arguments provided
     *
     * @param arguments
     */
    public FindCommand(String arguments) {
        String[] details = arguments.split(FLAG_SEPARATOR);
        this.term = details[0];
        this.flags = Arrays.copyOfRange(details, 1, details.length);
    }

    /**
     * Finds products with a given term and filters them
     * according to given flag values.
     *
     * Adds a foodItem to the result
     * only if each flag was satisfied and the loop iteration
     * wasn't interrupted early by non-matching flag value
     *
     * @param foodList
     * @return CommandResult
     * @throws FSTException
     */
    @Override
    public CommandResult execute(FoodList foodList) throws FSTException {
        FoodList result = new FoodList();
        if (Objects.equals(term, "") && flags.length == 0) {
            throw new FSTException("No term or flag provided");
        }

        foodItemLoop:
        for (Food foodItem : foodList.getFoodList()) {
            String foodItemName = foodItem.getName();
            LocalDate expiryDate = foodItem.parseExpiryDate();
            String foodCategory = foodItem.getCategoryString(foodItem.getCategory());
            String foodUnit = foodItem.getUnit();
            boolean hasTerm = foodItemName.toLowerCase().contains(term.toLowerCase().trim());

            if (hasTerm) {
                for (String flag : flags) {
                    String[] flagParts = flag.trim().split(" ", 2);
                    ;

                    String flagName = flagParts[0];

                    switch (flagName) {
                    case "fresh":
                        boolean isFresh = expiryDate.isAfter(LocalDate.now());
                        if (!isFresh) {
                            continue foodItemLoop;
                        }
                        break;

                    case "expired":
                        boolean isExpired = expiryDate.isBefore(LocalDate.now());
                        if (!isExpired) {
                            continue foodItemLoop;
                        }
                        break;
                    case "c":
                        if (flagParts.length == 1) {
                            throw new InvalidFlagValueException(flagName);
                        }
                        String categoryValue = flagParts[1].toLowerCase().trim();
                        if (!categoryValue.equals(foodCategory)) {
                            continue foodItemLoop;
                        }
                        break;
                    case "u":
                        if (flagParts.length == 1) {
                            throw new InvalidFlagValueException(flagName);
                        }
                        String unitValue = flagParts[1].toLowerCase().trim();
                        if (!unitValue.equals(foodUnit)) {
                            continue foodItemLoop;
                        }
                        break;
                    case "q":
                        if (flagParts.length == 1) {
                            throw new InvalidFlagValueException(flagName);
                        }
                        String quantityValue = flagParts[1].toLowerCase().trim();

                        double q = 0;
                        try {
                            q = Double.parseDouble(quantityValue.trim());
                        } catch (NumberFormatException e) {
                            String wrongIndexMessage = "Invalid quantity format!";
                            throw new FSTException(wrongIndexMessage);
                        }

                        if (foodItem.getQuantity() != q) {
                            continue foodItemLoop;
                        }
                        break;

                    default:
                        throw new InvalidFlagException(flag);
                    }
                }

                result.addFood(foodItem);
            }
        }

        int foundNumber = result.getNumberOfFood();

        if (result.getNumberOfFood() > 0) {
            System.out.println(result);
            return new CommandResult("Found " + foundNumber + " of food items");
        } else {
            return new CommandResult(NOT_FOUND);
        }
    }
}
