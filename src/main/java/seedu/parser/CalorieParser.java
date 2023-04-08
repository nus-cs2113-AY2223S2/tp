package seedu.parser;

import seedu.commands.Command;
import seedu.commands.caloriecommands.AddCalorieCommand;
import seedu.commands.caloriecommands.ViewCaloriesCommand;
import seedu.exceptions.InvalidArgumentException;
import seedu.exceptions.InvalidSyntaxException;

import java.util.Date;
import java.util.regex.Pattern;

import static seedu.parser.Parser.parseDate;

/**
 * Represents the parser for calorie commands.
 */
public class CalorieParser {
    private static final int DATE_LENGTH = 8;
    public CalorieParser() {
    }

    //@@author calebcjl
    /**
     * Parses arguments for AddCalorieCommand.
     *
     * @param arguments Arguments to parse.
     * @return AddCalorieCommand.
     * @throws InvalidArgumentException If arguments are invalid.
     */
    public static Command parseAddCalorieCommand(String arguments)
            throws InvalidArgumentException, InvalidSyntaxException {
        arguments = arguments.trim();
        Date date;
        String foodName;
        int foodCalories;
        try {
            date = parseDate(arguments.substring(0,DATE_LENGTH));
            if (Pattern.compile("\\D+")
                    .matcher(arguments.substring(arguments.length() - 1)).matches()) {
                return new AddCalorieCommand(date, arguments.substring(DATE_LENGTH).trim());
            }
            foodName = parseFoodName(arguments);
            foodCalories =
                    Integer.parseUnsignedInt(arguments.substring(arguments.lastIndexOf(" ")).trim());
        } catch (NumberFormatException e) {
            throw new InvalidArgumentException("calories");
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidSyntaxException("/cadd command");
        }
        return new AddCalorieCommand(date, foodName, foodCalories);
    }

    //@@author calebcjl
    /**
     * Parses food name for AddCalorieCommand.
     *
     * @param arguments Arguments to parse.
     * @return Food name.
     * @throws InvalidArgumentException If food name is not valid.
     */
    private static String parseFoodName(String arguments) throws InvalidArgumentException {
        String foodName = arguments.substring(DATE_LENGTH, arguments.lastIndexOf(" ")).trim();
        if (!isValidFoodName(foodName)) {
            throw new InvalidArgumentException("food name");
        }
        return foodName;
    }

    //@@author calebcjl
    /**
     * Check if food name is valid.
     * A valid food name must not have numbers in it.
     *
     * @param foodName Name of food.
     * @return True if food name is valid. Returns false otherwise.
     */
    private static boolean isValidFoodName(String foodName){
        if (foodName.isEmpty()) {
            return false;
        }
        if (foodName.contains("\\d+")){
            return false;
        }
        return true;
    }

    public static Command parseViewCalories(String arguments)
            throws InvalidArgumentException, InvalidSyntaxException {
        Date date;
        date = parseDate(arguments.trim());
        return new ViewCaloriesCommand(date);
    }
}
