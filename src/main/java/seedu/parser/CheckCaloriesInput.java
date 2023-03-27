package seedu.parser;

import seedu.commands.Command;
import seedu.commands.IncorrectCommand;
import seedu.commands.caloriecommands.AddCalorieCommand;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import static seedu.commands.caloriecommands.AddCalorieCommand.CALORIES_NOT_GIVEN;

public class CheckCaloriesInput {
    private static final int DATE_INDEX = 0;
    private static final int FOOD_INDEX = 1;
    private static final int CALORIES_INDEX = 2;

    //public static final DateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");


    public static Command processAddCalories(String arguments) {

        Date date;
        String food;
        int calories;

        try {
            String[] addCaloriesArguments = arguments.trim().split("\\s+", 3);
            date = new SimpleDateFormat("dd/MM/yyyy").parse(addCaloriesArguments[DATE_INDEX].trim());
            food = addCaloriesArguments[FOOD_INDEX].trim();

            if (addCaloriesArguments.length == 3) {
                calories = Integer.parseInt(addCaloriesArguments[CALORIES_INDEX].trim());
            } else {
                calories = CALORIES_NOT_GIVEN;
            }
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please enter the date in the format dd/mm/yy.");
            return new IncorrectCommand();
        } catch (NumberFormatException e) {
            System.out.println("Invalid calories format. Please enter an integer");
            return new IncorrectCommand();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Invalid command format.");
            return new IncorrectCommand();
        }

        return new AddCalorieCommand(date, food, calories);
    }
}
