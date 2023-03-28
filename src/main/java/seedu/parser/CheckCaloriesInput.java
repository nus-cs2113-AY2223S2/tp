package seedu.parser;


import seedu.commands.Command;
import seedu.commands.IncorrectSyntaxCommand;
import seedu.commands.caloriecommands.AddCalorieCommand;
import seedu.commands.caloriecommands.ViewCaloriesCommand;

import java.text.ParseException;
import java.util.Date;

import static seedu.commands.caloriecommands.AddCalorieCommand.CALORIES_NOT_GIVEN;

public class CheckCaloriesInput {
    private static final int DATE_INDEX = 0;
    private static final int FOOD_INDEX = 1;
    private static final int CALORIES_INDEX = 2;

    public static Command processAddCalories(String arguments) {
        Date date;
        String food;
        int calories;

        try {
            String[] addCaloriesArguments = arguments.trim().split("\\s+", 3);
            date = DateFormatter.stringToDate(addCaloriesArguments[DATE_INDEX].trim());
            food = addCaloriesArguments[FOOD_INDEX].trim();

            if (addCaloriesArguments.length == 3) {
                calories = Integer.parseInt(addCaloriesArguments[CALORIES_INDEX].trim());
            } else {
                calories = CALORIES_NOT_GIVEN;
            }
        } catch (ParseException e) {
            return new IncorrectSyntaxCommand("date");
        } catch (NumberFormatException e) {
            return new IncorrectSyntaxCommand("calories");
        } catch (ArrayIndexOutOfBoundsException e) {
            return new IncorrectSyntaxCommand("/cadd command");
        }

        return new AddCalorieCommand(date, food, calories);
    }

    public static Command processViewCalories(String arguments) {
        Date date;

        try {
            date = DateFormatter.stringToDate(arguments.trim());
        } catch (ParseException e) {
            return new IncorrectSyntaxCommand("date");
        }

        return new ViewCaloriesCommand(date);
    }
}
