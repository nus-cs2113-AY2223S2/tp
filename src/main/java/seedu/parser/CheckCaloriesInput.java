package seedu.parser;


import seedu.commands.Command;
import seedu.commands.IncorrectSyntaxCommand;
import seedu.commands.caloriecommands.AddCalorieCommand;
import seedu.commands.caloriecommands.StartDayCaloriesCommand;

import java.util.Date;

import static seedu.commands.caloriecommands.AddCalorieCommand.CALORIES_NOT_GIVEN;

public class CheckCaloriesInput {
    private static final int FOOD_INDEX = 0;
    private static final int CALORIES_INDEX = 1;


    static Command processDayCalories(String arguments) {
        Date date = CheckInputs.parseDate(arguments);
        return date != null && CheckInputs.parseInput(arguments) ? new StartDayCaloriesCommand(date) :
                new IncorrectSyntaxCommand("/cday command");
    }

    public static Command processAddCalories(String arguments) {
        Date date;
        String food;
        int calories;

        try {
            String[] addCaloriesArguments = arguments.trim().split("\\s+");
            //date = DateFormatter.stringToDate(addCaloriesArguments[DATE_INDEX].trim());
            food = addCaloriesArguments[FOOD_INDEX].trim();

            if (addCaloriesArguments.length == 2) {
                calories = Integer.parseInt(addCaloriesArguments[CALORIES_INDEX].trim());
            } else if (addCaloriesArguments.length == 1) {
                calories = CALORIES_NOT_GIVEN;
            } else {
                return new IncorrectSyntaxCommand("/cadd command");
            }

            if (food.matches("\\d+")) {
                System.out.println("Invalid input. The input cannot be a number.");
                return null;
            }

        } catch (NumberFormatException e) {
            return new IncorrectSyntaxCommand("calories");
        } catch (ArrayIndexOutOfBoundsException e) {
            return new IncorrectSyntaxCommand("/cadd command");
        }

        return new AddCalorieCommand(food, calories);
    }

    /* public static Command processViewCalories(String arguments) {
        Date date;

        try {
            date = DateFormatter.stringToDate(arguments.trim());
        } catch (ParseException e) {
            return new IncorrectSyntaxCommand("cday");
        }

        return new ViewCaloriesCommand();
     }*/

}
